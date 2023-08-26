package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.Baojia
import com.example.myapplication.data.NotgetGood

class BaojiaAdapter(val baojiaList: List<Baojia>, val context: Context) :
    RecyclerView.Adapter<BaojiaAdapter.ViewHolder>() {


    lateinit var onItemRefuse:(Int)->Unit
    lateinit var onItemRecieve:(Int)->Unit


    fun setOnRefuse(onItemRefuse: (Int) -> Unit){
        this.onItemRefuse=onItemRefuse

    }

    fun setOnRecieve(onItemRecieve: (Int) -> Unit){
        this.onItemRecieve=onItemRecieve
    }



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val buyerid: TextView = view.findViewById(R.id.buyer_id)
        val baojiaprice: TextView = view.findViewById(R.id.baojia_price)
        val reciever: Button =view.findViewById(R.id.recieve_btn)
        val refuse: Button =view.findViewById(R.id.refuse_btn)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.baojia_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val baojia = baojiaList[position]
        holder.buyerid.text= baojia.from_user.toString()
        holder.baojiaprice.text=baojia.price.toString()


        holder.reciever.setOnClickListener {
            val position=holder.adapterPosition
            onItemRecieve.invoke(position)
        }

        holder.refuse.setOnClickListener {
            val position=holder.adapterPosition
            onItemRefuse.invoke(position)
        }



    }
    override fun getItemCount() = baojiaList.size

}