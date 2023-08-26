package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.networkdata.chatlist2
import com.example.myapplication.fragment.ContractFragment

class ChatAdapter(val chatList: List<chatlist2>, val context: ContractFragment) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {


    lateinit var onItemClick: (Int) -> Unit


    fun setOnItemClickListener(onItemClick: (Int) -> Unit) {
        this.onItemClick= onItemClick
    }



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val chatuser: TextView = view.findViewById(R.id.chatid)
        val chatprofile: ImageView= view.findViewById(R.id.chatprofile)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chat = chatList[position]
        holder.chatuser.text= chat.user_id.toString()
        Glide.with(context).load(chat.profile).into(holder.chatprofile)

        holder.itemView.setOnClickListener {
            val position=holder.adapterPosition
            onItemClick.invoke(position)

        }




    }
    override fun getItemCount() = chatList.size

}