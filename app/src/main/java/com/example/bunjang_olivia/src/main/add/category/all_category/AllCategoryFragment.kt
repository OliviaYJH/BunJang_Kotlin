package com.example.bunjang_olivia.src.main.add.category.all_category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.FragmentAllCategoryBinding
import com.example.bunjang_olivia.src.main.add.category.CategoryActivity
import com.example.bunjang_olivia.src.main.add.category.bag.BagFragment
import com.example.bunjang_olivia.src.main.add.category.digital.DigitalFragment
import com.example.bunjang_olivia.src.main.add.category.fashion.FashionFragment
import com.example.bunjang_olivia.src.main.add.category.jewerly.JewerlyFragment
import com.example.bunjang_olivia.src.main.add.category.man_cloth.ManClothFragment
import com.example.bunjang_olivia.src.main.add.category.shoes.ShoesFragment
import com.example.bunjang_olivia.src.main.add.category.woman_cloth.WomanClothFragment
import com.example.bunjang_olivia.util.recycler.allCategory.AllCategoryAdapter
import com.example.bunjang_olivia.util.recycler.allCategory.AllCategoryData


class AllCategoryFragment : Fragment(){

    private lateinit var binding: FragmentAllCategoryBinding

    lateinit var categoryAdapter: AllCategoryAdapter
    val categoryDatas = mutableListOf<AllCategoryData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllCategoryBinding.inflate(layoutInflater)

        /*
        categoryAdapter = AllCategoryAdapter(requireContext() as FragmentActivity)
        binding.rvAllCategory.adapter = categoryAdapter

        categoryDatas.apply {
            add(AllCategoryData(Image = R.drawable.ic_woman_cloth, title = getString(R.string.woman_cloth_category)))
            for(i in 0..8)
                add(AllCategoryData(Image = R.drawable.ic_man_cloth, title = getString(R.string.man_cloth_category)))
        }

        categoryAdapter.categoryDatas = categoryDatas
        categoryAdapter.notifyDataSetChanged()

         */


        binding.womanCloth.setOnClickListener {
            (activity as CategoryActivity).changeCategory(WomanClothFragment())
        }

        binding.manCloth.setOnClickListener {
            (activity as CategoryActivity).changeCategory(ManClothFragment())
        }

        binding.shoes.setOnClickListener {
            (activity as CategoryActivity).changeCategory(ShoesFragment())
        }

        binding.bag.setOnClickListener {
            (activity as CategoryActivity).changeCategory(BagFragment())
        }

        binding.jewerly.setOnClickListener {
            (activity as CategoryActivity).changeCategory(JewerlyFragment())
        }

        binding.fashion.setOnClickListener {
            (activity as CategoryActivity).changeCategory(FashionFragment())
        }
        binding.digital.setOnClickListener {
            (activity as CategoryActivity).changeCategory(DigitalFragment())
        }

        return binding.root
    }


}