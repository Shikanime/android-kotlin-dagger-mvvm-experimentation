package io.etna.whatstheweather.ui.main

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import io.etna.whatstheweather.model.Location
import io.etna.whatstheweather.repository.WeatherRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    var weatherRepository: WeatherRepository
) : ViewModel() {
    var locationWeather: MediatorLiveData<Location> = MediatorLiveData()

    fun getLocationWeather(query: String) {
        val source = weatherRepository.getLocationWeather(query)

        locationWeather.addSource(source) { location ->
            locationWeather.value = location
            locationWeather.removeSource(source)
        }
    }
}