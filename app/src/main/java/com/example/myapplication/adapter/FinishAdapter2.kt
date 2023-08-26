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

class FinishAdapter2(val context: Context, val finishList: List<NotgetGood>) :
    RecyclerView.Adapter<FinishAdapter2.ViewHolder>() {
    lateinit var onItemClick: (Int) -> Unit

    fun setOnItemClickListener(onItemClick: (Int) -> Unit) {
        this.onItemClick= onItemClick
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val finishImage: ImageView = view.findViewById(R.id.finishimage)
        val finishTitle: TextView = view.findViewById(R.id.finishgood)
        val finishPrice: TextView =view.findViewById(R.id.finishprice)


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.finish2_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val finish = finishList[position]
        holder.finishTitle.text = finish.title
        holder.finishPrice.text= finish.price.toString()
        Glide.with(context).load(finish.pic).into(holder.finishImage)
        holder.itemView.setOnClickListener {
            val position=holder.adapterPosition
            onItemClick.invoke(position)
        }

    }

    override fun getItemCount() = finishList.size
}