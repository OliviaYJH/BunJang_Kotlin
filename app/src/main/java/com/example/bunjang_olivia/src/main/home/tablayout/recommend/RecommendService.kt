package com.example.bunjang_olivia.src.main.home.tablayout.recommend

import android.util.Log
import com.example.android_mvc_template.config.ApplicationClass
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.models.RecommendListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendService(val recommendView: RecommendView) {

    fun tryGetRecommend(offset: Int, limit: Int){
        val recommendInterface = ApplicationClass.sRetrofit.create(RecommendInterface::class.java)
        recommendInterface.getRecommend(offset, limit).enqueue(object:
            Callback<RecommendListResponse> {
            override fun onResponse(
                call: Call<RecommendListResponse>,
                response: Response<RecommendListResponse>
            ) {
                recommendView.onGetRecommendSuccess(response.body() as RecommendListResponse)
            }

            override fun onFailure(call: Call<RecommendListResponse>, t: Throwable) {
                recommendView.onGetRecommendFailure(t.message ?: "통신 오류")
            }

        })
    }

}