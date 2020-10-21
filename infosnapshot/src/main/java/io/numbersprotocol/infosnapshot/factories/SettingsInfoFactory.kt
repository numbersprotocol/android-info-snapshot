package io.numbersprotocol.infosnapshot.factories

import android.content.Context
import android.os.Build
import android.provider.Settings
import io.numbersprotocol.infosnapshot.model.info.SettingsInfo
import io.numbersprotocol.infosnapshot.model.response.NullReason
import io.numbersprotocol.infosnapshot.model.response.Response
import io.numbersprotocol.infosnapshot.utils.HashUtils

object SettingsInfoFactory {

    fun newSettingsInfo(context: Context): SettingsInfo {

        return SettingsInfo(
            getNullableGlobalSettingString(context, Settings.Global.ADB_ENABLED),
            getNullableGlobalSettingString(context, Settings.Global.AIRPLANE_MODE_ON),
            getNullableGlobalSettingString(context, Settings.Global.AIRPLANE_MODE_RADIOS),
            getNullableGlobalSettingString(context, Settings.Global.AUTO_TIME),
            getNullableGlobalSettingString(context, Settings.Global.AUTO_TIME_ZONE),
            getNullableGlobalSettingString(context, Settings.Global.BLUETOOTH_ON),
            getNullableGlobalSettingString(context, Settings.Global.DATA_ROAMING),
            getNullableGlobalSettingString(context, Settings.Global.DEVELOPMENT_SETTINGS_ENABLED),

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                getNullableGlobalSettingString(context, Settings.Global.DEVICE_NAME)
            } else Response.Null(NullReason.ANDROID_SDK_TOO_OLD),

            getNullableGlobalSettingString(context, Settings.Global.DEVICE_PROVISIONED),
            getNullableGlobalSettingString(context, Settings.Global.HTTP_PROXY),
            getNullableGlobalSettingString(context, Settings.Global.MODE_RINGER),
            getNullableGlobalSettingString(context, Settings.Global.WIFI_ON),
            getNullableSecureSettingString(context, Settings.Secure.ANDROID_ID)
        )
    }

    private fun getNullableGlobalSettingString(context: Context, settingName: String) =
        Settings.Global.getString(context.contentResolver, settingName).let {
            if (it == null) Response.Null(NullReason.NOT_FOUND)
            else Response.Result(it)
        }

    private fun getNullableSecureSettingString(context: Context, settingName: String) =
        Settings.Secure.getString(context.contentResolver, settingName).let {
            if (it == null) Response.Null(NullReason.NOT_FOUND)
            else Response.Result(HashUtils.sha256(it))
        }

}