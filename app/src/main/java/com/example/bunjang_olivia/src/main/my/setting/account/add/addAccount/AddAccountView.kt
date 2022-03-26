package com.example.bunjang_olivia.src.main.my.setting.account.add.addAccount

import com.example.bunjang_olivia.src.main.my.setting.account.add.models.GetAccountResponse
import com.example.rc_aos_tem.config.BaseResponse

interface AddAccountView {

    fun onPostAddAccountSuccess(response: BaseResponse)

    fun onPostAddAccountFailure(message: String)


}