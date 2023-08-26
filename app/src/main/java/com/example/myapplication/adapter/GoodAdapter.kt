package com.example.myapplication.adapter

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
import com.example.myapplication.fragment.HomeFragment

class GoodAdapter(val context: HomeFragment, val goodList: List<Good>) :
    RecyclerView.Adapter<GoodAdapter.ViewHolder>() {
    lateinit var onItemClick: (Int) -> Unit


    fun setOnItemClickListener(onItemClick: (Int) -> Unit) {
        this.onItemClick= onItemClick
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val goodImage: ImageView = view.findViewById(R.id.goodImage)
        val goodTitle: TextView = view.findViewById(R.id.goodTitle)
        val goodGame:TextView=view.findViewById(R.id.goodGame)
        val goodPrice:TextView=view.findViewById(R.id.goodPrice)
        val sellId:TextView=view.findViewById(R.id.sell_id)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.good_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val good = goodList[position]
        holder.goodTitle.text = good.title
        holder.goodGame.text=good.game
        holder.goodPrice.text= good.price.toString()
        holder.sellId.text=good.sellerId.toString()
        Glide.with(context).load(good.pic).into(holder.goodImage)

        holder.itemView.setOnClickListener {
            val position=holder.adapterPosition
            onItemClick.invoke(position)

        }
    }

    override fun getItemCount() = goodList.size
}