package com.itpn.findyourbooks

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

lateinit var botonLlamada:TextView

class Contactanos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contactanos)

        botonLlamada = findViewById(R.id.phoneNumber)
        botonLlamada.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL , Uri.parse("tel:8781154800"))
            startActivity(callIntent)
        }
    }
}