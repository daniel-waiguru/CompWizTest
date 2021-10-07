package io.compwiz.countrylister.domain.repository

import io.compwiz.countrylister.domain.model.CountryDomain
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    suspend fun getAllCountries(): Flow<List<CountryDomain>>
}