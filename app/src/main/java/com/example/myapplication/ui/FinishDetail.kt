package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.Good
import com.example.myapplication.data.NotgetGood
import com.example.myapplication.databinding.ActivityFinishDetailBinding
import com.example.myapplication.databinding.ActivityFinishDtail2Binding

class FinishDetail : AppCompatActivity() {
    private lateinit var binding: ActivityFinishDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFinishDetailBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        val finish =intent.getSerializableExtra("finish") as NotgetGood//转换类型
        binding.sellerId.text=finish.sellernickname
        binding.finishbianhao.text=finish.uid.toString()
        binding.finishgood.text=finish.title
        binding.finishprice.text=finish.price.toString()
        binding.finishpaytime.text=finish.pay_time
        Glide.with(this).load(finish.pic).into(binding.finishimage)

    }
}