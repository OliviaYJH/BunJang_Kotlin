package com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.store.models

data class MyFollowingResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
)