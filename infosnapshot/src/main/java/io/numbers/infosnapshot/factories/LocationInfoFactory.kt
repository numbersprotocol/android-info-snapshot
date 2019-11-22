package io.numbers.infosnapshot.factories

import android.content.Context
import android.util.Log
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import io.numbers.infosnapshot.utils.TAG
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

object LocationInfoFactory {

    suspend fun newLocationInfo(context: Context, duration: Long) = coroutineScope {
        val locationRequest = createLocationRequest(duration)
        val client = LocationServices.getFusedLocationProviderClient(context)
        val locationCallback = createLocationCallback()

        client.requestLocationUpdates(locationRequest, locationCallback, null)
        delay(duration)
        client.removeLocationUpdates(locationCallback)
        return@coroutineScope
    }

    private fun createLocationCallback() = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult?) {
            super.onLocationResult(result)
            result ?: let {
                Log.e(TAG, "Null for location result.")
                return
            }
            result.locations.forEach {
                Log.i(TAG, "$it")
            }
        }

        override fun onLocationAvailability(availability: LocationAvailability) {
            super.onLocationAvailability(availability)
            Log.i(TAG, "availability: ${availability.isLocationAvailable}")
        }
    }

    private fun checkLocationSettings(
        context: Context,
        request: LocationRequest
    ): Task<LocationSettingsResponse> {
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(request)
        val client = LocationServices.getSettingsClient(context)
        return client.checkLocationSettings(builder.build())
    }

    private fun createLocationRequest(duration: Long) = LocationRequest.create().apply {
        interval = duration / 2
        fastestInterval = duration / 5
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }
}