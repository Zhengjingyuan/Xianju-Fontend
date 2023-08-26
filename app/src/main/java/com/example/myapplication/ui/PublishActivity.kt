package com.example.myapplication.ui

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.NotGetAdapter
import com.example.myapplication.adapter.PublishAdapter
import com.example.myapplication.data.NotgetGood
import com.example.myapplication.databinding.ActivityPublishBinding
import com.example.myapplication.logic.AppService
import com.example.myapplication.logic.TokenService
import com.example.myapplication.logic.response.allfundResponse
import com.example.myapplication.logic.response.publishResponse
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class PublishActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPublishBinding
    private val publishList = ArrayList<NotgetGood>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPublishBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        initPublishGood()
        val layoutManager = LinearLayoutManager(this)
        binding.publishrecycleview.layoutManager = layoutManager
        val adapter = PublishAdapter(publishList,this)
        binding.publishrecycleview.adapter = adapter

        adapter.setOnItemClickListener() {
            val intent= Intent(this, PublishDetail::class.java)
            intent.putExtra("publish", publishList.get(it))//强转
            startActivity(intent)
        }
        adapter.setOnCprice {
            val intent= Intent(this, CpriceActivity::class.java)
            startActivity(intent)
        }
        adapter.setOnDelet {
            publishList.removeAt(it)
            adapter.notifyItemRemoved(it)
            adapter.notifyItemRangeChanged(it, publishList.size - it, "removeItem")

        }
    }

    private fun initPublishGood() {
//        val RegService= TokenService.create<AppService>()
//        val call=RegService.getPublishfund("02")
//        call.enqueue(object : retrofit2.Callback<ResponseBody>{
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                println("请求成功")
//                val result = response.body()?.string()
//                Log.d(ContentValues.TAG, "onResponse: result = $result, isSuccess = ${response.isSuccessful}" + ", error body = ${response.errorBody()?.string()}")
//                val entity: publishResponse = kotlin.runCatching {
//                    Gson().fromJson(result, publishResponse::class.java)
//                }.getOrNull() ?: return
//                publishList.addAll(entity.data)
//            }
//
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                Toast.makeText(this@PublishActivity,"请求失败, 请稍后再试!", Toast.LENGTH_SHORT).show()
//                Log.d(ContentValues.TAG, "onFailure: message = ${t.message}")
//            }
//        })

        publishList.add(NotgetGood(123,666,444,5,"待发货",200f,"2022.5","2022.5","圆神","出元神","http://dmimg.5054399.com/allimg/shiyan/djy/2.jpg","25314","empty","66"))
        publishList.add(NotgetGood(123,666,444,5,"已发货",200f,"2022.5","2022.5","csgo","出csgo","http://dmimg.5054399.com/allimg/shiyan/djy/2.jpg","666","小怪兽游戏屋","66"))
        publishList.add(NotgetGood(123,666,444,5,"待发货",200f,"2022.5","2022.5","女神异闻录","出女神5","http://dmimg.5054399.com/allimg/shiyan/djy/2.jpg","777","潮玩旗舰店","66"))
        publishList.add(NotgetGood(123,666,444,5,"待发货",200f,"2022.5","2022.5","圆神","出老头环","http://dmimg.5054399.com/allimg/shiyan/djy/2.jpg","25314","empty","66"))
        publishList.add(NotgetGood(123,666,444,5,"已发货",200f,"2022.5","2022.5","csgo","出csgo","http://dmimg.5054399.com/allimg/shiyan/djy/2.jpg","666","小怪兽游戏屋","66"))
        publishList.add(NotgetGood(123,666,444,5,"待发货",200f,"2022.5","2022.5","女神异闻录","出女神5皇家版","http://dmimg.5054399.com/allimg/shiyan/djy/2.jpg","777","潮玩旗舰店","66"))


    }
}