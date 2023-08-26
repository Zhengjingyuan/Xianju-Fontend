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

class AllRefundAdapter(val allrefundList: List<NotgetGood>, val context: Context) :
    RecyclerView.Adapter<AllRefundAdapter.ViewHolder>() {

    lateinit var onItemClick: (Int) -> Unit


    fun setOnItemClickListener(onItemClick: (Int) -> Unit) {
        this.onItemClick= onItemClick
    }





    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val goodid: TextView = view.findViewById(R.id.allrefundgood)
        val allrefundprice: TextView = view.findViewById(R.id.allrefundprice)
        val allrefundimage: ImageView =view.findViewById(R.id.allrefundimage)
        val xiangqing: Button =view.findViewById(R.id.xiangqing)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.allrefund_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val allfund = allrefundList[position]
        holder.goodid.text= allfund.title
        holder.allrefundprice.text=allfund.price.toString()
        Glide.with(context).load(allfund.pic).into(holder.allrefundimage)

        holder.xiangqing.setOnClickListener {
            val position=holder.adapterPosition
            onItemClick.invoke(position)

        }




    }
    override fun getItemCount() = allrefundList.size

}