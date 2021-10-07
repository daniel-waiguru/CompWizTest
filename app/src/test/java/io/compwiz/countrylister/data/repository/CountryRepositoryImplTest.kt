package io.compwiz.countrylister.data.repository

import androidx.test.runner.AndroidJUnit4
import com.google.common.truth.Truth
import io.compwiz.countrylister.BaseTest
import io.compwiz.countrylister.data.mapper.CountryMapper
import io.compwiz.countrylister.domain.repository.CountryRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(
    sdk = [Config.OLDEST_SDK],
    manifest = Config.NONE
)
class CountryRepositoryImplTest: BaseTest() {
    private lateinit var countryRepository: CountryRepository
    private val countryMapper: CountryMapper by inject()

    override fun setup() {
        super.setup()
        countryRepository = CountryRepositoryImpl(apiService, countryMapper)
    }

    @Test
    fun `fetching posts from Api actually returns data`() {
        val expectedName = "Afghanistan"
        runBlocking {
            val countriesFlow = countryRepository.getAllCountries()
            countriesFlow.collect { countries ->
                Truth.assertThat(countries[0].name).matches(expectedName)
            }
        }
    }
}