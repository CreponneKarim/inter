package com.equipe_24.smsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.equipe_24.smsapp.sms_cars_fragments.AddCarSms
import com.equipe_24.smsapp.sms_cars_fragments.CarsListSms
import com.equipe_24.smsapp.sms_cars_fragments.MapsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_container_sms)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_sms,CarsListSms()).commit()
    }
}