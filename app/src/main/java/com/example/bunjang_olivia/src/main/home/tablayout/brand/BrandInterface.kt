package com.example.bunjang_olivia.src.main.home.tablayout.brand

import com.example.bunjang_olivia.src.main.home.tablayout.brand.models.BrandResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BrandInterface {

    @GET("/api/items/brand/{brandId}")
    fun getBrand(
        @Path("brandId") brandId: Int,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<BrandResponse>
}