package com.example.bunjang_olivia.util.recycler.mySell

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bunjang_olivia.databinding.ItemMySellingRecyclerBinding
import com.example.bunjang_olivia.util.recycler.account.ViewHolder

class MySellAdapter(val context: FragmentActivity):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var mySellDatas = mutableListOf<MySellData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemMySellingRecyclerBinding.inflate(layoutInflater, parent, false)
        return SellingViewHolder(context, itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SellingViewHolder).bind(mySellDatas[position])
    }

    override fun getItemCount(): Int = mySellDatas.size


}


class SellingViewHolder(val context: FragmentActivity, val binding: ItemMySellingRecyclerBinding)
    : RecyclerView.ViewHolder(binding.root){

        private val product_img : ImageView = binding.ivMySellProduct
        private val time: TextView = binding.tvMySellTime
        private val title: TextView = binding.tvMySellTitle
        private val price: TextView = binding.tvMySellPrice

        fun bind(item: MySellData){
            Glide.with(itemView.context)
                .load(item.image_path)
                .into(product_img)

            time.text = item.created_at
            title.text = item.title
            price.text = item.price


        }
    }