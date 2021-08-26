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