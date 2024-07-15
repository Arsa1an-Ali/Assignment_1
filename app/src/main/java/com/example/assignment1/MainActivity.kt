package com.example.assignment1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var citySpinner: Spinner
    private lateinit var fetchButton: Button
    private var selectedCity: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Weather App"

        citySpinner = findViewById(R.id.city_spinner)
        fetchButton = findViewById(R.id.fetch_button)

        val cities = arrayOf("New York", "London", "Tokyo", "Paris", "Sydney")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        citySpinner.adapter = adapter

        citySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedCity = cities[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedCity = null
            }
        }

        fetchButton.setOnClickListener {
            selectedCity?.let {
                val intent = Intent(this, DetailsActivity::class.java)
                intent.putExtra("CITY_NAME", it)
                startActivity(intent)
            }
        }
    }
}

