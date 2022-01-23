package com.example.ticketsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketsystem.adapter.MyAdapter
import com.example.ticketsystem.adapter.MyAdapterInterface
import com.google.firebase.firestore.*
import java.io.Serializable

class UserlistActivity : AppCompatActivity(),MyAdapterInterface {

    private lateinit var userRecyclerview : RecyclerView
    private lateinit var userArrayList : ArrayList<User>
    private lateinit var myAdapter: MyAdapter
    lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userlist)

        val intent : Intent = getIntent()
        val city = intent.getStringExtra("cityValue") ?: ""


        userRecyclerview = findViewById(R.id.userList)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)

        userArrayList = arrayListOf()

        myAdapter= MyAdapter(userArrayList)
        myAdapter.listener = this

        userRecyclerview.adapter=myAdapter


        userRecyclerview.setOnClickListener {

            val intent = Intent(this, bookingappointment::class.java)
            startActivity(intent)

        }


        getUserData(city)


    }

    private fun getUserData( city : String) {
        firestore= FirebaseFirestore.getInstance()
        firestore.collection("Dashboard Shops").whereEqualTo("shop_city",city).addSnapshotListener(object:EventListener<QuerySnapshot>{
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {

                if(error!=null){
                    Toast.makeText(applicationContext,"Some Error Occurred",Toast.LENGTH_SHORT).show()
                }
                for (dc: DocumentChange in value?.documentChanges!! ){
                    if (dc.type==DocumentChange.Type.ADDED){
                        userArrayList.add(dc.document.toObject(User::class.java))
                    }
                }
            myAdapter.notifyDataSetChanged()
            }

        })
    }

    override fun onClick(user: User) {
        val intent = Intent(this, bookingappointment::class.java)
        intent.putExtra("user",user as Serializable)
        startActivity(intent)
    }
}