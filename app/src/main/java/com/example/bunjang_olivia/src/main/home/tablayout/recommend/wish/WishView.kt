package com.example.bunjang_olivia.src.main.home.tablayout.recommend.wish

import com.example.bunjang_olivia.src.main.home.tablayout.recommend.models.WishListResponse
import com.example.rc_aos_tem.config.BaseResponse

interface WishView {

    fun onPostWishSuccess(response: BaseResponse)

    fun onPostWishFailure(message: String)

    fun onPatchWishSuccess(response: BaseResponse)

    fun onPatchWishFailure(message:String)

    fun onGetWishListSuccess(response: WishListResponse)

    fun onGetWishListFailure(message: String)
}