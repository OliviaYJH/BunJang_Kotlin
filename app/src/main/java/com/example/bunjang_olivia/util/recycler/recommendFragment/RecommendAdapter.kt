package com.example.bunjang_olivia.util.recycler.recommendFragment

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.ItemRecommendRecyclerBinding
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.RecommendFragment
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.RecommendView
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.ProductDetailActivity
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.wish.WishService
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.wish.WishView
import com.example.rc_aos_tem.config.BaseResponse
import org.w3c.dom.Text
import java.text.DecimalFormat

class RecommendAdapter(val context: FragmentActivity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var datas = mutableListOf<RecommendData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemRecommendRecyclerBinding.inflate(layoutInflater, parent, false)
        return RecommendViewHolder(context, itemBinding)
    }

    override fun getItemCount(): Int = datas.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RecommendViewHolder).bind(datas[position])
    }


}

class RecommendViewHolder(val context: FragmentActivity, val binding: ItemRecommendRecyclerBinding)
    : RecyclerView.ViewHolder(binding.root){

    private val image_path: ImageView = binding.ivProduct
    private val price: TextView = itemView.findViewById(R.id.tv_price)
    private val safety_pay: TextView = itemView.findViewById(R.id.tv_safety)
    private val title: TextView = itemView.findViewById(R.id.tv_product)
    private val location: TextView = itemView.findViewById(R.id.tv_place)
    private val created_at: TextView = itemView.findViewById(R.id.tv_time)
    private val wish_count: TextView = binding.tvWish

    private val iv_wish :ImageView = binding.ivWish
    private var check = true
    private var isWish = false


    fun bind(item: RecommendData){
        Glide.with(itemView.context).load(item.image_path)
            .apply(RequestOptions().override(180,200))
            .into(image_path)



        price.text = item.price
        if(item.safety_pay == 1)
            safety_pay.isVisible = true //
        title.text = item.title
        location.text = item.location + " · "
        created_at.text = item.created_at
        wish_count.text = item.wish_count.toString()
        val item_id = item.item_id
        // 초기화
        if(item.wish) {
            // 찜한 상태
            iv_wish.setColorFilter(Color.parseColor("#E11616"))
            iv_wish.setImageResource(R.drawable.ic_heart_red)

        }else{
            // 찜 안한 상태
            iv_wish.setColorFilter(Color.parseColor("#fcfcfc"))
            iv_wish.setImageResource(R.drawable.ic_heart_white)

        }

        itemView.setOnClickListener {
            // 추천 목록에서 해당 상품을 클릭했을 경우
            var intent = Intent(itemView.context, ProductDetailActivity::class.java)
            intent.putExtra("price", item.price)
            intent.putExtra("safety_pay", item.safety_pay)
            intent.putExtra("title", item.title)
            intent.putExtra("location", item.location)
            intent.putExtra("img", item.image_path)
            intent.putExtra("item_id", item.item_id)
            itemView.context.startActivity(intent)

        }


        // 하트 누른 경우
        iv_wish.setOnClickListener {

            if(check){
                isWish = RecommendFragment().wishPost(item_id, item.wish)
                check = false
            }else{
                isWish = RecommendFragment().wishPost(item_id, isWish)
            }

            if(isWish){
                Toast.makeText(itemView.context, "찜 목록에 추가했어요!", Toast.LENGTH_LONG).show()
                iv_wish.setColorFilter(Color.parseColor("#E11616"))
                iv_wish.setImageResource(R.drawable.ic_heart_red)
            }else{
                Toast.makeText(itemView.context, "찜 해제가 완료되었어요", Toast.LENGTH_LONG).show()
                iv_wish.setColorFilter(Color.parseColor("#fcfcfc"))
                iv_wish.setImageResource(R.drawable.ic_heart_white)
            }

        }

    }
}