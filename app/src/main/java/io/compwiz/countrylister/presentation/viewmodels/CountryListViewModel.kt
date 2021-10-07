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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.compwiz.countrylister.data.local.entity.CountryEntity
import io.compwiz.countrylister.domain.use_case.FetchCountriesUseCase
import io.compwiz.countrylister.utils.ResultWrapper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class CountryListViewModel (
    private val fetchCountryUseCase: FetchCountriesUseCase): ViewModel() {
        //val countries = fetchCountryUseCase.invoke().asLiveData()
    private val _resState: MutableLiveData<ResultWrapper<List<CountryEntity>>> = MutableLiveData()
    val resState: LiveData<ResultWrapper<List<CountryEntity>>> get() = _resState
    init {
        fetchCountries()
    }
    fun fetchCountries() {
        viewModelScope.launch {
            fetchCountryUseCase.invoke().collect {
                _resState.postValue(it)
            }
        }
    }
    /*private val _resState: MutableLiveData<StateWrapper<List<CountryDomain>>> = MutableLiveData()
    val resState: LiveData<StateWrapper<List<CountryDomain>>>
    get() = _resState
    fun fetchCountries() {
        viewModelScope.launch {
            fetchCountryUseCase.invoke()
                .onStart {
                    _resState.value = StateWrapper.Loading
                }
                .catch { exception ->
                    _resState.value = StateWrapper.Failure(
                        false,
                        null,
                        exception.message ?: "An error occurred!"
                    )
                }
                .collect {
                    _resState.value = StateWrapper.Success(it)
                }
        }
    }*/
}