package com.example.bunjang_olivia.util.recycler.myWish

data class MyWishData(
    val item_id: Int,
    val image_path: String,
    val safety_pay: Int,
    val title: String,
    val price: Int,
    val created_at: String,
    val shop_name: String
    //val shop_image: Any
)
