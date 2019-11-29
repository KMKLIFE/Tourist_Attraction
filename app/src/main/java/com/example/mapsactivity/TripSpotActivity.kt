package com.example.mapsactivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.e.myapplication.AsyncTaskCallback
import com.e.myapplication.ConnectServerAsyncTask
import com.e.myapplication.HotelVO
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_tripspot.*
import org.w3c.dom.Text

class TripSpotActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tripspot)

        val tripspotList = ArrayList<TripSpotVO>()


        //findViewById<R.id.>()

        val task = ConnectServerAsyncTask(object : AsyncTaskCallback {
            override fun onSuccess(tripspots: ArrayList<TripSpotVO>) {
                Log.d("TripSpotActivity", "관광지사이즈 ${tripspots.size}")
                for( i in 1..tripspots.size)
                    tripspotList.add(tripspots[i-1])
                   // Log.d("TripSpotActivity",(tripspots[i-1].Name))

                if(tripspots[0].Categoy == "문화시설") {
                    gyeongbokgung.text = tripspotList[0].Name
                    gyeongbokgung.text = tripspotList[1].Name
                    gyeongbokgung.text = tripspotList[2].Name
                    gyeongbokgung.text = tripspotList[3].Name
                }
            }

            override fun onFailure() {

            }
        })
        task.execute()











        /*
        selectHotelButton.setOnClickListener {
            val intent = Intent(this, HotelActivity::class.java)
            startActivity(intent)
        }*/
        selectHotelButton.setOnClickListener {
            var tripspotIntent = Intent(this,HotelActivity ::class.java)
            tripspotIntent.putExtra("list",tripspotList)
            startActivity(tripspotIntent)

        }
    }
}