package com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.models

data class ProductDetailResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)