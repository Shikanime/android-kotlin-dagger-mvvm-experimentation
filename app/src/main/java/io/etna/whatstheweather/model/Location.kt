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
    var id: Int = -1,
    var weather: List<Weather> = emptyList(),
    var main: Main? = null,
    var visibility: Int = -1,
    var wind: Wind? = null,
    var clouds: Clouds? = null,
    var name: String = ""
)

