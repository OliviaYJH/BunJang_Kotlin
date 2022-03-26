package com.example.bunjang_olivia.src.main.my

import com.example.bunjang_olivia.src.main.my.models.MyPageResponse

interface MyPageView {

    fun onGetMyPageSuccess(response: MyPageResponse)

    fun onGetMyPageFailure(message: String)
}