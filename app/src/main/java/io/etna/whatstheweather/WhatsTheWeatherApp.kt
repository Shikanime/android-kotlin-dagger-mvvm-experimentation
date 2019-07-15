package io.etna.whatstheweather

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.etna.whatstheweather.di.DaggerAppComponent

class WhatsTheWeatherApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}