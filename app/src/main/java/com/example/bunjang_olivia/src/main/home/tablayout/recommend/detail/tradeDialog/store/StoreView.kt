package com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.store

import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.store.models.MyFollowingResponse
import com.example.rc_aos_tem.config.BaseResponse

interface StoreView {

    fun onPostStoreSuccess(response: BaseResponse)

    fun onPostStoreFailures(message: String)

    fun onPatchStoreSuccess(response:BaseResponse)

    fun onPatchStoreFailure(message: String)

    fun onGetMyFollowingSuccess(response: MyFollowingResponse)

    fun onGetMyFollowingFailure(message: String)
}