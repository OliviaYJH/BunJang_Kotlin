package com.example.bunjang_olivia.src.login.models

data class LoginResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)