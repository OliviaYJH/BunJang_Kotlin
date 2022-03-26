package com.example.bunjang_olivia.src.main.my.setting.account.add.bottomDialog

import com.example.android_mvc_template.config.ApplicationClass
import com.example.bunjang_olivia.src.main.my.setting.account.add.addAccount.models.Account
import com.example.rc_aos_tem.config.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteAccountService(val deleteAccountView: DeleteAccountView) {

    fun tryPatchAccount(userId: Int, account_num: Account){
        val patchAccountInterface = ApplicationClass.sRetrofit.create(DeleteAccountInterface::class.java)
        patchAccountInterface.pathAccount(userId, account_num).enqueue(object :
            Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                deleteAccountView.onPatchAccountSuccess(response.body() as BaseResponse)
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                deleteAccountView.onPatchAccountFailure(t.message ?: "통신 오류")
            }

        })
    }
}