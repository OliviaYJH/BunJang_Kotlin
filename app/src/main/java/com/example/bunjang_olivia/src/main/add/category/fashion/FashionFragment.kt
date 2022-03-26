package com.example.bunjang_olivia.src.main.add.category.fashion

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.FragmentFashionBinding
import com.example.bunjang_olivia.src.main.add.category.CategoryActivity
import com.example.rc_aos_tem.config.BaseFragment

class FashionFragment : BaseFragment<FragmentFashionBinding>(FragmentFashionBinding::bind
        , R.layout.fragment_fashion) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var intent = Intent()

        binding.wallet.setOnClickListener {
            intent.putExtra("category", "패션 액세서리 > 지갑")
            intent.putExtra("id", 60)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }
        binding.belt.setOnClickListener {
            intent.putExtra("category", "패션 액세서리 > 벨트")
            intent.putExtra("id", 61)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }
        binding.hat.setOnClickListener {
            intent.putExtra("category", "패션 액세서리 > 모자")
            intent.putExtra("id", 62)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }
        binding.glove.setOnClickListener {
            intent.putExtra("category", "패션 액세서리 > 목도리/장갑")
            intent.putExtra("id", 63)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }
        binding.scarf.setOnClickListener {
            intent.putExtra("category", "패션 액세서리 > 스카프/넥타이")
            intent.putExtra("id", 64)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }
        binding.glasses.setOnClickListener {
            intent.putExtra("category", "패션 액세서리 > 스카프/넥타이")
            intent.putExtra("id", 65)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }
    }

}