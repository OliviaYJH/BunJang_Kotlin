package com.example.bunjang_olivia.src.main.home.tablayout.brand

import com.example.android_mvc_template.config.ApplicationClass
import com.example.bunjang_olivia.src.main.home.tablayout.brand.models.BrandResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BrandService(val brandView: BrandView) {

    fun tryGetBrand(brandId: Int, offset: Int, limit: Int){
        val brandInterface = ApplicationClass.sRetrofit.create(BrandInterface::class.java)
        brandInterface.getBrand(brandId, offset,limit).enqueue(object : Callback<BrandResponse> {
            override fun onResponse(call: Call<BrandResponse>, response: Response<BrandResponse>) {
                brandView.onGetBrandSuccess(response.body() as BrandResponse)
            }

            override fun onFailure(call: Call<BrandResponse>, t: Throwable) {
                brandView.onGetBrandFailure(t.message ?: "통신 오류")
            }

        })
    }
}