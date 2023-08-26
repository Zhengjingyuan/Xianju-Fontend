package com.example.myapplication.ui

import android.R.attr.banner
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.NotGetAdapter
import com.example.myapplication.data.NotgetGood
import com.example.myapplication.databinding.ActivityNotGetGoodsBinding
import com.example.myapplication.logic.AppService
import com.example.myapplication.logic.TokenService
import com.example.myapplication.logic.response.publishResponse
import com.example.myapplication.logic.response.somefundEntity
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response


class NotGetGoodsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotGetGoodsBinding
    private val notgetList = ArrayList<NotgetGood>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNotGetGoodsBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        initNotegetGood()
        val layoutManager = LinearLayoutManager(this)
        binding.notgetrecycleview.layoutManager = layoutManager
        val adapter = NotGetAdapter(notgetList,this)
        binding.notgetrecycleview.adapter = adapter

        adapter.setOnItemClickListener() {
            val intent= Intent(this, Notgetgood_detail::class.java)
            intent.putExtra("notget", notgetList.get(it))//强转
            startActivity(intent)
        }
        adapter.setOnRefund{
            val intent= Intent(this, Notgetgood_detail::class.java)
            intent.putExtra("notget", notgetList.get(it))//强转
            startActivity(intent)
        }
        adapter.setOnConfirm {

            val intent= Intent(this, ConfirmActivity::class.java)
            intent.putExtra("confirm", notgetList.get(it))//强转
            startActivity(intent)
            //val confirmgood=notgetList[it]

            if (it>=0&&it<notgetList.size) {
                notgetList.removeAt(it)
                adapter.notifyItemRemoved(it)
                adapter.notifyItemRangeChanged(it, notgetList.size - it, "removeItem")
            }

        }




    }

    private fun initNotegetGood() {
//        val RegService= TokenService.create<AppService>()
//        val call=RegService.getSomefund("03")
//        call.enqueue(object : retrofit2.Callback<ResponseBody>{
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                println("请求成功")
//                val result = response.body()?.string()
//                Log.d(ContentValues.TAG, "onResponse: result = $result, isSuccess = ${response.isSuccessful}" + ", error body = ${response.errorBody()?.string()}")
//                val entity: somefundEntity = kotlin.runCatching {
//                    Gson().fromJson(result, somefundEntity::class.java)
//                }.getOrNull() ?: return
//                notgetList.addAll(entity.data)
//            }
//
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                Toast.makeText(this@NotGetGoodsActivity,"请求失败, 请稍后再试!", Toast.LENGTH_SHORT).show()
//                Log.d(ContentValues.TAG, "onFailure: message = ${t.message}")
//            }
//        })

        notgetList.add(NotgetGood(123,666,444,5,"待发货",200f,"2022.5","2022.5","圆神","出元神","http://dmimg.5054399.com/allimg/shiyan/djy/2.jpg","25314","empty","66"))
        notgetList.add(NotgetGood(123,666,444,5,"已发货",200f,"2022.5","2022.5","csgo","出csgo","http://dmimg.5054399.com/allimg/shiyan/djy/2.jpg","666","小怪兽游戏屋","66"))
        notgetList.add(NotgetGood(123,666,444,5,"待发货",200f,"2022.5","2022.5","女神异闻录","出女神5","http://dmimg.5054399.com/allimg/shiyan/djy/2.jpg","777","潮玩旗舰店","66"))
        notgetList.add(NotgetGood(123,666,444,5,"待发货",200f,"2022.5","2022.5","圆神","出老头环","http://dmimg.5054399.com/allimg/shiyan/djy/2.jpg","25314","empty","66"))
        notgetList.add(NotgetGood(123,666,444,5,"已发货",200f,"2022.5","2022.5","csgo","出csgo","http://dmimg.5054399.com/allimg/shiyan/djy/2.jpg","666","小怪兽游戏屋","66"))
        notgetList.add(NotgetGood(123,666,444,5,"待发货",200f,"2022.5","2022.5","女神异闻录","出女神5皇家版","http://dmimg.5054399.com/allimg/shiyan/djy/2.jpg","777","潮玩旗舰店","66"))

    }

}