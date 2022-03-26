package com.example.bunjang_olivia.src.main.my

import com.example.android_mvc_template.config.ApplicationClass
import com.example.bunjang_olivia.src.main.my.models.MyPageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageService(val myPageView: MyPageView) {

    fun tryGetMyPage(userId: Int){
        val myPageInterface = ApplicationClass.sRetrofit.create(MyPageInterface::class.java)
        myPageInterface.getMyPage(userId).enqueue(object: Callback<MyPageResponse> {
            override fun onResponse(
                call: Call<MyPageResponse>,
                response: Response<MyPageResponse>
            ) {
                myPageView.onGetMyPageSuccess(response.body() as MyPageResponse)
            }

            override fun onFailure(call: Call<MyPageResponse>, t: Throwable) {
                myPageView.onGetMyPageFailure(t.message ?: "통신 오류")
            }

        })
    }
}