package com.example.bunjang_olivia.src.main.my.bottomdialog.store

import com.example.bunjang_olivia.src.main.my.bottomdialog.store.models.StoreDetailResponse

interface StoreView {

    fun onGetStoreSuccess(response: StoreDetailResponse)

    fun onGetStoreFailure(message: String)
}