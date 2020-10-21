package io.numbersprotocol.infosnapshot.model.response

enum class NullReason {
    UNKNOWN,
    UNSUPPORTED,
    NOT_FOUND,
    USER_DISABLED,
    ANDROID_SDK_TOO_OLD,
    NO_UPDATE_RECEIVED_DURING_SNAP,
    NO_NETWORK
}