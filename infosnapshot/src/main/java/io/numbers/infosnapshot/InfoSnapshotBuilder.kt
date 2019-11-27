package io.numbers.infosnapshot

import android.content.Context
import io.numbers.infosnapshot.factories.LocationInfoFactory
import io.numbers.infosnapshot.factories.SensorInfoFactory
import io.numbers.infosnapshot.factories.SettingsInfoFactory
import io.numbers.infosnapshot.model.Snapshot
import io.numbers.infosnapshot.model.info.DeviceInfo
import io.numbers.infosnapshot.model.info.LocaleInfo
import io.numbers.infosnapshot.utils.NullReason
import io.numbers.infosnapshot.utils.NullableWithReason
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class InfoSnapshotBuilder(private val context: Context) {

    var duration: Long = 3000
    var enableDeviceInfo = true
    var enableLocaleInfo = true
    var enableLocationInfo = true
    var enableSensorInfo = true
    var enableSettingsInfo = true

    suspend fun snap() = coroutineScope {
        val deviceInfo = if (enableDeviceInfo) NullableWithReason(DeviceInfo())
        else NullableWithReason(NullReason.USER_DISABLED)

        val localeInfo = if (enableLocaleInfo) NullableWithReason(LocaleInfo())
        else NullableWithReason(NullReason.USER_DISABLED)


        val locationInfoDeferred = async {
            if (enableLocationInfo) {
                NullableWithReason(LocationInfoFactory.newLocationInfo(context, duration))
            } else NullableWithReason(NullReason.USER_DISABLED)
        }
        val sensorInfoDeferred = async {
            if (enableSensorInfo) {
                NullableWithReason(SensorInfoFactory.newSensorInfo(context, duration))
            } else NullableWithReason(NullReason.USER_DISABLED)
        }

        val settingsInfo =
            if (enableSettingsInfo) NullableWithReason(SettingsInfoFactory.newSettingsInfo(context))
            else NullableWithReason(NullReason.USER_DISABLED)

        return@coroutineScope Snapshot(
            deviceInfo,
            localeInfo,
            locationInfoDeferred.await(),
            sensorInfoDeferred.await(),
            settingsInfo
        )
    }
}