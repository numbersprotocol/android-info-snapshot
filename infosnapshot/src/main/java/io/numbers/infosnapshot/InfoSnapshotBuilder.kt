package io.numbers.infosnapshot

import android.content.Context
import io.numbers.infosnapshot.factories.SensorInfoFactory
import io.numbers.infosnapshot.model.Snapshot
import io.numbers.infosnapshot.model.info.DeviceInfo

class InfoSnapshotBuilder(private val context: Context) {

    var duration: Long = 100

    suspend fun snap() = Snapshot(
        DeviceInfo(),
        SensorInfoFactory.newSensorInfo(context, duration)
    )
}