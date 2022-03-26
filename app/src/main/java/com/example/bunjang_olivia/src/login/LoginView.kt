package com.example.bunjang_olivia.src.login

import com.example.bunjang_olivia.src.login.models.LoginResponse
import com.example.bunjang_olivia.src.login.models.RegisterResponse

interface LoginView {

    fun onPostRegisterSuccess(response: RegisterResponse)

    fun onPostRegisterFailure(message: String)

    fun onPostLoginSuccess(response: LoginResponse)

    fun onPostLoginFailure(message: String)

}