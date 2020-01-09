package io.numbers.infosnapshot.utils

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import io.numbers.infosnapshot.model.Snapshot
import io.numbers.infosnapshot.model.response.ResponseAdapter

object Singleton {
    private var moshi: Moshi? = null

    private fun getMoshi(): Moshi = moshi ?: Moshi.Builder()
        .add(ResponseAdapter())
        .build().also { moshi = it }

    fun getSnapshotAdapter(): JsonAdapter<Snapshot> = getMoshi().adapter(Snapshot::class.java)
}