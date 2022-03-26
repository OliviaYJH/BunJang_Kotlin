package com.example.bunjang_olivia.src.main.home.tablayout.brand

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.FragmentBrandBinding
import com.example.bunjang_olivia.src.main.home.tablayout.brand.models.BrandResponse
import com.example.bunjang_olivia.util.recycler.brand.BrandAdapter
import com.example.bunjang_olivia.util.recycler.brand.BrandData
import com.example.rc_aos_tem.config.BaseFragment

class BrandFragment : BaseFragment<FragmentBrandBinding>(FragmentBrandBinding::bind, R.layout.fragment_brand)
    , BrandView{

    lateinit var brandAdapter: BrandAdapter
    val brandDatas = mutableListOf<BrandData>()
    var title: String?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoadingDialog(requireContext())

        // nike -> 3
        BrandService(this).tryGetBrand(3, 0, 20)
    }

    override fun onGetBrandSuccess(response: BrandResponse) {
        dismissLoadingDialog()

        brandAdapter = BrandAdapter(context as FragmentActivity?)
        binding.recyclerBrand.adapter = brandAdapter

        for(i in response.result.indices){
            var result= response.result[i]

            brandDatas.apply {
                add(BrandData(created_at = result.created_at, image_path = "https://bjclone.s3.ap-northeast-2.amazonaws.com/" + result.image_path, item_id = result.item_id,
                    location = result.location, price = result.price, safety_pay = result.safety_pay,
                        title = result.title, wish_count = result.wish_count))
            }
        }

        brandAdapter.brandDatas = brandDatas
        brandAdapter.notifyDataSetChanged()
    }

    override fun onGetBrandFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }

    override fun onStop() {
        super.onStop()
        brandDatas.clear()
    }
}