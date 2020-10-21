package io.numbers.infosnapshot.model.response

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class ResponseAdapter {

    @ToJson
    fun toJson(response: Response): Any {
        return when (response) {
            is Response.Result<*> -> response.value!!
            is Response.Null -> response.reason
        }
    }

    @FromJson
    fun fromJson(@Suppress("UNUSED_PARAMETER") json: Any): Response {
        throw NotImplementedError("Currently, we do NOT support parsing from JSON.")
    }
}