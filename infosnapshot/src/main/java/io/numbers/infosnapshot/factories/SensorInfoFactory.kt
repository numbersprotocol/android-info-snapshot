package io.numbers.infosnapshot.factories

import android.content.Context
import android.hardware.*
import android.os.Build
import android.util.Log
import io.numbers.infosnapshot.model.info.SensorData
import io.numbers.infosnapshot.model.info.SensorInfo
import io.numbers.infosnapshot.model.response.NullReason
import io.numbers.infosnapshot.model.response.Response
import io.numbers.infosnapshot.utils.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

object SensorInfoFactory {

    private lateinit var sensorManager: SensorManager

    suspend fun newSensorInfo(context: Context, duration: Long) = coroutineScope {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val accelerometerData = async(Dispatchers.Default) {
            propagateAverageDataOrNullReason(recordSensor(Sensor.TYPE_ACCELEROMETER, duration))
        }
        val accelerometerUncalibratedData = async(Dispatchers.Default) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                propagateAverageDataOrNullReason(
                    recordSensor(Sensor.TYPE_ACCELEROMETER_UNCALIBRATED, duration)
                )
            } else Response.Null(NullReason.ANDROID_SDK_TOO_OLD)
        }
        val ambientTemperatureData = async(Dispatchers.Default) {
            propagateAverageDataOrNullReason(
                recordSensor(
                    Sensor.TYPE_AMBIENT_TEMPERATURE,
                    duration
                )
            )
        }
        val gameRotationVectorData = async(Dispatchers.Default) {
            propagateAverageDataOrNullReason(
                recordSensor(
                    Sensor.TYPE_GAME_ROTATION_VECTOR,
                    duration
                )
            )
        }
        val geomagneticRotationVectorData = async(Dispatchers.Default) {
            propagateAverageDataOrNullReason(
                recordSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR, duration)
            )
        }
        val gravityData = async(Dispatchers.Default) {
            propagateAverageDataOrNullReason(recordSensor(Sensor.TYPE_GRAVITY, duration))
        }
        val gyroscopeData = async(Dispatchers.Default) {
            propagateAverageDataOrNullReason(recordSensor(Sensor.TYPE_GYROSCOPE, duration))
        }
        val gyroscopeUncalibratedData = async(Dispatchers.Default) {
            propagateAverageDataOrNullReason(
                recordSensor(
                    Sensor.TYPE_GYROSCOPE_UNCALIBRATED,
                    duration
                )
            )
        }
        val heartBeatData = async(Dispatchers.Default) {
            propagateAverageDataOrNullReason(recordSensor(Sensor.TYPE_HEART_BEAT, duration))
        }
        val heartRateData = async(Dispatchers.Default) {
            propagateAverageDataOrNullReason(recordSensor(Sensor.TYPE_HEART_RATE, duration))
        }
        val lightData = async(Dispatchers.Default) {
            propagateAverageDataOrNullReason(recordSensor(Sensor.TYPE_LIGHT, duration))
        }
        val linearAccelerationData = async(Dispatchers.Default) {
            propagateAverageDataOrNullReason(
                recordSensor(
                    Sensor.TYPE_LINEAR_ACCELERATION,
                    duration
                )
            )
        }
        val lowLatencyOffbodyDetectData = async(Dispatchers.Default) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                propagateAverageDataOrNullReason(
                    recordSensor(Sensor.TYPE_LOW_LATENCY_OFFBODY_DETECT, duration)
                )
            } else Response.Null(NullReason.ANDROID_SDK_TOO_OLD)
        }
        val magneticFieldData = async(Dispatchers.Default) {
            propagateAverageDataOrNullReason(recordSensor(Sensor.TYPE_MAGNETIC_FIELD, duration))
        }
        val magneticFieldUncalibratedData = async(Dispatchers.Default) {
            propagateAverageDataOrNullReason(
                recordSensor(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED, duration)
            )
        }
        val motionDetectData = async(Dispatchers.Default) {
            propagateAverageDataOrNullReason(recordSensor(Sensor.TYPE_MOTION_DETECT, duration))
        }
        val pose6DofData = async(Dispatchers.Default) {
            propagateAverageDataOrNullReason(recordSensor(Sensor.TYPE_POSE_6DOF, duration))
        }
        val pressureData = async(Dispatchers.Default) {
            propagateAverageDataOrNullReason(recordSensor(Sensor.TYPE_PRESSURE, duration))
        }
        val proximityData = async(Dispatchers.Default) {
            propagateAverageDataOrNullReason(recordSensor(Sensor.TYPE_PROXIMITY, duration))
        }
        val relativeHumidityData = async(Dispatchers.Default) {
            propagateAverageDataOrNullReason(recordSensor(Sensor.TYPE_RELATIVE_HUMIDITY, duration))
        }
        val rotationVectorData = async(Dispatchers.Default) {
            propagateAverageDataOrNullReason(recordSensor(Sensor.TYPE_ROTATION_VECTOR, duration))
        }
        val significantMotionData = async(Dispatchers.Default) {
            propagateAverageDataOrNullReason(recordSensor(Sensor.TYPE_SIGNIFICANT_MOTION, duration))
        }
        val stationaryDetectData = async(Dispatchers.Default) {
            propagateAverageDataOrNullReason(recordSensor(Sensor.TYPE_STATIONARY_DETECT, duration))
        }
        val stepCounter = async(Dispatchers.Default) {
            propagateAverageDataOrNullReason(recordSensor(Sensor.TYPE_STEP_COUNTER, duration))
        }
        val stepDetector = async(Dispatchers.Default) {
            propagateAverageDataOrNullReason(recordSensor(Sensor.TYPE_STEP_DETECTOR, duration))
        }

        return@coroutineScope SensorInfo(
            accelerometerData.await(),
            accelerometerUncalibratedData.await(),
            ambientTemperatureData.await(),
            gameRotationVectorData.await(),
            geomagneticRotationVectorData.await(),
            gravityData.await(),
            gyroscopeData.await(),
            gyroscopeUncalibratedData.await(),
            heartBeatData.await(),
            heartRateData.await(),
            lightData.await(),
            linearAccelerationData.await(),
            lowLatencyOffbodyDetectData.await(),
            magneticFieldData.await(),
            magneticFieldUncalibratedData.await(),
            motionDetectData.await(),
            pose6DofData.await(),
            pressureData.await(),
            proximityData.await(),
            relativeHumidityData.await(),
            rotationVectorData.await(),
            significantMotionData.await(),
            stationaryDetectData.await(),
            stepCounter.await(),
            stepDetector.await()
        )
    }

    @Suppress("UNCHECKED_CAST")
    private fun propagateAverageDataOrNullReason(history: Response) =
        when (history) {
            is Response.Null -> history
            is Response.Result<*> -> (history as Response.Result<SensorHistory>).value.average()
        }

    private suspend fun recordSensor(sensorType: Int, duration: Long): Response {
        val accuracies = mutableListOf<Int>()
        val values = mutableListOf<FloatArray>()
        return sensorManager.getDefaultSensor(sensorType)?.let {
            Log.d(TAG, "${it.name} reporting mode is: ${it.reportingMode}")
            if (it.reportingMode == Sensor.REPORTING_MODE_ONE_SHOT) {
                val listener = createTriggerEventListener(values)
                try {
                    sensorManager.requestTriggerSensor(listener, it)
                    delay(duration)
                } finally {
                    sensorManager.cancelTriggerSensor(listener, it)
                }
            } else {
                val listener = createSensorEventListener(accuracies, values)
                try {
                    sensorManager.registerListener(listener, it, SensorManager.SENSOR_DELAY_FASTEST)
                    delay(duration)
                } finally {
                    sensorManager.unregisterListener(listener)
                }
            }
            Response.Result(SensorHistory(accuracies, values))
        } ?: Response.Null(NullReason.UNSUPPORTED)
    }

    private fun createSensorEventListener(
        accuracyHolder: MutableList<Int>,
        valueHolder: MutableList<FloatArray>
    ) = object : SensorEventListener {

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            accuracyHolder.add(accuracy)
        }

        override fun onSensorChanged(event: SensorEvent) {
            valueHolder.add(event.values)
        }
    }

    private fun createTriggerEventListener(
        valueHolder: MutableList<FloatArray>
    ) = object : TriggerEventListener() {

        override fun onTrigger(event: TriggerEvent) {
            valueHolder.add(event.values)
        }
    }

    data class SensorHistory(
        val accuracies: MutableList<Int>,
        val values: MutableList<FloatArray>
    ) {

        fun average(): Response {
            return values.map { it.size }.maxOrNull()?.let { maxValueSize ->
                val sumOfValue = FloatArray(maxValueSize)

                values.forEach {
                    for (i in sumOfValue.indices) {
                        sumOfValue[i] += it.getOrElse(i) { 0f }
                    }
                }

                val averageAccuracy: Response =
                    if (accuracies.isEmpty()) Response.Null(NullReason.NO_UPDATE_RECEIVED_DURING_SNAP)
                    else Response.Result(accuracies.average())

                Response.Result(SensorData(averageAccuracy, sumOfValue.map { it / values.size }))
            } ?: Response.Null(NullReason.NO_UPDATE_RECEIVED_DURING_SNAP)
        }
    }
}