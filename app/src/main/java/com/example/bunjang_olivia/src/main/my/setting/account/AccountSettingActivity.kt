package com.example.bunjang_olivia.src.main.my.setting.account

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.android_mvc_template.config.ApplicationClass
import com.example.android_mvc_template.config.ApplicationClass.Companion.sSharedPreferences
import com.example.bunjang_olivia.databinding.ActivityAccountSettingBinding
import com.example.bunjang_olivia.src.main.my.setting.account.add.addAccount.AddAccountActivity
import com.example.bunjang_olivia.src.main.my.setting.account.add.models.GetAccountResponse
import com.example.bunjang_olivia.util.recycler.account.AccountAdapter
import com.example.bunjang_olivia.util.recycler.account.AccountData
import com.example.rc_aos_tem.config.BaseActivity

class AccountSettingActivity : BaseActivity<ActivityAccountSettingBinding>(ActivityAccountSettingBinding::inflate)
        , AccountView{

    lateinit var accountAdapter: AccountAdapter
    val accountDatas = mutableListOf<AccountData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showLoadingDialog(this)
        var userId = sSharedPreferences.getInt("userId", 0)
        AccountService(this).tryGetAccount(userId)


        binding.btnClose.setOnClickListener { finish() }

        binding.btnAddAccount.setOnClickListener {
            startActivity(Intent(this, AddAccountActivity::class.java))
        }
    }

    override fun onGetAccountSuccess(response: GetAccountResponse) {
        dismissLoadingDialog()

        if(response.result.isNotEmpty()){
           // 등록한 계좌 있는 경우
            binding.recyclerAccount.isVisible = true
            binding.linearNone.isGone = true
        }else{
            // 등록한 계좌 없는 경우
            binding.linearNone.isVisible = true
            binding.recyclerAccount.isGone = true
        }

        if(response.result.size == 2) binding.btnAddAccount.isInvisible = true

        accountAdapter = AccountAdapter(this)
        binding.recyclerAccount.adapter = accountAdapter

        for(i in response.result.indices){

            var result = response.result[i]

            accountDatas.apply {
                add(AccountData(account_number = result.account_number, bank = result.bank,
                    user_name = result.user_name, for_sale = result.for_sale,
                for_return = result.for_return))
            }
        }

        accountAdapter.accoundDatas = accountDatas
        accountAdapter.notifyDataSetChanged()
    }

    override fun onGetAccountFailure(message: String) {
        showCustomToast(message)
    }

    override fun onRestart() {
        super.onRestart()

        accountDatas.clear()

        showLoadingDialog(this)
        var userId = sSharedPreferences.getInt("userId", 0)
        AccountService(this).tryGetAccount(userId)
    }

}