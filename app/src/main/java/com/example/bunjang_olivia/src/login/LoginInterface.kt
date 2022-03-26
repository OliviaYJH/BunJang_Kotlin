package com.example.bunjang_olivia.src.login

import com.example.android_mvc_template.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.example.android_mvc_template.config.ApplicationClass.Companion.sSharedPreferences
import com.example.bunjang_olivia.src.login.models.GetToken
import com.example.bunjang_olivia.src.login.models.LoginResponse
import com.example.bunjang_olivia.src.login.models.RegisterResponse
import com.example.bunjang_olivia.src.login.models.Users
import com.example.bunjang_olivia.src.splash.models.AutoLoginResponse
import retrofit2.Call
import retrofit2.http.*

interface LoginInterface {

    @POST("/api/users")
    fun postRegister(
        @Body users: Users
    ): Call<RegisterResponse>

    @POST("/api/auth/login")
    fun postLogin(
        @Body token: GetToken
    ): Call<LoginResponse>



}