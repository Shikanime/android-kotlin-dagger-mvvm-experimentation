package io.etna.whatstheweather.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams.fromPublisher
import io.etna.whatstheweather.Resource
import io.etna.whatstheweather.model.Location
import io.etna.whatstheweather.service.openweatherapi.OpenWeatherApiService
import io.etna.whatstheweather.util.OpenWeatherConstants
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val openWeatherApiService: OpenWeatherApiService
) {
    fun getLocationWeather(query: String): LiveData<Location> {
        return fromPublisher(
            openWeatherApiService.getLocationWeather(query, OpenWeatherConstants.API_APP_ID)
                .onErrorReturn { Location() }
                .subscribeOn(Schedulers.io())
        )
    }
}