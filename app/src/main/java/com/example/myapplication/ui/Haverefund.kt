package com.example.myapplication.ui

import android.content.ContentValues.TAG
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.data.NotgetGood
import com.example.myapplication.databinding.ActivityHaverefundBinding

class Haverefund : AppCompatActivity() {
    private lateinit var binding: ActivityHaverefundBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHaverefundBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val uid:Int=intent.getIntExtra("uid",0)
        val price:Float=intent.getFloatExtra("price",0f)
        binding.refundprice.text=price.toString()
        binding.refundbianhao.text=uid.toString()
        Log.d(TAG, "onResponse: result = $uid"+price)
    }
}