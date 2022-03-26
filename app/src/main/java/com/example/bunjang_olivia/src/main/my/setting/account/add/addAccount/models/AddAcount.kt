package com.example.bunjang_olivia.src.main.my.setting.account.add.addAccount.models

import com.example.rc_aos_tem.config.BaseResponse
import com.google.gson.annotations.SerializedName
import retrofit2.Call

data class AddAcount(
    @SerializedName("name")
    val name: String,

    @SerializedName("bank")
    val bank: String,

    @SerializedName("account_num")
    val account_num: String
)
