package com.example.bunjang_olivia.src.main.my.setting.account.add.bottomDialog.modify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.android_mvc_template.config.ApplicationClass
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.ActivityModifyAccountBinding
import com.example.bunjang_olivia.src.main.my.setting.account.add.bottomDialog.modify.models.ModifyAccount
import com.example.rc_aos_tem.config.BaseActivity
import com.example.rc_aos_tem.config.BaseResponse

class ModifyAccountActivity : BaseActivity<ActivityModifyAccountBinding>(ActivityModifyAccountBinding::inflate), ModifyAccountView {

    var userId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var my_account_number = intent.getStringExtra("account_number")
        var my_bank = intent.getStringExtra("bank")
        var my_user_name = intent.getStringExtra("user_name")
        var my_for_sale = intent.getIntExtra("for_sale", -1)
        var my_for_return = intent.getIntExtra("for_return", -1)

        Log.d("result", my_account_number!!)
        Log.d("result", my_bank!!)
        Log.d("result", my_user_name!!)
        Log.d("result", my_for_sale!!.toString())
        Log.d("result", my_for_return!!.toString())

        binding.etAccountHolder.setText(my_user_name!!)
        binding.etAccountBank.text = my_bank!!
        binding.etAccountNumber.setText(my_account_number!!)



        userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)


        binding.btnModifyAccount.setOnClickListener {
            val new_account_num = binding.etAccountNumber.text.toString()
            val modify = ModifyAccount(my_account_number, my_user_name, my_bank, new_account_num, my_for_sale, my_for_return)
            ModifyAccountService(this).tryPutAccount(userId!!,modify)
        }

        binding.icLeftArrow.setOnClickListener { finish() }
        binding.ivClose.setOnClickListener { finish() }
    }

    override fun onPutAccountSuccess(response: BaseResponse) {
        Log.d("result", "modify success")
        finish()
    }

    override fun onPutAccountFailure(message: String) {
        showCustomToast(message)
    }

}