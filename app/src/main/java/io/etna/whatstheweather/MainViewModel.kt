package io.etna.whattheweather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.etna.whatstheweather.data.WeatherLocationFavoriteDatabase
import io.etna.whatstheweather.data.WeatherLocationRecord
import io.etna.whatstheweather.data.WeatherLocationSearchDatabase
import io.etna.whatstheweather.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel constructor(
    private val weatherRepository: WeatherRepository,
    private val weatherLocationSearchDatabase: WeatherLocationSearchDatabase,
    private val weatherLocationFavoriteDatabase: WeatherLocationFavoriteDatabase
) : ViewModel() {

    var currentWeatherLocation: MediatorLiveData<WeatherLocationRecord> = MediatorLiveData()

    fun openWeatherLocation(value: WeatherLocationRecord) {
        currentWeatherLocation.value = value
    }

    var searchWeatherLocation: LiveData<List<WeatherLocationRecord>> =
        weatherLocationSearchDatabase.weatherLocationDao().all()

    fun searchLocationWeather(query: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            try {
                val locationWeather = weatherRepository.searchLocationWeather(query)
                weatherLocationSearchDatabase.weatherLocationDao().add(locationWeather)
            } catch (e: retrofit2.HttpException) {
                Log.d("MainViewModel", "Couldn't retrieve location weather")
            }
        }
    }

    val favoriteWeatherWeatherLocation: LiveData<List<WeatherLocationRecord>> =
        weatherLocationFavoriteDatabase.weatherLocationDao().all()

    fun addFavoriteLocationWeather(value: WeatherLocationRecord) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            weatherLocationFavoriteDatabase.weatherLocationDao().add(value)
        }
    }

    fun removeFavoriteLocationWeather(value: WeatherLocationRecord) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            weatherLocationFavoriteDatabase.weatherLocationDao().remove(value)
        }
    }
}