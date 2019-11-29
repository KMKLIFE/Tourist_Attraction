package com.example.mapsactivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_hotel.*
import kotlinx.android.synthetic.main.activity_tripspot.*

class HotelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel)


        /*
        selectHotelButton3.setOnClickListener {
            val intent = Intent(this, CourseActivity::class.java)
            startActivity(intent)
        }*/
        selectHotelButton3.setOnClickListener {
            var hotelIntent = Intent(this,CourseActivity ::class.java)
            hotelIntent.putExtra("list",intent.getSerializableExtra("list") as ArrayList<TripSpotVO>)
            startActivity(hotelIntent)
        }
    }



}