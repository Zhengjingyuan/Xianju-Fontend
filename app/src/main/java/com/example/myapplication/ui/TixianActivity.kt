package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.myapplication.databinding.ActivityTixianBinding

class TixianActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTixianBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTixianBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        binding.tixianBtn.setOnClickListener {
            tixian()
        }

    }

    private fun tixian() {
        val tixian=binding.tixian.text.toString()
        if(TextUtils.isEmpty(tixian)){
            Toast.makeText(this,"请输入提现金额", Toast.LENGTH_SHORT).show()
            return
        }
        requesttixian(tixian)
    }

    private fun requesttixian(tixian: String) {

    }
}