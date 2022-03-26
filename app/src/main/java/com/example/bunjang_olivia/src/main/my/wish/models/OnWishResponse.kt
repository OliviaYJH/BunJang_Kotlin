package com.example.bunjang_olivia.src.main.my.wish.models

data class OnWishResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
)