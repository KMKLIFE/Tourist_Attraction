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

    }

}
