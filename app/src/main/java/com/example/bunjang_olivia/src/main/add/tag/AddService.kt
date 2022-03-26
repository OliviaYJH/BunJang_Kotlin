package com.example.bunjang_olivia.src.main.add.tag

import android.util.Log
import com.example.android_mvc_template.config.ApplicationClass
import com.example.bunjang_olivia.src.main.add.AddInterface
import com.example.bunjang_olivia.src.main.add.AddView
import com.example.rc_aos_tem.config.BaseResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddService(val addView: AddView) {

    fun tryPostAddProduct(data: HashMap<String, RequestBody>, image: ArrayList<MultipartBody.Part>){
        val addProductInterface = ApplicationClass.sRetrofit.create(AddInterface::class.java)
        addProductInterface.postAdd(data, image).enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                addView.onPostAddSuccess(response.body() as BaseResponse)
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                addView.onPostAddFailure(t.message ?: "통신 오류")
            }

        })
    }
}