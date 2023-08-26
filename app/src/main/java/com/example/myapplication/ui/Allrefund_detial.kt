package com.example.myapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.NotgetGood
import com.example.myapplication.databinding.ActivityAllrefundDetialBinding
import com.example.myapplication.databinding.ActivityNotgetgoodDetailBinding

class Allrefund_detial : AppCompatActivity() {
    private lateinit var binding: ActivityAllrefundDetialBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAllrefundDetialBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val allrefund =intent.getSerializableExtra("allrefund") as NotgetGood//转换类型
        binding.allrefundbianhao.text=allrefund.uid.toString()
        binding.allrefundmoney.text=allrefund.price.toString()
        binding.allrefundname.text=allrefund.title
        binding.allrefundprice.text=allrefund.price.toString()
        binding.sellerId.text=allrefund.sellernickname

        Glide.with(this).load(allrefund.pic).into(binding.allrefundimage)

    }
}