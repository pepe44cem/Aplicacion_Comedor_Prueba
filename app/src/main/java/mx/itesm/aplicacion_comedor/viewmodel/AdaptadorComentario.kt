package mx.itesm.aplicacion_comedor.viewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.itesm.aplicacion_comedor.R
import mx.itesm.aplicacion_comedor.databinding.RenglonComentarioBinding
import mx.itesm.aplicacion_comedor.model.others.Comentario

/**
 * Autor: Alonso Segura
 * Clase que sirve como adaptador para llenar los comentarios que se mostraran en la seccion de
 * graficas.
 */

class AdaptadorComentario (private val contexto: Context, var arrComentarios: Array<Comentario>)
    : RecyclerView.Adapter<AdaptadorComentario.RenglonComentario>(){

    class RenglonComentario (var vistaRenglon: View): RecyclerView.ViewHolder(vistaRenglon){
        fun set(comentario: Comentario){
            vistaRenglon.findViewById<TextView>(R.id.fecha).text=comentario.fecha
            vistaRenglon.findViewById<TextView>(R.id.contenido).text=comentario.contenido
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RenglonComentario {
        val binding = RenglonComentarioBinding.inflate(LayoutInflater.from(contexto))
        return RenglonComentario(binding.root)
    }

    override fun getItemCount(): Int {
        return arrComentarios.size
    }

    override fun onBindViewHolder(holder: RenglonComentario, position: Int) {
        val comentario = arrComentarios[position]
        holder.set(comentario)
    }
}


