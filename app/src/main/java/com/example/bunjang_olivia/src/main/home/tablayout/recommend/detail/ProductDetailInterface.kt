package com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail

import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.models.ProductDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductDetailInterface {
    @GET("/api/items/{itemId}")
    fun getDetail(
        @Path("itemId") itemId: Int
    ): Call<ProductDetailResponse>
}