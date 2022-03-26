package com.example.bunjang_olivia.src.main.my.wish

import com.example.bunjang_olivia.src.main.my.wish.models.OnWishResponse

interface MyOnWishView {

    fun onGetOnMyWishSuccess(response: OnWishResponse)

    fun onGetOnMyWishFailure(message: String)
}