package com.example.bunjang_olivia.src.main.my.models

data class MyPageResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)