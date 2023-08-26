package com.example.myapplication.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.Good
import com.example.myapplication.databinding.ActivityGoodDteilBinding

import de.hdodenhof.circleimageview.CircleImageView

class GoodDteil : AppCompatActivity() {
    lateinit var binding: ActivityGoodDteilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_good_dteil)
        binding=ActivityGoodDteilBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        val good =intent.getSerializableExtra("good") as Good//转换类型
        binding.selliddt.text=good.title
        binding.sellgamedt.text=good.game
        binding.selltext.text=good.detail
        val toid=good.sellerId
        Glide.with(this).load(good.pic).into(binding.sellimagedt)
        binding.checkcomment.setOnClickListener {
            val intent= Intent(this, AllcommentActivity::class.java)
            intent.putExtra("comment", toid)//强转
            startActivity(intent)
        }
        binding.checkseller.setOnClickListener {
            val intent= Intent(this, UsersInfor::class.java)
            intent.putExtra("good2", good)//强转
            startActivity(intent)
        }

    }
}