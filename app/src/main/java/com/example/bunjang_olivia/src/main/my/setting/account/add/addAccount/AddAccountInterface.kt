package com.example.bunjang_olivia.src.main.my.setting.account.add.addAccount

import com.example.bunjang_olivia.src.main.my.setting.account.add.addAccount.models.AddAcount
import com.example.rc_aos_tem.config.BaseResponse
import retrofit2.Call
import retrofit2.http.*

interface AddAccountInterface {

    @POST("/api/users/account/{userId}")
    fun postAccount(
        @Path("userId") userId: Int,
        @Body add: AddAcount
    ): Call<BaseResponse>


}