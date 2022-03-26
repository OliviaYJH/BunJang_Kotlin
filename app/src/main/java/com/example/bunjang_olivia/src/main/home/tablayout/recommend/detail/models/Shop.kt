package com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.models

data class Shop(
    val follower_count: Int,
    val item_count: Int,
    val seller_id: Int,
    val shop_items: List<ShopItem>,
    val shop_name: String
)