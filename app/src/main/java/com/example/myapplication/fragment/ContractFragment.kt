package com.example.myapplication.fragment

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.adapter.ChatAdapter
import com.example.myapplication.data.networkdata.chatlist2
import com.example.myapplication.databinding.FragmentContractBinding
import com.example.myapplication.logic.AppService
import com.example.myapplication.logic.TokenService
import com.example.myapplication.logic.response.ChatListEntity
import com.example.myapplication.ui.ChatActivity
import com.example.myapplication.ui.GoodDteil
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class ContractFragment : Fragment() {
    private var _binding:FragmentContractBinding?=null
    private val binding get() = _binding!!
    val chatList=ArrayList<chatlist2>()
    val adapter = ChatAdapter( chatList,this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentContractBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initChats()
        val layoutManager = GridLayoutManager(context, 2)//
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter=adapter
        adapter.setOnItemClickListener {
            if (chatList.get(it).is_seller==false){
            val intent= Intent(activity, ChatActivity::class.java)
            intent.putExtra("chat", chatList.get(it))//强转
            startActivity(intent)
            }
        }

    }

    private fun initChats() {
        chatList.add(chatlist2(111,111,"2022","http://dmimg.5054399.com/allimg/shiyan/djy/2.jpg","546",true))
        chatList.add(chatlist2(111,111,"2022","http://dmimg.5054399.com/allimg/shiyan/djy/2.jpg","546",true))

//        val RegService= TokenService.create<AppService>()
//        val call=RegService.getAllchat()
//        call.enqueue(object : retrofit2.Callback<ResponseBody>{
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                println("请求成功")
//                val result = response.body()?.string()
//                Log.d(ContentValues.TAG, "onResponse: result = $result, isSuccess = ${response.isSuccessful}" + ", error body = ${response.errorBody()?.string()}")
//                val entity: ChatListEntity = kotlin.runCatching {
//                    Gson().fromJson(result, ChatListEntity::class.java)
//                }.getOrNull() ?: return
//                val clist=entity.data.list
//                chatList.addAll(clist)
//                adapter.notifyDataSetChanged()
//            }
//
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                Toast.makeText(activity,"请求失败, 请稍后再试!", Toast.LENGTH_SHORT).show()
//                Log.d(ContentValues.TAG, "onFailure: message = ${t.message}")
//            }
//        })

    }


}