package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.Msg
import com.example.myapplication.data.networkdata.MessageData

class MsgAdapter(val msgList: List<MessageData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class LeftViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val leftMsg: TextView = view.findViewById(R.id.leftMsg)
    }
    inner class RightViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rightMsg: TextView = view.findViewById(R.id.rightMsg)
    }
    override fun getItemViewType(position: Int): Int {
        val data = msgList[position]
        if (data.type == "plaintext") {
            if (data.sendId == "me") {
                return Msg.TYPE_PLAINTEXT_SEND
            } else {
                return Msg.TYPE_PLAINTEXT_RECEIVED
            }
        } else {
            if (data.sendId == "me") {
                return Msg.TYPE_IMAGE_SEND
            } else {
                return Msg.TYPE_IMAGE_RECEIVED
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = if (viewType ==
        Msg.TYPE_IMAGE_RECEIVED) {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_left_item,
            parent, false)
        LeftViewHolder(view)
    } else {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_right_item,
            parent, false)
        RightViewHolder(view)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = msgList[position]
//        when (holder) {
//            is LeftViewHolder -> holder.leftMsg.text = msg.content
//            is RightViewHolder -> holder.rightMsg.text = msg.content
//            else -> throw IllegalArgumentException()
//        }
    }
    override fun getItemCount() = msgList.size
}