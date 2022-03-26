package com.example.bunjang_olivia.src.main.my.bottomdialog.store

import com.example.android_mvc_template.config.ApplicationClass
import com.example.bunjang_olivia.src.main.my.bottomdialog.store.models.StoreDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreService(val storeView: StoreView) {

    fun tryGetStore(userId: Int){
        val storeInterface = ApplicationClass.sRetrofit.create(StoreInterface::class.java)
        storeInterface.getStore(userId).enqueue(object: Callback<StoreDetailResponse> {
            override fun onResponse(
                call: Call<StoreDetailResponse>,
                response: Response<StoreDetailResponse>
            ) {
                storeView.onGetStoreSuccess(response.body() as StoreDetailResponse)
            }

            override fun onFailure(call: Call<StoreDetailResponse>, t: Throwable) {
                storeView.onGetStoreFailure(t.message ?: "통신 오류")
            }

        })
    }
}