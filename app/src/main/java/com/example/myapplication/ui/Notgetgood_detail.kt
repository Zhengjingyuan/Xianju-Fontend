package com.example.myapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.Good
import com.example.myapplication.data.NotgetGood
import com.example.myapplication.databinding.ActivityNotgetgoodDetailBinding

class Notgetgood_detail : AppCompatActivity() {
    private lateinit var binding: ActivityNotgetgoodDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNotgetgoodDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val notget =intent.getSerializableExtra("notget") as NotgetGood//转换类型
        binding.notgetbianhao.text=notget.uid.toString()
        binding.notgettime.text=notget.pay_time
        binding.notgetname.text=notget.title
        binding.notgetprice.text=notget.price.toString()
        binding.sellerId.text=notget.sellernickname

        Glide.with(this).load(notget.pic).into(binding.notgetimage)
        binding.refundBtn.setOnClickListener {
            val intent = Intent(this, Haverefund::class.java)
            intent.putExtra("uid", notget.uid)//强转
            intent.putExtra("price", notget.price)//强转
            startActivity(intent)
        }
    }
}