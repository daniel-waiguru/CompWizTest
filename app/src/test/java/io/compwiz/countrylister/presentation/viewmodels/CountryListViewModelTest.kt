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

package io.compwiz.countrylister.presentation.viewmodels

import io.compwiz.countrylister.base.BaseViewModelTest
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
            //viewModel.resState.test().assertValue(ResultWrapper.Success(dummyData).data)
        }
    }
}