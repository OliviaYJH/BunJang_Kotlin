package com.example.bunjang_olivia.src.main.add.category.jewerly

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.FragmentJewerlyBinding
import com.example.bunjang_olivia.src.main.add.category.CategoryActivity
import com.example.rc_aos_tem.config.BaseFragment

class JewerlyFragment : BaseFragment<FragmentJewerlyBinding>(FragmentJewerlyBinding::bind
    , R.layout.fragment_jewerly) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var intent = Intent()

        binding.watch.setOnClickListener {
            intent.putExtra("category", "시계/쥬얼리 > 시계")
            intent.putExtra("id", 50)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }
        binding.jewerly.setOnClickListener {
            intent.putExtra("category", "시계/쥬얼리 > 쥬얼리")
            intent.putExtra("id", 51)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }
    }

}