package io.etna.whatstheweather.di

import androidx.room.Room
import io.etna.whatstheweather.data.WeatherDatabase
import io.etna.whatstheweather.repository.WeatherRepository
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(
            get(),
            WeatherDatabase::class.java,
            "weather_location_db"
        )
            .build()
    }

    single { WeatherRepository(get()) }
}
