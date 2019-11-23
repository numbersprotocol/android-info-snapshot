package io.numbers.infosnapshot.model

import com.squareup.moshi.JsonClass
import io.numbers.infosnapshot.model.info.DeviceInfo
import io.numbers.infosnapshot.model.info.LocationInfo
import io.numbers.infosnapshot.model.info.SensorInfo
import io.numbers.infosnapshot.utils.Singleton

@JsonClass(generateAdapter = true)
data class Snapshot(
    val deviceInfo: DeviceInfo,
    val locationInfo: LocationInfo,
    val sensorInfo: SensorInfo
) {
    fun toJson(): String = SnapshotJsonAdapter(Singleton.getMoshi()).toJson(this)
}