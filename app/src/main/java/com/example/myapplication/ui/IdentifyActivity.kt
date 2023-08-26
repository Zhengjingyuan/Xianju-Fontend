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
import com.example.myapplication.data.networkdata.Identity
import com.example.myapplication.databinding.ActivityIdentifyBinding
import com.example.myapplication.logic.AppService
import com.example.myapplication.logic.ServiceCreator
import com.example.myapplication.logic.response.IDresponse
import com.example.myapplication.logic.response.loginResponse
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class IdentifyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIdentifyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityIdentifyBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        binding.idbtn.setOnClickListener {
            identify()
        }

    }

    private fun identify() {
        val idname=binding.idname.text.toString()
        val idcard=binding.idname.text.toString()

        if(TextUtils.isEmpty(idcard)){
            Toast.makeText(this,"身份证号不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        if(TextUtils.isEmpty(idname)){
            Toast.makeText(this,"姓名不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        val ID=Identity(idname,idcard)
        requestId(ID)
    }

    private fun requestId(ID:Identity) {
        val RegService= ServiceCreator.create<AppService>()
        val call=RegService.postId(ID)
        call.enqueue(object : retrofit2.Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                println("请求成功")
                val result = response.body()?.string()
                Log.d(ContentValues.TAG, "onResponse: result = $result, isSuccess = ${response.isSuccessful}" + ", error body = ${response.errorBody()?.string()}")
                val entity: IDresponse = kotlin.runCatching {
                    Gson().fromJson(result, IDresponse::class.java)
                }.getOrNull() ?: return
                Toast.makeText(this@IdentifyActivity,"实名成功！", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@IdentifyActivity, ShezhiActivity::class.java)
                startActivity(intent)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@IdentifyActivity,"请求失败, 请稍后再试!", Toast.LENGTH_SHORT).show()
                Log.d(ContentValues.TAG, "onFailure: message = ${t.message}")
            }
        })
    }
}