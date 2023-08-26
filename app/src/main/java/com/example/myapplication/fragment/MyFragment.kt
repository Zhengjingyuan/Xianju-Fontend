package com.example.myapplication.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMyBinding
import com.example.myapplication.ui.*

class MyFragment : Fragment() {
    private  var _binding: FragmentMyBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentMyBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.daishouhuoBtn.setOnClickListener{
            val intent = Intent(activity, NotGetGoodsActivity::class.java)
            startActivity(intent)
        }
        binding.allrefundBtn.setOnClickListener {
            val intent = Intent(activity, AllrefundActivity::class.java)
            startActivity(intent)
        }
        binding.finishBtn.setOnClickListener {
            val intent = Intent(activity, FinishActivity::class.java)
            startActivity(intent)
        }
        binding.notsendBtn.setOnClickListener {
            val intent = Intent(activity, NotsendoutActivity::class.java)
            startActivity(intent)
        }
        binding.notpayBtn.setOnClickListener {
            val intent = Intent(activity, NopayActivity::class.java)
            startActivity(intent)

        }
        binding.finishBtn2.setOnClickListener {
            val intent = Intent(activity, Finish2Activity::class.java)
            startActivity(intent)
        }
        binding.publishBtn.setOnClickListener {
            val intent = Intent(activity, PublishActivity::class.java)
            startActivity(intent)
        }
        binding.yueBtn.setOnClickListener {
            val intent = Intent(activity, YueActivity::class.java)
            startActivity(intent)
        }
        binding.rechargeBtn.setOnClickListener {
            val intent = Intent(activity, RechargeActivity::class.java)
            startActivity(intent)
        }
        binding.shezhibtn.setOnClickListener {
            val intent = Intent(activity, ShezhiActivity::class.java)
            startActivity(intent)
        }
        binding.tixianBtn.setOnClickListener {
            val intent = Intent(activity, TixianActivity::class.java)
            startActivity(intent)
        }
        binding.rechargeBtn.setOnClickListener {
            val intent = Intent(activity, RechargeActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
         super.onDestroyView()
         _binding = null
         }


}