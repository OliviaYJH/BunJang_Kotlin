package com.example.bunjang_olivia.src.splash

import com.example.bunjang_olivia.src.login.models.RegisterResponse
import com.example.bunjang_olivia.src.splash.models.AutoLoginResponse

interface AutoLoginView {

    fun onGetAutoLoginSuccess(response: AutoLoginResponse)

    fun onGetAutoLoginFailure(message: String)
}