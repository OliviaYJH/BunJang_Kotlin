package com.example.bunjang_olivia.src.main.my.tradeRecord.buy

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.FragmentBuyRecordBinding
import com.example.bunjang_olivia.src.main.my.tradeRecord.buy.models.BuyResponse
import com.example.bunjang_olivia.util.recycler.buyAdapter.BuyAdapter
import com.example.bunjang_olivia.util.recycler.buyAdapter.BuyData
import com.example.rc_aos_tem.config.BaseFragment
import java.text.DecimalFormat

class BuyRecordFragment : BaseFragment<FragmentBuyRecordBinding>(FragmentBuyRecordBinding::bind,
        R.layout.fragment_buy_record), TradeView {

    lateinit var buyAdapter: BuyAdapter
    val buyDatas = mutableListOf<BuyData>()
    var time: String?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoadingDialog(requireContext())
        TradeService(this).tryGetBuy()
    }

    override fun onGetBuySuccess(response: BuyResponse) {
        dismissLoadingDialog()

        //success
        if(response.result.isNotEmpty()){
            // 구매 내역이 있는 경우
            binding.tvBuy.isGone = true
            binding.recyclerFinishBuy.isVisible = true



            buyAdapter = BuyAdapter(requireContext() as FragmentActivity)
            binding.recyclerFinishBuy.adapter = buyAdapter


            var format = DecimalFormat("#,###")

            for(i in response.result.indices){
                var result = response.result[i]

                var product_price = format.format(result.price)
                var price = product_price + "원"

                var trade_year = Integer.parseInt(result.created_at.substring(0,4))
                var trade_month = Integer.parseInt(result.created_at.substring(5,7))
                var trade_day = Integer.parseInt(result.created_at.substring(8,10))

                buyDatas.apply {
                    add(BuyData(image_path ="https://bjclone.s3.ap-northeast-2.amazonaws.com/" + result.image_path,
                        title = result.title, price = price,
                        deal_id = result.deal_id, process = result.process,
                        safety_pay = result.safety_pay, seller_id = result.seller_id,
                        created_at = "$trade_year.$trade_month.$trade_day"
                    ))
                }

            }


            buyAdapter.buyDatas = buyDatas
            buyAdapter.notifyDataSetChanged()


        }else{
            // 구매 내역이 없는 경우
            binding.tvBuy.isVisible = true
            binding.recyclerFinishBuy.isGone = true
        }


    }

    override fun onGetBuyFailure(message: String) {
        showCustomToast(message)
    }

    override fun onResume() {
        super.onResume()
        buyDatas.clear()
    }

}