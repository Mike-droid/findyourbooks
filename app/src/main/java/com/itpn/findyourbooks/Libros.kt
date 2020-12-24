package com.itpn.findyourbooks

class Libros {
    var id:Int = 0
    var titulo:String = ""
    var id_autor:Int = 0
    var id_tema:Int = 0

    constructor(id:Int, titulo:String, id_autor:Int, id_tema:Int){
        this.id = id
        this.titulo = titulo
        this.id_autor = id_autor
        this.id_tema = id_tema
    }
}