package com.itpn.findyourbooks

import android.app.Dialog
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

interface CellClickListener {
    fun onCellClickListener(data:TiendaClase)
}

class Tienda : AppCompatActivity(), CellClickListener {
    var tiendaLista : ArrayList<TiendaClase> = ArrayList()
    lateinit var tvNombreTienda : TextView
    lateinit var tvDireccionTienda : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tienda)
        val listaTiendas : RecyclerView = findViewById(R.id.rvTiendas)
        listaTiendas.layoutManager =LinearLayoutManager(this)
        listaTiendas.adapter =TiendaAdaptador(tiendaLista,this)

        llenarListaTienda()
    }

    fun llenarListaTienda(){
        for (i in 1..50){
            var c = Conexion(this,"biblioteca",null,1)
            val op1: SQLiteDatabase = c.readableDatabase
            val op2: SQLiteDatabase = c.readableDatabase
            val cursor1: Cursor = op1.rawQuery("SELECT `nombre_tienda` FROM `tienda` WHERE `idtienda` = $i LIMIT 1",null)
            val cursor2: Cursor = op2.rawQuery("SELECT `direccion` FROM `tienda` WHERE `idtienda` = $i LIMIT 1",null)
            cursor1.moveToFirst()
            cursor2.moveToFirst()
            var resultado1:String = cursor1.getString(0)
            var resultado2:String = cursor2.getString(0)

            val openDialog = Dialog(this)
            openDialog.setContentView(R.layout.listatiendas)

            tvNombreTienda = openDialog.findViewById(R.id.tvTienda)
            tvDireccionTienda = openDialog.findViewById(R.id.tvDireccionTienda)

            tvNombreTienda.text = resultado1
            tvDireccionTienda.text = resultado2
            tiendaLista.add(TiendaClase(tvNombreTienda.text as String, tvDireccionTienda.text as String,R.drawable.ic_baseline_storefront_24))
            op1.close()
            op2.close()
        }
    }

    override fun onCellClickListener(data: TiendaClase) {
        TODO("Not yet implemented")
    }
}