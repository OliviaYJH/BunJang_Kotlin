package com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.trade.models

import com.google.gson.annotations.SerializedName

data class TradeDirect(
    @SerializedName("deal_type")
    val deal_type: Int,

    @SerializedName("pay")
    val pay: String
)
