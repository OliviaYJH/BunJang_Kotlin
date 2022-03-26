package com.example.bunjang_olivia.src.main.my.tradeRecord.sell

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.FragmentSellRecordBinding
import com.example.bunjang_olivia.src.main.my.tradeRecord.sell.models.SellResponse
import com.example.rc_aos_tem.config.BaseFragment

class SellRecordFragment : BaseFragment<FragmentSellRecordBinding>(FragmentSellRecordBinding::bind, R.layout.fragment_sell_record)
        , SellView{

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SellService(this).tryGetSell()
    }

    override fun onGetSellSuccess(response: SellResponse) {
        //success
        Log.d("result","sell success")
    }

    override fun onGetSellFailure(message: String) {
        showCustomToast(message)
    }

}