package io.numbers.infosnapshot.model.info

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.util.Log
import com.squareup.moshi.JsonClass
import io.numbers.infosnapshot.model.response.NullReason
import io.numbers.infosnapshot.model.response.Response
import io.numbers.infosnapshot.utils.TAG
import java.io.IOException
import java.util.*

@JsonClass(generateAdapter = true)
data class LocationInfo(
    val lastKnown: Response,
    val current: Response
)

@JsonClass(generateAdapter = true)
data class LocationData(
    val accuracy: Float,
    val altitude: Double,
    val bearing: Float,
    val bearingAccuracyDegrees: Response,
    val latitude: Double,
    val longitude: Double,
    val provider: String,
    val speed: Float,
    val speedAccuracyMetersPerSecond: Response,
    val time: Long,
    val verticalAccuracyMeters: Response,
    val isFromMockProvider: Boolean,
    val address: Response
) {

    companion object {
        fun fromLocation(context: Context, location: Location): LocationData {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocationData(
                    location.accuracy,
                    location.altitude,
                    location.bearing,
                    Response.Result(location.bearingAccuracyDegrees),
                    location.latitude,
                    location.longitude,
                    location.provider,
                    location.speed,
                    Response.Result(location.speedAccuracyMetersPerSecond),
                    location.time,
                    Response.Result(location.verticalAccuracyMeters),
                    location.isFromMockProvider,
                    fetchAddress(context, location)
                )
            } else {
                LocationData(
                    location.accuracy,
                    location.altitude,
                    location.bearing,
                    Response.Null(NullReason.ANDROID_SDK_TOO_OLD),
                    location.latitude,
                    location.longitude,
                    location.provider,
                    location.speed,
                    Response.Null(NullReason.ANDROID_SDK_TOO_OLD),
                    location.time,
                    Response.Null(NullReason.ANDROID_SDK_TOO_OLD),
                    location.isFromMockProvider,
                    fetchAddress(context, location)
                )
            }
        }

        private fun fetchAddress(context: Context, location: Location): Response {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses: List<Address>
            try {
                addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
            } catch (e: IOException) {
                Log.e(TAG, "No available network access for geocoder.")
                return Response.Null(NullReason.NO_NETWORK)
            } catch (e: IllegalArgumentException) {
                Log.e(TAG, "Illegal location: ${location.latitude}, ${location.longitude}")
                return Response.Null(NullReason.UNKNOWN)
            }
            return if (addresses.isEmpty()) {
                Response.Null(NullReason.NOT_FOUND)
            } else {
                val address = addresses[0]
                val addressFragments = with(address) {
                    (0..maxAddressLineIndex).map { getAddressLine(it) }
                }
                Response.Result(addressFragments.joinToString(separator = "\n"))
            }
        }
    }
}