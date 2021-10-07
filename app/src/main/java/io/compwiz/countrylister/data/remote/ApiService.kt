package io.compwiz.countrylister.data.remote

import io.compwiz.countrylister.data.common.ApiConstants.ALL_COUNTRIES_ENDPOINT
import io.compwiz.countrylister.data.models.Country
import retrofit2.http.GET

interface ApiService {
    @GET(ALL_COUNTRIES_ENDPOINT)
    suspend fun getAllCountries(): List<Country>
}