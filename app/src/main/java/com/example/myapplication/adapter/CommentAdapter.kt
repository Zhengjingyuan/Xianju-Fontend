package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.Baojia
import com.example.myapplication.data.Comment


class CommentAdapter(val commentList: List<Comment>, val context: Context) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {


    lateinit var onItemClick: (Int) -> Unit

    fun setOnItemClickListener(onItemClick: (Int) -> Unit) {
        this.onItemClick= onItemClick
    }



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val buyerid: TextView = view.findViewById(R.id.buyer_id)
        val commentuid: TextView = view.findViewById(R.id.commentbianhao)
        val star: RatingBar = view.findViewById(R.id.star)
        val commentdetail: TextView =view.findViewById(R.id.comment_detail)
        val commentimage: ImageView=view.findViewById(R.id.commentimage)
        val commenttouxiang: ImageView=view.findViewById(R.id.commenttouxiang)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.comment_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = commentList[position]
        holder.buyerid.text= comment.nickname
        holder.star.rating= comment.star.toFloat()
        holder.commentuid.text=comment.uid.toString()
        holder.commentdetail.text=comment.detail
        Glide.with(context).load(comment.commentPic).into(holder.commentimage)
        Glide.with(context).load(comment.profile).into(holder.commenttouxiang)


        holder.itemView.setOnClickListener {
            val position=holder.adapterPosition
            onItemClick.invoke(position)
        }




    }
    override fun getItemCount() = commentList.size

}