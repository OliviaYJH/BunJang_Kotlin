package com.example.bunjang_olivia.src.main.my.bottomdialog.store.models

data class StoreDetailResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)