package com.example.myapplication.ui

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityChangePasswordBinding
import com.example.myapplication.logic.AppService
import com.example.myapplication.logic.ServiceCreator
import com.example.myapplication.logic.TokenService
import com.example.myapplication.logic.response.loginResponse
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class ChangePasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChangePasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityChangePasswordBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        binding.ckeybtn.setOnClickListener {
            cpassword()
        }
    }

    private fun cpassword() {
        val oldkey=binding.oldkey.text.toString()
        val newkey=binding.newkey.text.toString()
        val newkey2=binding.newkey2.text.toString()
        if(TextUtils.isEmpty(oldkey)){
            Toast.makeText(this,"原密码不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        if(TextUtils.isEmpty(newkey)){
            Toast.makeText(this,"新密码不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        if(TextUtils.isEmpty(newkey2)){
            Toast.makeText(this,"确认密码不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        if(newkey2!=newkey){
            Toast.makeText(this,"两次密码不同！", Toast.LENGTH_SHORT).show()
            return
        }
        requestCpassword(newkey)
    }

    private fun requestCpassword(newkey: String) {
        val RegService= TokenService.create<AppService>()
        val call=RegService.postCkey(newkey)
        call.enqueue(object : retrofit2.Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                println("请求成功")
                val result = response.body()?.string()
                Log.d(ContentValues.TAG, "onResponse: result = $result, isSuccess = ${response.isSuccessful}" + ", error body = ${response.errorBody()?.string()}")
//                val entity: loginResponse = kotlin.runCatching {
//                    Gson().fromJson(result, loginResponse::class.java)
//                }.getOrNull() ?: return
                Toast.makeText(this@ChangePasswordActivity,"修改成功！", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@ChangePasswordActivity, ShezhiActivity::class.java)
                startActivity(intent)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@ChangePasswordActivity,"请求失败, 请稍后再试!", Toast.LENGTH_SHORT).show()
                Log.d(ContentValues.TAG, "onFailure: message = ${t.message}")
            }
        })
    }
}