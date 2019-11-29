package com.e.myapplication

import com.example.mapsactivity.TripSpotVO
import java.util.ArrayList

interface AsyncTaskCallback {
    fun onSuccess(hotels: ArrayList<TripSpotVO>)
    fun onFailure()
}