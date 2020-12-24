package com.itpn.findyourbooks

class Persona {
    var id:Int = 0
    var nombre:String = ""
    var email:String = ""
    var contrasena:String = ""
    var edad:Int = 0

    constructor(id:Int, nombre:String, email:String, contrasena:String, edad:Int){
        this.id = id
        this.nombre = nombre
        this.email = email
        this.contrasena = contrasena
        this.edad = edad
    }
}