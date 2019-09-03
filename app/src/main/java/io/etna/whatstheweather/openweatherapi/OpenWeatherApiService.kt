package io.etna.whatstheweather.service.openweatherapi

import io.etna.whatstheweather.data.WeatherLocationRecord
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApiService {
    @GET("/data/$VERSION/$SUBJECT")
    suspend fun getLocationWeather(
        @Query("q") location: String,
        @Query("appid") appId: String
    ): WeatherLocationRecord

    companion object {
        private const val VERSION = "2.5"
        private const val SUBJECT = "weather"
    }
}