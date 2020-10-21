package io.numbersprotocol.infosnapshot.model.info

import com.squareup.moshi.JsonClass
import io.numbersprotocol.infosnapshot.model.response.Response

@JsonClass(generateAdapter = true)
data class SettingsInfo(
    val adbEnabled: Response,
    val airplaneModeOn: Response,
    val airplaneModeRadios: Response,
    val autoTime: Response,
    val autoTimeZone: Response,
    val bluetoothOn: Response,
    val dataRoaming: Response,
    val developmentSettingsEnabled: Response,
    val deviceName: Response,
    val deviceProvisioned: Response,
    val httpProxy: Response,
    val modeRinger: Response,
    val wifiOn: Response,
    val androidIdHash: Response
)