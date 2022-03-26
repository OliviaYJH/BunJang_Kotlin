package com.example.bunjang_olivia.src.main.my.setting.nickname

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.ActivityNickNameSettingBinding
import com.example.bunjang_olivia.src.login.LoginActivity
import com.example.rc_aos_tem.config.BaseActivity
import com.kakao.sdk.user.UserApiClient

class NickNameSettingActivity : BaseActivity<ActivityNickNameSettingBinding>(ActivityNickNameSettingBinding::inflate) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.icLeftArrow.setOnClickListener { finish() }

        binding.btnLeaveMember.setOnClickListener {
            UserApiClient.instance.unlink { error ->
                if(error != null){
                    showCustomToast("회원 탈퇴 실패 $error")
                }else{
                    showCustomToast("회원 탈퇴 성공")
                    LoginActivity.register = false

                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    finish()
                }
            }


        }
    }
}