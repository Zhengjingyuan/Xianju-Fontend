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
import com.example.myapplication.data.NotgetGood

class NopayAdapter(val context: Context, val notpayList: List<NotgetGood>) :
    RecyclerView.Adapter<NopayAdapter.ViewHolder>() {
    lateinit var onItemClick: (Int) -> Unit

    fun setOnItemClickListener(onItemClick: (Int) -> Unit) {
        this.onItemClick= onItemClick
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nopayImage: ImageView = view.findViewById(R.id.nopayimage)
        val nopayTitle: TextView = view.findViewById(R.id.nopaygood)
        val nopayPrice: TextView =view.findViewById(R.id.nopayprice)
        val nopayState: TextView =view.findViewById(R.id.nopaystate)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.nopay_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nopay = notpayList[position]
        holder.nopayTitle.text = nopay.title
        holder.nopayPrice.text= nopay.price.toString()
        holder.nopayState.text=nopay.state
        Glide.with(context).load(nopay.pic).into(holder.nopayImage)

        holder.itemView.setOnClickListener {
            val position=holder.adapterPosition
            onItemClick.invoke(position)

        }
    }

    override fun getItemCount() = notpayList.size
}