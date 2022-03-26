package com.example.bunjang_olivia.src.main.my.tradeRecord.sell.models

data class SellResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
)