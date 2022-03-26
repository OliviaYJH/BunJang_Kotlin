package com.example.bunjang_olivia.src.main.my.tradeRecord.balance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_mvc_template.config.ApplicationClass.Companion.sSharedPreferences
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.FragmentBalanceRecordBinding
import com.example.bunjang_olivia.src.main.my.setting.account.AccountService
import com.example.bunjang_olivia.src.main.my.setting.account.AccountView
import com.example.bunjang_olivia.src.main.my.setting.account.add.models.GetAccountResponse
import com.example.rc_aos_tem.config.BaseFragment

class BalanceRecordFragment : BaseFragment<FragmentBalanceRecordBinding>(FragmentBalanceRecordBinding::bind
        ,R.layout.fragment_balance_record), AccountView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoadingDialog(requireContext())

        var userId = sSharedPreferences.getInt("userId", 0)
        AccountService(this).tryGetAccount(userId)
    }

    override fun onGetAccountSuccess(response: GetAccountResponse) {
        dismissLoadingDialog()

        if(response.result.isNotEmpty()){
            // 계좌 있는 경우
            binding.tvBalanceAccount.text = response.result[0].bank + " " + response.result[0].account_number
        }
    }

    override fun onGetAccountFailure(message: String) {
        showCustomToast(message)
    }

}