package com.example.bunjang_olivia.src.main.home.tablayout.recommend.models

data class WishListResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<ResultX>
)