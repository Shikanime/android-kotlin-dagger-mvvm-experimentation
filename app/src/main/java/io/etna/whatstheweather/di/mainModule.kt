package io.etna.whatstheweather.di

import io.etna.whattheweather.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel {
        MainViewModel(get(), get(), get())
    }
}
