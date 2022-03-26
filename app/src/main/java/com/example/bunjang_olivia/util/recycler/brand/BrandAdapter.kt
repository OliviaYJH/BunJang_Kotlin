package com.example.bunjang_olivia.util.recycler.brand

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bunjang_olivia.databinding.ItemAccountRecyclerBinding
import com.example.bunjang_olivia.databinding.ItemBrandRecyclerBinding
import org.w3c.dom.Text

class BrandAdapter(val context: FragmentActivity?):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var brandDatas = mutableListOf<BrandData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemBrandRecyclerBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(context, itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(brandDatas[position])
    }

    override fun getItemCount(): Int = brandDatas.size


}

class ViewHolder(val context: FragmentActivity?, val binding: ItemBrandRecyclerBinding)
    :RecyclerView.ViewHolder(binding.root){

    private val image_path: ImageView = binding.ivProduct
    private val price: TextView = binding.tvPrice
    private val safety_pay: TextView = binding.tvSafety
    private val title: TextView = binding.tvProduct

        fun bind(item: BrandData){
            Glide.with(itemView.context).load(item.image_path)
                .apply(RequestOptions().override(150, 170))
                .into(image_path)

            price.text = item.price.toString()
            if(item.safety_pay == 1){
                safety_pay.isVisible = true
            }


            title.text = item.title

        }
    }