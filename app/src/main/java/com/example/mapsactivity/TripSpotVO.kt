package com.example.mapsactivity

import java.io.Serializable

class TripSpotVO : Serializable{
    var TripSpotId = 0
    var Category = ""
    var Name = ""
    var LocationId = 0
    var OpenTime = ""
    var CloseTime = ""
    var EntranceFee = 0
    var StarRating = 0.0
    var Coordinate_x = 0L
    var Coordinate_y = 0L
    var isChecked = false

    var LocationName = ""
}