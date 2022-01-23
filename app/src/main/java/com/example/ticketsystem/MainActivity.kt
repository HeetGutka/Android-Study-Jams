package com.example.ticketsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var head_text: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //head_text = findViewById<View>(R.id.carRent) as TextView

        // YoYo.with(Techniques.BounceInUp).duration(1000).repeat(3).playOn(head_text)


        Handler().postDelayed(Runnable {
            login()
            finish()
        }, 3000)


    }

    fun login() {
        val intent = Intent(this, AppDashboard::class.java)
        startActivity(intent)
        //Animatoo.animateZoom(this)

    }

}