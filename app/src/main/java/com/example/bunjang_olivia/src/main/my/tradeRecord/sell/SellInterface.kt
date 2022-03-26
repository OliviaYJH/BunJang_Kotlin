package com.example.bunjang_olivia.src.main.my.tradeRecord.sell

import com.example.bunjang_olivia.src.main.my.tradeRecord.sell.models.SellResponse
import retrofit2.Call
import retrofit2.http.GET

interface SellInterface {

    @GET("/api/deal/seller")
    fun getSell(): Call<SellResponse>
}