package com.example.bunjang_olivia.src.main.my.bottomdialog.modify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_mvc_template.config.ApplicationClass.Companion.sSharedPreferences
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.ActivityMyModifyBinding
import com.example.bunjang_olivia.src.main.my.bottomdialog.store.StoreService
import com.example.bunjang_olivia.src.main.my.bottomdialog.store.StoreView
import com.example.bunjang_olivia.src.main.my.bottomdialog.store.models.StoreDetailResponse
import com.example.rc_aos_tem.config.BaseActivity

class MyModifyActivity : BaseActivity<ActivityMyModifyBinding>(ActivityMyModifyBinding::inflate), StoreView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.icLeftArrow.setOnClickListener { finish() }

        var userId = sSharedPreferences.getInt("userId", 0)
        StoreService(this).tryGetStore(userId)
    }

    override fun onGetStoreSuccess(response: StoreDetailResponse) {
        binding.etStoreName.hint = response.result.userInfoResult.shop_name

        var start = response.result.userInfoResult.shop_time.substring(0,2)
        var end = response.result.userInfoResult.shop_time.substring(2,4)

        binding.etStorePossibleTime.hint = start + "시 ~ " + end + "시"
    }

    override fun onGetStoreFailure(message: String) {
        showCustomToast(message)
    }
}