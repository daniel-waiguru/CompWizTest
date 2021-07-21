package io.compwiz.countrylister.domain.model

data class CountryDomain(
    val name: String,
    val region: String,
    val subRegion: String,
    val capitalCity: String,
    val alphaCode: String,
    val population: Int,
    val imageUrl: String
)