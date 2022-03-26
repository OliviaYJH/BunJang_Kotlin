package com.example.bunjang_olivia.src

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.android_mvc_template.config.ApplicationClass
import com.example.android_mvc_template.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.example.android_mvc_template.config.ApplicationClass.Companion.editor
import com.example.android_mvc_template.config.ApplicationClass.Companion.sSharedPreferences
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.ActivityMainBinding
import com.example.bunjang_olivia.src.login.LoginActivity
import com.example.bunjang_olivia.src.login.LoginService
import com.example.bunjang_olivia.src.login.LoginView
import com.example.bunjang_olivia.src.login.models.GetToken
import com.example.bunjang_olivia.src.login.models.LoginResponse
import com.example.bunjang_olivia.src.login.models.RegisterResponse
import com.example.bunjang_olivia.src.login.models.Users
import com.example.bunjang_olivia.src.login.settingName.LoginSettingStoreNameActivity
import com.example.bunjang_olivia.src.main.add.AddActivity
import com.example.bunjang_olivia.src.main.home.HomeFragment
import com.example.bunjang_olivia.src.main.my.MyFragment
import com.example.bunjang_olivia.src.main.search.SearchActivity
import com.example.bunjang_olivia.src.main.talk.TalkFragment
import com.example.rc_aos_tem.config.BaseActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    LoginView {

    var social_id: String?= null
    var nickname: String? = null
    val social_info = "Kakao"
    var shop_name: String?= null

    //
    var socialid: String? = null
    var nametem: String? = null
    var shopname: String? = null



    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 임의 계정으로 로그인
        socialid = "55"
        nametem = "test"
        shopname = "ok"
        showLoadingDialog(this)
        LoginService(this).tryPostLogin(GetToken(social_info,socialid!!, nametem!!))

        UserApiClient.instance.me{ user, error ->
            // 회원 번호 user?.id
            // 유저이름 user?.kakaoAccount?.profile?.nickname
            // 소셜 정보 Kakao



            // 상점명
            shop_name = sSharedPreferences.getString("store_name", "error")
            social_id = user?.id.toString()

            nickname = user?.kakaoAccount?.profile?.nickname.toString()

            var register_data = Users(social_id!!,nickname!!, social_info, shop_name!!)

            showLoadingDialog(this)
            LoginService(this).tryPostLogin(GetToken(social_info,socialid!!, nametem!!))
        }

        window.setDecorFitsSystemWindows(false)

        supportFragmentManager.beginTransaction().replace(R.id.main_frm,
            HomeFragment()).commitAllowingStateLoss()

        binding.mainBtmNav.setOnItemSelectedListener{ item ->
            when (item.itemId) {
                R.id.home_tab -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.search_tab -> {
                    startActivity(Intent(this, SearchActivity::class.java))
                }
                R.id.register_tab -> {
                    startActivity(Intent(this, AddActivity::class.java))
                }
                R.id.talk_tab -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, TalkFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.my_tab -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, MyFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

            }
            false

        }
    }
    override fun onPostRegisterSuccess(response: RegisterResponse) {
        LoginService(this).tryPostLogin(GetToken(social_info,social_id!!, nickname!!))
    }

    override fun onPostRegisterFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }

    override fun onPostLoginSuccess(response: LoginResponse) {
        dismissLoadingDialog()
        editor.putString(X_ACCESS_TOKEN, response.result.jwt)
        editor.putInt("userId", response.result.user_id)
        editor.commit()
    }

    override fun onPostLoginFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }


}