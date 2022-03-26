package com.example.bunjang_olivia.util.recycler.myWish

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.src.main.my.wish.onWish.MyOnWishFragment
import com.example.bunjang_olivia.util.recycler.recommendFragment.RecommendAdapter
import java.text.DecimalFormat

class MyWishAdapter(context: MyOnWishFragment):
    RecyclerView.Adapter<MyWishAdapter.ViewHolder>(){

    var myWishDatas = mutableListOf<MyWishData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_my_wish_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myWishDatas[position])
    }

    override fun getItemCount(): Int = myWishDatas.size


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val image_path: ImageView = itemView.findViewById(R.id.iv_my_wish_product)
        private val my_heart : ImageView = itemView.findViewById(R.id.iv_my_wish_heart)
        private val title: TextView = itemView.findViewById(R.id.tv_my_wish_title)
        private val price: TextView = itemView.findViewById(R.id.tv_my_wish_price)
        private val shop_image: ImageView = itemView.findViewById(R.id.iv_my_wish_shop_img)
        private val shop_name: TextView = itemView.findViewById(R.id.tv_my_wish_shop_name)
        private val time: TextView = itemView.findViewById(R.id.tv_my_wish_time)

        fun bind(item: MyWishData){
            Glide.with(itemView.context).load(item.image_path)
                .apply(RequestOptions().override(180,200))
                .into(image_path)
            /*
            Glide.with(itemView.context).load(item.shop_image)
                .apply(RequestOptions().override(30,30))
                .into(shop_image)

             */


            var format = DecimalFormat("#,###")
            var product_price = format.format(item.price)
            price.text = product_price + "원"
            title.text = item.title
            shop_name.text = item.shop_name
        }
    }

}