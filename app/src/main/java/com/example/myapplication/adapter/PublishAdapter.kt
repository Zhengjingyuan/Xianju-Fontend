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

class PublishAdapter(val publishList: List<NotgetGood>, val context: Context) :
    RecyclerView.Adapter<PublishAdapter.ViewHolder>() {

    lateinit var onItemClick: (Int) -> Unit
    lateinit var onItemRefund:(Int)->Unit
    lateinit var onItemConfirm:(Int)->Unit

    fun setOnItemClickListener(onItemClick: (Int) -> Unit) {
        this.onItemClick= onItemClick
    }

    fun setOnDelet(onItemRefund: (Int) -> Unit){
        this.onItemRefund=onItemRefund

    }

    fun setOnCprice(onItemConfirm: (Int) -> Unit){
        this.onItemConfirm=onItemConfirm
    }



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val goodid: TextView = view.findViewById(R.id.publishgood)
        val publishstate: TextView = view.findViewById(R.id.publishstate)
        val publishprice: TextView = view.findViewById(R.id.publishprice)
        val publishimage: ImageView =view.findViewById(R.id.publishimage)
        val delete: Button =view.findViewById(R.id.delet_btn)
        val changeprice: Button =view.findViewById(R.id.changemoney_btn)
        val changeinfor: Button =view.findViewById(R.id.changeinfor_btn)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.publish_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val publish = publishList[position]
        holder.goodid.text= publish.title
        holder.publishstate.text=publish.state
        holder.publishprice.text=publish.price.toString()
        Glide.with(context).load(publish.pic).into(holder.publishimage)

        holder.itemView.setOnClickListener {
            val position=holder.adapterPosition
            onItemClick.invoke(position)

        }
        holder.delete.setOnClickListener {
            val position=holder.adapterPosition
            onItemRefund.invoke(position)
        }

        holder.changeprice.setOnClickListener {
            val position=holder.adapterPosition
            onItemConfirm.invoke(position)
        }



    }
    override fun getItemCount() = publishList.size

}