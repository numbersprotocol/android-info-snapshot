package io.numbers.infosnapshot.model.info

import com.squareup.moshi.JsonClass
import io.numbers.infosnapshot.utils.NullableWithReason

@JsonClass(generateAdapter = true)
data class SettingsInfo(
    val adbEnabled: NullableWithReason<String>,
    val airplaneModeOn: NullableWithReason<String>,
    val airplaneModeRadios: NullableWithReason<String>,
    val autoTime: NullableWithReason<String>,
    val autoTimeZone: NullableWithReason<String>,
    val bluetoothOn: NullableWithReason<String>,
    val dataRoaming: NullableWithReason<String>,
    val developmentSettingsEnabled: NullableWithReason<String>,
    val deviceName: NullableWithReason<String>,
    val deviceProvisioned: NullableWithReason<String>,
    val httpProxy: NullableWithReason<String>,
    val modeRinger: NullableWithReason<String>,
    val wifiOn: NullableWithReason<String>,
    val androidIdHash: NullableWithReason<String>
)