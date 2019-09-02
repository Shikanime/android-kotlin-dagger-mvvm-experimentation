package io.etna.whatstheweather.repository

import io.etna.whatstheweather.service.openweatherapi.OpenWeatherApiService
import io.etna.whatstheweather.util.OpenWeatherConstants
import org.koin.core.KoinComponent


class WeatherRepository constructor(
    private val openWeatherApiService: OpenWeatherApiService
) : KoinComponent {

    suspend fun getLocationWeather(query: String) =
        openWeatherApiService.getLocationWeather(query, OpenWeatherConstants.API_APP_ID)
}
