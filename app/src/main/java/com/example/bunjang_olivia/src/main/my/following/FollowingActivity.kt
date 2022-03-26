package com.example.bunjang_olivia.src.main.my.following

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.ActivityFollowingBinding
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.store.StoreService
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.store.StoreView
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.store.models.MyFollowingResponse
import com.example.bunjang_olivia.util.recycler.following.FollowingAdapter
import com.example.bunjang_olivia.util.recycler.following.FollowingData
import com.example.bunjang_olivia.util.recycler.following.GetFollowingData
import com.example.rc_aos_tem.config.BaseActivity
import com.example.rc_aos_tem.config.BaseResponse
import java.text.DecimalFormat

class FollowingActivity : BaseActivity<ActivityFollowingBinding>(ActivityFollowingBinding::inflate),
    StoreView {

    lateinit var followingAdapter: FollowingAdapter
    var followingDatas = mutableListOf<FollowingData>()
    var getFollowingDatas = ArrayList<GetFollowingData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showLoadingDialog(this)

        binding.ivLeftArrow.setOnClickListener { finish() }

        StoreService(this).tryGetMyFollow()
    }

    override fun onPostStoreSuccess(response: BaseResponse) {

    }

    override fun onPostStoreFailures(message: String) {
        showCustomToast(message)
    }

    override fun onPatchStoreSuccess(response: BaseResponse) {

    }

    override fun onPatchStoreFailure(message: String) {
        showCustomToast(message)
    }

    override fun onGetMyFollowingSuccess(response: MyFollowingResponse) {
        dismissLoadingDialog()
        // success
        //response.result[0].getItemResult.size



        followingAdapter = FollowingAdapter(this)
        binding.recyclerMyFollowing.adapter = followingAdapter



        for(i in response.result.indices){
            var result = response.result[i]



            for(j in result.getItemResult.indices){

                var format = DecimalFormat("#,###")
                var product_price = format.format(result.getItemResult[j].price)
                var price = product_price + "원"


                getFollowingDatas.apply {
                    add(GetFollowingData(image_path = "https://bjclone.s3.ap-northeast-2.amazonaws.com/" + result.getItemResult[j].image_path,
                                        price = price))
                }
            }

            followingDatas.apply {
                add(FollowingData(shop_name = result.shopName, howManyFollowers = result.howManyFollowers.toString(),
                    howManyItem = result.howManyItem.toString(),
                    getItemResult = getFollowingDatas))
            }
        }



        followingAdapter.followingDatas = followingDatas
        followingAdapter.notifyDataSetChanged()

    }

    override fun onGetMyFollowingFailure(message: String) {
        showCustomToast(message)
    }
}