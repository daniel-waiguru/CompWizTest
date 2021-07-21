package io.compwiz.countrylister.data.repository

import io.compwiz.countrylister.domain.model.CountryDomain
import io.compwiz.countrylister.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow

class CountryRepositoryImpl: CountryRepository {
    override suspend fun getAllCountries(): Flow<List<CountryDomain>> {
        TODO("Not yet implemented")
    }
}