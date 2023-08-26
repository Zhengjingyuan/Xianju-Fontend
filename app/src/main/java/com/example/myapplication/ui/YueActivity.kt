package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityYueBinding

class YueActivity : AppCompatActivity() {
    lateinit var binding: ActivityYueBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityYueBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        binding.yue.text="2800.00"

    }
}