package io.compwiz.countrylister.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryDomain(
    val name: String,
    val region: String,
    val subRegion: String,
    val capitalCity: String,
    val alphaCode: String,
    val population: Int,
    val imageUrl: String
): Parcelable