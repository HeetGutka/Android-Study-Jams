package com.example.ticketsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.cardview.widget.CardView
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class NewDashboard : AppCompatActivity() {


    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView
    lateinit var booktheApp:CardView
    lateinit var allApp:CardView
    lateinit var profile:CardView
    lateinit var bookmarks:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_dashboard)


        booktheApp=findViewById(R.id.book_the_app)
        allApp=findViewById(R.id.all_appoint)
        profile=findViewById(R.id.profile_new)
        bookmarks=findViewById(R.id.bookmark_new)

        allApp=findViewById(R.id.all_appoint)

        profile=findViewById(R.id.profile_new)

        bookmarks=findViewById(R.id.bookmark_new)

        allApp.setOnClickListener {

            val intent = Intent(this, Appointment::class.java)
            startActivity(intent)

        }

        booktheApp.setOnClickListener {

            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)

        }

        bookmarks.setOnClickListener {

            val intent = Intent(this, UserlistActivity::class.java)
            startActivity(intent)

        }

        profile.setOnClickListener {

            val intent = Intent(this, UserlistActivity::class.java)
            startActivity(intent)

        }
        drawerLayout = findViewById(R.id.drawer_layout)

        // Pass the ActionBarToggle action into the drawerListener
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(actionBarToggle)

        // Display the hamburger icon to launch the drawer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Call syncState() on the action bar so it'll automatically change to the back button when the drawer layout is open
        actionBarToggle.syncState()


        // Call findViewById on the NavigationView
        navView = findViewById(R.id.nav_view_new)

        // Call setNavigationItemSelectedListener on the NavigationView to detect when items are clicked
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_bookap -> {
                    Toast.makeText(this, "My Profile", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_abtus -> {
                    Toast.makeText(this, "People", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.log_out -> {
                    Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}