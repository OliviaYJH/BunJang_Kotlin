package com.example.bunjang_olivia.src.login.settingName

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.android_mvc_template.config.ApplicationClass.Companion.editor
import com.example.android_mvc_template.config.ApplicationClass.Companion.sSharedPreferences
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.ActivityLoginSettingStoreNameBinding
import com.example.bunjang_olivia.src.MainActivity
import com.example.bunjang_olivia.src.login.LoginService
import com.example.bunjang_olivia.src.login.LoginView
import com.example.bunjang_olivia.src.login.models.LoginResponse
import com.example.bunjang_olivia.src.login.models.RegisterResponse
import com.example.bunjang_olivia.src.login.models.Users
import com.example.rc_aos_tem.config.BaseActivity
import com.kakao.sdk.user.model.User

class LoginSettingStoreNameActivity : BaseActivity<ActivityLoginSettingStoreNameBinding>(ActivityLoginSettingStoreNameBinding::inflate)
        , LoginView{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.etStoreName.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnNext.isEnabled = true
                binding.btnNext.setBackgroundColor(resources.getColor(R.color.store_btn_able))
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        binding.btnNext.setOnClickListener {
            var store_name = binding.etStoreName.text.toString()

            editor.putString("store_name",store_name)
            editor.commit()
            showLoadingDialog(this)


            var socialid = 55
            var nametem = "test"

            var user = Users(socialid.toString(), nametem, "Kakao", store_name)
            LoginService(this).tryPostRegister(user)

        }
    }

    override fun onPostRegisterSuccess(response: RegisterResponse) {
        dismissLoadingDialog()
        startActivity(Intent(this, MainActivity::class.java))

    }

    override fun onPostRegisterFailure(message: String) {
        showCustomToast(message)
    }

    override fun onPostLoginSuccess(response: LoginResponse) {

    }

    override fun onPostLoginFailure(message: String) {
        showCustomToast(message)
    }
}