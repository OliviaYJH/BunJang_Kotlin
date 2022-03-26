package com.example.bunjang_olivia.src.main.add.category.bag

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.FragmentBagBinding
import com.example.bunjang_olivia.src.main.add.category.CategoryActivity
import com.example.rc_aos_tem.config.BaseFragment

class BagFragment : BaseFragment<FragmentBagBinding>(FragmentBagBinding::bind, R.layout.fragment_bag) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var intent = Intent()

        binding.womanBag.setOnClickListener {
            intent.putExtra("category", "가방 > 여성가방")
            intent.putExtra("id", 40)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }
        binding.manBag.setOnClickListener {
            intent.putExtra("category", "가방 > 남성가방")
            intent.putExtra("id", 41)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }
        binding.travelBag.setOnClickListener {
            intent.putExtra("category", "가방 > 여행용")
            intent.putExtra("id", 42)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }
    }

}