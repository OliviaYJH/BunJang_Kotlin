package com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail

import com.example.android_mvc_template.config.ApplicationClass
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.models.ProductDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailService(val productDetailView: ProductDetailView) {

    fun tryGetProductDetail(itemId: Int){
        val productDetailInterface = ApplicationClass.sRetrofit.create(ProductDetailInterface::class.java)
        productDetailInterface.getDetail(itemId).enqueue(object: Callback<ProductDetailResponse> {
            override fun onResponse(
                call: Call<ProductDetailResponse>,
                response: Response<ProductDetailResponse>
            ) {
                productDetailView.onGetProductDetailSuccess(response.body() as ProductDetailResponse)
            }

            override fun onFailure(call: Call<ProductDetailResponse>, t: Throwable) {
                productDetailView.onGetProductDetailFailure(t.message ?: "통신 오류")
            }

        })
    }
}