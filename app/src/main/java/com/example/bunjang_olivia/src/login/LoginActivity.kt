package com.example.bunjang_olivia.src.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager

import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.ActivityLoginBinding
import com.example.bunjang_olivia.src.MainActivity
import com.example.bunjang_olivia.src.login.bottomSheet.LoginBottomSheetDialogFragment
import com.example.bunjang_olivia.src.login.settingName.LoginSettingStoreNameActivity
import com.example.bunjang_olivia.src.login.tablayout.firstTab.LoginFirstTabFragment
import com.example.bunjang_olivia.src.login.tablayout.fourthTab.LoginFourthTabFragment
import com.example.bunjang_olivia.src.login.tablayout.secondTab.LoginSecondTabFragment
import com.example.bunjang_olivia.src.login.tablayout.thirdTab.LoginThirdTabFragment
import com.example.rc_aos_tem.config.BaseActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate){

    private val adapter by lazy{ ViewPagerAdapter(supportFragmentManager)}

    var handler = Handler(Looper.getMainLooper()){
        setPage()
        true
    }
    companion object{
        var register = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewpagerLogin.adapter = adapter
        val thread = Thread(PagerRunnable())
        thread.start()

        binding.viewpagerLogin.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                binding.icIndicator1.setImageDrawable(getDrawable(R.drawable.ic_circle_gray))
                binding.icIndicator2.setImageDrawable(getDrawable(R.drawable.ic_circle_gray))
                binding.icIndicator3.setImageDrawable(getDrawable(R.drawable.ic_circle_gray))
                binding.icIndicator4.setImageDrawable(getDrawable(R.drawable.ic_circle_gray))

                when(position){
                    0 -> binding.icIndicator1.setImageDrawable(getDrawable(R.drawable.ic_circle_black))
                    1 -> binding.icIndicator2.setImageDrawable(getDrawable(R.drawable.ic_circle_black))
                    2 -> binding.icIndicator3.setImageDrawable(getDrawable(R.drawable.ic_circle_black))
                    else -> binding.icIndicator4.setImageDrawable(getDrawable(R.drawable.ic_circle_black))
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })

        binding.txtOtherLogin.setOnClickListener {
            val bottomSheet = LoginBottomSheetDialogFragment()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }

        // 로그인 정보 확인
        UserApiClient.instance.accessTokenInfo{ tokenInfo, error ->
            if(error != null){
            }else if(tokenInfo != null){
                register = true
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if(error != null){

            }
            else if(token != null){
                showCustomToast("회원가입 성공")

                val intent = Intent(this, LoginSettingStoreNameActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }

        }

        binding.btnKakaoLogin.setOnClickListener {
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            }else{
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }

    }

    fun setPage(){
        if(binding.viewpagerLogin.currentItem < 3)
            binding.viewpagerLogin.currentItem += 1
        else if(binding.viewpagerLogin.currentItem == 3)
            binding.viewpagerLogin.currentItem = 0
    }

    inner class PagerRunnable:Runnable{
        override fun run() {
            while(true){
                Thread.sleep(4000)
                handler.sendEmptyMessage(0)
            }
        }

    }
}

class ViewPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm){
    override fun getCount(): Int = 4

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> LoginFirstTabFragment()
            1 -> LoginSecondTabFragment()
            2 -> LoginThirdTabFragment()
            else -> LoginFourthTabFragment()
        }
    }

}


