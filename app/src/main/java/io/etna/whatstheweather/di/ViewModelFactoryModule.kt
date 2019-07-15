package io.etna.whatstheweather.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import io.etna.whatstheweather.viewmodel.ViewModelFactory

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(modelProviderFactory: ViewModelFactory): ViewModelProvider.Factory
}