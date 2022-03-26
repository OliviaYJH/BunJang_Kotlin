package com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.models

data class Item(
    val category_id: Int,
    val condition: Int,
    val count: Int,
    val creatd_at: String,
    val delivery_fee_included: Int,
    val detail: String,
    val images: List<Image>,
    val inquiry_count: Int,
    val price: Int,
    val safety_pay: Int,
    val tags: List<Tag>,
    val title: String,
    val view: Int,
    val wish_count: Int
)