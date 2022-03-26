package com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.trade

import com.example.rc_aos_tem.config.BaseResponse

interface ProductTradeView {

    fun onPostTradeSuccess(response: BaseResponse)

    fun onPostTradeFailure(message: String)
}