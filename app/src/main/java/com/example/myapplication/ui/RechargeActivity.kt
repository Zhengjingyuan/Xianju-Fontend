package com.example.myapplication.ui

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityRechargeBinding


class RechargeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRechargeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRechargeBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        binding.rechargeBtn.setOnClickListener {
            val dialog: AlertDialog = AlertDialog.Builder(this)
                .setIcon(com.example.myapplication.R.drawable.baseline_attach_money_24) //设置标题的图片
                .setTitle("充值") //设置对话框的标题
                .setMessage("确定充值") //设置对话框的内容
                //设置对话框的按钮
                .setNegativeButton("取消", DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
                .setPositiveButton("确定", DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                }).create()
            dialog.show()
        }



    }
}