package com.example.bunjang_olivia.src.main.my.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_mvc_template.config.ApplicationClass.Companion.sSharedPreferences
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.ActivitySettingBinding
import com.example.bunjang_olivia.src.login.LoginActivity
import com.example.bunjang_olivia.src.main.my.setting.account.AccountSettingActivity
import com.example.bunjang_olivia.src.main.my.setting.nickname.NickNameSettingActivity
import com.example.rc_aos_tem.config.BaseActivity
import com.kakao.sdk.user.UserApiClient

class SettingActivity : BaseActivity<ActivitySettingBinding>(ActivitySettingBinding::inflate) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.icLeftArrow.setOnClickListener { finish() }


        binding.btnNicknameSetting.setOnClickListener {
            startActivity(Intent(this, NickNameSettingActivity::class.java))
        }

        binding.btnAccountSetting.setOnClickListener {
            startActivity(Intent(this, AccountSettingActivity::class.java))
        }

        binding.tvLogout.setOnClickListener {
            UserApiClient.instance.logout { error ->
                if(error != null){
                    //showCustomToast("로그아웃 실패 $error")
                }else{
                    showCustomToast("로그아웃 성공")
                }

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }
    }
}