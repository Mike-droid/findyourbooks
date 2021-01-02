package com.itpn.findyourbooks

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class RegisterPerson : AppCompatActivity() {

    lateinit var c:Conexion
    lateinit var etEmail:EditText
    lateinit var etNombre:EditText
    lateinit var etContrasena1:EditText
    lateinit var etContrasena2:EditText
    lateinit var etEdad:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_person)

        c = Conexion(this,"Usuario",null,1)

        etEmail = findViewById(R.id.etEmail)
        etNombre = findViewById(R.id.etName)
        etContrasena1 = findViewById(R.id.etPassword)
        etContrasena2 = findViewById(R.id.etRepeatPassword)
        etEdad = findViewById(R.id.etAge)
    }

    fun abrirPrincipalPersona(v:View){

        //----------------MÉTODO PARA INSERTAR PERSONA EN BBDD----------------------

        if (etContrasena1.text.toString() == etContrasena2.text.toString()){

            val op:SQLiteDatabase = c.writableDatabase

            val instruccion = "INSERT INTO usuario (nombre_usuario,correo_usuario,contrasena,edad) VALUES ('${etNombre.text}' , '${etEmail.text}' , '${etContrasena1.text}' , '${etEdad.text}')"

            op.execSQL(instruccion)
            op.close()

            Toast.makeText(this,"Te has registrado con éxito",Toast.LENGTH_LONG).show()

            intent = Intent(this,pantallaPrincipalPersona::class.java)
            startActivity(intent)

        }else{
            Toast.makeText(this,"Las contraseñas deben coincidir",Toast.LENGTH_SHORT).show()
        }
    }

    fun abrirIniciaSesionPersona(v:View){
        intent = Intent(this,IniciaSesionPersona::class.java)
        startActivity(intent)
    }
}