package io.etna.whatstheweather.di.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.etna.whatstheweather.ui.main.location_detail.LocationDetailFragment

@Module
abstract class LocationDetailFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeLocationDetailFragment(): LocationDetailFragment;
}