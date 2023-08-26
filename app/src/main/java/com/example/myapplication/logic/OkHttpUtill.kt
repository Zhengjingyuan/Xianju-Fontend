package com.example.myapplication.logic

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


class OkHttpUtill {
    companion object {
        fun getOkHttpClient(): OkHttpClient {
            //定制OkHttp
            val httpClientBuilder = OkHttpClient.Builder()
            //设置超时时间
            httpClientBuilder.connectTimeout(3000, TimeUnit.SECONDS)
            httpClientBuilder.writeTimeout(3000, TimeUnit.SECONDS)
            httpClientBuilder.readTimeout(3000, TimeUnit.SECONDS)
            //使用拦截器
            httpClientBuilder.addInterceptor(TokenHeaderInterceptor())
            return httpClientBuilder.build()

        }
    }
}