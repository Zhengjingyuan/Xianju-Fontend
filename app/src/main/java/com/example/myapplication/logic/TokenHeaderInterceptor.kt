package com.example.myapplication.logic

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class TokenHeaderInterceptor:Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val Token: String = spUtill.getToken()

        val request: Request = chain.request()
            .newBuilder()
            .addHeader("token", Token)
            .build()
        //Log.e("返回数据：",response.body().string());
        //Log.e("返回数据：",response.body().string());
        return chain.proceed(request)

    }
}