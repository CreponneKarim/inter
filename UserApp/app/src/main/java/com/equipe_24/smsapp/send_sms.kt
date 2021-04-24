package com.equipe_24.smsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.equipe_24.smsapp.sms_cars_fragments.periode_fragment

class send_sms : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.send_sms)


        var periode_fragment = periode_fragment()

        var button_click = findViewById<Button>(R.id.button_periodique)
        var button_two = findViewById<Button>(R.id.button_une_fois)

        button_click.setOnClickListener {
            periode_fragment.show(supportFragmentManager, "TAG")
        }
        button_two.setOnClickListener{
            showActivity2()
        }
    }

    private fun showActivity2(){
        var intent_ = Intent(this,add_user_activity::class.java)
        startActivity(intent_)
    }
}