package com.example.bunjang_olivia.src.main.add.category

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.ActivityCategoryBinding
import com.example.bunjang_olivia.src.main.add.category.all_category.AllCategoryFragment
import com.example.bunjang_olivia.src.main.add.category.man_cloth.ManClothFragment
import com.example.bunjang_olivia.src.main.add.category.woman_cloth.WomanClothFragment
import com.example.rc_aos_tem.config.BaseActivity

class CategoryActivity : BaseActivity<ActivityCategoryBinding>(ActivityCategoryBinding::inflate) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.icLeftArrow.setOnClickListener { finish() }


        supportFragmentManager.beginTransaction()
            .add(R.id.framelayout_category, AllCategoryFragment())
            .commit()


    }

    fun changeCategory(fa: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.framelayout_category, fa)
            .commit()
    }


}