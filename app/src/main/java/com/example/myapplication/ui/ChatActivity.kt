package com.example.myapplication.ui

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.adapter.MsgAdapter
import com.example.myapplication.data.Msg
import com.example.myapplication.data.networkdata.MessageData
import com.example.myapplication.data.networkdata.chatlist2
import com.example.myapplication.databinding.ActivityChatBinding
import com.example.myapplication.logic.AppService
import com.example.myapplication.logic.TokenService
import com.example.myapplication.logic.response.ChatgoodEntity
import com.example.myapplication.logic.spUtill
import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket
//import kotlinx.coroutines.NonCancellable.message
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import java.net.URISyntaxException
import java.util.Collections.singletonMap


class ChatActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityChatBinding
    private val msgList = ArrayList<Msg>()
    private var adapter: MsgAdapter? = null
    private var mSocket: Socket? = null


    companion object {
        private const val TAG = "ChatActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val chatlist =intent.getSerializableExtra("chat") as chatlist2//转换类型
        val goodid=chatlist.goods_id


        binding= ActivityChatBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        //商品消息
        initGoodInf(goodid)




        //聊天消息
        try {
            val options = IO.Options.builder()
                .setAuth(singletonMap("token", spUtill.getToken()))
                .build()
            //1.初始化socket.io，设置链接
            mSocket = IO.socket("http://10.132.26.15:7890/chat", options)
        } catch (e: URISyntaxException) {
        }

        mSocket?.emit("join", "{\"room_id\": [roomId]}")

        //使用 onNewMessage 来监听服务器发来的 "new message" 事件
        mSocket?.on("message") {
            Log.d(TAG, "onCreate: args = $it")
            val message = it.firstOrNull() as? String ?: return@on
            val messageData = Gson().fromJson(message, MessageData::class.java)
            
            Log.d(TAG, "onCreate: message = $messageData")
        }
        mSocket?.on(Socket.EVENT_CONNECT) {
            Log.d(TAG, "onConnect: args = $it")
        }
        mSocket?.on(Socket.EVENT_CONNECT_ERROR) {
            Log.d(TAG, "onConnectError: args = $it")
        }
        mSocket?.on(Socket.EVENT_DISCONNECT) {
            Log.d(TAG, "onDisconnect: args = $it")
        }
        mSocket?.connect()



        initMsg()
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
//        adapter = MsgAdapter(msgList)
        binding.recyclerView.adapter = adapter
        binding.sendtextbtn.setOnClickListener(this)

    }

    private fun initGoodInf(goodid:Int) {
        val RegService= TokenService.create<AppService>()
        val call=RegService.getChatgood(goodid)
        call.enqueue(object : retrofit2.Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                println("请求成功")
                val result = response.body()?.string()
                Log.d(ContentValues.TAG, "onResponse: result = $result, isSuccess = ${response.isSuccessful}" + ", error body = ${response.errorBody()?.string()}")
                val entity: ChatgoodEntity = kotlin.runCatching {
                    Gson().fromJson(result, ChatgoodEntity::class.java)
                }.getOrNull() ?: return
                binding.goodname.text=entity.data.title
                binding.chatname.text=entity.data.nickname
                binding.price.text=entity.data.price.toString()
                Glide.with(this@ChatActivity).load(entity.data.pic).into(binding.profile)


            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@ChatActivity,"请求失败, 请稍后再试!", Toast.LENGTH_SHORT).show()
                Log.d(ContentValues.TAG, "onFailure: message = ${t.message}")
            }
        })

    }

    //发送消息的方法
    override fun onClick(v: View?) {
        when (v) {
            binding.sendtextbtn -> {
                val content = binding.inputText.text.toString()
                if (content.isNotEmpty()) {
                    val msg = Msg(content, Msg.TYPE_SENT)
                    msgList.add(msg)
                    adapter?.notifyItemInserted(msgList.size - 1) // 当有新消息时， 刷新RecyclerView中的显示
                    binding.recyclerView.scrollToPosition(msgList.size - 1) // 将RecyclerView 定位到最后一行
                    binding.inputText.setText("") // 清空输入框中的内容
//                    val message = MessageData("plaintext", content)
//                    mSocket!!.emit("message", Gson().toJson(message))//传输
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mSocket?.disconnect()
    }

    private fun initMsg() {

    }
}