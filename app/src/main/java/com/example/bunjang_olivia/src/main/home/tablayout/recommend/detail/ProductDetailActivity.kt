package com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.example.android_mvc_template.config.ApplicationClass.Companion.sSharedPreferences
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.ActivityProductDetailBinding
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.models.ProductDetailResponse
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.ProductTradeBottomDialogFragment
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.image.SlideImageFragment
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.store.StoreService
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.store.StoreView
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.store.models.Delete
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.store.models.Follow
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.store.models.MyFollowingResponse
import com.example.rc_aos_tem.config.BaseActivity
import com.example.rc_aos_tem.config.BaseResponse
import org.json.JSONArray
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


class ProductDetailActivity : BaseActivity<ActivityProductDetailBinding>(ActivityProductDetailBinding::inflate),
    ProductDetailView, StoreView{

    var price : Int? = null
    var title: String? = null
    var image_path: String? = null
    var item_id: Int? = null
    var sellerId : Int? = null
    var shop = false
    var time: String?= null
    var category: String? = null
    var location: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showLoadingDialog(this)

        binding.ivClose.setOnClickListener { finish() }

        item_id = intent.getIntExtra("item_id", -1)
        location = intent.getStringExtra("location")
        ProductDetailService(this).tryGetProductDetail(item_id!!)

        binding.btnPay.setOnClickListener {
            // pay dialog
            val bottomSheet = ProductTradeBottomDialogFragment()

            val bundle = Bundle()
            bundle.putInt("price", price!!)
            bundle.putString("title", title!!)
            bundle.putString("image_path", image_path)
            bundle.putInt("item_id", item_id!!)
            bottomSheet.arguments = bundle

            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }

        var userId = sSharedPreferences.getInt("userId", -1)

        binding.ivShopFollow.setOnClickListener {
            if(!shop){
                // 색 들어오게
                binding.ivShopFollow.setColorFilter(Color.parseColor("#E11616"))
                StoreService(this).tryGetStore(userId, Follow(sellerId!!))

                // dialog

                var dialog = Dialog(this)
                dialog.setContentView(R.layout.dialog_store_alarm)
                dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT)
                dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.setCanceledOnTouchOutside(true)
                dialog.setCancelable(true)
                dialog.show()

                val btnNo = dialog.findViewById<TextView>(R.id.tv_alarm_no)
                val btnYes = dialog.findViewById<TextView>(R.id.tv_alarm_yes)

                btnNo.setOnClickListener { dialog.dismiss() }
                btnYes.setOnClickListener { dialog.dismiss() }



                shop = true
                // 팔로잉 상점 상품 등록되면 알림 받을거냐는 dialog
            }else{
                binding.ivShopFollow.setColorFilter(Color.parseColor("#606060"))
                StoreService(this).tryPatchStore(userId, Delete(sellerId!!))
                shop = false
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onGetProductDetailSuccess(response: ProductDetailResponse) {
        dismissLoadingDialog()
        var result = response.result

        // json
        val json = assets.open("Category.json").reader().readText()
        val jsonArray = JSONArray(json)

        for(i in 0..jsonArray.length()-1){
            var n = jsonArray.getJSONObject(i)
            val value = n.getInt("category_id")

            if(value == result.item.category_id){
                category = n.getString("name")
            }
        }

        binding.tvProductDetailCategory.text = category

        // detail of product
        price = result.item.price
        title = result.item.title

        var img = arrayOfNulls<String>(result.item.images.size)

        for(i in result.item.images.indices){
            // 이미지 모아모아
            img[i] = result.item.images[i].image_path
        }

        // 결제 화면으로 보내기 위해 지정
        image_path = "https://bjclone.s3.ap-northeast-2.amazonaws.com/" + result.item.images[0].image_path


        // 이미지
        val pagerAdapter = DetailImageAdapter(this, img)
        binding.viewpagerDetail.adapter = pagerAdapter
        binding.wormDotsIndicator.setViewPager2(binding.viewpagerDetail)
        //

        binding.tvProductDetailLocation.text = location
        binding.tvProductDetailTitle.text = result.item.title

        // 현재 시간

        val now: Long = System.currentTimeMillis()
        val date = Date(now)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale("ko", "KR"))
        val stringTime = dateFormat.format(date)

        val now_day = Integer.parseInt(stringTime.substring(8,10))
        val now_hour = Integer.parseInt(stringTime.substring(11,13))
        val now_minute = Integer.parseInt(stringTime.substring(14,16))
        val now_second = Integer.parseInt(stringTime.substring(17,19))

        // product add time 상품 등록 시간
        var product_day = Integer.parseInt(result.item.creatd_at.substring(8,10))
        var product_hour = Integer.parseInt(result.item.creatd_at.substring(11,13))
        var product_minute = Integer.parseInt(result.item.creatd_at.substring(14,16))
        var product_second = Integer.parseInt(result.item.creatd_at.substring(17,19))

        // 표기
        if(now_day-product_day == 0){
            // 같은날
            if(now_hour-product_hour - 9 == 0){
                if(now_minute - product_minute == 0){
                    time = (now_second - product_second).toString() + "초 전"
                }else{
                    time = (now_minute - product_minute).toString() + "분 전"
                }
            }else{
                time = (now_hour- product_hour - 9 ).toString() + "시간 전"
            }
        }else{
            // 다른날 등록
            time = (now_day-product_day).toString() + "일 전"
        }
        binding.tvProductAddTime.text = time

        binding.tvProductView.text = result.item.view.toString()
        binding.tvProductWish.text = result.item.wish_count.toString()

        var format = DecimalFormat("#,###")
        var product_price = format.format(result.item.price)
        binding.tvProductDetailPrice.text = product_price + "원"

        if(result.item.safety_pay == 1){
            binding.tvProductDetailSafety.isVisible = true
        }
        
        if(result.item.condition == 0){
            binding.tvCondition.text = "새상품"
        }
        
        if(result.item.delivery_fee_included == 0){
            binding.tvDeliveryFee.text = "· 배송비 포함"
        }
        
        binding.tvCount.text = "· 총 " + result.item.count.toString() + "개"

        binding.tvProductDetailDescription.text = result.item.detail

        var product_tag = ""
        for(i in result.item.tags.indices){
            product_tag += "#"+result.item.tags[i].tag_name + " "
        }
        binding.tvProductDetailTag.text = product_tag

        binding.tvInquireCount.text = "상품 문의하기 " + result.item.inquiry_count.toString()

        sellerId = result.shop.seller_id

        binding.tvShopName.text = result.shop.shop_name

        binding.tvFollowerCount.text = result.shop.item_count.toString()

        binding.tvItemCount.text = result.shop.item_count.toString()



        var shop_imgs = arrayOf(binding.ivProduct1, binding.ivProduct2, binding.ivProduct3)
        var shop_prices = arrayOf(binding.tvProduct1Price,binding.tvProduct2Price, binding.tvProduct3Price)

        for(i in  0..result.shop.shop_items.size-1){
            var shop_price = format.format(result.shop.shop_items[i].price)
            shop_prices[i].text = shop_price + "원"

            Glide.with(this)
                .load("https://bjclone.s3.ap-northeast-2.amazonaws.com/" + result.shop.shop_items[i].image_path)
                .into(shop_imgs[i])

        }

        binding.tvItemReviewCount.text = result.review.review_count.toString()

        StoreService(this).tryGetMyFollow()
    }

    override fun onGetProductDetailFailure(message: String) {
        showCustomToast(message)
    }

    override fun onPostStoreSuccess(response: BaseResponse) {
        Log.d("follow", response.message!!)
    }

    override fun onPostStoreFailures(message: String) {
        showCustomToast(message)
    }

    override fun onPatchStoreSuccess(response: BaseResponse) {
        Log.d("unfollow", response.message!!)
    }

    override fun onPatchStoreFailure(message: String) {
        showCustomToast(message)
    }

    override fun onGetMyFollowingSuccess(response: MyFollowingResponse) {
        if(response.result == null){
            Log.d("result", "null")
        }else{
            for(i in response.result.indices){
                if(response.result[i].userId == sellerId){
                    binding.ivShopFollow.setColorFilter(Color.parseColor("#E11616"))
                    shop = true
                }
            }
        }
    }

    override fun onGetMyFollowingFailure(message: String) {
        showCustomToast(message)
    }

}

class DetailImageAdapter(fa: FragmentActivity, val images: Array<String?>): FragmentStateAdapter(fa){
    override fun getItemCount(): Int = images.size

    override fun createFragment(position: Int): Fragment {
        return when(position){
            position -> SlideImageFragment(images[position]!!)
            else -> SlideImageFragment(images[0]!!)
        }
    }

}

