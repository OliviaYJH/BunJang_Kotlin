package com.example.bunjang_olivia.src.main.my.setting.account.add.bottomDialog

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.android_mvc_template.config.ApplicationClass.Companion.sSharedPreferences
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.FragmentAccountBottomDialogBinding
import com.example.bunjang_olivia.src.main.my.setting.account.add.addAccount.models.Account
import com.example.bunjang_olivia.src.main.my.setting.account.add.bottomDialog.modify.ModifyAccountActivity
import com.example.rc_aos_tem.config.BaseResponse
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AccountBottomDialogFragment : BottomSheetDialogFragment(), DeleteAccountView {

    private lateinit var binding: FragmentAccountBottomDialogBinding

    var my_account_number : String?= null
    var my_bank: String?= null
    var my_user_name: String?= null
    var my_for_sale: Int?=null
    var my_for_return: Int?=null
    var userId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBottomDialogBinding.inflate(layoutInflater)

        userId = sSharedPreferences.getInt("userId", 0)

        my_account_number = arguments?.getString("account_number")
        my_bank = arguments?.getString("bank")
        my_user_name = arguments?.getString("user_name")
        my_for_sale = arguments?.getInt("for_sale")
        my_for_return = arguments?.getInt("for_return")

        binding.tvModifyAccount.setOnClickListener {
            var intent = Intent(context, ModifyAccountActivity::class.java)
            intent.putExtra("account_number", my_account_number)
            intent.putExtra("bank",my_bank )
            intent.putExtra("user_name",my_user_name )
            intent.putExtra("for_sale",my_for_sale)
            intent.putExtra("for_return", my_for_return)
            startActivity(intent)
        }

        binding.tvDeleteAccount.setOnClickListener {
            dismiss()

            var dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.dialog_delete_account)
            dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCanceledOnTouchOutside(true)
            dialog.setCancelable(true)
            dialog.show()

            val btnNo = dialog.findViewById<TextView>(R.id.tv_no)
            val btnYes = dialog.findViewById<TextView>(R.id.tv_yes)

            btnNo.setOnClickListener {
                dialog.dismiss()
            }

            btnYes.setOnClickListener {
                dialog.dismiss()
                var delete = Account(my_account_number!!)
                DeleteAccountService(this).tryPatchAccount(userId!!, delete)
            }

        }

        return binding.root
    }

    override fun onPatchAccountSuccess(response: BaseResponse) {

    }

    override fun onPatchAccountFailure(message: String) {
        Log.d("fail", message)
    }


}