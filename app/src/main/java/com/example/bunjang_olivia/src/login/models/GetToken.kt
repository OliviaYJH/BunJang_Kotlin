package com.example.bunjang_olivia.src.login.models

import com.google.gson.annotations.SerializedName

data class GetToken(
    @SerializedName("social_info")
    val social_info: String,

    @SerializedName("social_id")
    val social_id: String,

    @SerializedName("name")
    val name: String
)
