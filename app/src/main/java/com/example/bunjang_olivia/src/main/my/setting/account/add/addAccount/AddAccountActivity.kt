package com.example.bunjang_olivia.src.main.my.setting.account.add.addAccount

import android.os.Bundle
import com.example.android_mvc_template.config.ApplicationClass.Companion.sSharedPreferences
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.ActivityAddAccountBinding
import com.example.bunjang_olivia.src.main.my.setting.account.add.addAccount.bottomDialog.BankBottomDialogFragment
import com.example.bunjang_olivia.src.main.my.setting.account.add.addAccount.models.AddAcount
import com.example.rc_aos_tem.config.BaseActivity
import com.example.rc_aos_tem.config.BaseResponse

class AddAccountActivity : BaseActivity<ActivityAddAccountBinding>(ActivityAddAccountBinding::inflate)
    , AddAccountView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding.ivClose.setOnClickListener { finish() }


        binding.etAccountHolder.setOnClickListener {
            binding.linearAddAccountHolder.setBackgroundResource(R.drawable.bottom_border_black)
            binding.linearAddAccountNumber.setBackgroundResource(R.drawable.bottom_border_gray)
        }

        binding.linearAddAccountBank.setOnClickListener {
            // 은행 선택 bottom dialog 띄우기
            val bottomsheet = BankBottomDialogFragment()
            bottomsheet.show(this.supportFragmentManager, bottomsheet.tag)

        }



        binding.etAccountNumber.setOnClickListener {
            binding.etAccountBank.text = sSharedPreferences.getString("bank", "error")
            binding.linearAddAccountNumber.setBackgroundResource(R.drawable.bottom_border_black)
            binding.linearAddAccountHolder.setBackgroundResource(R.drawable.bottom_border_gray)
        }

        binding.btnAddNewAccount.setOnClickListener {
            var holder = binding.etAccountHolder.text.toString()
            var bank = sSharedPreferences.getString("bank", "error")
            var accountNumber = binding.etAccountNumber.text.toString()

            var userId = sSharedPreferences.getInt("userId", 0)
            var add = AddAcount(holder, bank!!, accountNumber)
            AddAccountService(this).tryPostAddAccount(userId, add)
        }
    }

    override fun onPostAddAccountSuccess(response: BaseResponse) {
        showCustomToast("계좌가 추가되었습니다")
        finish()
    }

    override fun onPostAddAccountFailure(message: String) {
        showCustomToast(message)
    }




}