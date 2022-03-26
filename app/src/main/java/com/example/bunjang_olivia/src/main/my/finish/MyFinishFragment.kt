package com.example.bunjang_olivia.src.main.my.finish

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.FragmentMyFinishBinding
import com.example.bunjang_olivia.src.main.my.tradeRecord.buy.TradeService
import com.example.bunjang_olivia.src.main.my.tradeRecord.buy.models.BuyResponse
import com.example.bunjang_olivia.src.main.my.tradeRecord.buy.TradeView
import com.example.rc_aos_tem.config.BaseFragment

class MyFinishFragment : BaseFragment<FragmentMyFinishBinding>(FragmentMyFinishBinding::bind,
        R.layout.fragment_my_finish), TradeView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        TradeService(this).tryGetBuy()
    }

    override fun onGetBuySuccess(response: BuyResponse) {
        // success

        if(response.result.isNotEmpty()){
            // 구매한 상품이 있는 경우
            binding.contraintFinish.isGone = true


        }else{
            // 구매한 상품이 없는 경우
            binding.contraintFinish.isVisible = true

        }

        /*
        if(response.isSuccess){
            binding.ivNothing.isGone = true
            binding.tvNothing.isGone = true
        }else{
            binding.ivNothing.isVisible = true
            binding.tvNothing.isVisible = true
        }

         */
    }

    override fun onGetBuyFailure(message: String) {
        showCustomToast(message)
    }

}