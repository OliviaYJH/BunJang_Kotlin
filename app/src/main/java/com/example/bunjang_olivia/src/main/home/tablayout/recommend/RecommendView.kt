package com.example.bunjang_olivia.src.main.home.tablayout.recommend

import com.example.bunjang_olivia.src.main.home.tablayout.recommend.models.RecommendListResponse

interface RecommendView {

    fun onGetRecommendSuccess(response : RecommendListResponse)

    fun onGetRecommendFailure(message: String)
}