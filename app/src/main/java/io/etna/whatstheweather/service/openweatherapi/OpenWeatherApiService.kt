package io.etna.whatstheweather.service.openweatherapi

import io.etna.whatstheweather.model.Location
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApiService {
    @GET("/data/$VERSION/$SUBJECT")
    fun getLocationWeather(
        @Query("q") location: String,
        @Query("appid") appId: String
    ): Flowable<Location>

    companion object {
        private const val VERSION = "2.5"
        private const val SUBJECT = "weather"
    }
}