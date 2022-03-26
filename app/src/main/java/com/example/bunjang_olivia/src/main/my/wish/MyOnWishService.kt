package com.example.bunjang_olivia.src.main.my.wish

import com.example.android_mvc_template.config.ApplicationClass
import com.example.bunjang_olivia.src.main.my.wish.models.OnWishResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyOnWishService(val myOnWishView: MyOnWishView) {

    fun tryGetMyOnWish(){
        val myOnWishInterface = ApplicationClass.sRetrofit.create(MyWishInterface::class.java)
        myOnWishInterface.getMyWish().enqueue(object: Callback<OnWishResponse> {
            override fun onResponse(
                call: Call<OnWishResponse>,
                response: Response<OnWishResponse>
            ) {
                myOnWishView.onGetOnMyWishSuccess(response.body() as OnWishResponse)
            }

            override fun onFailure(call: Call<OnWishResponse>, t: Throwable) {
                myOnWishView.onGetOnMyWishFailure(t.message ?: "통신 오류")
            }

        })
    }
}