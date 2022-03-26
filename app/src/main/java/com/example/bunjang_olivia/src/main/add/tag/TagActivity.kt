package com.example.bunjang_olivia.src.main.add.tag

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.ActivityTagBinding
import com.example.rc_aos_tem.config.BaseActivity

class TagActivity : BaseActivity<ActivityTagBinding>(ActivityTagBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.icLeftArrow.setOnClickListener { finish() }

        binding.ivOk.setOnClickListener {
            var intent = Intent()
            intent.putExtra("tag", binding.etTag.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

}