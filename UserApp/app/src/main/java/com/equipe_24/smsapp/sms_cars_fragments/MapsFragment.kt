package com.equipe_24.smsapp.sms_cars_fragments

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.equipe_24.smsapp.Coordinates
import com.equipe_24.smsapp.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {
    private val Coors: Coordinates = Coordinates(36.6665227,2.9364004)

    private lateinit var mMap: GoogleMap

    val point = LatLng(Coors.lat, Coors.long)

    lateinit var textview : TextView

    private val callback = OnMapReadyCallback { googleMap ->
        mMap = googleMap

        textview.text = Coors.template

        mMap.addMarker(MarkerOptions().position(point).title("Marker in Douera"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point,10.0f))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_maps_sms_once, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?

        textview = view.findViewById<TextView>(R.id.coordinates)

        mapFragment?.getMapAsync(callback)
    }
}