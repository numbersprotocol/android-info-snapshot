package io.numbersprotocol.infosnapshot.model.info

import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class LocaleInfo(
    val country: String = Locale.getDefault().displayCountry,
    val variant: String = Locale.getDefault().displayVariant,
    val language: String = Locale.getDefault().displayLanguage,
    val script: String = Locale.getDefault().displayScript,
    val name: String = Locale.getDefault().displayName
)