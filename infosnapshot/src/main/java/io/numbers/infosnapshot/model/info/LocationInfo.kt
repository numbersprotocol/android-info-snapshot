package io.numbers.infosnapshot.model.info

import android.location.Location
import android.os.Build
import com.squareup.moshi.JsonClass
import io.numbers.infosnapshot.utils.NullReason
import io.numbers.infosnapshot.utils.NullableWithReason

@JsonClass(generateAdapter = true)
data class LocationInfo(
    val lastKnown: NullableWithReason<LocationData>,
    val current: NullableWithReason<LocationData>
)

@JsonClass(generateAdapter = true)
data class LocationData(
    val accuracy: Float,
    val altitude: Double,
    val bearing: Float,
    val bearingAccuracyDegrees: NullableWithReason<Float>,
    val latitude: Double,
    val longitude: Double,
    val provider: String,
    val speed: Float,
    val speedAccuracyMetersPerSecond: NullableWithReason<Float>,
    val time: Long,
    val verticalAccuracyMeters: NullableWithReason<Float>,
    val isFromMockProvider: Boolean
) {

    companion object {
        fun fromLocation(location: Location): LocationData {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocationData(
                    location.accuracy,
                    location.altitude,
                    location.bearing,
                    NullableWithReason(location.bearingAccuracyDegrees),
                    location.latitude,
                    location.longitude,
                    location.provider,
                    location.speed,
                    NullableWithReason(location.speedAccuracyMetersPerSecond),
                    location.time,
                    NullableWithReason(location.verticalAccuracyMeters),
                    location.isFromMockProvider
                )
            } else {
                LocationData(
                    location.accuracy,
                    location.altitude,
                    location.bearing,
                    NullableWithReason(NullReason.ANDROID_SDK_TOO_OLD),
                    location.latitude,
                    location.longitude,
                    location.provider,
                    location.speed,
                    NullableWithReason(NullReason.ANDROID_SDK_TOO_OLD),
                    location.time,
                    NullableWithReason(NullReason.ANDROID_SDK_TOO_OLD),
                    location.isFromMockProvider
                )
            }
        }
    }
}