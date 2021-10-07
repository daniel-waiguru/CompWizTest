package io.compwiz.countrylister.presentation.di

import io.compwiz.countrylister.presentation.viewmodels.CountryListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CountryListViewModel(get()) }
}