package com.example.ticketsystem.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ticketsystem.data.City

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(city:List<City>)

    @Query("SELECT city FROM city WHERE state =:id")
    fun getData(id: String): LiveData<List<String>>

    @Query("DELETE FROM city")
    suspend fun delete()
}
