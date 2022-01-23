package com.example.ticketsystem.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
data class City(@PrimaryKey val id:Int,val state:String,val city:String)

