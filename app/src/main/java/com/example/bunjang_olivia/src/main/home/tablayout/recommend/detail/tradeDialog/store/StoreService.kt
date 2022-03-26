package com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.store

import com.example.android_mvc_template.config.ApplicationClass
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.store.models.Delete
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.store.models.Follow
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.store.models.MyFollowingResponse
import com.example.rc_aos_tem.config.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreService(val storeView: StoreView) {

    fun tryGetStore(userId: Int, follow: Follow){
        val getStoreInterface = ApplicationClass.sRetrofit.create(StoreInterface::class.java)
        getStoreInterface.postStore(userId, follow).enqueue(object: Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                storeView.onPostStoreSuccess(response.body() as BaseResponse)
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
               storeView.onPostStoreFailures(t.message ?: "통신 오류")
            }

        })
    }

    fun tryPatchStore(userId: Int, delete: Delete){
        val patchStoreInterface = ApplicationClass.sRetrofit.create(StoreInterface::class.java)
        patchStoreInterface.patchStore(userId, delete).enqueue(object: Callback<BaseResponse>{
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                storeView.onPatchStoreSuccess(response.body() as BaseResponse)
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                storeView.onPatchStoreFailure(t.message ?: "통신 오류")
            }

        })
    }

    fun tryGetMyFollow(){
        val getFollowInterface = ApplicationClass.sRetrofit.create(StoreInterface::class.java)
        getFollowInterface.getMyFollowers().enqueue(object: Callback<MyFollowingResponse>{
            override fun onResponse(
                call: Call<MyFollowingResponse>,
                response: Response<MyFollowingResponse>
            ) {
                storeView.onGetMyFollowingSuccess(response.body() as MyFollowingResponse)
            }

            override fun onFailure(call: Call<MyFollowingResponse>, t: Throwable) {
                storeView.onGetMyFollowingFailure(t.message ?: "통신 오류")
            }

        })
    }
}