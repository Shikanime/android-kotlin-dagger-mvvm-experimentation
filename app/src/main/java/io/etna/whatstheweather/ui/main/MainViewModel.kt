package io.etna.whatstheweather.ui.main

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.etna.whatstheweather.model.Location
import io.etna.whatstheweather.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    var locationWeather: MediatorLiveData<Location> = MediatorLiveData()

    fun getLocationWeather(query: String) {
        viewModelScope.launch {
            locationWeather.value = withContext(Dispatchers.IO) {
                try {
                    weatherRepository.getLocationWeather(query)
                } catch (e: retrofit2.HttpException) {
                    null
                }
            }
        }
    }

    var favoriteLocationWeather: MutableLiveData<MutableList<Location>> = MutableLiveData()

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