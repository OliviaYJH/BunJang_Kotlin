package com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.store.models

data class Result(
    val getItemResult: List<GetItemResult>,
    val howManyFollowers: Int,
    val howManyItem: Int,
    val shopName: String,
    val userId: Int
)