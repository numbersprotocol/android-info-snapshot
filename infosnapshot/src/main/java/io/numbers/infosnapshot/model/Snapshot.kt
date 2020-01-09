package io.numbers.infosnapshot.model

import android.content.Context
import com.squareup.moshi.JsonClass
import io.numbers.infosnapshot.factories.LocationInfoFactory
import io.numbers.infosnapshot.factories.SensorInfoFactory
import io.numbers.infosnapshot.factories.SettingsInfoFactory
import io.numbers.infosnapshot.model.info.DeviceInfo
import io.numbers.infosnapshot.model.info.LocaleInfo
import io.numbers.infosnapshot.model.response.NullReason
import io.numbers.infosnapshot.model.response.Response
import io.numbers.infosnapshot.utils.Singleton
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

@JsonClass(generateAdapter = true)
data class Snapshot(
    val deviceInfo: Response,
    val localeInfo: Response,
    val locationInfo: Response,
    val sensorInfo: Response,
    val settingsInfo: Response
) {
    fun toJson(): String = Singleton.getSnapshotAdapter().toJson(this)

    @Suppress("MemberVisibilityCanBePrivate")
    class Builder(private val context: Context) {

        var duration: Long = 5000
        var enableDeviceInfo = true
        var enableLocaleInfo = true
        var enableLocationInfo = true
        var enableSensorInfo = true
        var enableSettingsInfo = true

        suspend fun snap() = coroutineScope {
            val deviceInfo = if (enableDeviceInfo) Response.Result(DeviceInfo())
            else Response.Null(NullReason.USER_DISABLED)

            val localeInfo = if (enableLocaleInfo) Response.Result(LocaleInfo())
            else Response.Null(NullReason.USER_DISABLED)


            val locationInfoDeferred = async {
                if (enableLocationInfo) {
                    Response.Result(LocationInfoFactory.newLocationInfo(context, duration))
                } else Response.Null(NullReason.USER_DISABLED)
            }
            val sensorInfoDeferred = async {
                if (enableSensorInfo) {
                    Response.Result(SensorInfoFactory.newSensorInfo(context, duration))
                } else Response.Null(NullReason.USER_DISABLED)
            }

            val settingsInfo =
                if (enableSettingsInfo) Response.Result(SettingsInfoFactory.newSettingsInfo(context))
                else Response.Null(NullReason.USER_DISABLED)

            return@coroutineScope Snapshot(
                deviceInfo,
                localeInfo,
                locationInfoDeferred.await(),
                sensorInfoDeferred.await(),
                settingsInfo
            )
        }
    }
}