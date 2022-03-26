package com.example.bunjang_olivia.src.main.home.tablayout.brand.models

data class BrandResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
)