package io.numbers.infosnapshot.model

import io.numbers.infosnapshot.model.info.DeviceInfo
import io.numbers.infosnapshot.model.info.LocationInfo
import io.numbers.infosnapshot.model.info.SensorInfo

data class Snapshot(
    val deviceInfo: DeviceInfo,
    val locationInfo: LocationInfo,
    val sensorInfo: SensorInfo
)