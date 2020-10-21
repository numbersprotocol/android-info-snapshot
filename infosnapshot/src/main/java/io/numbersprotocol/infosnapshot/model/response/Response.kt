package io.numbersprotocol.infosnapshot.model.response

sealed class Response {
    data class Result<T>(val value: T) : Response() {
        override fun toString(): String {
            return value.toString()
        }
    }

    data class Null(val reason: NullReason = NullReason.UNKNOWN) : Response() {
        override fun toString(): String {
            return reason.toString()
        }
    }
}