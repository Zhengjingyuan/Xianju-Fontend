package com.example.myapplication.ui

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.data.networkdata.Register
import com.example.myapplication.databinding.ActivityRegisterBinding
import com.example.myapplication.logic.AppService
import com.example.myapplication.logic.ServiceCreator
import com.example.myapplication.logic.response.emailResponse
import com.example.myapplication.logic.response.rigResponse
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        binding.yanzBtn.setOnClickListener {
            getYanz()
        }
        binding.rigBtn.setOnClickListener {
            rigister()
        }
    }

    private fun rigister() {
        val username:String=binding.username.text.toString()
        val nickname:String=binding.nickname.text.toString()
        val password:String=binding.password.text.toString()
        val password2:String=binding.password2.text.toString()
        val email:String=binding.email.text.toString()
        val captcha:String=binding.captcha.text.toString()
        if(TextUtils.isEmpty(username)){
            Toast.makeText(this,"用户名不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"密码不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        if(TextUtils.isEmpty(nickname)){
            Toast.makeText(this,"昵称不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"邮箱不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        if(TextUtils.isEmpty(captcha)){
            Toast.makeText(this,"验证码不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        if(password2!=password){
            Toast.makeText(this,"两次密码不同！", Toast.LENGTH_SHORT).show()
            return
        }
        val adduser= Register(captcha,username,nickname,password,email)
        requestRig(adduser)
    }

    private fun requestRig(adduser: Register) {
        val RegService= ServiceCreator.create<AppService>()
        /*val gson = Gson()
        val route = gson.toJson(adduser)
        println(route.toString())*/
        val call=RegService.submitRegister(adduser)
        call.enqueue(object : retrofit2.Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                println("请求成功")
                val result = response.body()?.string()
                Log.d(TAG, "onResponse: result = $result, isSuccess = ${response.isSuccessful}" + ", error body = ${response.errorBody()?.string()}")
                val entity: rigResponse = kotlin.runCatching {
                    Gson().fromJson(result, rigResponse::class.java)
                }.getOrNull() ?: return
                Toast.makeText(this@RegisterActivity,"注册成功！", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@RegisterActivity,"请求失败, 请稍后再试!", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "onFailure: message = ${t.message}")
            }
        })

    }




    private fun getYanz() {
        val email:String=binding.email.text.toString()
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
                Log.d(TAG, "onResponse: result = $result, isSuccess = ${response.isSuccessful}" + ", error body = ${response.errorBody()?.string()}")
                val entity: emailResponse = kotlin.runCatching {
                    Gson().fromJson(result, emailResponse::class.java)
                }.getOrNull() ?: return
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@RegisterActivity,"请求失败, 请稍后再试!", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "onFailure: message = ${t.message}")
            }
        })

    }


}


