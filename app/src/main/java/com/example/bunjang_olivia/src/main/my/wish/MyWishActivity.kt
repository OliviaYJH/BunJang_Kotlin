package com.example.bunjang_olivia.src.main.my.wish


import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.ActivityMyWishBinding
import com.example.bunjang_olivia.src.main.my.wish.onWish.MyOnWishFragment
import com.example.bunjang_olivia.src.main.my.wish.recent.MyRecentFragment
import com.example.rc_aos_tem.config.BaseActivity
import com.google.android.material.tabs.TabLayout

class MyWishActivity : BaseActivity<ActivityMyWishBinding>(ActivityMyWishBinding::inflate) {

    lateinit var OnWishFragment: MyOnWishFragment
    lateinit var RecentFragment: MyRecentFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        OnWishFragment = MyOnWishFragment()
        RecentFragment = MyRecentFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.framelayout_my_wish,OnWishFragment)
            .commit()
        binding.tablayoutWish.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> replaceView(OnWishFragment)
                    1 -> replaceView(RecentFragment)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding.icLeftArrow.setOnClickListener { finish() }
    }

    private fun replaceView(tab: Fragment){
        var selectedFragment: Fragment? = null
        selectedFragment = tab
        selectedFragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.framelayout_my_wish, it).commit()
        }
    }
}