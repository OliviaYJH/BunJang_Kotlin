package com.example.bunjang_olivia.src.main.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.ImageView
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.FragmentHomeBinding
import com.example.bunjang_olivia.src.login.LoginActivity
import com.example.bunjang_olivia.src.login.tablayout.firstTab.LoginFirstTabFragment
import com.example.bunjang_olivia.src.login.tablayout.fourthTab.LoginFourthTabFragment
import com.example.bunjang_olivia.src.login.tablayout.secondTab.LoginSecondTabFragment
import com.example.bunjang_olivia.src.login.tablayout.thirdTab.LoginThirdTabFragment
import com.example.bunjang_olivia.src.main.home.ad.firstAd.HomeFirstAdFragment
import com.example.bunjang_olivia.src.main.home.ad.fourthAd.HomeFourthAdFragment
import com.example.bunjang_olivia.src.main.home.ad.secondAd.HomeSecondAdFragment
import com.example.bunjang_olivia.src.main.home.ad.thirdAd.HomeThirdAdFragment
import com.example.bunjang_olivia.src.main.home.tablayout.brand.BrandFragment
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.RecommendFragment
import com.example.rc_aos_tem.config.BaseFragment
import com.google.android.material.tabs.TabLayout

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    lateinit var RecommendFragment: RecommendFragment
    lateinit var BrandFragment: BrandFragment



    private val adapter by lazy { HomeViewPagerAdapter(childFragmentManager) }

    val viewhandler = Handler(Looper.getMainLooper()){
        setPage() // error
        true
    }

    private val thread = Thread(AdPagerRunnable())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 화면 onCreate될때마다 중간에 home_users_taste_txt 바뀌도록 하기



        binding.viewpagerAd.adapter = adapter

        binding.viewpagerAd.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                binding.tvAdNum.text = (binding.viewpagerAd.currentItem + 1).toString() + "/4"

            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })





        thread.start()


        RecommendFragment = RecommendFragment()
        BrandFragment = BrandFragment()

        childFragmentManager.beginTransaction().add(R.id.framelayout_home, RecommendFragment).commit()
        binding.tablayoutHome.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> replaceView(RecommendFragment)
                    else -> replaceView(BrandFragment)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    override fun onStop() {
        super.onStop()
        thread.interrupt()
    }

    private fun setPage(){

        if(binding.viewpagerAd.currentItem in 0..2)
            binding.viewpagerAd.currentItem += 1
        else if(binding.viewpagerAd.currentItem == 3)
            binding.viewpagerAd.currentItem = 0

    }
    
    inner class AdPagerRunnable:Runnable{
        override fun run() {
            while(true){

                viewhandler.sendEmptyMessage(0)

                try {
                    Thread.sleep(4000)
                }catch (e:InterruptedException){
                    e.printStackTrace()
                    return
                }
            }
        }

    }

    private fun replaceView(tab: Fragment){
        var selectedFragment: Fragment? = null
        selectedFragment = tab
        selectedFragment?.let {
            childFragmentManager.beginTransaction()
                .replace(R.id.framelayout_home, it).commit()
        }
    }


}

class HomeViewPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm){
    override fun getCount(): Int = 4

    override fun getItem(position: Int): Fragment {

        return when(position){
            0 -> HomeFirstAdFragment()
            1 -> HomeSecondAdFragment()
            2 -> HomeThirdAdFragment()
            else -> HomeFourthAdFragment()
        }
    }

}