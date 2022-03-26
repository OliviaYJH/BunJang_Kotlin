package com.example.bunjang_olivia.src.main.my.tradeRecord.sell

import com.example.bunjang_olivia.src.main.my.tradeRecord.sell.models.SellResponse

interface SellView {

    fun onGetSellSuccess(response: SellResponse)

    fun onGetSellFailure(message: String)
}