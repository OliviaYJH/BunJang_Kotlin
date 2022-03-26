package com.example.bunjang_olivia.src.main.my.setting.account

import com.example.bunjang_olivia.src.main.my.setting.account.add.models.GetAccountResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AccountInterface {

    @GET("/api/users/account/{userId}")
    fun getAccount(
        @Path("userId") userId: Int
    ): Call<GetAccountResponse>
}