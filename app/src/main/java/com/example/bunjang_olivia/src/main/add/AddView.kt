package com.example.bunjang_olivia.src.main.add

import com.example.rc_aos_tem.config.BaseResponse

interface AddView {

    fun onPostAddSuccess(response: BaseResponse)

    fun onPostAddFailure(message: String)
}