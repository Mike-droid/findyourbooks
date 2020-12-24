package com.itpn.findyourbooks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class RegisterPerson : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_person)
    }

    fun abrirPrincipalPersona(v:View){
        intent = Intent(this,pantallaPrincipalPersona::class.java)
        startActivity(intent)
    }

    fun abrirIniciaSesionPersona(v:View){
        intent = Intent(this,IniciaSesionPersona::class.java)
        startActivity(intent)
    }
}