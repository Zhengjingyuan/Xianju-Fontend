package com.example.myapplication.data.networkdata

import java.io.Serializable

class MessageData(var type: String, val detail: String, val sendId: String, val sendTime: Long): Serializable {

}