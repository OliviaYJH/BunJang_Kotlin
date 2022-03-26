package com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.trade

import com.example.android_mvc_template.config.ApplicationClass
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.trade.models.Trade
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.trade.models.TradeDirect
import com.example.rc_aos_tem.config.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductTradeService(val productTradeView: ProductTradeView) {

    fun tryPostTrade(itemId: Int, trade: Trade){
        val tradeInterface = ApplicationClass.sRetrofit.create(ProductTradeInterface::class.java)
        tradeInterface.postTrade(itemId, trade).enqueue(object: Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                productTradeView.onPostTradeSuccess(response.body() as BaseResponse)
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                productTradeView.onPostTradeFailure(t.message ?: "통신 오류")
            }

        })
    }

    fun tryPostTradeDirect(itemId: Int, tradeDirect: TradeDirect){
        val tradeDirectInterface = ApplicationClass.sRetrofit.create(ProductTradeInterface::class.java)
        tradeDirectInterface.postDirectTrade(itemId, tradeDirect).enqueue(object: Callback<BaseResponse>{
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                productTradeView.onPostTradeSuccess(response.body() as BaseResponse)
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                productTradeView.onPostTradeFailure(t.message ?: "통신 오류")
            }

        })
    }
}