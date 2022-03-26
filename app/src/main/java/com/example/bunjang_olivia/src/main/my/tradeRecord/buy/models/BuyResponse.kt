package com.example.bunjang_olivia.src.main.my.tradeRecord.buy.models

data class BuyResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
)