package com.example.bunjang_olivia.util.recycler.buyAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bunjang_olivia.databinding.ItemFinishRecyclerBinding

class BuyAdapter(val context: FragmentActivity):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var buyDatas = mutableListOf<BuyData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemFinishRecyclerBinding.inflate(layoutInflater,parent, false)
        return BuyViewHolder(context, itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BuyViewHolder).bind(buyDatas[position])
    }

    override fun getItemCount(): Int = buyDatas.size


}

class BuyViewHolder(val context: FragmentActivity, val binding: ItemFinishRecyclerBinding)
    : RecyclerView.ViewHolder(binding.root){

    private val img: ImageView = binding.ivFinishProduct
    private val title: TextView = binding.tvFinishTitle
    private val date: TextView = binding.tvFinishDate
    private val price: TextView = binding.tvFinishPrice

        fun bind(item: BuyData){
            Glide.with(itemView.context)
                .load(item.image_path)
                .into(img)

            title.text = item.title
            date.text = item.created_at
            price.text = item.price
        }
    }