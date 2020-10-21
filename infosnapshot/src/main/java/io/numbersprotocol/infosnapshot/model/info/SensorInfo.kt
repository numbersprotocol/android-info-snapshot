package io.numbersprotocol.infosnapshot.model.info

import com.squareup.moshi.JsonClass
import io.numbersprotocol.infosnapshot.model.response.Response

@JsonClass(generateAdapter = true)
data class SensorInfo(
    val accelerometer: Response,
    val accelerometerUncalibrated: Response,
    val ambientTemperature: Response,
    val gameRotationVector: Response,
    val geomagneticRotationVector: Response,
    val gravity: Response,
    val gyroscope: Response,
    val gyroscopeUncalibrated: Response,
    val heartBeat: Response,
    val heartRate: Response,
    val light: Response,
    val linearAcceleration: Response,
    val lowLatencyOffbodyDetect: Response,
    val magneticField: Response,
    val magneticFieldUncalibrated: Response,
    val motionDetect: Response,
    val pose6Dof: Response,
    val pressure: Response,
    val proximity: Response,
    val relativeHumidity: Response,
    val rotationVector: Response,
    val significantMotion: Response,
    val stationaryDetect: Response,
    val stepCounter: Response,
    val stepDetector: Response
)

@JsonClass(generateAdapter = true)
data class SensorData(
    val accuracy: Response,
    val value: List<Float>
)