package com.example.bunjang_olivia.src.main.my.bottomdialog.store.models

data class UserInfoResult(
    val created_at: String,
    val delivery_deal_count: Int,
    val direct_deal_count: Int,
    val follower_count: Int,
    val following_count: Int,
    val image: Any,
    val item_count: Int,
    val shop_introduce: Any,
    val shop_name: String,
    val shop_notice: Any,
    val shop_policy: Any,
    val shop_time: String,
    val total_deal_count: Int
)