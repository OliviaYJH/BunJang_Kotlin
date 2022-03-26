package com.example.bunjang_olivia.src.main.home.tablayout.recommend.models

data class RecommendListResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
)