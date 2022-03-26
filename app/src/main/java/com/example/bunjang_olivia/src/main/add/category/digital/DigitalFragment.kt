package com.example.bunjang_olivia.src.main.add.category.digital

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.FragmentDigitalBinding
import com.example.bunjang_olivia.src.main.add.category.CategoryActivity
import com.example.rc_aos_tem.config.BaseFragment

class DigitalFragment : BaseFragment<FragmentDigitalBinding>(FragmentDigitalBinding::bind, R.layout.fragment_digital) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var intent = Intent()

        binding.mobile.setOnClickListener {
            intent.putExtra("category", "디지털/가전 > 모바일")
            intent.putExtra("id", 70)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }
        binding.electronics.setOnClickListener {
            intent.putExtra("category", "디지털/가전 > 가전제품")
            intent.putExtra("id", 71)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }
        binding.audio.setOnClickListener {
            intent.putExtra("category", "디지털/가전 > 오디오/영상/관련기기")
            intent.putExtra("id", 72)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }
        binding.pc.setOnClickListener {
            intent.putExtra("category", "디지털/가전 > 오디오/영상/관련기기")
            intent.putExtra("id", 73)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }
        binding.game.setOnClickListener {
            intent.putExtra("category", "디지털/가전 > 오디오/영상/관련기기")
            intent.putExtra("id", 74)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }
        binding.camera.setOnClickListener {
            intent.putExtra("category", "디지털/가전 > 오디오/영상/관련기기")
            intent.putExtra("id", 75)
            activity?.setResult(Activity.RESULT_OK, intent)
            (activity as CategoryActivity).finish()
        }
    }

}