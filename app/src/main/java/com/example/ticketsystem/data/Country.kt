package com.example.ticketsystem.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country")
data class Country(@PrimaryKey val id:Int,val country:String)

