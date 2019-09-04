package io.etna.whatstheweather.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface WeatherLocationDao {

    @Insert(onConflict = REPLACE)
    suspend fun add(record: WeatherLocationRecord)

    @Update
    suspend fun update(record: WeatherLocationRecord)

    @Delete
    suspend fun remove(record: WeatherLocationRecord)

    @Query("SELECT * FROM weatherLocationRecord ORDER BY id DESC")
    fun all(): LiveData<List<WeatherLocationRecord>>
}