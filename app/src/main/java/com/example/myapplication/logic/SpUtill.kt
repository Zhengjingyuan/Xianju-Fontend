package com.example.myapplication.logic

import android.app.Application
import android.content.Context

class spUtill {

    companion object:Application() {
        fun getToken(): String{
            val sp=AppApplication.context.getSharedPreferences("token", Context.MODE_PRIVATE)
            return sp.getString("token","")!!
        }
    }
}