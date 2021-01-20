package com.itpn.findyourbooks

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class IniciaSesionPersona : AppCompatActivity() {

    lateinit var c:Conexion
    lateinit var etEmail:EditText
    lateinit var etPassword:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicia_sesion_persona)

        c = Conexion(this,"Usuario",null,1)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
    }

    fun iniciaSesion(v: View){
        val op:SQLiteDatabase = c.readableDatabase
        val cursor:Cursor = op.rawQuery("SELECT correo_usuario, contrasena from usuario WHERE correo_usuario='${etEmail.text}' AND contrasena='${etPassword.text}' LIMIT 1",null)

        if (cursor.count == 1){
            intent = Intent(this,pantallaPrincipalPersona::class.java)
            startActivity(intent)
            cursor.close()
        }else{
            Toast.makeText(this,"Email o contraseña incorrecta",Toast.LENGTH_LONG).show()
        }
    }
}