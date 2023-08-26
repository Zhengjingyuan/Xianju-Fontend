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
import com.example.myapplication.data.NotgetGood


class NotGetAdapter( val notgetList: List<NotgetGood>,val context:Context) :
    RecyclerView.Adapter<NotGetAdapter.ViewHolder>() {

    lateinit var onItemClick: (Int) -> Unit
    lateinit var onItemRefund:(Int)->Unit
    lateinit var onItemConfirm:(Int)->Unit

    fun setOnItemClickListener(onItemClick: (Int) -> Unit) {
        this.onItemClick= onItemClick
    }

    fun setOnRefund(onItemRefund: (Int) -> Unit){
        this.onItemRefund=onItemRefund

    }

    fun setOnConfirm(onItemConfirm: (Int) -> Unit){
        this.onItemConfirm=onItemConfirm
    }



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val goodid: TextView = view.findViewById(R.id.notgetgood)
        val notgetstate: TextView = view.findViewById(R.id.notgetstate)
        val notgetprice: TextView = view.findViewById(R.id.notgetprice)
        val notgetimage:ImageView=view.findViewById(R.id.notgetimage)
        val refund:Button=view.findViewById(R.id.refund)
        val confirm:Button=view.findViewById(R.id.confirm)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notget_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notget = notgetList[position]
        holder.goodid.text= notget.title
        holder.notgetstate.text=notget.state
        holder.notgetprice.text=notget.price.toString()
        Glide.with(context).load(notget.pic).into(holder.notgetimage)

        holder.itemView.setOnClickListener {
            val position=holder.adapterPosition
            onItemClick.invoke(position)

        }
        holder.refund.setOnClickListener {
            val position=holder.adapterPosition
            onItemRefund.invoke(position)
        }

        holder.confirm.setOnClickListener {
            val position=holder.adapterPosition
            onItemConfirm.invoke(position)
        }



    }
    override fun getItemCount() = notgetList.size

}