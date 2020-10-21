package io.numbersprotocol.infosnapshot.model.info

import android.os.Build
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeviceInfo(
    val board: String = Build.BOARD,
    val bootloader: String = Build.BOOTLOADER,
    val brand: String = Build.BRAND,
    val device: String = Build.DEVICE,
    val display: String = Build.DISPLAY,
    val fingerprint: String = Build.FINGERPRINT,
    val hardware: String = Build.HARDWARE,
    val host: String = Build.HOST,
    val id: String = Build.ID,
    val manufacturer: String = Build.MANUFACTURER,
    val model: String = Build.MODEL,
    val product: String = Build.PRODUCT,
    val supportedAbis: List<String> = Build.SUPPORTED_ABIS.toList(),
    val tags: String = Build.TAGS,
    val buildTime: Long = Build.TIME,
    val type: String = Build.TYPE,
    val user: String = Build.USER
)