package io.etna.whatstheweather

import android.app.Application
import io.etna.whatstheweather.di.appModule
import io.etna.whatstheweather.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class WhatsTheWeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@WhatsTheWeatherApp)
            modules(appModule)
            modules(mainModule)
        }
    }
}