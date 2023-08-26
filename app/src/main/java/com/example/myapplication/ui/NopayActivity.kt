package com.example.myapplication.ui

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.NopayAdapter
import com.example.myapplication.adapter.NotGetAdapter
import com.example.myapplication.data.NotgetGood
import com.example.myapplication.databinding.ActivityNopayBinding
import com.example.myapplication.logic.AppService
import com.example.myapplication.logic.TokenService
import com.example.myapplication.logic.response.somefundEntity
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class NopayActivity : AppCompatActivity() {
    private val notpayList = ArrayList<NotgetGood>()
    private lateinit var binding: ActivityNopayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNopayBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        initNotpayGood()
        val layoutManager = LinearLayoutManager(this)
        binding.nopayrecycleview.layoutManager = layoutManager
        val adapter = NopayAdapter(this,notpayList)
        binding.nopayrecycleview.adapter = adapter
        adapter.setOnItemClickListener {
            val intent= Intent(this, Notpaydetail::class.java)
            intent.putExtra("notpay", notpayList.get(it))//强转
            startActivity(intent)
        }

    }

    private fun initNotpayGood() {

        //业务对接
//        val RegService= TokenService.create<AppService>()
//        val call=RegService.getSomefund("01")
//        call.enqueue(object : retrofit2.Callback<ResponseBody>{
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                println("请求成功")
//                val result = response.body()?.string()
//                Log.d(ContentValues.TAG, "onResponse: result = $result, isSuccess = ${response.isSuccessful}" + ", error body = ${response.errorBody()?.string()}")
//                val entity: somefundEntity = kotlin.runCatching {
//                    Gson().fromJson(result, somefundEntity::class.java)
//                }.getOrNull() ?: return
//                notpayList.addAll(entity.data)
//            }
//
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                Toast.makeText(this@NopayActivity,"请求失败, 请稍后再试!", Toast.LENGTH_SHORT).show()
//                Log.d(ContentValues.TAG, "onFailure: message = ${t.message}")
//            }
//        })

        //离线数据
        notpayList.add(NotgetGood(123,666,444,5,"待发货",200f,"2022.5","2022.5","圆神","出元神","http://dmimg.5054399.com/allimg/shiyan/djy/2.jpg","25314","empty","55"))
        notpayList.add(NotgetGood(123,666,444,5,"已发货",200f,"2022.5","2022.5","csgo","出csgo","http://dmimg.5054399.com/allimg/shiyan/djy/2.jpg","666","小怪兽游戏屋","55"))
        notpayList.add(NotgetGood(123,666,444,5,"待发货",200f,"2022.5","2022.5","女神异闻录","出女神5","http://dmimg.5054399.com/allimg/shiyan/djy/2.jpg","777","潮玩旗舰店","55"))
        notpayList.add(NotgetGood(123,666,444,5,"待发货",200f,"2022.5","2022.5","圆神","出老头环","http://dmimg.5054399.com/allimg/shiyan/djy/2.jpg","25314","empty","55"))
        notpayList.add(NotgetGood(123,666,444,5,"已发货",200f,"2022.5","2022.5","csgo","出csgo","http://dmimg.5054399.com/allimg/shiyan/djy/2.jpg","666","小怪兽游戏屋","55"))
        notpayList.add(NotgetGood(123,666,444,5,"待发货",200f,"2022.5","2022.5","女神异闻录","出女神5皇家版","http://dmimg.5054399.com/allimg/shiyan/djy/2.jpg","777","潮玩旗舰店","55"))
    }
}