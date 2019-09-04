package io.etna.whatstheweather.repository

import io.etna.whatstheweather.service.openweatherapi.OpenWeatherApiService
import io.etna.whatstheweather.util.OpenWeatherConstants


class WeatherRepository(
    private val openWeatherApiService: OpenWeatherApiService
) {

    suspend fun searchLocationWeather(query: String) =
        openWeatherApiService.getLocationWeather(query, OpenWeatherConstants.APP_ID)
}
