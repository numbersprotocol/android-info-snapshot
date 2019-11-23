package io.numbers.infosnapshot.utils

import com.squareup.moshi.Moshi

object Singleton {
    private var moshi: Moshi? = null

    fun getMoshi(): Moshi = moshi ?: Moshi.Builder()
        .build().also { moshi = it }
}