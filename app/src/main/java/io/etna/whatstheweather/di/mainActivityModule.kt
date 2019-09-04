package io.etna.whatstheweather.di

import io.etna.whattheweather.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainActivityModule = module {
    viewModel { MainViewModel(get(), get()) }
}
