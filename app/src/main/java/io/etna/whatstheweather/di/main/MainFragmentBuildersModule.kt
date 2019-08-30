package io.etna.whatstheweather.di.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.etna.whatstheweather.ui.main.BookmarksFragment.BookmarksFragment
import io.etna.whatstheweather.ui.main.DetailFragment.DetailFragment

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeDetailFragment(): DetailFragment

    @ContributesAndroidInjector
    abstract fun contributeBookmarksFragment(): BookmarksFragment
}