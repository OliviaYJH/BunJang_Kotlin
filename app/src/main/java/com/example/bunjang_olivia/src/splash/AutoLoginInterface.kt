package com.example.bunjang_olivia.src.splash

import com.example.bunjang_olivia.src.splash.models.AutoLoginResponse
import retrofit2.Call
import retrofit2.http.GET

interface AutoLoginInterface {
    @GET("/api/auth/auto-login")
    fun getAutoLogin(): Call<AutoLoginResponse>
}