package io.numbers.infosnapshot.model

import com.squareup.moshi.JsonClass
import io.numbers.infosnapshot.model.info.*
import io.numbers.infosnapshot.utils.NullableWithReason
import io.numbers.infosnapshot.utils.Singleton

@JsonClass(generateAdapter = true)
data class Snapshot(
    val deviceInfo: NullableWithReason<DeviceInfo>,
    val localeInfo: NullableWithReason<LocaleInfo>,
    val locationInfo: NullableWithReason<LocationInfo>,
    val sensorInfo: NullableWithReason<SensorInfo>,
    val settingsInfo: NullableWithReason<SettingsInfo>
) {
    fun toJson(): String = SnapshotJsonAdapter(Singleton.getMoshi()).toJson(this)
}