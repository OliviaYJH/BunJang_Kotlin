package com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.models

data class ReviewX(
    val buyer_id: Int,
    val buyer_image: Any,
    val buyer_name: String,
    val content: String,
    val created_at: String,
    val item_id: Int,
    val item_title: String,
    val rank: Int,
    val review_id: Int
)