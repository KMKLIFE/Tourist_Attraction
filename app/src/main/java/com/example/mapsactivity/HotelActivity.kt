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
        val tripspots = intent.getSerializableExtra("list") as ArrayList<TripSpotVO>

        if(tripspots[4].Category == "숙박시설") {
            slname.text = tripspots[4].Name
            slfee.text = "가격: "+tripspots[4].EntranceFee+"원"
            slstar.text = "평점: "+tripspots[4].StarRating
        }
        if(tripspots[5].Category == "숙박시설") {
            okname.text = tripspots[5].Name
            okfee.text = "가격: "+tripspots[5].EntranceFee+"원"
            okstar.text = "평점: "+tripspots[5].StarRating
        }
        if(tripspots[6].Category == "숙박시설") {
            cmname.text = tripspots[6].Name
            cmfee.text = "가격: "+tripspots[6].EntranceFee+"원"
            cmstar.text = "평점: "+tripspots[6].StarRating
        }
        if(tripspots[7].Category == "숙박시설") {
            mpname.text = tripspots[7].Name
            mpfee.text = "가격: "+tripspots[7].EntranceFee+"원"
            mpstar.text = "평점: "+tripspots[7].StarRating
        }


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

        checkss.setOnClickListener{
            tripspots[4].isChecked=true
        }
        checkora.setOnClickListener{
            tripspots[5].isChecked=true
        }
        checkcm.setOnClickListener{
            tripspots[6].isChecked=true
        }
        checkms.setOnClickListener{
            tripspots[7].isChecked=true
        }
    }



}