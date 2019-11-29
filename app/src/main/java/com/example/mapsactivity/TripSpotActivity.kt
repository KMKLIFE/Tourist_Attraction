package com.example.mapsactivity

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.e.myapplication.AsyncTaskCallback
import com.e.myapplication.ConnectServerAsyncTask
import kotlinx.android.synthetic.main.activity_tripspot.*



class TripSpotActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.mapsactivity.R.layout.activity_tripspot)

        val tripspotList = ArrayList<TripSpotVO>()


        //findViewById<R.id.>()

        val task = ConnectServerAsyncTask(object : AsyncTaskCallback {
            override fun onSuccess(tripspots: ArrayList<TripSpotVO>) {
                Log.d("TripSpotActivity", "관광지사이즈 ${tripspots.size}")
                for (i in 1..tripspots.size) {
                    tripspotList.add(tripspots[i - 1])
                    Log.d("TripSpotActivity", (tripspots[i - 1].Name))
                }

                if (tripspots[0].Category == "문화시설") {
                    gbgname.text = tripspots[0].Name
                    Log.d("Name", (tripspots[0].Name))
                    gbgtime.text = "운영시간: " + tripspots[0].OpenTime + "~" + tripspots[0].CloseTime
                    gbgfee.text = "입장료: " + tripspots[0].EntranceFee + "원"
                    gbgstar.text = "평점: " + tripspots[0].StarRating
                }
                if (tripspots[1].Category == "문화시설") {
                    gmname.text = tripspots[1].Name
                    gmtime.text =
                        "운영시간: " + tripspots[1].OpenTime + "~" + tripspots[1].CloseTime
                    gmfee.text = "입장료: " + tripspots[1].EntranceFee + "원"
                    gmstar.text = "평점: " + tripspots[1].StarRating
                }
                if (tripspots[2].Category == "문화시설") {
                    cgcname.text = tripspots[2].Name
                    cgctime.text =
                        "운영시간: " + tripspots[2].OpenTime + "~" + tripspots[2].CloseTime
                    cgcfee.text = "입장료: " + tripspots[2].EntranceFee + "원"
                    cgcstar.text = "평점: " + tripspots[2].StarRating
                }
                if (tripspots[3].Category == "문화시설") {
                    ngname.text = tripspots[3].Name
                    ngtime.text = "운영시간: " + tripspots[3].OpenTime + "~" + tripspots[3].CloseTime
                    ngfee.text = "입장료: " + tripspots[3].EntranceFee + "원"
                    ngstar.text = "평점: " + tripspots[3].StarRating
                }
/*
                val button = findViewById(R.id.button1) as Button
                button.setOnClickListener{ }
*/

            }

            override fun onFailure() {

            }
            })
        task.execute()

        checkgbg.setOnClickListener{
            tripspotList[0].isChecked=true
        }
        checkgm.setOnClickListener{
            tripspotList[1].isChecked=true
        }
        checkcgc.setOnClickListener{
            tripspotList[2].isChecked=true
        }
        checkng.setOnClickListener{
            tripspotList[3].isChecked=true
        }

        /*
        selectHotelButton.setOnClickListener {
            val intent = Intent(this, HotelActivity::class.java)
            startActivity(intent)
        }*/
        selectHotelButton.setOnClickListener {
            var tripspotIntent = Intent(this, HotelActivity::class.java)
            tripspotIntent.putExtra("list", tripspotList)
            startActivity(tripspotIntent)

        }






    }
}