package io.numbers.infosnapshot.utils

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NullableWithReason<T> constructor(
    val value: T? = null,
    val nullReason: NullReason? = NullReason.UNKNOWN
) {
    constructor(value: T) : this(value, null)
    constructor(nullReason: NullReason) : this(null, nullReason)

    override fun toString(): String {
        if (value == null) return nullReason.toString()
        return value.toString()
    }
}