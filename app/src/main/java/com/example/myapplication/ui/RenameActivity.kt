package com.example.myapplication.ui

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.rename
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityRenameBinding
import com.example.myapplication.logic.AppService
import com.example.myapplication.logic.TokenService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class RenameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRenameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRenameBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        binding.renamebtn.setOnClickListener {
            Rename()
        }
    }

    private fun Rename() {
        val newname=binding.newname.text.toString()
        if(TextUtils.isEmpty(newname)){
            Toast.makeText(this,"昵称不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        requestrename(newname)

    }

    private fun requestrename(newname: String) {
        val RegService= TokenService.create<AppService>()
        val call=RegService.postNewname(newname)
        call.enqueue(object : retrofit2.Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                println("请求成功")
                val result = response.body()?.string()
                Log.d(ContentValues.TAG, "onResponse: result = $result, isSuccess = ${response.isSuccessful}" + ", error body = ${response.errorBody()?.string()}")
//                val entity: loginResponse = kotlin.runCatching {
//                    Gson().fromJson(result, loginResponse::class.java)
//                }.getOrNull() ?: return
                Toast.makeText(this@RenameActivity,"修改成功！", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@RenameActivity, ShezhiActivity::class.java)
                startActivity(intent)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@RenameActivity,"请求失败, 请稍后再试!", Toast.LENGTH_SHORT).show()
                Log.d(ContentValues.TAG, "onFailure: message = ${t.message}")
            }
        })
    }


}