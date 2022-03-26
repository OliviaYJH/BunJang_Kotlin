package com.example.bunjang_olivia.src.login

import android.util.Log
import com.example.android_mvc_template.config.ApplicationClass
import com.example.bunjang_olivia.src.login.models.GetToken
import com.example.bunjang_olivia.src.login.models.LoginResponse
import com.example.bunjang_olivia.src.login.models.RegisterResponse
import com.example.bunjang_olivia.src.login.models.Users
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService(val registerView: LoginView) {

    fun tryPostRegister(data: Users){
        val kakaoRegisterInterface = ApplicationClass.sRetrofit.create(LoginInterface::class.java)
        kakaoRegisterInterface.postRegister(data).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if(response.isSuccessful){
                    registerView.onPostRegisterSuccess (response.body() as RegisterResponse)
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                registerView.onPostRegisterFailure(t.message ?: "통신 오류")
            }

        })
    }

    fun tryPostLogin(data: GetToken){
        val kakaoRegisterInterface = ApplicationClass.sRetrofit.create(LoginInterface::class.java)
        kakaoRegisterInterface.postLogin(data).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.isSuccessful){
                    registerView.onPostLoginSuccess(response.body() as LoginResponse)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                registerView.onPostLoginFailure(t.message ?: "통신 오류")
            }

        })
    }

}