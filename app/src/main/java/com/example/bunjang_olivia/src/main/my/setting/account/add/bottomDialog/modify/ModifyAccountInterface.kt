package com.example.bunjang_olivia.src.main.my.setting.account.add.bottomDialog.modify

import com.example.bunjang_olivia.src.main.my.setting.account.add.bottomDialog.modify.models.ModifyAccount
import com.example.rc_aos_tem.config.BaseResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

interface ModifyAccountInterface {

    @PUT("/api/users/account/{userId}")
    fun putAccount(
        @Path("userId") userId: Int,
        @Body modify: ModifyAccount
    ): Call<BaseResponse>
}