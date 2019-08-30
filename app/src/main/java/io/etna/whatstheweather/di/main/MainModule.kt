package io.etna.whatstheweather.di.main

import dagger.Module
import dagger.Provides
import io.etna.whatstheweather.ui.main.BookmarksFragment.BookmarksRecycleAdapter

@Module
class MainModule {
    @Provides
    fun provideAdapter(): BookmarksRecycleAdapter {
        return BookmarksRecycleAdapter()
    }
}
