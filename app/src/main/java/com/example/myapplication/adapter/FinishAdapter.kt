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
import com.example.myapplication.data.Good
import com.example.myapplication.data.NotgetGood


class FinishAdapter(val context: Context, val finishList: List<NotgetGood>) :
    RecyclerView.Adapter<FinishAdapter.ViewHolder>() {
    lateinit var onItemClick: (Int) -> Unit

    fun setOnItemClickListener(onItemClick: (Int) -> Unit) {
        this.onItemClick= onItemClick
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val finishImage: ImageView = view.findViewById(R.id.finishimage)
        val finishTitle: TextView = view.findViewById(R.id.finishgood)
        val finishPrice: TextView =view.findViewById(R.id.finishprice)
        val contract:Button=view.findViewById(R.id.contract_btn)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.finish_item, parent, false)
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