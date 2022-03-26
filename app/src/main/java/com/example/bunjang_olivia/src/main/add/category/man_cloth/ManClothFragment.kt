package com.example.bunjang_olivia.src.main.add.category.man_cloth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.FragmentManClothBinding
import com.example.bunjang_olivia.src.main.add.category.CategoryActivity
import com.example.rc_aos_tem.config.BaseFragment

class ManClothFragment : BaseFragment<FragmentManClothBinding>(FragmentManClothBinding::bind,
    R.layout.fragment_man_cloth) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val intent = Intent()

        binding.manJacket.setOnClickListener {
            intent.putExtra("category", "남성의류 > 패딩/점퍼")
            intent.putExtra("id", 20)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }

        binding.manCoat.setOnClickListener {
            intent.putExtra("category", "남성의류 > 코트")
            intent.putExtra("id", 21)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }
        binding.manOneToOne.setOnClickListener {
            intent.putExtra("category", "남성의류 > 맨투맨")
            intent.putExtra("id", 22)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }
        binding.manHooded.setOnClickListener {
            intent.putExtra("category", "남성의류 > 후드티/후드집업")
            intent.putExtra("id", 23)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }
        binding.manTshirt.setOnClickListener {
            intent.putExtra("category", "남성의류 > 티셔츠")
            intent.putExtra("id", 24)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }
        binding.manShirt.setOnClickListener {
            intent.putExtra("category", "남성의류 > 셔츠")
            intent.putExtra("id", 25)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }

    }

}