package com.example.bunjang_olivia.src.login.models

import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("social_id")
    val social_id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("social_info")
    val social_info: String,

    @SerializedName("shop_name")
    val shop_name: String
)
