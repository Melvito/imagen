package com.example.misamigos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.iniciosesion.R

// se crea una clase contructor y se le crea un adaptador y la variable clicklistener
class adap constructor(private val listfriend:List<classdatos>):
RecyclerView.Adapter<adap.MyViewHolder>() {
    private var clickListener: ClickListener<classdatos>? = null
    // se crea una funcion para llenar  con info el layout de el recicleview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adap.MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.resicleview, parent, false)
        return MyViewHolder(view)
    }
  //se crea otra funcion para asignarle la informacion en la anterior funcion
    override fun onBindViewHolder(holder: adap.MyViewHolder, position: Int) {
        val amikum = listfriend[position]
        holder.title.text = amikum.name
        holder.imgbro.setBackgroundResource(amikum.fotito)
        holder.card.setOnClickListener { clickListener!!.onItemClick(amikum) }
        holder.des.text = "${amikum.number}"
    }
//con esta funcion se retorna el tamanio de la lista
    override fun getItemCount(): Int {
        return listfriend.size
    }
    // se le asifna valor a la clickloistener
    fun setOnItemClickListener(amikoClickListener: ClickListener<classdatos>){
        clickListener = amikoClickListener
    }
// se crea una clase interna la cual se asignan las variables con los intem que se encuentran en el recicleview
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.nombre)
        val imgbro: ImageView = itemView.findViewById(R.id.imgamigo)
        val des: TextView = itemView.findViewById(R.id.description)
        val card: CardView = itemView.findViewById(R.id.carView)

    }

}
// esta es la interface para el; clicklistener
interface ClickListener <T>{
    fun onItemClick(data:T)
}