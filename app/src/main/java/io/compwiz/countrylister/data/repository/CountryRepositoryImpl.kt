package io.compwiz.countrylister.data.repository

import io.compwiz.countrylister.data.remote.ApiService
import io.compwiz.countrylister.domain.model.CountryDomain
import io.compwiz.countrylister.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow

class CountryRepositoryImpl(private val apiService: ApiService): CountryRepository {
    override suspend fun getAllCountries(): Flow<List<CountryDomain>> {
        TODO("Not yet implemented")
    }
}