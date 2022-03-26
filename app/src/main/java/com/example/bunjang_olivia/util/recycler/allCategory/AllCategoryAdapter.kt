package com.example.bunjang_olivia.util.recycler.allCategory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.ItemAllCategoryBinding
import com.example.bunjang_olivia.src.main.add.category.all_category.AllCategoryFragment
import org.json.JSONArray
import java.nio.channels.FileChannel.open
import java.nio.channels.Selector.open
import java.util.*


class AllCategoryAdapter(val context: FragmentActivity):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var categoryDatas = mutableListOf<AllCategoryData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemAllCategoryBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(context, itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(categoryDatas[position])
    }

    override fun getItemCount(): Int = categoryDatas.size

}

class ViewHolder(val context: FragmentActivity, val binding: ItemAllCategoryBinding)
    : RecyclerView.ViewHolder(binding.root){

    private val icon: ImageView = itemView.findViewById(R.id.iv_all_category_icon)
    private val title: TextView = itemView.findViewById(R.id.tv_all_category_title)


    fun bind(item: AllCategoryData){
        icon.setImageResource(item.Image)
        title.text = item.title


    }
}