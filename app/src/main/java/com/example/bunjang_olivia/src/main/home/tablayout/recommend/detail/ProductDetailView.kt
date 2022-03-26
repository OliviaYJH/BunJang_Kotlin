package com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail

import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.models.ProductDetailResponse

interface ProductDetailView {

    fun onGetProductDetailSuccess(response: ProductDetailResponse)

    fun onGetProductDetailFailure(message: String)
}