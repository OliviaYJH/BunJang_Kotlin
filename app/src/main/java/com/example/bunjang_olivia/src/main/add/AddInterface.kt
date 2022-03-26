package com.example.bunjang_olivia.src.main.add

import com.example.bunjang_olivia.src.main.add.models.AddProduct
import com.example.rc_aos_tem.config.BaseResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface AddInterface {

    @Multipart
    @POST("/api/items")
    fun postAdd(
        @PartMap data: HashMap<String, RequestBody>,
        @Part image: ArrayList<MultipartBody.Part>
    ): Call<BaseResponse>
}