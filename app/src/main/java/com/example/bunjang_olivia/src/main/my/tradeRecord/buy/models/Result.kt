package com.example.bunjang_olivia.src.main.my.tradeRecord.buy.models

data class Result(
    val created_at: String,
    val deal_id: Int,
    val image_path: String,
    val price: Int,
    val process: String,
    val safety_pay: Int,
    val seller_id: Int,
    val title: String
)