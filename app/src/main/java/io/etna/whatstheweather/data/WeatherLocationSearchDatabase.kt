package io.etna.whatstheweather.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [WeatherLocationRecord::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(WeatherLocationRecord.DataTypeConverter::class)
abstract class WeatherLocationSearchDatabase : RoomDatabase() {

    abstract fun weatherLocationDao(): WeatherLocationDao
}