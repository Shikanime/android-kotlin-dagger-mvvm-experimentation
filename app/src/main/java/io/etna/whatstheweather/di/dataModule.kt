package io.etna.whatstheweather.di

import androidx.room.Room
import io.etna.whatstheweather.data.WeatherLocationFavoriteDatabase
import io.etna.whatstheweather.data.WeatherLocationSearchDatabase
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(
            get(),
            WeatherLocationFavoriteDatabase::class.java,
            "weather_location_favorite_db"
        )
            .build()
    }


    single {
        Room.databaseBuilder(
            get(),
            WeatherLocationSearchDatabase::class.java,
            "weather_location_search_db"
        )
            .build()
    }
}
