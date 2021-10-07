package io.compwiz.countrylister.data.mapper

import io.compwiz.countrylister.data.models.Country
import io.compwiz.countrylister.domain.model.CountryDomain

class CountryMapper: DtoToDomainMapper<Country, CountryDomain> {
    override fun toDomain(dto: Country): CountryDomain {
        return CountryDomain(
            name = dto.name,
            region = dto.region,
            subRegion = dto.subregion,
            capitalCity = dto.capital,
            alphaCode = dto.alpha2Code,
            population = dto.population,
            imageUrl = dto.flag
        )
    }
}