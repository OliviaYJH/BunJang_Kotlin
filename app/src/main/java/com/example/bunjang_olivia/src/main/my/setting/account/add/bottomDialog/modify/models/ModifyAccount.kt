package com.example.bunjang_olivia.src.main.my.setting.account.add.bottomDialog.modify.models

import com.google.gson.annotations.SerializedName

data class ModifyAccount(
    @SerializedName("changeAccountNum")
    val changeAccountNum: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("bank")
    val bank: String,

    @SerializedName("account_num")
    val account_num: String,

    @SerializedName("for_sale")
    val for_sale: Int,

    @SerializedName("for_return")
    val for_return: Int
)
