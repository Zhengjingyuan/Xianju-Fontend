package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.Good
import com.example.myapplication.data.NotgetGood
import com.example.myapplication.databinding.ActivityNotsenddetailBinding

class Notsenddetail : AppCompatActivity() {
    private lateinit var binding: ActivityNotsenddetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNotsenddetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val notsend =intent.getSerializableExtra("notsend") as NotgetGood//转换类型
        binding.notsendgood.text=notsend.title
        binding.notsendBaojia.text=notsend.price.toString()
        binding.notsendbianhao.text=notsend.uid.toString()
        binding.notsendprice.text=notsend.price.toString()
        binding.notsendPaytime.text=notsend.pay_time
        binding.buyerId.text=notsend.to_id.toString()
        Glide.with(this).load(notsend.pic).into(binding.notsendimage)
    }
}