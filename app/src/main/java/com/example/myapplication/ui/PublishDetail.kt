package com.example.myapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.NotgetGood
import com.example.myapplication.databinding.ActivityPublishDetailBinding

class PublishDetail : AppCompatActivity() {
    private lateinit var binding: ActivityPublishDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPublishDetailBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        val publish =intent.getSerializableExtra("publish") as NotgetGood//转换类型
        binding.publishgood.text=publish.title
        binding.publishbianhao.text=publish.uid.toString()
        binding.publishprice.text=publish.price.toString()
        binding.publishstate.text=publish.state
        Glide.with(this).load(publish.pic).into(binding.publishimage)
        binding.checkbaojiaBtn.setOnClickListener {
            val intent= Intent(this, BaojiaActivity::class.java)
            intent.putExtra("baojia", publish.good_id)//强转
            startActivity(intent)
        }
    }
}