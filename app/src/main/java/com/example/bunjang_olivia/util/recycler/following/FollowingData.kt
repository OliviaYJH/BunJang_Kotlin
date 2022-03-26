package com.example.bunjang_olivia.util.recycler.following

data class FollowingData(
    val shop_name:String,
    val howManyItem: String,
    val howManyFollowers: String,
    val getItemResult: ArrayList<GetFollowingData>
)
