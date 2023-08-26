package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityCpriceBinding

class CpriceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCpriceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCpriceBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

    }
}