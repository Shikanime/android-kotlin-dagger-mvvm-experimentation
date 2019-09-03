package io.etna.whatstheweather.repository

import android.location.Location
import io.etna.whatstheweather.data.WeatherLocationRecord
import io.etna.whatstheweather.service.openweatherapi.OpenWeatherApiService
import io.etna.whatstheweather.util.OpenWeatherConstants


class WeatherRepository constructor(
    private val openWeatherApiService: OpenWeatherApiService
) {

    suspend fun searchLocationWeather(query: String): WeatherLocationRecord {
        return openWeatherApiService.getLocationWeather(query, OpenWeatherConstants.API_APP_ID)
    }
}
