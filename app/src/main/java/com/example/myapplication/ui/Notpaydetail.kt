package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.NotgetGood
import com.example.myapplication.databinding.ActivityNotpaydetailBinding

class Notpaydetail : AppCompatActivity() {
    private lateinit var binding: ActivityNotpaydetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNotpaydetailBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        val notpay =intent.getSerializableExtra("notpay") as NotgetGood//转换类型
        binding.sellerId.text=notpay.sellernickname
        binding.notpaygood.text=notpay.title
        binding.notpayprice.text=notpay.price.toString()
        Glide.with(this).load(notpay.pic).into(binding.notpayimage)
    }
}