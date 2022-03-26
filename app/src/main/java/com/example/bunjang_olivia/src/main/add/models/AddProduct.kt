package com.example.bunjang_olivia.src.main.add.models

import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import retrofit2.http.Multipart

data class AddProduct(
    @SerializedName("category_id")
    val category_id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("location")
    val location: String,

    @SerializedName("price")
    val price: Int,

    @SerializedName("delivery_fee_included")
    val delivery_fee_included: Int,

    @SerializedName("count")
    val count: Int,

    @SerializedName("condition")
    val condition: Int,

    @SerializedName("exchange")
    val exchange: Int,

    @SerializedName("detail")
    val detail: String,

    @SerializedName("safety_pay")
    val safety_pay: Int,

    @SerializedName("tags")
    val tags: Tags,

    @SerializedName("images")
    val images: MultipartBody.Part
)
