package io.compwiz.countrylister.data.models

import com.squareup.moshi.Json

data class Translations(
    val br: String,
    val de: String,
    val es: String,
    val fa: String,
    val fr: String,
    val hr: String,
    @field:Json(name = "it") val italy: String,
    val ja: String,
    val nl: String,
    val pt: String
)