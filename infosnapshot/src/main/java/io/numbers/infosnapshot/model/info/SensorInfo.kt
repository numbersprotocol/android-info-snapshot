package io.numbers.infosnapshot.model.info

data class SensorInfo(
    val accelerometer: SensorData?,
    val accelerometerUncalibrated: SensorData?,
    val ambientTemperature: SensorData?,
    val gameRotationVector: SensorData?,
    val geomagneticRotationVector: SensorData?,
    val gravity: SensorData?,
    val gyroscope: SensorData?,
    val gyroscopeUncalibrated: SensorData?,
    val heartBeat: SensorData?,
    val heartRate: SensorData?,
    val light: SensorData?,
    val linearAcceleration: SensorData?,
    val lowLatencyOffbodyDetect: SensorData?,
    val magneticField: SensorData?,
    val magneticFieldUncalibrated: SensorData?,
    val motionDetect: SensorData?,
    val pose6Dof: SensorData?,
    val pressure: SensorData?,
    val proximity: SensorData?,
    val relativeHumidity: SensorData?,
    val rotationVector: SensorData?,
    val significantMotion: SensorData?,
    val stationaryDetect: SensorData?,
    val stepCounter: SensorData?,
    val stepDetector: SensorData?
)

data class SensorData(
    val accuracy: Double,
    val value: List<Float>
)