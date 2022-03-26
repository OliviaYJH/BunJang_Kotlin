package com.example.bunjang_olivia.src.main.home.tablayout.recommend

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.FragmentRecommendBinding
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.models.RecommendListResponse
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.models.WishListResponse
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.wish.WishService
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.wish.WishView
import com.example.bunjang_olivia.util.recycler.recommendFragment.RecommendAdapter
import com.example.bunjang_olivia.util.recycler.recommendFragment.RecommendData
import com.example.rc_aos_tem.config.BaseFragment
import com.example.rc_aos_tem.config.BaseResponse
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


class RecommendFragment : BaseFragment<FragmentRecommendBinding>(FragmentRecommendBinding::bind, R.layout.fragment_recommend)
    ,RecommendView, WishView{

    lateinit var recommendAdapter: RecommendAdapter
    val datas = mutableListOf<RecommendData>()
    val wishes = arrayListOf<Int>()
    var wish = false
    var time: String?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoadingDialog(requireContext())

        val gridLayoutManager = GridLayoutManager(context, 2)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvRecommend.layoutManager = gridLayoutManager


        WishService(this).tryGetWishList()
    }

    fun wishPost(itemId: Int, wish: Boolean):Boolean{
        if(wish){
            WishService(this).tryPatchWish(itemId)
            return false
        }else{
            WishService(this).tryPostWish(itemId)
            return true
        }
    }

    override fun onGetWishListSuccess(response: WishListResponse) {


        for(i in response.result.indices){
            wishes.add(response.result[i].item_id)
        }

        RecommendService(this).tryGetRecommend(0, 20)
    }

    override fun onGetWishListFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }

    override fun onGetRecommendSuccess(response: RecommendListResponse) {
        dismissLoadingDialog()

        recommendAdapter = RecommendAdapter(requireContext() as FragmentActivity)
        binding.rvRecommend.adapter = recommendAdapter


        val now: Long = System.currentTimeMillis()
        val date = Date(now)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale("ko", "KR"))
        val stringTime = dateFormat.format(date)

        val now_day = Integer.parseInt(stringTime.substring(8,10))
        val now_hour = Integer.parseInt(stringTime.substring(11,13))
        val now_minute = Integer.parseInt(stringTime.substring(14,16))
        val now_second = Integer.parseInt(stringTime.substring(17,19))

        // fix
        for(i in response.result.indices){
            var result = response.result[i]

            var format = DecimalFormat("#,###")
            var product_price = format.format(result.price)
            var price = product_price + "원"

            var product_day = Integer.parseInt(result.created_at.substring(8,10))
            var product_hour = Integer.parseInt(result.created_at.substring(11,13))
            var product_minute = Integer.parseInt(result.created_at.substring(14,16))
            var product_second = Integer.parseInt(result.created_at.substring(17,19))


            if(now_day-product_day == 0){
                // 같은날
                if(now_hour-product_hour-9 == 0){
                    if(now_minute - product_minute == 0){
                        time = (now_second - product_second).toString() + "초 전"
                    }else{
                        time = (now_minute - product_minute).toString() + "분 전"
                    }
                }else{
                    time = (now_hour- product_hour-9).toString() + "시간 전"
                }
            }else{
                // 다른날 등록
                time = (now_day-product_day).toString() + "일 전"
            }

            wish = wishes.contains(result.item_id)

            var location = result.location

            if(location == null) location = "지역정보 없음"

                datas.apply {
                    add(RecommendData(title = result.title, price = price,
                        safety_pay = result.safety_pay, location = location,
                        created_at = time!!, image_path = "https://bjclone.s3.ap-northeast-2.amazonaws.com/" + result.image_path,
                        wish_count = result.wish_count, item_id = result.item_id, wish = wish))
                }

        }

        recommendAdapter.datas = datas
        recommendAdapter.notifyDataSetChanged()

    }



    override fun onGetRecommendFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)

    }

    override fun onPostWishSuccess(response: BaseResponse) {
        //showCustomToast("success")
    }

    override fun onPostWishFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }

    override fun onPatchWishSuccess(response: BaseResponse) {
        //showCustomToast("success")
    }

    override fun onPatchWishFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }


}