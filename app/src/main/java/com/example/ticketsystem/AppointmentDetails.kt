package com.example.ticketsystem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class AppointmentDetails : AppCompatActivity() {
    companion object {
        const val shop = "shop1"
        const val location = "Not Found"
        const val appointmentId = "-10"
        const val date = "20:12:2021"
        const val time = "12:00"
        const val isCancel = "false"
        const val slotId = "-1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment_details)

        val cancelAppointmentButton = findViewById<View>(R.id.appointmentDetailsCancelAppointment)
        val isCancel = intent?.extras?.getString(isCancel)
        val appointmentId = intent?.extras?.getString(appointmentId)
        val slotId = intent?.extras?.getString(slotId)
        if(isCancel.toBoolean()){
            cancelAppointmentButton.visibility = View.VISIBLE
        }
        cancelAppointmentButton.setOnClickListener {

         val db = FirebaseFirestore.getInstance()
            if (appointmentId != null) {
                db.collection("Appointment").document(appointmentId).delete()
                if (slotId != null) { db.collection("SlotId").document(slotId).delete() }
            }
            finish()
        }

        val shop = intent?.extras?.getString(shop)
        val location = intent?.extras?.getString(location)
        val date = intent?.extras?.getString(date)
        val time = intent?.extras?.getString(time)

        val shopview = findViewById<View>(R.id.appointmentDetailsShop) as TextView
        val shopLocationView = findViewById<View>(R.id.appointmentDetailsLocation) as TextView
        val appointmentDateView = findViewById<View>(R.id.appointmentDetailsDate) as TextView
        val appointmentTimeView = findViewById<View>(R.id.appointmentDetailsTime) as TextView
        shopLocationView.setText(location)
        shopview.setText(shop)
        appointmentDateView.setText(date)
        appointmentTimeView.setText(time)
    }
}