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

package io.compwiz.countrylister.data.di

import io.compwiz.countrylister.data.common.ApiConstants.BASE_URL
import io.compwiz.countrylister.data.dispatchers.DispatchersProviderImpl
import io.compwiz.countrylister.data.mapper.CountryMapper
import io.compwiz.countrylister.data.remote.ApiService
import io.compwiz.countrylister.data.repository.CountryRepositoryImpl
import io.compwiz.countrylister.domain.dispatchers.DispatchersProvider
import io.compwiz.countrylister.domain.repository.CountryRepository
import io.compwiz.countrylister.domain.use_case.FetchCountriesUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkingModule = module {
    factory { provideOkHttpClient() }
    single { provideRetrofitInstance(get()) }
    single { provideApiService(get()) }
}
fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
}
fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}
fun provideApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}
val appModule = module {
    single<CountryRepository>{ CountryRepositoryImpl(get(), get()) }
    single { CountryMapper() }
    single { FetchCountriesUseCase(get()) }
    single<DispatchersProvider>{ DispatchersProviderImpl() }
}