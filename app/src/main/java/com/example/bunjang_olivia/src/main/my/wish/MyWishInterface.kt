package com.example.bunjang_olivia.src.main.my.wish

import com.example.bunjang_olivia.src.main.my.wish.models.OnWishResponse
import retrofit2.Call
import retrofit2.http.GET

interface MyWishInterface {

    @GET("/api/wishes")
    fun getMyWish(): Call<OnWishResponse>
}