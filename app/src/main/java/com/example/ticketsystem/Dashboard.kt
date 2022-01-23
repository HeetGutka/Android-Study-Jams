package com.example.ticketsystem

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.ticketsystem.data.City
import com.example.ticketsystem.data.Country
import com.example.ticketsystem.data.State
import com.example.ticketsystem.db.CountryDatabase
import com.example.ticketsystem.model.repository
import com.example.ticketsystem.model.viewmodel
import com.example.ticketsystem.model.viewmodelfactory
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Dashboard : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var spinerModel: viewmodel
    lateinit var database: CountryDatabase
    lateinit var recyclerbtn: Button
    var country: String = "India"
    lateinit var auth: FirebaseAuth
    lateinit var spinner: Spinner
    private var cityValue: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        recyclerbtn = findViewById(R.id.recyclerviewbtn)
        auth = FirebaseAuth.getInstance()
        spinner = findViewById(R.id.city_spinner)


        recyclerbtn.setOnClickListener {

            val i = Intent(this, UserlistActivity::class.java)
            i.putExtra("cityValue",cityValue)
            startActivity(i)
            finish()
        }

        database = CountryDatabase(this)

        val repo = repository(database)
        val fact = viewmodelfactory(repo)
        spinerModel = ViewModelProvider(this, fact).get(viewmodel::class.java)


        //Insert data in database
        val country: List<Country> = arrayListOf(
            Country(1, "India")
        )

        val state: List<State> = arrayListOf(
            State(1, "India", "Maharashtra")
        )

        val city: List<City> = arrayListOf(
            City(1, "Maharashtra", "Thane West"),
            City(2, "Maharashtra", "Thane East"),
            City(3, "Maharashtra", "Dombivli West"),
            City(4, "Maharashtra", "Dombivli East"),
            City(5, "Maharashtra", "Kalyan East"),
            City(6, "Maharashtra", "Kalyan West")
        )


        lifecycleScope.launch(Dispatchers.IO) {
            database.countryDao().insert(country)
            database.stateDao().insert(state)
            database.cityDao().insert(city)


            val countryAdapter: ArrayAdapter<String> =
                ArrayAdapter(
                    this@Dashboard,
                    android.R.layout.simple_spinner_item,
                    spinerModel.getCountry()
                )

            lifecycleScope.launch(Dispatchers.Main) {
                countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                countryAdapter.notifyDataSetChanged()
                country_spinner.adapter = countryAdapter
            }
        }

        country_spinner.onItemSelectedListener = this
        state_spinner.onItemSelectedListener = this
        city_spinner.onItemSelectedListener = this

    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (view == country_spinner.selectedView) {

            country = country_spinner.selectedItem.toString()
            spinerModel.getState(country).observe(this@Dashboard, Observer {
                val stateAdapter: ArrayAdapter<String> =
                    ArrayAdapter(this@Dashboard, android.R.layout.simple_spinner_item, it)
                stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                stateAdapter.notifyDataSetChanged()
                state_spinner.adapter = stateAdapter
            })

        }

        if (view == state_spinner.selectedView) {
            spinerModel.getCity(state_spinner.selectedItem.toString()).observe(this, Observer {
                val cityAdapter: ArrayAdapter<String> =
                    ArrayAdapter(this@Dashboard, android.R.layout.simple_spinner_item, it)
                cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                cityAdapter.notifyDataSetChanged()
                city_spinner.adapter = cityAdapter
            })
        }

        if (view == city_spinner.selectedView){
            cityValue = city_spinner.selectedItem.toString()
        }


    }


    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}