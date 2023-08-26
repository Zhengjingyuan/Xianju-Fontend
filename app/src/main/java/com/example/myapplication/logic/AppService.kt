package com.example.myapplication.logic

import com.example.myapplication.data.networkdata.Changepassword
import com.example.myapplication.data.networkdata.Identity
import com.example.myapplication.data.networkdata.Login
import com.example.myapplication.data.networkdata.Register
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface AppService {

    @POST("user/register")
    fun submitRegister(@Body data: Register): Call<ResponseBody>

    @POST("captcha/{email}")
    fun sendCaptcha(@Path("email")email:String):Call<ResponseBody>

    @POST("user/login")
    fun submitLogin(@Body data: Login): Call<ResponseBody>

    @GET("goods")
     fun getAllfund():  Call<ResponseBody>

    @PUT("user")
    fun changePassword(@Body data:Changepassword):Call<ResponseBody>

    @GET("goods/state/{state}")
    fun getPublishfund(@Path("state")state:String):Call<ResponseBody>

    @GET("order/{state}")
    fun getSomefund(@Path("state")state:String):Call<ResponseBody>

    @PATCH("user/identify")
    fun postId(@Body data: Identity): Call<ResponseBody>

    @GET("comments/{to_id}")
    fun getComment(@Path("to_id")to_id:Int):Call<ResponseBody>

    @PATCH("user/updatepwd")
    fun postCkey(@Body password:String): Call<ResponseBody>

    @PATCH("user/updatenickname")
    fun postNewname(@Body nickname:String): Call<ResponseBody>

    @GET("chat")
    fun getAllchat():  Call<ResponseBody>

    @GET("goods/(goods_id)")
    fun getChatgood(@Query("goods_id")goods_id:Int):Call<ResponseBody>

}
