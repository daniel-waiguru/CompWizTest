/*
 * MIT License
 *
 * Copyright (c) 2021 Daniel Waiguru
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package io.compwiz.countrylister.data.repository

import io.compwiz.countrylister.data.local.CountryDao
import io.compwiz.countrylister.data.local.entity.CountryEntity
import io.compwiz.countrylister.data.mapper.CountryMapper
import io.compwiz.countrylister.data.mapper.toEntity
import io.compwiz.countrylister.data.remote.ApiService
import io.compwiz.countrylister.domain.model.CountryDomain
import io.compwiz.countrylister.domain.repository.CountryRepository
import io.compwiz.countrylister.utils.ResultWrapper
import io.compwiz.countrylister.utils.networkBoundResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CountryRepositoryImpl(
    private val apiService: ApiService,
    private val countryMapper: CountryMapper,
    private val countryDao: CountryDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): CountryRepository {
    //private val countryDao: CountryDao = db.countryDao()
    override suspend fun getAllCountries(): Flow<List<CountryDomain>> {
        return flow {
            val countries = apiService.getAllCountries().map { countryDto ->
                countryMapper.toDomain(countryDto)
            }
            emit(countries)
        }.flowOn(ioDispatcher)
    }

    override suspend fun getCountries(): Flow<ResultWrapper<List<CountryEntity>>> =
        networkBoundResource(
            ioDispatcher,
            query = {
                countryDao.getCountries()
            },
            fetch = {
                apiService.getAllCountries()
            },
            saveFetchResult = { countriesDto ->
                countryDao.updateData(countriesDto.map { it.toEntity() })
                /*db.withTransaction {
                    countryDao.clearData()
                    countryDao.storeCountries(countriesDto.map { it.toEntity() })
                }*/
            }
        )
}