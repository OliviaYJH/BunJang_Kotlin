package com.example.bunjang_olivia.src.main.home.tablayout.recommend

import com.example.bunjang_olivia.src.main.home.tablayout.recommend.models.RecommendListResponse
import com.example.rc_aos_tem.config.BaseResponse
import retrofit2.Call
import retrofit2.http.*

interface RecommendInterface {

    @GET("/api/items/main/recommends")
    fun getRecommend(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<RecommendListResponse>


}