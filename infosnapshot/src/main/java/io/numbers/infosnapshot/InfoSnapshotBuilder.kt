package io.numbers.infosnapshot

import io.numbers.infosnapshot.info.Device
import io.numbers.infosnapshot.model.Snapshot

class InfoSnapshotBuilder {

    fun snap() = Snapshot(
        Device()
    )
}