package io.compwiz.countrylister.domain.use_case

import io.compwiz.countrylister.domain.repository.CountryRepository

class FetchCountriesUseCase(private val repo: CountryRepository) {
    suspend operator fun invoke() = repo.getAllCountries()
}