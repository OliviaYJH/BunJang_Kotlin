package com.example.bunjang_olivia.src.main.my.setting.account.add.addAccount

import com.example.android_mvc_template.config.ApplicationClass
import com.example.bunjang_olivia.src.main.my.setting.account.add.addAccount.models.AddAcount
import com.example.rc_aos_tem.config.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddAccountService(val addAccountView: AddAccountView) {

    fun tryPostAddAccount(userId: Int, add: AddAcount){
        val addAccountInterface = ApplicationClass.sRetrofit.create(AddAccountInterface::class.java)
        addAccountInterface.postAccount(userId, add).enqueue(object:
            Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                addAccountView.onPostAddAccountSuccess(response.body() as BaseResponse)
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                addAccountView.onPostAddAccountFailure(t.message ?: "통신 오류")
            }
        })
    }

}