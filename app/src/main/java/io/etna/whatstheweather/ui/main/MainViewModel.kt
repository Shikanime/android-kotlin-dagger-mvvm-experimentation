package io.etna.whattheweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import io.etna.whatstheweather.data.RequestStatus
import io.etna.whatstheweather.data.WeatherDatabase
import io.etna.whatstheweather.data.WeatherLocationRecord
import io.etna.whatstheweather.repository.WeatherRepository
import kotlinx.coroutines.launch


class MainViewModel(
    private val weatherRepository: WeatherRepository,
    private val weatherDatabase: WeatherDatabase
) : ViewModel() {

    var currentWeatherLocation: MutableLiveData<WeatherLocationRecord> = MutableLiveData()

    fun openWeatherLocation(value: WeatherLocationRecord) {
        currentWeatherLocation.value = value
    }

    var searchWeatherLocation: MutableLiveData<Either<RequestStatus, List<WeatherLocationRecord>>> =
        MutableLiveData()

    fun searchLocationWeather(query: String) = viewModelScope.launch {
        searchWeatherLocation.value = Either.left(RequestStatus.LOADING)

        try {
            val locationWeather = weatherRepository.searchLocationWeather(query)
            searchWeatherLocation.value = Either.right(listOf(locationWeather))
        } catch (e: retrofit2.HttpException) {
            searchWeatherLocation.value = Either.left(RequestStatus.ERROR)
        }
    }

    val favoriteWeatherWeatherLocation: LiveData<List<WeatherLocationRecord>> =
        weatherDatabase.weatherLocationDao().all()

    fun addFavoriteLocationWeather(value: WeatherLocationRecord) = viewModelScope.launch {
        weatherDatabase.weatherLocationDao().add(value)
    }

    fun removeFavoriteLocationWeather(value: WeatherLocationRecord) = viewModelScope.launch {
        weatherDatabase.weatherLocationDao().remove(value)
    }
}