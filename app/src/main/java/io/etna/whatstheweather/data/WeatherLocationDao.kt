package io.etna.whatstheweather.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface WeatherLocationDao {

    @Insert(onConflict = REPLACE)
    suspend fun add(todoRecord: WeatherLocationRecord)

    @Delete
    suspend fun remove(todoRecord: WeatherLocationRecord)

    @Query("SELECT * FROM weatherLocationRecord ORDER BY id DESC")
    fun all(): LiveData<List<WeatherLocationRecord>>
}