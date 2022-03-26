package com.example.bunjang_olivia.src.main.my.tradeRecord.sell.models

data class Result(
    val created_at: String,
    val image_path: String,
    val item_id: Int,
    val price: Int,
    val safety_pay: Int,
    val sale: String,
    val title: String
)