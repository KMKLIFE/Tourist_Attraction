package com.e.myapplication

import android.os.AsyncTask
import android.util.JsonReader
import android.util.Log
import com.example.mapsactivity.TripSpotVO
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ConnectServerAsyncTask(private val mCallBack: AsyncTaskCallback?) : AsyncTask<Void?, Void?, Void?>() {
    private val mException: Exception? = null
    val tripspots = ArrayList<TripSpotVO>()

    override fun doInBackground(vararg params: Void?): Void? {
        val urlString = "http://10.10.16.57:8081/TripSpot"
        val url = URL(urlString)

        val urlConnection = url.openConnection() as HttpURLConnection
        if (urlConnection.responseCode == 200) {
            val responseBody = urlConnection.inputStream
            val responseBodyReader = InputStreamReader(responseBody, "UTF-8")
            val jsonReader = JsonReader(responseBodyReader)

            jsonReader.beginArray() // Start processing the JSON object
            while (jsonReader.hasNext()) {
                tripspots.add(readTripSpotMessage(jsonReader))
                Log.d("TripSpotActivity", "관광지리스트 추가됨")
            }
            jsonReader.endArray()

        } else {
            // Error handling code goes here
            Log.d("TripSpotActivity", "관광지서버연결에러")
        }

        return null
    }

    override fun onPostExecute(aVoid: Void?) {
        super.onPostExecute(aVoid)
        if (mCallBack != null) {
            mCallBack.onSuccess( tripspots )
        } else {
            mCallBack!!.onFailure()
        }
    }


    @Throws(IOException::class)
    fun readTripSpotMessage(reader: JsonReader): TripSpotVO {
        var tripspot = TripSpotVO()
        reader.beginObject()
        while (reader.hasNext()) {
            val name = reader.nextName()
            if (name == "tripSpotId") {
                tripspot.TripSpotId= reader.nextInt()
            } else if (name == "category") {
                tripspot.Category = reader.nextString()
            } else if (name == "name" ) {
                tripspot.Name = reader.nextString()
            } else if (name == "locationId") {
                tripspot.LocationId = reader.nextInt()
            } else if (name == "openTime"){
                tripspot.OpenTime = reader.nextString()
            } else if (name == "closeTime"){
                tripspot.CloseTime = reader.nextString()
            }else if (name == "entranceFee"){
                tripspot.EntranceFee = reader.nextInt()
            }else if (name == "starRating"){
                tripspot.StarRating = reader.nextDouble()
            }else if (name == "coordinate_x"){
                tripspot.Coordinate_x = reader.nextLong()
            }else if (name == "coordinate_y"){
                tripspot.Coordinate_y = reader.nextLong()
            }else if (name == "locationName"){
                tripspot.LocationName = reader.nextString()
            }else {
                reader.skipValue()
                Log.d("CourseActivity", "skip $name")
            }
        }
        reader.endObject()
        return tripspot
    }
}
