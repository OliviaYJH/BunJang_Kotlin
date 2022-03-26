package com.example.bunjang_olivia.src.main.home.tablayout.recommend.wish

import com.example.android_mvc_template.config.ApplicationClass
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.models.WishListResponse
import com.example.rc_aos_tem.config.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WishService(val wishView: WishView) {

    fun tryPostWish(itemId: Int){
        val wishInterface = ApplicationClass.sRetrofit.create(WishInterface::class.java)
        wishInterface.postWish(itemId).enqueue(object: Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                wishView.onPostWishSuccess(response.body() as BaseResponse)
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                wishView.onPostWishFailure(t.message ?: "통신 오류")
            }

        })
    }

    fun tryPatchWish(itemId: Int){
        val wishPatchInterface = ApplicationClass.sRetrofit.create(WishInterface::class.java)
        wishPatchInterface.patchWish(itemId).enqueue(object: Callback<BaseResponse>{
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                wishView.onPatchWishSuccess(response.body() as BaseResponse)
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                wishView.onPatchWishFailure(t.message ?: "통신 오류")
            }

        })
    }

    fun tryGetWishList(){
        val wishGetInterface = ApplicationClass.sRetrofit.create(WishInterface::class.java)
        wishGetInterface.getWish().enqueue(object: Callback<WishListResponse>{
            override fun onResponse(
                call: Call<WishListResponse>,
                response: Response<WishListResponse>
            ) {
                wishView.onGetWishListSuccess(response.body() as WishListResponse)
            }

            override fun onFailure(call: Call<WishListResponse>, t: Throwable) {
                wishView.onGetWishListFailure(t.message ?: "통신 오류")
            }

        })
    }
}

