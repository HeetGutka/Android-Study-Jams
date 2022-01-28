package com.example.ticketsystem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class Appointment : AppCompatActivity() {
    
    private lateinit var recyclerView: RecyclerView
    var appointmentArrayList: ArrayList<AppointmentData> = ArrayList()
    private lateinit var appointmentAdapter: AppointmentAdapter
    private lateinit var db: FirebaseFirestore
    var userid=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment)
        
        recyclerView = findViewById<RecyclerView>(R.id.AppointmentRecyclerView)
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = linearLayoutManager

        dataChangeListener() // data retrieval and inserttion in the arraylist

        //println(appointmentArrayList.size)
        appointmentAdapter = AppointmentAdapter(appointmentArrayList)
        recyclerView.adapter = appointmentAdapter
    }

    private fun dataChangeListener() {

       lateinit var auth: FirebaseAuth
       auth = Firebase.auth
       userid = auth.uid.toString()  // userid retrieved from auth on login

      //   userid = "3ypbcFn2nTPc3jpA57qa4zYSUWG3"

        db = FirebaseFirestore.getInstance()  // Retrieving document from firebase and storing in dataclass
        db.collection("Appointment")
            .whereEqualTo("userId", userid).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val shopidref = document.data.get("shopId").toString()
                    db.collection("Shop").document(shopidref).get()
                        .addOnSuccessListener { shopDocument ->
                            if (document != null) {
                                appointmentArrayList.add(AppointmentData(this@Appointment,
                                    shopDocument.data?.get("shopName").toString(),
                                    shopDocument.data?.get("location").toString(),
                                    document.data.get("date").toString(),
                                    document.data["time"].toString(),
                                    document.id,
                                    document["slotId"].toString()
                                ))
                                appointmentAdapter.notifyItemInserted(appointmentArrayList.size-1)

                            }
                            //    println(shopDocument.data!!["location"])
                        }
                }
            }

    }
}
