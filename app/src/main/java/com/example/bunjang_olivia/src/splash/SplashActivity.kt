package com.example.bunjang_olivia.src.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.animation.AnimationUtils
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.ActivitySplashBinding
import com.example.bunjang_olivia.src.MainActivity
import com.example.bunjang_olivia.src.login.LoginActivity
import com.example.bunjang_olivia.src.splash.models.AutoLoginResponse
import com.example.rc_aos_tem.config.BaseActivity
import com.kakao.sdk.user.UserApiClient

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate),
    AutoLoginView{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AutoLoginService(this).tryGetAutoLogin()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 1500)


        binding.ivSplash.startAnimation(AnimationUtils.loadAnimation(this, R.anim.splash))
    }

    override fun onGetAutoLoginSuccess(response: AutoLoginResponse) {
        showCustomToast(response.message)
        if(response.isSuccess){
            // auto login
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            finish()
        }else{
            // register

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }

    override fun onGetAutoLoginFailure(message: String) {
        showCustomToast(message)
    }


}