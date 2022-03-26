package com.example.bunjang_olivia.src.main.my.wish.models

data class Result(
    val created_at: String,
    val image_path: String,
    val item_id: Int,
    val price: Int,
    val safety_pay: Int,
    val shop_image: Any,
    val shop_name: String,
    val title: String
)