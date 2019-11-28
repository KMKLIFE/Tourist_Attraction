package com.example.mapsactivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_map.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        selectLocationButton.setOnClickListener {
            val intent = Intent(this, TripSpotActivity::class.java)
            startActivity(intent)
        }
    }





}
