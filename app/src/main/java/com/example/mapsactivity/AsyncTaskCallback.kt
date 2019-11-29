package com.e.myapplication

import com.example.mapsactivity.TripSpotVO
import java.util.ArrayList

interface AsyncTaskCallback {
    fun onSuccess(tripspots: ArrayList<TripSpotVO>)
    fun onFailure()
}