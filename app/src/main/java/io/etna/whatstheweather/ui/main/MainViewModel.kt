package io.etna.whatstheweather.ui.main

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.etna.whatstheweather.Status
import io.etna.whatstheweather.model.Location
import io.etna.whatstheweather.repository.WeatherRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    var weatherRepository: WeatherRepository
) : ViewModel() {
    var locationWeather: MediatorLiveData<Location> = MediatorLiveData()
    var favoriteLocationWeather: MutableLiveData<MutableList<Location>> = MutableLiveData()

    fun getLocationWeather(query: String) {
        val source = weatherRepository.getLocationWeather(query)
        locationWeather.addSource(source) {
            locationWeather.value = it
            locationWeather.removeSource(source)
        }
    }

    fun addFavoriteLocationWeather() {
        favoriteLocationWeather.value = if (favoriteLocationWeather.value !== null) {
            favoriteLocationWeather.value!!.add(locationWeather.value!!)
            favoriteLocationWeather.value
        } else {
            mutableListOf(locationWeather.value!!)
        }
    }

    fun removeFavoriteLocationWeather(locationWeather: Location) {
        favoriteLocationWeather.value?.remove(locationWeather)
    }
}