package com.example.bunjang_olivia.src.main.my.tradeRecord.buy

import com.example.bunjang_olivia.src.main.my.tradeRecord.buy.models.BuyResponse
import retrofit2.Call
import retrofit2.http.GET

interface TradeInterface {

    @GET("/api/deal/buyer")
    fun getBuy(): Call<BuyResponse>
}