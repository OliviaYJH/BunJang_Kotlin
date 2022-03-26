package com.example.bunjang_olivia.src.main.add.category.woman_cloth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.FragmentWomanClothBinding
import com.example.bunjang_olivia.src.main.add.category.CategoryActivity
import com.example.rc_aos_tem.config.BaseFragment

class WomanClothFragment : BaseFragment<FragmentWomanClothBinding>(FragmentWomanClothBinding::bind, R.layout.fragment_woman_cloth){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val intent = Intent()

        binding.womanJacket.setOnClickListener {
            intent.putExtra("category", "여성의류 > 패딩/점퍼")
            intent.putExtra("id", 10)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }

        binding.womanCoat.setOnClickListener {
            intent.putExtra("category", "여성의류 > 코트")
            intent.putExtra("id", 11)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }

        binding.womanOneToOne.setOnClickListener {
            intent.putExtra("category", "여성의류 > 맨투맨")
            intent.putExtra("id", 12)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }

        binding.womanHooded.setOnClickListener {
            intent.putExtra("category", "여성의류 > 후드티/후드집업")
            intent.putExtra("id", 13)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }

        binding.womanTshirt.setOnClickListener {
            intent.putExtra("category", "여성의류 > 티셔츠")
            intent.putExtra("id", 14)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }
        binding.womanBlouse.setOnClickListener {
            intent.putExtra("category", "여성의류 > 블라우스")
            intent.putExtra("id", 15)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }

    }
   
}