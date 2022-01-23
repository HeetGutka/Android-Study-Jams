package com.example.ticketsystem.model

import android.util.Log
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class AppointmentViewModel : ViewModel() {

    private val TAG = "MyActivity"

    val sdf = SimpleDateFormat("dd/M/yyyy")
    val currentDate = sdf.format(Date())

    /**
     * Set the pickup date for this order.
     *
     * @param pickupDate is the date for pickup as a string
     */
    fun setDate() {
        Log.d(currentDate, TAG)
    }
}