package com.example.bunjang_olivia.src.main.home.tablayout.brand

import com.example.bunjang_olivia.src.main.home.tablayout.brand.models.BrandResponse

interface BrandView {

    fun onGetBrandSuccess(response: BrandResponse)

    fun onGetBrandFailure(message: String)
}