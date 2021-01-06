package com.itpn.findyourbooks

class TiendaClase {
    var idtienda:Int=0
    var nombreTienda:String=""
    var direccion:String=""
    var imagen:Int = 0

    constructor(idtienda:Int, nombreTienda:String, direccion:String){
        this.idtienda = idtienda
        this.nombreTienda = nombreTienda
        this.direccion = direccion
    }

    constructor(idtienda:Int, nombreTienda:String, direccion:String, imagen:Int){
        this.idtienda = idtienda
        this.nombreTienda = nombreTienda
        this.direccion = direccion
        this.imagen = imagen
    }

    constructor(nombreTienda:String, direccion:String, imagen:Int){
        this.nombreTienda = nombreTienda
        this.direccion = direccion
        this.imagen = imagen
    }
}