package com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.image

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.FragmentSlideImageBinding
import com.example.rc_aos_tem.config.BaseFragment

class SlideImageFragment(val image: String) : BaseFragment<FragmentSlideImageBinding>(FragmentSlideImageBinding::bind, R.layout.fragment_slide_image) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("img", image)

        Glide.with(this).load("https://bjclone.s3.ap-northeast-2.amazonaws.com/" + image)
            .apply(RequestOptions().override(410,350))
            .into(binding.ivImageViewer)
    }

}