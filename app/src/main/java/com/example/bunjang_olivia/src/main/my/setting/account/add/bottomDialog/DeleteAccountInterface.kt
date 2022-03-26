package com.example.bunjang_olivia.src.main.my.setting.account.add.bottomDialog

import com.example.bunjang_olivia.src.main.my.setting.account.add.addAccount.models.Account
import com.example.rc_aos_tem.config.BaseResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.Path

interface DeleteAccountInterface {

    @PATCH("/api/users/account/{userId}")
    fun pathAccount(
        @Path("userId") userId: Int,
        @Body account_num: Account
    ): Call<BaseResponse>
}