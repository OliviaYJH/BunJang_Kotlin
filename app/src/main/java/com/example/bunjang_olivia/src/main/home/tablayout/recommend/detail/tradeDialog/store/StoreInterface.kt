package com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.store

import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.store.models.Delete
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.store.models.Follow
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.store.models.MyFollowingResponse
import com.example.rc_aos_tem.config.BaseResponse
import retrofit2.Call
import retrofit2.http.*

interface StoreInterface {

    @POST("/api/follow/users/{userId}")
    fun postStore(
        @Path("userId") userId:Int,
        @Body follow: Follow
    ): Call<BaseResponse>

    @PATCH("/api/follow/users/{userId}")
    fun patchStore(
        @Path("userId") userId:Int,
        @Body delete: Delete
    ): Call<BaseResponse>

    @GET("/api/follow/users/following")
    fun getMyFollowers(): Call<MyFollowingResponse>
}