package com.example.ticketsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AppDashboard : AppCompatActivity() {
    lateinit var login_from_here:Button
    lateinit var register_from_here:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_dashboard)

        login_from_here=findViewById(R.id.login_from_here)
        register_from_here=findViewById(R.id.register_from_here)

        login_from_here.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        register_from_here.setOnClickListener {

            val intent = Intent(this, Registeration::class.java)
            startActivity(intent)
        }
    }
}