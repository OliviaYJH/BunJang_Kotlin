package com.example.bunjang_olivia.src.main.home.tablayout.brand.models

data class Result(
    val created_at: String,
    val image_path: String,
    val item_id: Int,
    val location: String,
    val price: Int,
    val safety_pay: Int,
    val title: String,
    val wish_count: Int
)