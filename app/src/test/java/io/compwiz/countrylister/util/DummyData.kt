package io.compwiz.countrylister.util

import io.compwiz.countrylister.domain.model.CountryDomain

val dummyData = listOf(
    CountryDomain(
        name = "Afghanistan",
        region = "Asia",
        subRegion = "Southern Asia",
        capitalCity = "Kabul",
        alphaCode = "AF",
        population = 27657145,
        imageUrl = "https://restcountries.eu/data/afg.svg"
    ),
    CountryDomain(
        name = "American Samoa",
        region = "Oceania",
        subRegion = "Polynesia",
        capitalCity = "Pago Pago",
        alphaCode = "AS",
        population = 57100,
        imageUrl = "https://restcountries.eu/data/asm.svg"
    ),
)