package io.numbers.infosnapshot.model.info

import android.location.Location
import android.os.Build

data class LocationInfo(
    val lastKnown: LocationData?,
    val current: LocationData?
)

data class LocationData(
    val accuracy: Float,
    val altitude: Double,
    val bearing: Float,
    val bearingAccuracyDegrees: Float?,
    val latitude: Double,
    val longitude: Double,
    val provider: String,
    val speed: Float,
    val speedAccuracyMetersPerSecond: Float?,
    val time: Long,
    val verticalAccuracyMeters: Float?,
    val isFromMockProvider: Boolean
) {

    companion object {
        fun fromLocation(location: Location): LocationData {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocationData(
                    location.accuracy,
                    location.altitude,
                    location.bearing,
                    location.bearingAccuracyDegrees,
                    location.latitude,
                    location.longitude,
                    location.provider,
                    location.speed,
                    location.speedAccuracyMetersPerSecond,
                    location.time,
                    location.verticalAccuracyMeters,
                    location.isFromMockProvider
                )
            } else {
                LocationData(
                    location.accuracy,
                    location.altitude,
                    location.bearing,
                    null,
                    location.latitude,
                    location.longitude,
                    location.provider,
                    location.speed,
                    null,
                    location.time,
                    null,
                    location.isFromMockProvider
                )
            }
        }
    }
}