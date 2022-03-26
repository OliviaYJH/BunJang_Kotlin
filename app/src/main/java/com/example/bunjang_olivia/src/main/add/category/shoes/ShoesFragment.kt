package com.example.bunjang_olivia.src.main.add.category.shoes

import android.app.Activity
import android.content.Intent
import android.os.Bundle

import android.view.View
import android.view.ViewGroup
import com.example.bunjang_olivia.R

import com.example.bunjang_olivia.databinding.FragmentShoesBinding
import com.example.bunjang_olivia.src.main.add.category.CategoryActivity

import com.example.rc_aos_tem.config.BaseFragment

class ShoesFragment : BaseFragment<FragmentShoesBinding>(FragmentShoesBinding::bind,
    R.layout.fragment_shoes) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var intent = Intent()

        binding.shoesSneakers.setOnClickListener {
            intent.putExtra("category", "신발 > 스니커즈")
            intent.putExtra("id", 30)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }
        binding.shoesMan.setOnClickListener {
            intent.putExtra("category", "신발 > 남성화")
            intent.putExtra("id", 31)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }
        binding.shoesWoman.setOnClickListener {
            intent.putExtra("category", "신발 > 여성화")
            intent.putExtra("id", 32)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }
    }

}