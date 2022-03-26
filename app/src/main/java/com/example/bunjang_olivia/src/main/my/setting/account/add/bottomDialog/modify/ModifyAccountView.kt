package com.example.bunjang_olivia.src.main.my.setting.account.add.bottomDialog.modify

import com.example.rc_aos_tem.config.BaseResponse

interface ModifyAccountView {

    fun onPutAccountSuccess(response: BaseResponse)

    fun onPutAccountFailure(message: String)
}