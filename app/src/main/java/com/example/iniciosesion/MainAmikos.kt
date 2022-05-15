package com.example.iniciosesion

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.misamigos.ClickListener
import com.example.misamigos.adap
import com.example.misamigos.classdatos

class MainAmikos : AppCompatActivity() {
    private var recyclerView: RecyclerView?=null
    private var recyclerViewAdapter: adap?=null
    private var contacfriend = mutableListOf<classdatos>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_amikos)
        // se crea una arrae y se asignan variables para su futuro uso
        contacfriend = ArrayList()
        recyclerView = findViewById(R.id.reciclebb)as RecyclerView
        recyclerViewAdapter = adap(contacfriend)
        //Se crea variable par vista par los cuadros de amigos y se le asigna con los objetos ya creados
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager=layoutManager
        recyclerViewAdapter!!.setOnItemClickListener(object: ClickListener<classdatos> {
            override fun onItemClick(data: classdatos) {
                //Un intent explicito cuando se quiere llamar al numero de cada recycler
                val dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse("tel:"+data.number)
                startActivity(dialIntent)
            }
        })
        recyclerView!!.adapter=recyclerViewAdapter
        contacamiko()
    }
    private fun contacamiko(){
        // se crea una variable para ir agregandole la informacion.y asi susecibamente para los demas
        var amik = classdatos("Melvito",R.drawable.melvito,"74585452")
        contacfriend.add(amik)
        amik = classdatos("Chamba",R.drawable.chamba,"79251452")
        contacfriend.add(amik)
        amik = classdatos("Gabo",R.drawable.gabo,"85697412")
        contacfriend.add(amik)
        amik = classdatos("Manco",R.drawable.manco,"25476985")
        contacfriend.add(amik)
        amik = classdatos("jaime",R.drawable.jaima,"23547854")
        amik = classdatos("Diego",R.drawable.diego,"74124585")
        contacfriend.add(amik)
        amik = classdatos("Emely",R.drawable.emely,"79562845 ")
        contacfriend.add(amik)
        // el recicle notifica que la informacion ha sido cambiada
        recyclerViewAdapter?.notifyDataSetChanged()
    }
}