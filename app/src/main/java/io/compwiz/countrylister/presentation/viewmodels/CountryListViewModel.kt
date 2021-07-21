package io.compwiz.countrylister.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.compwiz.countrylister.data.models.StateWrapper
import io.compwiz.countrylister.domain.exceptions.HttpException
import io.compwiz.countrylister.domain.model.CountryDomain
import io.compwiz.countrylister.domain.use_case.FetchCountriesUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


class CountryListViewModel (
    private val fetchCountryUseCase: FetchCountriesUseCase): ViewModel() {
    private val _resState: MutableLiveData<StateWrapper<List<CountryDomain>>> = MutableLiveData()
    val resState: LiveData<StateWrapper<List<CountryDomain>>>
    get() = _resState
    fun fetchCountries() {
        viewModelScope.launch {
            fetchCountryUseCase.invoke()
                .onStart {
                    _resState.value = StateWrapper.Loading
                }
                .catch { exception ->
                    when (exception) {
                        is HttpException -> {
                            _resState.value = StateWrapper.Failure(
                                false,
                                exception.code(),
                                exception.message
                            )
                        }
                        else -> {
                            _resState.value = StateWrapper.Failure(
                                true,
                                null,
                                "No connection available"
                            )
                        }
                    }
                }
                .collect {
                    _resState.value = StateWrapper.Success(it)
                }
        }
    }
}