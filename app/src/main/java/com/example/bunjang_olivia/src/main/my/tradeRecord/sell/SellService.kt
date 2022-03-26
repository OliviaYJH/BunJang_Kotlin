package com.example.bunjang_olivia.src.main.my.tradeRecord.sell

import com.example.android_mvc_template.config.ApplicationClass
import com.example.bunjang_olivia.src.main.my.tradeRecord.sell.models.SellResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SellService(val sellView: SellView) {

    fun tryGetSell(){
        val sellInterface = ApplicationClass.sRetrofit.create(SellInterface::class.java)
        sellInterface.getSell().enqueue(object: Callback<SellResponse> {
            override fun onResponse(call: Call<SellResponse>, response: Response<SellResponse>) {
                sellView.onGetSellSuccess(response.body() as SellResponse)
            }

            override fun onFailure(call: Call<SellResponse>, t: Throwable) {
                sellView.onGetSellFailure(t.message ?: "통신 오류")
            }

        })
    }
}