package com.example.bunjang_olivia.src.main.my

import com.example.bunjang_olivia.src.main.my.models.MyPageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MyPageInterface {

    @GET("/api/users/myPage/{userId}")
    fun getMyPage(
        @Path("userId") userId:Int
    ): Call<MyPageResponse>
}