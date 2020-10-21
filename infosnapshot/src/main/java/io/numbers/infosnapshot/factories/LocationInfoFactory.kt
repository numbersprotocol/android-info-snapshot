package io.numbers.infosnapshot.factories

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import android.util.Log
import com.google.android.gms.location.*
import io.numbers.infosnapshot.model.info.LocationData
import io.numbers.infosnapshot.model.info.LocationInfo
import io.numbers.infosnapshot.model.response.NullReason
import io.numbers.infosnapshot.model.response.Response
import io.numbers.infosnapshot.utils.TAG
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

object LocationInfoFactory {

    @SuppressLint("MissingPermission")
    suspend fun newLocationInfo(context: Context, duration: Long) = coroutineScope {
        val client = LocationServices.getFusedLocationProviderClient(context)
        val locationRequest = createLocationRequest(duration)

        var lastKnownLocationData: Response =
            Response.Null(NullReason.NO_UPDATE_RECEIVED_DURING_SNAP)

        client.lastLocation.addOnSuccessListener { location: Location? ->
            Log.d(TAG, "Received last location: $location")
            if (location != null) {
                lastKnownLocationData = Response.Result(
                    LocationData.fromLocation(context, location)
                )
            }
        }

        val currentLocations = mutableListOf<LocationData>()
        val locationCallback = createLocationCallback(context, currentLocations)
        try {
            client.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
            delay(duration)
        } finally {
            client.removeLocationUpdates(locationCallback)
        }

        val lastCurrentLocation: Response =
            if (currentLocations.isEmpty()) Response.Null(NullReason.NO_UPDATE_RECEIVED_DURING_SNAP)
            else Response.Result(currentLocations.last())

        return@coroutineScope LocationInfo(lastKnownLocationData, lastCurrentLocation)
    }

    private fun createLocationCallback(
        context: Context,
        locationHolder: MutableList<LocationData>
    ) = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult?) {
            super.onLocationResult(result)
            result?.apply {
                Log.d(TAG, "Received current location: $lastLocation")
                locationHolder.add(LocationData.fromLocation(context, lastLocation))
            } ?: Log.e(TAG, "Null result from location callback.")
        }

        override fun onLocationAvailability(availability: LocationAvailability) {
            super.onLocationAvailability(availability)
            Log.d(TAG, "Location availability: ${availability.isLocationAvailable}")
        }
    }

    private fun createLocationRequest(duration: Long) = LocationRequest.create().apply {
        interval = duration / 2
        fastestInterval = duration / 5
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }
}