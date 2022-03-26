package com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.trade.direct

import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.ActivityProductDirectBinding
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.trade.ProductTradeService
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.trade.ProductTradeView
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.trade.models.Trade
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.trade.models.TradeDirect
import com.example.rc_aos_tem.config.BaseActivity
import com.example.rc_aos_tem.config.BaseResponse
import java.text.DecimalFormat

class ProductDirectActivity : BaseActivity<ActivityProductDirectBinding>(ActivityProductDirectBinding::inflate)
    , ProductTradeView {

    var personal_checked = false

    var title: String? = null
    var item_id: Int? = null
    var image_path: String? = null
    var price: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        title= intent.getStringExtra("title")
        price = intent.getIntExtra("price", -1)
        image_path = intent.getStringExtra("image_path")
        item_id = intent.getIntExtra("item_id", -1)

        Glide.with(this)
            .load(image_path)
            .into(binding.ivProduct)
        var format = DecimalFormat("#,###")
        var product_price = format.format(price)

        binding.tvProductTitle.text = title
        binding.tvProductPrice.text = product_price + "원"
        binding.tvDeliveryProductPrice.text = product_price + "원"
        binding.tvDeliveryProductPayPrice.text = product_price + "원"
        binding.tvTotalPrice.text = product_price + "원"


        binding.icClose.setOnClickListener { finish() }

        binding.linearCheckPersonalAgreement.setOnClickListener {
            if(!personal_checked){
                binding.ivPersonalAgreement.setColorFilter(ContextCompat.getColor(this, R.color.home_ad_first))
                binding.ivPersonalAgreement.setImageResource(R.drawable.ic_checked)
                personal_checked = true
            }else{
                binding.ivPersonalAgreement.setColorFilter(ContextCompat.getColor(this, R.color.colorLightGray))
                binding.ivPersonalAgreement.setImageResource(R.drawable.ic_non_check)
                personal_checked = false
            }
        }
        
        binding.btnDeliveryPay.setOnClickListener { 
            // 결제
            var trade = TradeDirect(0,"kakao")
            showLoadingDialog(this)
            ProductTradeService(this).tryPostTradeDirect(item_id!!, trade)
        }
    }

    override fun onPostTradeSuccess(response: BaseResponse) {
        dismissLoadingDialog()
        Log.d("trade", "success")
        finish()
    }

    override fun onPostTradeFailure(message: String) {
        showCustomToast(message)
    }
}