package com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.models

data class Review(
    val rank_avg: String,
    val review_count: Int,
    val reviews: List<ReviewX>
)