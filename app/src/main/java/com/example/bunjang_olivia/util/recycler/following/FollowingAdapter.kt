package com.example.bunjang_olivia.util.recycler.following

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bunjang_olivia.databinding.ItemAccountRecyclerBinding
import com.example.bunjang_olivia.databinding.ItemMyStoreFollowingRecyclerBinding
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.store.models.Follow
import com.example.bunjang_olivia.util.recycler.account.ViewHolder

class FollowingAdapter(val context: FragmentActivity): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var followingDatas = mutableListOf<FollowingData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemMyStoreFollowingRecyclerBinding.inflate(layoutInflater, parent, false)
        return FollowingHolder(context, itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FollowingHolder).bind(followingDatas[position])
    }

    override fun getItemCount(): Int = followingDatas.size


}

class FollowingHolder(val context: FragmentActivity,val binding: ItemMyStoreFollowingRecyclerBinding): RecyclerView.ViewHolder(binding.root){

    private val shop_name: TextView = binding.tvFollowShopName
    private val item_count : TextView = binding.tvProductCount
    private val follower_count: TextView = binding.tvFollowingCount

    private val img1: ImageView = binding.ivProduct1
    private val img2: ImageView = binding.ivProduct2
    private val img3: ImageView = binding.ivProduct3

    private val price1: TextView = binding.tvProduct1Price
    private val price2: TextView = binding.tvProduct2Price
    private val price3: TextView = binding.tvProduct3Price

    fun bind(item: FollowingData){
        shop_name.text = item.shop_name
        item_count.text = item.howManyItem
        follower_count.text = item.howManyFollowers

        var imgs = arrayOf(img1, img2, img3)
        var prices = arrayOf(price1, price2, price3)

        for(i in 0 until item.getItemResult.size){
            Glide.with(itemView.context)
                .load(item.getItemResult[i].image_path)
                .into(imgs[i])

            prices[i].text = item.getItemResult[i].price
        }
    }
}