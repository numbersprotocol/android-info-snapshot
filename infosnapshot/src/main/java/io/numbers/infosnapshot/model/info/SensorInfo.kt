package io.numbers.infosnapshot.model.info

import com.squareup.moshi.JsonClass
import io.numbers.infosnapshot.utils.NullableWithReason

@JsonClass(generateAdapter = true)
data class SensorInfo(
    val accelerometer: NullableWithReason<SensorData>,
    val accelerometerUncalibrated: NullableWithReason<SensorData>,
    val ambientTemperature: NullableWithReason<SensorData>,
    val gameRotationVector: NullableWithReason<SensorData>,
    val geomagneticRotationVector: NullableWithReason<SensorData>,
    val gravity: NullableWithReason<SensorData>,
    val gyroscope: NullableWithReason<SensorData>,
    val gyroscopeUncalibrated: NullableWithReason<SensorData>,
    val heartBeat: NullableWithReason<SensorData>,
    val heartRate: NullableWithReason<SensorData>,
    val light: NullableWithReason<SensorData>,
    val linearAcceleration: NullableWithReason<SensorData>,
    val lowLatencyOffbodyDetect: NullableWithReason<SensorData>,
    val magneticField: NullableWithReason<SensorData>,
    val magneticFieldUncalibrated: NullableWithReason<SensorData>,
    val motionDetect: NullableWithReason<SensorData>,
    val pose6Dof: NullableWithReason<SensorData>,
    val pressure: NullableWithReason<SensorData>,
    val proximity: NullableWithReason<SensorData>,
    val relativeHumidity: NullableWithReason<SensorData>,
    val rotationVector: NullableWithReason<SensorData>,
    val significantMotion: NullableWithReason<SensorData>,
    val stationaryDetect: NullableWithReason<SensorData>,
    val stepCounter: NullableWithReason<SensorData>,
    val stepDetector: NullableWithReason<SensorData>
)

@JsonClass(generateAdapter = true)
data class SensorData(
    val accuracy: NullableWithReason<Double>,
    val value: List<Float>
)