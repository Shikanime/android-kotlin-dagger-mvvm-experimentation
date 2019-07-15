package io.etna.whatstheweather.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Weather(
    var id: Int,
    var main: String,
    var description: String,
    var icon: String
)

data class Main(
    var temp: Float,
    var pressure: Int,
    var humidity: Int,
    var temp_min: Float,
    var temp_max: Float
)

data class Wind(
    var speed: Float,
    var deg: Int
)

data class Clouds(
    var all: Int
)

@Entity
data class Location(
    @PrimaryKey
    var id: Int = 0,
    var weather: List<Weather>,
    var main: Main,
    var visibility: Int = 0,
    var wind: Wind,
    var clouds: Clouds,
    var name: String = ""
)

