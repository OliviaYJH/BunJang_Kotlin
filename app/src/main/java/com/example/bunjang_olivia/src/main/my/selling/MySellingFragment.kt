package com.example.bunjang_olivia.src.main.my.selling

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.FragmentMySellingBinding
import com.example.bunjang_olivia.src.main.my.tradeRecord.sell.SellService
import com.example.bunjang_olivia.src.main.my.tradeRecord.sell.SellView
import com.example.bunjang_olivia.src.main.my.tradeRecord.sell.models.SellResponse
import com.example.bunjang_olivia.util.recycler.mySell.MySellAdapter
import com.example.bunjang_olivia.util.recycler.mySell.MySellData
import com.example.rc_aos_tem.config.BaseFragment
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class MySellingFragment : BaseFragment<FragmentMySellingBinding>(FragmentMySellingBinding::bind,
        R.layout.fragment_my_selling), SellView{

    lateinit var mySellAdapter: MySellAdapter
    val mySellDatas = mutableListOf<MySellData>()

    var time: String?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SellService(this).tryGetSell()


        binding.ivSellEdit.setOnClickListener {
            // 수정

        }
    }

    override fun onGetSellSuccess(response: SellResponse) {

        if(response.result.isNotEmpty()){
            binding.constraintSelling.isGone = true
            binding.linearMySell.isVisible = true
            binding.tvSellingCount.text = response.result.size.toString() + "개"
        }else{
            binding.constraintSelling.isVisible = true
            binding.linearMySell.isGone = true
        }

        mySellAdapter = MySellAdapter(requireContext() as FragmentActivity)
        binding.recyclerMySell.adapter = mySellAdapter

        // now time
        val now: Long = System.currentTimeMillis()
        val date = Date(now)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale("ko", "KR"))
        val stringTime = dateFormat.format(date)

        val now_day = Integer.parseInt(stringTime.substring(8,10))
        val now_hour = Integer.parseInt(stringTime.substring(11,13))
        val now_minute = Integer.parseInt(stringTime.substring(14,16))
        val now_second = Integer.parseInt(stringTime.substring(17,19))

        for(i in response.result.indices){
            var result = response.result[i]

            // price
            var format = DecimalFormat("#,###")
            var product_price = format.format(result.price)
            var price = product_price + "원"

            // time
            var product_day = Integer.parseInt(result.created_at.substring(8,10))
            var product_hour = Integer.parseInt(result.created_at.substring(11,13))
            var product_minute = Integer.parseInt(result.created_at.substring(14,16))
            var product_second = Integer.parseInt(result.created_at.substring(17,19))


            if(now_day-product_day == 0){
                // 같은날
                if(now_hour-product_hour-9 == 0){
                    if(now_minute - product_minute == 0){
                        time = (now_second - product_second).toString() + "초 전"
                    }else{
                        time = (now_minute - product_minute).toString() + "분 전"
                    }
                }else{
                    time = (now_hour- product_hour-9).toString() + "시간 전"
                }
            }else{
                // 다른날 등록
                time = (now_day-product_day).toString() + "일 전"
            }


            mySellDatas.apply {
                add(MySellData(price = price, title = result.title, created_at = time!!, item_id = result.item_id,
                    safety_pay = result.safety_pay, sale = result.sale, image_path = "https://bjclone.s3.ap-northeast-2.amazonaws.com/" + result.image_path))
            }
        }

        mySellAdapter.mySellDatas = mySellDatas
        mySellAdapter.notifyDataSetChanged()

    }

    override fun onGetSellFailure(message: String) {
        showCustomToast(message)
    }

    override fun onResume() {
        super.onResume()
        mySellDatas.clear()
    }


}