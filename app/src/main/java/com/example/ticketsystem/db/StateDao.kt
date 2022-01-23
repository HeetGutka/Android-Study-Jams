package com.example.ticketsystem.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ticketsystem.data.State

@Dao
interface StateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(state:List<State>)

    @Query("SELECT state FROM state WHERE country =:id")
    fun getData(id: String):LiveData<List<String>>

    @Query("DELETE FROM state")
    suspend fun delete()
}