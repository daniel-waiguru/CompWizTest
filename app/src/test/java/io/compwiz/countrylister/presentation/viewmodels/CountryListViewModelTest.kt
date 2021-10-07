package io.compwiz.countrylister.presentation.viewmodels

import com.jraska.livedata.test
import io.compwiz.countrylister.base.BaseViewModelTest
import io.compwiz.countrylister.data.models.StateWrapper
import io.compwiz.countrylister.domain.repository.CountryRepository
import io.compwiz.countrylister.domain.use_case.FetchCountriesUseCase
import io.compwiz.countrylister.util.dummyData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CountryListViewModelTest: BaseViewModelTest() {
    private lateinit var viewModel: CountryListViewModel
    private val countryRepository = mockk<CountryRepository>()
    private lateinit var useCase: FetchCountriesUseCase
    @Before
    fun setUp() {
        useCase = FetchCountriesUseCase(countryRepository)
        viewModel = CountryListViewModel(useCase)
    }

    @Test
    fun `fetch countries gives correct data`() {
        runBlocking {
            coEvery {
                countryRepository.getAllCountries()
            } returns flowOf(dummyData)
            viewModel.fetchCountries()
            coVerify { countryRepository.getAllCountries() }
            viewModel.resState.test().assertValue(StateWrapper.Success(dummyData))
        }
    }
}