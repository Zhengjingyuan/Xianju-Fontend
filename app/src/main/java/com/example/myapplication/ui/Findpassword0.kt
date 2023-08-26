package com.example.myapplication.ui

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.data.networkdata.Changepassword
import com.example.myapplication.databinding.ActivityFindpassword0Binding
import com.example.myapplication.logic.AppService
import com.example.myapplication.logic.ServiceCreator
import com.example.myapplication.logic.response.changeresponse
import com.example.myapplication.logic.response.emailResponse
import com.example.myapplication.logic.response.rigResponse
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class Findpassword0 : AppCompatActivity() {
    private lateinit var binding: ActivityFindpassword0Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFindpassword0Binding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        binding.quedingbtn.setOnClickListener {
            find0()
        }
        binding.yanzBtn.setOnClickListener {
            getyanz()
        }
    }

    private fun getyanz() {
        val email:String=binding.findyouxiang.text.toString()
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"邮箱不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        requestYanz(email)
    }

    private fun requestYanz(email: String) {
        val RegService= ServiceCreator.create<AppService>()
        val call=RegService.sendCaptcha(email)
        call.enqueue(object : retrofit2.Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                println("请求成功")
                val result = response.body()?.string()
                Log.d(ContentValues.TAG, "onResponse: result = $result, isSuccess = ${response.isSuccessful}" + ", error body = ${response.errorBody()?.string()}")
                val entity: emailResponse = kotlin.runCatching {
                    Gson().fromJson(result, emailResponse::class.java)
                }.getOrNull() ?: return
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@Findpassword0,"请求失败, 请稍后再试!", Toast.LENGTH_SHORT).show()
                Log.d(ContentValues.TAG, "onFailure: message = ${t.message}")
            }
        })

    }

    private fun find0() {
        val findnickname=binding.findyonghu.text.toString()
        val findyouxiang=binding.findyouxiang.text.toString()
        val yanz=binding.captcha.text.toString()
        val newpassword=binding.newpassword.text.toString()
        if(TextUtils.isEmpty(findnickname)){
            Toast.makeText(this,"用户名不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        if(TextUtils.isEmpty(findyouxiang)){
            Toast.makeText(this,"邮箱不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        if(TextUtils.isEmpty(yanz)){
            Toast.makeText(this,"验证码", Toast.LENGTH_SHORT).show()
            return
        }
        if(TextUtils.isEmpty(newpassword)){
            Toast.makeText(this,"新密码", Toast.LENGTH_SHORT).show()
            return
        }
        val change=Changepassword(findnickname,findyouxiang,yanz,newpassword)
        requestfind0(change)
    }

    private fun requestfind0(change:Changepassword) {
        val RegService= ServiceCreator.create<AppService>()
        val call=RegService.changePassword(change)
        call.enqueue(object : retrofit2.Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                println("请求成功")
                val result = response.body()?.string()
                Log.d(ContentValues.TAG, "onResponse: result = $result, isSuccess = ${response.isSuccessful}" + ", error body = ${response.errorBody()?.string()}")
                val entity: changeresponse = kotlin.runCatching {
                    Gson().fromJson(result, changeresponse::class.java)
                }.getOrNull() ?: return
                Toast.makeText(this@Findpassword0,"修改成功！", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@Findpassword0, LoginActivity::class.java)
                startActivity(intent)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@Findpassword0,"请求失败, 请稍后再试!", Toast.LENGTH_SHORT).show()
                Log.d(ContentValues.TAG, "onFailure: message = ${t.message}")
            }
        })
    }


}