package io.numbersprotocol.infosnapshot.utils

import java.security.MessageDigest

object HashUtils {

    fun sha256(byteArray: ByteArray): String {
        val messageDigest = MessageDigest.getInstance("SHA-256")
        val digest = messageDigest.digest(byteArray)
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }

    fun sha256(string: String): String = sha256(string.toByteArray())
}