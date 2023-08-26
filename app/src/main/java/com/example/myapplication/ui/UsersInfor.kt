package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.data.Good
import com.example.myapplication.databinding.ActivityUsersInforBinding

class UsersInfor : AppCompatActivity() {
    private lateinit var binding: ActivityUsersInforBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUsersInforBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        val good =intent.getSerializableExtra("good2") as Good//转换类型
        binding.nickname.text=good.sellerId.toString()
        binding.email.text="1564868@163.com"
        binding.uid.text=good.uid.toString()
        binding.userInfBool.text=good.state.toString()
        binding.time.text="2023.4.18"
    }
}