package io.etna.whatstheweather.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

@Entity
data class WeatherLocationRecord(
    @PrimaryKey
    var id: Int,
    var name: String,
    var visibility: Int,
    var weather: List<Weather>,
    @Embedded
    var main: Main,
    @Embedded
    var wind: Wind,
    @Embedded
    var clouds: Clouds
) {
    @Entity
    data class Main(
        var temp: Float,
        var pressure: Int,
        var humidity: Int,
        var temp_min: Float,
        var temp_max: Float
    )

    @Entity
    data class Wind(
        var speed: Float,
        var deg: Int
    )

    @Entity
    data class Clouds(
        var all: Int
    )

    @Entity
    data class Weather(
        var weatherLocationId: Int,
        var main: String,
        var description: String,
        var icon: String
    )


    class DataTypeConverter {
        private val gson = Gson()

        @TypeConverter
        fun toList(data: String?): List<Weather> {
            if (data == null) {
                return Collections.emptyList()
            }

            val listType = object : TypeToken<List<Weather>>() {}.type

            return gson.fromJson(data, listType)
        }

        @TypeConverter
        fun toString(someObjects: List<Weather>): String {
            return gson.toJson(someObjects)
        }
    }
}
