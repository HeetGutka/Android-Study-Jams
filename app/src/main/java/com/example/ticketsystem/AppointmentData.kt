package com.example.ticketsystem

import android.content.Context

data class AppointmentData(var context: Context, var shop: String,
                           var location:String, var date: String,
                           var time: String, var appointmentId: String, var slotId:String)
