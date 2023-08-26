package com.example.myapplication.data

class Msg(val content: String, val type: Int) {
    companion object {
        const val TYPE_RECEIVED = 0
        const val TYPE_SENT = 1
        const val TYPE_PLAINTEXT_RECEIVED = 0
        const val TYPE_PLAINTEXT_SEND = 1
        const val TYPE_IMAGE_RECEIVED = 2
        const val TYPE_IMAGE_SEND = 3
    }
}