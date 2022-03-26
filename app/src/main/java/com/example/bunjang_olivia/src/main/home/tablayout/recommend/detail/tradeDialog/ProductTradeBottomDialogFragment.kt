package com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.FragmentProductTradeBottomDialogBinding
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.trade.deliver.ProductDeliveryActivity
import com.example.bunjang_olivia.src.main.home.tablayout.recommend.detail.tradeDialog.trade.direct.ProductDirectActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ProductTradeBottomDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentProductTradeBottomDialogBinding

    var title: String? = null
    var item_id: Int? = null
    var image_path: String? = null
    var price: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.AppBottomSheetDialogTheme)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductTradeBottomDialogBinding.inflate(layoutInflater)

        title = arguments?.getString("title")
        item_id =arguments?.getInt("item_id", -1)
        image_path = arguments?.getString("image_path")
        price = arguments?.getInt("price", -1)

        binding.linearDeliveryTrade.setOnClickListener {
            var intent = Intent(context, ProductDeliveryActivity::class.java)
            intent.putExtra("title", title)
            intent.putExtra("item_id", item_id)
            intent.putExtra("image_path", image_path)
            intent.putExtra("price", price)
            startActivity(intent)
            dismiss()
        }

        binding.linearDirectTrade.setOnClickListener {
            var intent = Intent(context, ProductDirectActivity::class.java)
            intent.putExtra("title", title)
            intent.putExtra("item_id", item_id)
            intent.putExtra("image_path", image_path)
            intent.putExtra("price", price)
            startActivity(intent)
            dismiss()
        }

        binding.icClose.setOnClickListener { dismiss() }


        return binding.root
    }
}