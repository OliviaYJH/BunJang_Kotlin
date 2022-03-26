package com.example.bunjang_olivia.src.main.my.tradeRecord.buy

import com.example.bunjang_olivia.src.main.my.tradeRecord.buy.models.BuyResponse

interface TradeView {

    fun onGetBuySuccess(response: BuyResponse)

    fun onGetBuyFailure(message: String)
}