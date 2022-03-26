package com.example.bunjang_olivia.src.main.my.wish.onWish

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.FragmentMyOnWishBinding
import com.example.bunjang_olivia.src.main.my.wish.MyOnWishService
import com.example.bunjang_olivia.src.main.my.wish.MyOnWishView
import com.example.bunjang_olivia.src.main.my.wish.models.OnWishResponse
import com.example.bunjang_olivia.util.recycler.myWish.MyWishAdapter
import com.example.bunjang_olivia.util.recycler.myWish.MyWishData
import com.example.rc_aos_tem.config.BaseFragment

class MyOnWishFragment : BaseFragment<FragmentMyOnWishBinding>(FragmentMyOnWishBinding::bind,
        R.layout.fragment_my_on_wish), MyOnWishView  {

    lateinit var myWishAdapter: MyWishAdapter
    val myWishDatas = mutableListOf<MyWishData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gridLayoutManager = GridLayoutManager(context, 2)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerMyWish.layoutManager = gridLayoutManager

        MyOnWishService(this).tryGetMyOnWish()
    }

    override fun onGetOnMyWishSuccess(response: OnWishResponse) {
        myWishAdapter = MyWishAdapter(this)
        binding.recyclerMyWish.adapter = myWishAdapter

        for(i in response.result.indices){
            var result = response.result[i]


            if(result.image_path.startsWith("h")){
                myWishDatas.apply {
                    add(MyWishData(title = result.title, item_id = result.item_id,
                        image_path = result.image_path, created_at = result.created_at,
                        shop_name = result.shop_name,
                    safety_pay = result.safety_pay, price = result.price))
                }
            }else{
                myWishDatas.apply {
                    add(MyWishData(title = result.title, item_id = result.item_id,
                        image_path = "https://bjclone.s3.ap-northeast-2.amazonaws.com/" + result.image_path, created_at = result.created_at,
                        shop_name = result.shop_name,
                        safety_pay = result.safety_pay, price = result.price))
                }
            }


        }

        myWishAdapter.myWishDatas = myWishDatas
        myWishAdapter.notifyDataSetChanged()
    }

    override fun onGetOnMyWishFailure(message: String) {
        showCustomToast(message)
    }

    override fun onStop() {
        super.onStop()
        myWishDatas.clear()
    }


}