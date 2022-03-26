package com.example.bunjang_olivia.src.main.my.bottomdialog

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.FragmentMyBottomSheetDialogBinding
import com.example.bunjang_olivia.src.main.my.bottomdialog.modify.MyModifyActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MyBottomSheetDialogFragment : BottomSheetDialogFragment() {


    private lateinit var binding: FragmentMyBottomSheetDialogBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyBottomSheetDialogBinding.inflate(layoutInflater)

        binding.ivClose.setOnClickListener { dismiss() }

        binding.tvModify.setOnClickListener {
            startActivity(Intent(context, MyModifyActivity::class.java))
        }

        return binding.root
    }


}