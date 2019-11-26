package com.trip

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL


class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        RegisterTextView.text = "gogogogogo"
        restTest()




        /*
        AsyncTask.execute {
            val githubEndpoint = URL("http://localhost:8081/hotel")
            val myConnection = githubEndpoint.openConnection() as HttpsURLConnection

            /*
            val httpbinEndpoint = URL("https://httpbin.org/post")
            val myConnection = httpbinEndpoint.openConnection() as HttpsURLConnection
            myConnection.requestMethod = "POST"
            // Create the data
            val myData = "message=Hello"
            // Enable writing
            myConnection.doOutput = true
            // Write the data
            myConnection.outputStream.write(myData.toByteArray())

             */


            //myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1")
            //myConnection.setRequestProperty("Accept", "application/vnd.github.v3+json")
            //myConnection.setRequestProperty("Contact-Me", "hathibelagal@example.com")


            if (myConnection.getResponseCode() == 200) {
                // Success
                // Further processing here
            } else {
                // Error handling code goes here
            }
            val responseBody = myConnection.inputStream
            val responseBodyReader = InputStreamReader(responseBody, "UTF-8")
            val jsonReader = JsonReader(responseBodyReader)

            jsonReader.beginObject() // Start processing the JSON object
            while (jsonReader.hasNext()) { // Loop through all keys
                val key = jsonReader.nextName() // Fetch the next key
                if (key == "name") { // Check if desired key
                    // Fetch the value as a String
                    val value = jsonReader.nextString()
                    RegisterTextView.text = value
                    // Do something with the value
                    // ...

                    break // Break out of the loop
                } else {
                    jsonReader.skipValue() // Skip values of other keys
                }
            }
            jsonReader.close()
            myConnection.disconnect()


        }

         */
    }

    private fun restTest(){
        val urlString = "http://10.10.16.57:8081/hotel"
        val url = URL(urlString)
        val urlConnection = url.openConnection() as HttpURLConnection

        val inputStream = BufferedInputStream(urlConnection.inputStream)
        val json = JSONObject(getStringFromInputStream(inputStream))

        //w.setTemprature(json.getJSONObject("main").getInt("temp"))

        RegisterTextView.text = json.getString("name")





    }

    private fun getStringFromInputStream(`is`: InputStream): String {
        var br: BufferedReader? = null
        val sb = StringBuilder()
        var line: String
        try {
            br = BufferedReader(InputStreamReader(`is`))
            //while ((line = br.readLine()) != null) {
            while (true) {
                line = br.readLine()
                if( line == null )
                    break
                sb.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (br != null) {
                try {
                    br.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
        return sb.toString()
    }
}
