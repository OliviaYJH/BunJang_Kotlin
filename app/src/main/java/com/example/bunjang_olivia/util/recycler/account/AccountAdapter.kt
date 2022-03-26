package com.example.bunjang_olivia.util.recycler.account


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.ItemAccountRecyclerBinding
import com.example.bunjang_olivia.src.login.bottomSheet.LoginBottomSheetDialogFragment
import com.example.bunjang_olivia.src.main.my.setting.account.AccountSettingActivity
import com.example.bunjang_olivia.src.main.my.setting.account.add.bottomDialog.AccountBottomDialogFragment

class AccountAdapter(val context: FragmentActivity?):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var accoundDatas = mutableListOf<AccountData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemAccountRecyclerBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(context, itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(accoundDatas[position])
    }

    override fun getItemCount(): Int = accoundDatas.size

}

class ViewHolder(val context: FragmentActivity?, val binding: ItemAccountRecyclerBinding): RecyclerView.ViewHolder(binding.root){

    private val bank_img: ImageView = binding.ivAccountBank
    private val account_number: TextView = binding.tvMyAccountNumber
    private val bank: TextView = binding.tvMyAccountBank
    private val user_name: TextView = binding.tvMyAccountHolder
    private val account_menu: ImageView = binding.ivAccountMenu



    fun bind(item: AccountData){

        bank.text = item.bank
        account_number.text = item.account_number
        user_name.text = item.user_name

        if(item.bank.equals("국민은행")){
            bank_img.setImageResource(R.drawable.ic_kookmin)
        }else if(item.bank.equals("우리은행")){
            bank_img.setImageResource(R.drawable.ic_woori)
        }else if(item.bank.equals("신한은행")){
            bank_img.setImageResource(R.drawable.ic_sinhan)
        }else if (item.bank.equals("KEB하나은행")){
            bank_img.setImageResource(R.drawable.ic_hana)
        }else if(item.bank.equals("카카오뱅크")){
            bank_img.setImageResource(R.drawable.ic_kakao_bank)
        }else if(item.bank.equals("농협은행")){
            bank_img.setImageResource(R.drawable.ic_nonghuep)
        }else if(item.bank.equals("기업은행")){
            bank_img.setImageResource(R.drawable.ic_ibk)
        }else{
            bank_img.setImageResource(R.drawable.ic_sc)
        }


        account_menu.setOnClickListener {
            val bottomSheet = AccountBottomDialogFragment()

            val bundle = Bundle()
            bundle.putString("account_number", item.account_number)
            bundle.putString("bank", item.bank)
            bundle.putString("user_name", item.user_name)
            bundle.putInt("for_sale", item.for_sale)
            bundle.putInt("for_return", item.for_return)
            bottomSheet.arguments = bundle

            bottomSheet.show(context!!.supportFragmentManager, bottomSheet.tag)
        }

    }
}