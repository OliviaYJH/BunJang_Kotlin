package com.example.bunjang_olivia.util.recycler.recommendFragment

data class RecommendData(
    val title: String,
    val price: String,
    val safety_pay: Int,
    val location: String,
    val created_at: String, // time
    val image_path: String,
    val wish_count: Int,
    val item_id: Int,
    var wish: Boolean = false

)
