package com.example.assignment1

import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat

class DetailsActivity : AppCompatActivity() {

    private lateinit var weatherDetailsTextView: TextView
    private lateinit var detailsLayout: RelativeLayout

    private val weatherData = mapOf(
        "New York" to "Sunny, 25°C",
        "London" to "Cloudy, 18°C",
        "Tokyo" to "Rainy, 22°C",
        "Paris" to "Windy, 20°C",
        "Sydney" to "Clear, 27°C"
    )

    private val weatherBackgrounds = mapOf(
        "Sunny" to R.drawable.sunny,
        "Cloudy" to R.drawable.cloudy,
        "Rainy" to R.drawable.rainy,
        "Windy" to R.drawable.windy,
        "Clear" to R.drawable.clear
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Weather Details"

        toolbar.setNavigationOnClickListener {
            finish()
        }

        weatherDetailsTextView = findViewById(R.id.weather_details_text_view)
        detailsLayout = findViewById(R.id.details_layout)

        val cityName = intent.getStringExtra("CITY_NAME")
        cityName?.let {
            val weatherDetails = weatherData[it]
            weatherDetailsTextView.text = "Weather details for $cityName:\n\n$weatherDetails"

            val weatherCondition = weatherDetails?.split(",")?.get(0)?.trim()
            val backgroundResource = weatherCondition?.let { weatherBackgrounds[it] }
            backgroundResource?.let { detailsLayout.setBackgroundResource(it) }
        }
    }
}


