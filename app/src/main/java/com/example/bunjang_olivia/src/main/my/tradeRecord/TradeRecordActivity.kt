package com.example.bunjang_olivia.src.main.my.tradeRecord

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.ActivityTradeRecordBinding
import com.example.bunjang_olivia.src.main.my.tradeRecord.balance.BalanceRecordFragment
import com.example.bunjang_olivia.src.main.my.tradeRecord.buy.BuyRecordFragment
import com.example.bunjang_olivia.src.main.my.tradeRecord.sell.SellRecordFragment
import com.example.rc_aos_tem.config.BaseActivity
import com.google.android.material.tabs.TabLayout

class TradeRecordActivity : BaseActivity<ActivityTradeRecordBinding>(ActivityTradeRecordBinding::inflate) {

    lateinit var buyRecordFragment: BuyRecordFragment
    lateinit var sellRecordFragment: SellRecordFragment
    lateinit var balanceRecordFragment: BalanceRecordFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        buyRecordFragment = BuyRecordFragment()
        sellRecordFragment = SellRecordFragment()
        balanceRecordFragment = BalanceRecordFragment()

        supportFragmentManager.beginTransaction().add(R.id.framelayout_trade_record,buyRecordFragment).commit()
        binding.tablayoutTradeRecord.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> replaceView(buyRecordFragment)
                    1 -> replaceView(sellRecordFragment)
                    else -> replaceView(balanceRecordFragment)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding.icClose.setOnClickListener { finish() }

    }


    private fun replaceView(tab: Fragment){
        var selectedFragment: Fragment? = null
        selectedFragment = tab
        selectedFragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.framelayout_trade_record, it).commit()
        }
    }
}