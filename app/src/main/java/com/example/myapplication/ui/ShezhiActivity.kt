package com.example.myapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityShezhiBinding

class ShezhiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShezhiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityShezhiBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        binding.idbtn.setOnClickListener {
            val intent = Intent(this, IdentifyActivity::class.java)
            startActivity(intent)
        }
        binding.renamebtn.setOnClickListener {
            val intent = Intent(this, RenameActivity::class.java)
            startActivity(intent)
        }
        binding.repasswordbtn.setOnClickListener {
            val intent = Intent(this, ChangePasswordActivity::class.java)
            startActivity(intent)
        }

    }
}