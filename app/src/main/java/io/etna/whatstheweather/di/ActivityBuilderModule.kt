package io.etna.whatstheweather.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.etna.whatstheweather.di.main.LocationDetailFragmentBuildersModule
import io.etna.whatstheweather.di.main.MainViewModelsModule
import io.etna.whatstheweather.ui.main.MainActivity

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
        modules = [
            LocationDetailFragmentBuildersModule::class,
            MainViewModelsModule::class
        ]
    )
    abstract fun constributeMainAcitvity(): MainActivity
}