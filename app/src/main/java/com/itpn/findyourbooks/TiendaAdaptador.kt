package com.itpn.findyourbooks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import java.util.ArrayList

class TiendaAdaptador(var tiendas:ArrayList<TiendaClase>,
                        var cellClickListener:CellClickListener):
        Adapter<TiendaAdaptador.TiendaViewHolder>(){
                            inner class TiendaViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){
                                val textViewTienda: TextView = itemView.findViewById(R.id.tvTienda) as TextView
                                val textViewDireccion:TextView = itemView.findViewById(R.id.tvDireccionTienda) as TextView
                                val imageView:ImageView =itemView.findViewById(R.id.ivTienda) as ImageView
                            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TiendaViewHolder {
        val v:View =LayoutInflater.from(parent.context).inflate(R.layout.listatiendas,parent,false)
        return TiendaViewHolder(v)
    }

    override fun onBindViewHolder(holder: TiendaViewHolder, position: Int) {
        var tienda = tiendas[position]
        holder.imageView.setImageResource(tienda.imagen)
        holder.textViewTienda.text = tienda.nombreTienda
        holder.textViewDireccion.text = tienda.direccion

        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(data = tienda)
        }
    }

    override fun getItemCount(): Int {
        return tiendas.size
    }
}
