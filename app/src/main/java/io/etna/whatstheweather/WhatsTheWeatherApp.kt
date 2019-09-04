package io.etna.whatstheweather

import android.app.Application
import io.etna.whatstheweather.di.dataModule
import io.etna.whatstheweather.di.mainActivityModule
import io.etna.whatstheweather.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class WhatsTheWeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@WhatsTheWeatherApp)
            modules(networkModule)
            modules(dataModule)
            modules(mainActivityModule)
        }
    }
}