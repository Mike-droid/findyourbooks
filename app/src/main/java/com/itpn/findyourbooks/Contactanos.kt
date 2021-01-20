package com.itpn.findyourbooks

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class Contactanos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contactanos)

        val botonLlamada:TextView
        botonLlamada = findViewById(R.id.phoneNumber)
        botonLlamada.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:8787908628"))
            startActivity(callIntent)
        }

        val botonCorreo:TextView
        botonCorreo = findViewById(R.id.emailContacto)
        botonCorreo.setOnClickListener {
            Log.i("Enviar email", "")

            val TO = arrayOf("miguelreyesmoreno@gmail.com")
            val CC = arrayOf("xyz@gmail.com")
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.data = Uri.parse("mailto:")
            emailIntent.type = "text/plain"

            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO)
            emailIntent.putExtra(Intent.EXTRA_CC, CC)
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Tema")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Me parece una excelente app, se merece un 100 de calificación")

            try {
                startActivity(Intent.createChooser(emailIntent, "Send mail..."))
                finish()
                Log.i("Finished sending email...", "")
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(
                    this,
                    "There is no email client installed.", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}