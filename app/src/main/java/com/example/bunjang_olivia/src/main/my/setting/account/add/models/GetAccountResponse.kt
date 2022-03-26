package com.example.bunjang_olivia.src.main.my.setting.account.add.models

data class GetAccountResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
)