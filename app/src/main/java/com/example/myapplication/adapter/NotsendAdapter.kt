package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.Good
import com.example.myapplication.data.NotgetGood
import com.example.myapplication.fragment.HomeFragment

class NotsendAdapter(val context: Context, val notsendList: List<NotgetGood>) :
    RecyclerView.Adapter<NotsendAdapter.ViewHolder>() {
    lateinit var onItemClick: (Int) -> Unit

    fun setOnItemClickListener(onItemClick: (Int) -> Unit) {
        this.onItemClick= onItemClick
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val notsendImage: ImageView = view.findViewById(R.id.notsendimage)
        val notsendTitle: TextView = view.findViewById(R.id.notsendgood)
        val notsendPrice: TextView =view.findViewById(R.id.notsendprice)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notsend_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notsend = notsendList[position]
        holder.notsendTitle.text = notsend.title
        holder.notsendPrice.text= notsend.price.toString()
        Glide.with(context).load(notsend.pic).into(holder.notsendImage)

        holder.itemView.setOnClickListener {
            val position=holder.adapterPosition
            onItemClick.invoke(position)

        }
    }

    override fun getItemCount() = notsendList.size
}