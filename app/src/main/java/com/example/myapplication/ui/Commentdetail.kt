package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.myapplication.data.Comment
import com.example.myapplication.databinding.ActivityCommentdetailBinding

class Commentdetail : AppCompatActivity() {
    private lateinit var binding: ActivityCommentdetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCommentdetailBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        val comment=intent.getSerializableExtra("comment")as Comment
        binding.commentdetail.text=comment.detail
        binding.commentbianhao.text=comment.uid.toString()
        binding.commenttime.text=comment.time
        binding.star.rating= comment.star.toFloat()
        Glide.with(this).load(comment.commentPic).into(binding.commentimage)




    }
}