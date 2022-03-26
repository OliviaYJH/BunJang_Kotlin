package com.example.bunjang_olivia.src.login.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.FragmentLoginBottomSheetDialogBinding
import com.example.rc_aos_tem.config.BaseFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LoginBottomSheetDialogFragment : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.AppBottomSheetDialogTheme)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_login_bottom_sheet_dialog, container, false)
    }

}