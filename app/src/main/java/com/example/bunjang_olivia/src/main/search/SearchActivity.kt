package com.example.bunjang_olivia.src.main.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.ActivitySearchBinding
import com.example.rc_aos_tem.config.BaseActivity

class SearchActivity : BaseActivity<ActivitySearchBinding>(ActivitySearchBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.icClose.setOnClickListener { finish() }
    }
}