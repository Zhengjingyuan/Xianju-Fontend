package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.BaojiaAdapter
import com.example.myapplication.adapter.PublishAdapter
import com.example.myapplication.data.Baojia
import com.example.myapplication.data.NotgetGood
import com.example.myapplication.databinding.ActivityBaojiaBinding

class BaojiaActivity : AppCompatActivity() {
    private val baojiaList = ArrayList<Baojia>()
    private lateinit var binding: ActivityBaojiaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBaojiaBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        val good_id =intent.getIntExtra("baojia",0)
        initBaojia()
        val layoutManager = LinearLayoutManager(this)
        binding.baojiarecyclerview.layoutManager = layoutManager
        val adapter = BaojiaAdapter(baojiaList,this)
        binding.baojiarecyclerview.adapter = adapter
    }

    private fun initBaojia() {
        baojiaList.add(Baojia(666,13258,200f,"2023.4.23"))
        baojiaList.add(Baojia(666,13258,200f,"2023.4.23"))
        baojiaList.add(Baojia(666,13258,200f,"2023.4.23"))

    }
}