package com.itpn.findyourbooks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun iniciarActivityPersona(v: View){
        val intento : Intent = Intent(this,RegisterPerson::class.java)
        startActivity(intento)
    }

    fun iniciarActivityTienda(v:View){
        val intento : Intent = Intent(this,RegisterStore::class.java)
        startActivity(intento)
    }
}