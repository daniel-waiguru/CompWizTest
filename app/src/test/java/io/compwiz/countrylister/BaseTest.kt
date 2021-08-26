package io.compwiz.countrylister

import io.compwiz.countrylister.base.BaseKoinTest
import io.compwiz.countrylister.data.remote.ApiService
import io.compwiz.countrylister.helpers.CountriesRequestDispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import okio.IOException
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseTest: BaseKoinTest() {
    //mock web server
    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var loggingInterceptor: HttpLoggingInterceptor
    lateinit var apiService: ApiService

    @Before
    open fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = CountriesRequestDispatcher()
        mockWebServer.start()
        loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        okHttpClient = buildOkHttpClient(loggingInterceptor)

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/all/"))
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    @After
    @Throws(IOException::class)
    open fun tearDown() {
        mockWebServer.shutdown()
    }


    private fun buildOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return  OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }
}