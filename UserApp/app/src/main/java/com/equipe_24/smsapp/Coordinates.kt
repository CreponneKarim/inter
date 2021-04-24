package com.equipe_24.smsapp

data class Coordinates(
    var lat: Double = 0.0,
    var long: Double = 0.0,
    var template: String= "Latitude : ${lat.toString()}\nLongitude : ${long.toString()}"
)