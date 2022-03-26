package com.example.bunjang_olivia.src.main.my.setting.account.add.bottomDialog.modify

import com.example.android_mvc_template.config.ApplicationClass
import com.example.bunjang_olivia.src.main.my.setting.account.add.bottomDialog.modify.models.ModifyAccount
import com.example.rc_aos_tem.config.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModifyAccountService(val modifyAccountView: ModifyAccountView) {

    fun tryPutAccount(userId: Int, modify: ModifyAccount){
        val putAccountInterface = ApplicationClass.sRetrofit.create(ModifyAccountInterface::class.java)
        putAccountInterface.putAccount(userId, modify).enqueue(object: Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                modifyAccountView.onPutAccountSuccess(response.body() as BaseResponse)
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                modifyAccountView.onPutAccountFailure(t.message ?: "통신 오류")
            }

        })
    }
}