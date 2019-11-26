package io.numbers.infosnapshot.model

import com.squareup.moshi.JsonClass
import io.numbers.infosnapshot.model.info.*
import io.numbers.infosnapshot.utils.Singleton

@JsonClass(generateAdapter = true)
data class Snapshot(
    val deviceInfo: DeviceInfo,
    val localeInfo: LocaleInfo,
    val locationInfo: LocationInfo,
    val sensorInfo: SensorInfo,
    val settingsInfo: SettingsInfo
) {
    fun toJson(): String = SnapshotJsonAdapter(Singleton.getMoshi()).toJson(this)
}