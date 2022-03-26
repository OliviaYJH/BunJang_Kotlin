package com.example.bunjang_olivia.src.main.home.tablayout.recommend.wish

import com.example.bunjang_olivia.src.main.home.tablayout.recommend.models.WishListResponse
import com.example.rc_aos_tem.config.BaseResponse
import retrofit2.Call
import retrofit2.http.*

interface WishInterface {

    @POST("/api/wishes/{itemId}")
    fun postWish(
        @Path("itemId") itemId: Int
    ): Call<BaseResponse>

    @PATCH("/api/wishes/{itemId}")
    fun patchWish(
        @Path("itemId") itemId: Int
    ): Call<BaseResponse>

    @GET("/api/wishes")
    fun getWish(): Call<WishListResponse>
}