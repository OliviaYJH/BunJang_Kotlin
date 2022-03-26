package com.example.bunjang_olivia.src.main.my.tradeRecord.buy

import com.example.android_mvc_template.config.ApplicationClass
import com.example.bunjang_olivia.src.main.my.tradeRecord.buy.models.BuyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TradeService(val tradeView: TradeView) {

    fun tryGetBuy(){
        val buyInterface = ApplicationClass.sRetrofit.create(TradeInterface::class.java)
        buyInterface.getBuy().enqueue(object: Callback<BuyResponse> {
            override fun onResponse(call: Call<BuyResponse>, response: Response<BuyResponse>) {
                tradeView.onGetBuySuccess(response.body() as BuyResponse)
            }

            override fun onFailure(call: Call<BuyResponse>, t: Throwable) {
                tradeView.onGetBuyFailure(t.message ?: "통신 오류")
            }

        })
    }
}