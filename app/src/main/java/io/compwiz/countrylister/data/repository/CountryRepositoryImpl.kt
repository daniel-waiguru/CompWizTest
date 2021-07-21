package io.compwiz.countrylister.data.repository

import io.compwiz.countrylister.data.mapper.CountryMapper
import io.compwiz.countrylister.data.remote.ApiService
import io.compwiz.countrylister.domain.model.CountryDomain
import io.compwiz.countrylister.domain.repository.CountryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CountryRepositoryImpl(private val apiService: ApiService, private val countryMapper: CountryMapper): CountryRepository {
    override suspend fun getAllCountries(): Flow<List<CountryDomain>> {
        return flow {
            val countries = apiService.getAllCountries().map { countryDto ->
                countryMapper.toDomain(countryDto)
            }
            emit(countries)
        }.flowOn(Dispatchers.IO)
    }
}