package com.example.bunjang_olivia.src.main.my.bottomdialog.store

import com.example.bunjang_olivia.src.main.my.bottomdialog.store.models.StoreDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreInterface {
    @GET("/api/users/{userId}")
    fun getStore(
        @Path("userId") userId: Int
    ): Call<StoreDetailResponse>
}