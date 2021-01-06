package com.itpn.findyourbooks

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Conexion(context: Context?,
               name: String?,
               factory: SQLiteDatabase.CursorFactory?,
               version: Int) :
        SQLiteOpenHelper(context, name, factory, version) {

            override fun onCreate(p0: SQLiteDatabase?) {
                p0?.execSQL("CREATE TABLE IF NOT EXISTS tema (idtema INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre_tema TEXT NOT NULL);")

                p0?.execSQL("CREATE TABLE IF NOT EXISTS autor (idautor INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,nombre_autor TEXT NOT NULl);")

                p0?.execSQL("CREATE TABLE IF NOT EXISTS libro (idlibro INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,titulo TEXT NOT NULL,tema_idtema INTEGER NOT NULL,autor_idautor INTEGER NOT NULL, CONSTRAINT fk_libro_tema FOREIGN KEY (tema_idtema) REFERENCES tema (idtema) ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT fk_libro_autor1 FOREIGN KEY (autor_idautor) REFERENCES autor (idautor) ON DELETE CASCADE ON UPDATE CASCADE);")

                p0?.execSQL("CREATE TABLE IF NOT EXISTS usuario (idusuario INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre_usuario TEXT NOT NULL,correo_usuario TEXT NOT NULL UNIQUE, contrasena TEXT NOT NULL, edad INTEGER NOT NULL);")

                p0?.execSQL("CREATE TABLE IF NOT EXISTS usuario_has_libro (usuario_idusuario INTEGER,libro_idlibro INTEGER NOT NULL, PRIMARY KEY (usuario_idusuario, libro_idlibro), CONSTRAINT fk_usuario_has_libro_usuario1 FOREIGN KEY (usuario_idusuario) REFERENCES usuario (idusuario) ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT fk_usuario_has_libro_libro1 FOREIGN KEY (libro_idlibro) REFERENCES libro (idlibro) ON DELETE CASCADE ON UPDATE CASCADE);")

                p0?.execSQL("CREATE TABLE IF NOT EXISTS tienda (idtienda INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre_tienda TEXT NOT NULL, direccion TEXT NOT NULL);")
            }

            override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

            }
}