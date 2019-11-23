package io.numbers.infosnapshot.utils

enum class NullReason {
    UNKNOWN,
    UNSUPPORTED,
    ANDROID_SDK_TOO_OLD,
    SNAP_DURATION_TOO_SHORT,
    NO_CHANGE_DURING_SNAP
}