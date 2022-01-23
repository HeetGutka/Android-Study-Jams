package com.example.ticketsystem

import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import java.sql.Time
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.collections.ArrayList

class AppointmentAdapter(private var appointmentList: ArrayList<AppointmentData>

) :RecyclerView.Adapter<AppointmentAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view_appointment,parent,false)
        return MyViewHolder(v)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val appointment : AppointmentData = appointmentList[position]

        holder.shop.text = appointment.shop
        holder.location.text = appointment.location
        holder.appointmentTime.text = appointment.time
        holder.appointmentDate.text = appointment.date

       holder.itemView.setOnClickListener {
           val context: Context = appointment.context
           val sdf = SimpleDateFormat("yyyy-mm-dd")
           val stf = SimpleDateFormat("HH:mm")
           val v = sdf.format(Date())
           val isCancel = sdf.parse(appointment.date).after(Date())
           if(!isCancel){
               val isCancel = stf.parse(appointment.time).after(Date())
           }
           val intent = Intent(appointment.context,AppointmentDetails::class.java)

           //data to next activity using put extra
           intent.putExtra(AppointmentDetails.shop,holder.shop.text)
           intent.putExtra(AppointmentDetails.location,holder.location.text)
           intent.putExtra(AppointmentDetails.appointmentId,appointment.appointmentId)
           intent.putExtra(AppointmentDetails.date,holder.appointmentTime.text)
           intent.putExtra(AppointmentDetails.time,holder.appointmentDate.text)
           intent.putExtra(AppointmentDetails.isCancel,isCancel.toString())
           intent.putExtra(AppointmentDetails.slotId,appointment.slotId)
           context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return appointmentList.size
    }


    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var shop: TextView = itemView.findViewById<View>(R.id.appointmentShopTextView) as TextView
        var location = itemView.findViewById<View>(R.id.appointmentLocationTextView) as TextView
        var appointmentTime = itemView.findViewById<View>(R.id.appointmentTimeTextView) as TextView
        var appointmentDate = itemView.findViewById<View>(R.id.appointmentDateTextView) as TextView
    }
}