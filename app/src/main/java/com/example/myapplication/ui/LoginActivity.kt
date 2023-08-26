package com.example.myapplication.ui

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.data.networkdata.Login
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.logic.AppService
import com.example.myapplication.logic.ServiceCreator
import com.example.myapplication.logic.response.loginResponse
import com.example.myapplication.logic.response.rigResponse
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        binding.loginbtn.setOnClickListener {
            Toast.makeText(this@LoginActivity,"登录成功！", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
//            login()
        }
        binding.forgetbtn.setOnClickListener {
            val intent = Intent(this@LoginActivity, Findpassword0::class.java)
            startActivity(intent)
        }
    }

    private fun login() {
        val username:String=binding.loginusername.text.toString()
        val password:String=binding.loginpassword.text.toString()
        if(TextUtils.isEmpty(username)){
            Toast.makeText(this,"用户名不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"密码不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        val loginuser= Login(username,password)
        requestLogin(loginuser)
    }

    private fun requestLogin(loginuser: Login) {
        val editor = getSharedPreferences("token",Context.MODE_PRIVATE).edit()
        val RegService= ServiceCreator.create<AppService>()
        val call=RegService.submitLogin(loginuser)
        call.enqueue(object : retrofit2.Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                println("请求成功")
                val result = response.body()?.string()
                Log.d(ContentValues.TAG, "onResponse: result = $result, isSuccess = ${response.isSuccessful}" + ", error body = ${response.errorBody()?.string()}")
                val entity: loginResponse = kotlin.runCatching {
                    Gson().fromJson(result, loginResponse::class.java)
                }.getOrNull() ?: return
                editor.putString("token",entity.data.token)
                editor.apply()
                Toast.makeText(this@LoginActivity,"登录成功！", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@LoginActivity,"请求失败, 请稍后再试!", Toast.LENGTH_SHORT).show()
                Log.d(ContentValues.TAG, "onFailure: message = ${t.message}")
            }
        })
    }
}