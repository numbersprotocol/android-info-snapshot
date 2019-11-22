package io.numbers.infosnapshot.factories

import android.content.Context
import android.hardware.*
import android.os.Build
import android.util.Log
import io.numbers.infosnapshot.model.info.SensorData
import io.numbers.infosnapshot.model.info.SensorInfo
import io.numbers.infosnapshot.utils.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

object SensorInfoFactory {

    lateinit var sensorManager: SensorManager

    suspend fun newSensorInfo(context: Context, duration: Long) = coroutineScope {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val accelerometerData = async(Dispatchers.Default) {
            recordSensor(Sensor.TYPE_ACCELEROMETER, duration)?.simply()
        }
        val accelerometerUncalibratedData = async(Dispatchers.Default) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                recordSensor(Sensor.TYPE_ACCELEROMETER_UNCALIBRATED, duration)?.simply()
            } else null
        }
        val ambientTemperatureData = async(Dispatchers.Default) {
            recordSensor(Sensor.TYPE_AMBIENT_TEMPERATURE, duration)?.simply()
        }
        val gameRotationVectorData = async(Dispatchers.Default) {
            recordSensor(Sensor.TYPE_GAME_ROTATION_VECTOR, duration)?.simply()
        }
        val geomagneticRotationVectorData = async(Dispatchers.Default) {
            recordSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR, duration)?.simply()
        }
        val gravityData = async(Dispatchers.Default) {
            recordSensor(Sensor.TYPE_GRAVITY, duration)?.simply()
        }
        val gyroscopeData = async(Dispatchers.Default) {
            recordSensor(Sensor.TYPE_GYROSCOPE, duration)?.simply()
        }
        val gyroscopeUncalibratedData = async(Dispatchers.Default) {
            recordSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED, duration)?.simply()
        }
        val heartBeatData = async(Dispatchers.Default) {
            recordSensor(Sensor.TYPE_HEART_BEAT, duration)?.simply()
        }
        val heartRateData = async(Dispatchers.Default) {
            recordSensor(Sensor.TYPE_HEART_RATE, duration)?.simply()
        }
        val lightData = async(Dispatchers.Default) {
            recordSensor(Sensor.TYPE_LIGHT, duration)?.simply()
        }
        val linearAccelerationData = async(Dispatchers.Default) {
            recordSensor(Sensor.TYPE_LINEAR_ACCELERATION, duration)?.simply()
        }
        val lowLatencyOffbodyDetectData = async(Dispatchers.Default) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                recordSensor(Sensor.TYPE_LOW_LATENCY_OFFBODY_DETECT, duration)?.simply()
            } else null
        }
        val magneticFieldData = async(Dispatchers.Default) {
            recordSensor(Sensor.TYPE_MAGNETIC_FIELD, duration)?.simply()
        }
        val magneticFieldUncalibratedData = async(Dispatchers.Default) {
            recordSensor(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED, duration)?.simply()
        }
        val motionDetectData = async(Dispatchers.Default) {
            recordSensor(Sensor.TYPE_MOTION_DETECT, duration)?.simply()
        }
        val pose6DofData = async(Dispatchers.Default) {
            recordSensor(Sensor.TYPE_POSE_6DOF, duration)?.simply()
        }
        val pressureData = async(Dispatchers.Default) {
            recordSensor(Sensor.TYPE_PRESSURE, duration)?.simply()
        }
        val proximityData = async(Dispatchers.Default) {
            recordSensor(Sensor.TYPE_PROXIMITY, duration)?.simply()
        }
        val relativeHumidityData = async(Dispatchers.Default) {
            recordSensor(Sensor.TYPE_RELATIVE_HUMIDITY, duration)?.simply()
        }
        val rotationVectorData = async(Dispatchers.Default) {
            recordSensor(Sensor.TYPE_ROTATION_VECTOR, duration)?.simply()
        }
        val significantMotionData = async(Dispatchers.Default) {
            recordSensor(Sensor.TYPE_SIGNIFICANT_MOTION, duration)?.simply()
        }
        val stationaryDetectData = async(Dispatchers.Default) {
            recordSensor(Sensor.TYPE_STATIONARY_DETECT, duration)?.simply()
        }
        val stepCounter = async(Dispatchers.Default) {
            recordSensor(Sensor.TYPE_STEP_COUNTER, duration)?.simply()
        }
        val stepDetector = async(Dispatchers.Default) {
            recordSensor(Sensor.TYPE_STEP_DETECTOR, duration)?.simply()
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

    private suspend fun recordSensor(sensorType: Int, duration: Long): SensorHistory? {
        val accuracies = mutableListOf<Int>()
        val values = mutableListOf<FloatArray>()
        return sensorManager.getDefaultSensor(sensorType)?.let {
            Log.d(TAG, "${it.name} reporting mode is: ${it.reportingMode}")
            if (it.reportingMode == Sensor.REPORTING_MODE_ONE_SHOT) {
                val listener = createTriggerEventListener(values)
                sensorManager.requestTriggerSensor(listener, it)
            } else {
                val listener = createSensorEventListener(accuracies, values)
                sensorManager.registerListener(listener, it, SensorManager.SENSOR_DELAY_FASTEST)
                delay(duration)
                sensorManager.unregisterListener(listener)
            }
            SensorHistory(accuracies, values)
        } ?: let {
            Log.d(TAG, "Device does not have sensor type: $sensorType")
            null
        }
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

        fun simply(): SensorData? {
            return values.map { it.size }.max()?.let { maxValueSize ->
                val sumOfValue = FloatArray(maxValueSize)

                values.forEach {
                    for (i in sumOfValue.indices) {
                        sumOfValue[i] += it.getOrElse(i) { 0f }
                    }
                }

                SensorData(
                    accuracies.average(),
                    sumOfValue.map { it / values.size }
                )
            } ?: let {
                Log.d(TAG, "Cannot simply with empty values.")
                null
            }
        }
    }
}