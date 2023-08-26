package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.data.NotgetGood
import com.example.myapplication.databinding.ActivityConfirmBinding

class ConfirmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityConfirmBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val confirm=intent.getSerializableExtra("confirm") as NotgetGood
        binding.confirmbianhao.text=confirm.uid.toString()
        binding.confirmprice.text=confirm.price.toString()
    }
}