package com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.trade

import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.trade.models.Trade
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.trade.models.TradeDirect
import com.example.rc_aos_tem.config.BaseResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductTradeInterface {

    @POST("/api/deal/{itemId}")
    fun postTrade(
        @Path("itemId") itemId: Int,
        @Body trade: Trade
    ): Call<BaseResponse>

    @POST("/api/deal/{itemId}")
    fun postDirectTrade(
        @Path("itemId") itemId: Int,
        @Body trade: TradeDirect
    ): Call<BaseResponse>
}