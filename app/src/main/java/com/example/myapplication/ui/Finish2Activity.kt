package com.example.myapplication.ui

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.FinishAdapter
import com.example.myapplication.adapter.FinishAdapter2
import com.example.myapplication.data.NotgetGood
import com.example.myapplication.databinding.ActivityFinish2Binding
import com.example.myapplication.logic.AppService
import com.example.myapplication.logic.TokenService
import com.example.myapplication.logic.response.somefundEntity
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class Finish2Activity : AppCompatActivity() {
    private val finishList = ArrayList<NotgetGood>()
    private lateinit var binding: ActivityFinish2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFinish2Binding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        initFinishGood()
        val layoutManager = LinearLayoutManager(this)
        binding.finishrecycleview.layoutManager = layoutManager
        val adapter = FinishAdapter2(this,finishList)
        binding.finishrecycleview.adapter = adapter
        adapter.setOnItemClickListener {
            val intent= Intent(this,FinishDtail2::class.java)
            intent.putExtra("finish", finishList.get(it))//强转
            startActivity(intent)
        }
    }

    private fun initFinishGood() {
        val RegService= TokenService.create<AppService>()
        val call=RegService.getSomefund("")
        call.enqueue(object : retrofit2.Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                println("请求成功")
                val result = response.body()?.string()
                Log.d(ContentValues.TAG, "onResponse: result = $result, isSuccess = ${response.isSuccessful}" + ", error body = ${response.errorBody()?.string()}")
                val entity: somefundEntity = kotlin.runCatching {
                    Gson().fromJson(result, somefundEntity::class.java)
                }.getOrNull() ?: return
                finishList.addAll(entity.data)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@Finish2Activity,"请求失败, 请稍后再试!", Toast.LENGTH_SHORT).show()
                Log.d(ContentValues.TAG, "onFailure: message = ${t.message}")
            }
        })
    }
}