package com.example.bunjang_olivia.src.main.my.setting.account.add.addAccount.bottomDialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.android_mvc_template.config.ApplicationClass.Companion.editor
import com.example.android_mvc_template.config.ApplicationClass.Companion.sSharedPreferences
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.ActivityAddAccountBinding
import com.example.bunjang_olivia.databinding.FragmentBankBottomDialogBinding
import com.example.bunjang_olivia.src.main.my.setting.account.add.addAccount.AddAccountActivity
import com.example.bunjang_olivia.src.main.my.setting.account.add.addAccount.models.AddAcount
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BankBottomDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBankBottomDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.AppBottomSheetDialogTheme)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBankBottomDialogBinding.inflate(layoutInflater)

        binding.btnKookmin.setOnClickListener {
            editor.putString("bank", binding.tvKookmin.text.toString())
            editor.commit()
            dismiss()
        }
        binding.btnWoori.setOnClickListener {
            editor.putString("bank", binding.tvWoori.text.toString())
            editor.commit()
            dismiss()
        }
        binding.btnShinhan.setOnClickListener {
            editor.putString("bank", binding.tvShinhan.text.toString())
            editor.commit()
            dismiss()
        }
        binding.btnHana.setOnClickListener {
            editor.putString("bank", binding.tvHana.text.toString())
            editor.commit()
            dismiss()
        }
        binding.btnKakao.setOnClickListener {
            editor.putString("bank", binding.tvKakaoBank.text.toString())
            editor.commit()
            dismiss()
        }

        binding.btnNongheup.setOnClickListener {
            editor.putString("bank", binding.tvNonghuep.text.toString())
            editor.commit()
            dismiss()
        }

        binding.btnIbk.setOnClickListener {
            editor.putString("bank", binding.tvIbk.text.toString())
            editor.commit()
            dismiss()
        }
        binding.btnToss.setOnClickListener {
            editor.putString("bank", binding.tvToss.text.toString())
            editor.commit()
            dismiss()
        }




        return binding.root
    }

}