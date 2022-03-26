package com.example.bunjang_olivia.src.splash

import com.example.android_mvc_template.config.ApplicationClass
import com.example.bunjang_olivia.src.splash.models.AutoLoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AutoLoginService(val autoLoginView: AutoLoginView) {

    fun tryGetAutoLogin(){
        val autoLoginInterface = ApplicationClass.sRetrofit.create(AutoLoginInterface::class.java)
        autoLoginInterface.getAutoLogin().enqueue(object: Callback<AutoLoginResponse>{
            override fun onResponse(
                call: Call<AutoLoginResponse>,
                response: Response<AutoLoginResponse>
            ) {
                autoLoginView.onGetAutoLoginSuccess(response.body() as AutoLoginResponse)
            }

            override fun onFailure(call: Call<AutoLoginResponse>, t: Throwable) {
                autoLoginView.onGetAutoLoginFailure(t.message ?: "통신 오류")
            }

        })

    }

}

