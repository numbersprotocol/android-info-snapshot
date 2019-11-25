package io.numbers.infosnapshot

import android.content.Context
import io.numbers.infosnapshot.factories.LocationInfoFactory
import io.numbers.infosnapshot.factories.SensorInfoFactory
import io.numbers.infosnapshot.model.Snapshot
import io.numbers.infosnapshot.model.info.DeviceInfo
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class InfoSnapshotBuilder(private val context: Context) {

    var duration: Long = 100

    suspend fun snap() = coroutineScope {
        val locationInfo = async { LocationInfoFactory.newLocationInfo(context, duration) }
        val sensorInfo = async { SensorInfoFactory.newSensorInfo(context, duration) }
        return@coroutineScope Snapshot(
            DeviceInfo(),
            locationInfo.await(),
            sensorInfo.await()
        )
    }
}