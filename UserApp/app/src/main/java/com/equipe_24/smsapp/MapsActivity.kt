package com.equipe_24.smsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private val Coors: Coordinates = Coordinates(36.6665227,2.9364004)

    private lateinit var mMap: GoogleMap

    val point = LatLng(Coors.lat, Coors.long)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // SMS once Screen
        setContentView(R.layout.activity_maps_sms_once)
        // SMS periode Screen
        // setContentView(R.layout.activity_maps_sms_periode)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        findViewById<TextView>(R.id.coordinates).text = Coors.template

        mMap.addMarker(MarkerOptions().position(point).title("Marker in Douera"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point,10.0f))
    }
}