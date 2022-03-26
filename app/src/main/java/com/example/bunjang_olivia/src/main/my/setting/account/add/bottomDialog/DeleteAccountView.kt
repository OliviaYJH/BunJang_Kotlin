package com.example.bunjang_olivia.src.main.my.setting.account.add.bottomDialog


import com.example.rc_aos_tem.config.BaseResponse

interface DeleteAccountView {

    fun onPatchAccountSuccess(response: BaseResponse)

    fun onPatchAccountFailure(message: String)
}