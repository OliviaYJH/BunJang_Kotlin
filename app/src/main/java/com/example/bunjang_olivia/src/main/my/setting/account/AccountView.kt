package com.example.bunjang_olivia.src.main.my.setting.account

import com.example.bunjang_olivia.src.main.my.setting.account.add.models.GetAccountResponse

interface AccountView {
    fun onGetAccountSuccess(response: GetAccountResponse)

    fun onGetAccountFailure(message: String)
}