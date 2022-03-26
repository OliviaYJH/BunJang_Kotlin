package com.example.bunjang_olivia.src.main.my.setting.account

import com.example.android_mvc_template.config.ApplicationClass
import com.example.bunjang_olivia.src.main.my.setting.account.add.addAccount.AddAccountInterface
import com.example.bunjang_olivia.src.main.my.setting.account.add.models.GetAccountResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountService(val accountView: AccountView) {

    fun tryGetAccount(userId: Int){
        val getAccountInterface = ApplicationClass.sRetrofit.create(AccountInterface::class.java)
        getAccountInterface.getAccount(userId).enqueue(object: Callback<GetAccountResponse> {
            override fun onResponse(
                call: Call<GetAccountResponse>,
                response: Response<GetAccountResponse>
            ) {
                accountView.onGetAccountSuccess(response.body() as GetAccountResponse)
            }

            override fun onFailure(call: Call<GetAccountResponse>, t: Throwable) {
                accountView.onGetAccountFailure(t.message ?: "통신 오류")
            }

        })
    }
}