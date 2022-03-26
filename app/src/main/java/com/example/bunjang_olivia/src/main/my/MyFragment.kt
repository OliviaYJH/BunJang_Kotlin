package com.example.bunjang_olivia.src.main.my

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import com.bumptech.glide.Glide
import com.example.android_mvc_template.config.ApplicationClass.Companion.sSharedPreferences
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.FragmentMyBinding
import com.example.bunjang_olivia.src.main.my.bottomdialog.MyBottomSheetDialogFragment
import com.example.bunjang_olivia.src.main.my.finish.MyFinishFragment
import com.example.bunjang_olivia.src.main.my.following.FollowingActivity
import com.example.bunjang_olivia.src.main.my.models.MyPageResponse
import com.example.bunjang_olivia.src.main.my.reserving.MyReservingFragment
import com.example.bunjang_olivia.src.main.my.selling.MySellingFragment
import com.example.bunjang_olivia.src.main.my.setting.SettingActivity
import com.example.bunjang_olivia.src.main.my.tradeRecord.TradeRecordActivity
import com.example.bunjang_olivia.src.main.my.wish.MyOnWishService
import com.example.bunjang_olivia.src.main.my.wish.MyOnWishView
import com.example.bunjang_olivia.src.main.my.wish.MyWishActivity
import com.example.bunjang_olivia.src.main.my.wish.models.OnWishResponse
import com.example.rc_aos_tem.config.BaseFragment
import com.google.android.material.tabs.TabLayout

class MyFragment : BaseFragment<FragmentMyBinding>(FragmentMyBinding::bind, R.layout.fragment_my)
    , MyPageView{

    lateinit var mySellingFragment: MySellingFragment
    lateinit var myReservingFragment: MyReservingFragment
    lateinit var myFinishFragment: MyFinishFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoadingDialog(requireContext())

        var store_name = sSharedPreferences.getString("store_name", "error")
        binding.tvMyNickname.text = store_name

        var userId = sSharedPreferences.getInt("userId", 0)
        MyPageService(this).tryGetMyPage(userId)

        mySellingFragment = MySellingFragment()
        myReservingFragment = MyReservingFragment()
        myFinishFragment = MyFinishFragment()

        binding.myStore.setOnClickListener {
            // bottom dialog 띄우기
            val bottomSheet = MyBottomSheetDialogFragment()
            bottomSheet.show(childFragmentManager, bottomSheet.tag)

        }

        binding.ivSetting.setOnClickListener {
            startActivity(Intent(context, SettingActivity::class.java))
        }

        binding.linearFollowing.setOnClickListener {
            startActivity(Intent(context, FollowingActivity::class.java))
        }

        binding.btnMyWish.setOnClickListener {
            startActivity(Intent(context, MyWishActivity::class.java))
        }

        binding.linearTradeRecord.setOnClickListener {
            // 거래내역
            startActivity(Intent(context, TradeRecordActivity::class.java))
        }


        childFragmentManager.beginTransaction().add(R.id.framelayout_my,
            mySellingFragment).commit()

        binding.tablayoutMy.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> replaceView(mySellingFragment)
                    1 -> replaceView(myReservingFragment)
                    else -> replaceView(myFinishFragment)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

    }

    fun replaceView(tab: Fragment){
        var selectedFragment: Fragment? = null
        selectedFragment = tab
        selectedFragment?.let {
            childFragmentManager.beginTransaction()
                .replace(R.id.framelayout_my, it).commit()
        }
    }


    override fun onGetMyPageSuccess(response: MyPageResponse) {
        dismissLoadingDialog()

        binding.tvMyNickname.text = response.result.shop_name

        if(response.result.user_image != null){
            Glide.with(requireContext())
                .load("https://bjclone.s3.ap-northeast-2.amazonaws.com/" + response.result.user_image.toString())
                .into(binding.icMyStore)
        }

        binding.tvMyWish.text = response.result.wish_count.toString()
        binding.tvMyFollower.text = response.result.follower_count.toString()
        binding.tvMyFollowing.text = response.result.following_count.toString()

    }

    override fun onGetMyPageFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }

}