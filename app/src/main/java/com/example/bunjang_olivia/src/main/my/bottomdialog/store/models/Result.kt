package com.example.bunjang_olivia.src.main.my.bottomdialog.store.models

data class Result(
    val itemList: List<Item>,
    val reviewCount: Int,
    val reviewList: List<Any>,
    val userAvgRank: Any,
    val userInfoResult: UserInfoResult
)