package com.example.bunjang_olivia.src.main.add

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Gallery
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.bunjang_olivia.R
import com.example.bunjang_olivia.databinding.ActivityAddBinding
import com.example.bunjang_olivia.src.main.add.bottomDialog.OptionBottomSheetDialogFragment
import com.example.bunjang_olivia.src.main.add.category.CategoryActivity
import com.example.bunjang_olivia.src.main.add.models.Tags
import com.example.bunjang_olivia.src.main.add.tag.AddService
import com.example.bunjang_olivia.src.main.add.tag.TagActivity
import com.example.rc_aos_tem.config.BaseActivity
import com.example.rc_aos_tem.config.BaseResponse
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.BufferedSink
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.Normalizer
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class AddActivity : BaseActivity<ActivityAddBinding>(ActivityAddBinding::inflate), AddView {

    var delivery_checked = false
    var pay_checked = false

    private var OPEN_GALLERY = 1
    private var OPEN_CATEGORY = 2
    private var OPEN_TAG = 3

    var files = ArrayList<MultipartBody.Part>()
    var map = HashMap<String, RequestBody>()
    var tags = arrayOfNulls<String>(10)
    var product_category_id: Int? = null
    var tag: String?= null
    var bitmaps = ArrayList<Bitmap>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.icLeftArrow.setOnClickListener { finish() }

        binding.btnGallery.setOnClickListener{
            val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent, OPEN_GALLERY)
        }

        binding.linearlayoutDelivery.setOnClickListener {
            if(!delivery_checked){
                binding.ivDelivery.setColorFilter(ContextCompat.getColor(this, R.color.home_ad_first))
                binding.ivDelivery.setImageResource(R.drawable.ic_checked)
                delivery_checked = true
            }else{
                binding.ivDelivery.setColorFilter(ContextCompat.getColor(this, R.color.colorLightGray))
                binding.ivDelivery.setImageResource(R.drawable.ic_non_check)
                delivery_checked = false
            }

        }

        binding.btnOption.setOnClickListener {
            val bottomSheet = OptionBottomSheetDialogFragment()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }

        binding.btnSafety.setOnClickListener {
            if(!pay_checked){
                // 눌려있는 경우
                binding.btnSafety.setTextColor(ContextCompat.getColor(this, R.color.colorLightGray))
                binding.btnSafety.setBackgroundResource(R.drawable.btn_add_safety_none)
                binding.btnPayChecked.setColorFilter(ContextCompat.getColor(this, R.color.colorLightGray))
                pay_checked = true
            }else{
                binding.btnSafety.setTextColor(ContextCompat.getColor(this, R.color.black))
                binding.btnSafety.setBackgroundResource(R.drawable.btn_add_safety)
                binding.btnPayChecked.setColorFilter(ContextCompat.getColor(this, R.color.add_btn))
                pay_checked = false
            }
        }

        binding.tvCategory.setOnClickListener {
            val category_intent= Intent(this, CategoryActivity::class.java)
            startActivityForResult(category_intent, OPEN_CATEGORY)
        }

        binding.tvTag.setOnClickListener {
            val tag_intent = Intent(this, TagActivity::class.java)
            startActivityForResult(tag_intent, OPEN_TAG)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == OPEN_CATEGORY){
            var get_category = data?.getStringExtra("category")
            binding.tvCategory.text = get_category
            product_category_id = data?.getIntExtra("id", -1)
        }

        if(requestCode == OPEN_TAG){
            tag = data?.getStringExtra("tag")!!
            binding.tvTag.text = "# " + tag
        }

        if(resultCode == Activity.RESULT_OK){
            var gallery_imgs = arrayOf(binding.ivSelected1,binding.ivSelected2, binding.ivSelected3, binding.ivSelected4)
            var delete_imgs = arrayOf(binding.ivDelete1, binding.ivDelete2, binding.ivDelete3, binding.ivDelete4)

            if(requestCode == OPEN_GALLERY){
                var currentImageUrl: Uri? = data?.data
                try{
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, currentImageUrl)

                    bitmaps.add(bitmap)

                    for(i in bitmaps.indices){
                        delete_imgs[i].setOnClickListener {
                            delete_imgs[bitmaps.size-1].isGone = true
                            bitmaps.removeAt(i)
                            gallery_imgs[i].isInvisible = true

                            binding.tvImgNum.text = bitmaps.size.toString() + "/12"
                        }
                    }

                    for(i in bitmaps.indices){
                        gallery_imgs[i].setImageBitmap(bitmaps[i])
                        delete_imgs[i].isVisible = true

                        binding.tvImgNum.text = (i+1).toString() + "/12"
                    }

                    val bitmapRequestBody = BitmapRequestBody(bitmap)
                    val bitmapMultipartBody: MultipartBody.Part? =
                        if(bitmapRequestBody == null) null
                        else MultipartBody.Part.createFormData("image", ".png", bitmapRequestBody)

                    files.add(bitmapMultipartBody!!)


                    binding.btnProductAdd.setOnClickListener {
                        // 추가
                        showLoadingDialog(this)
                        addProduct()
                        AddService(this).tryPostAddProduct(map, files)

                    }


                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
    }

    inner class BitmapRequestBody(private val bitmap: Bitmap): RequestBody(){
        override fun contentType(): MediaType? = "image/jpeg".toMediaType()

        override fun writeTo(sink: BufferedSink) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 99, sink.outputStream())
        }

    }

    override fun onPostAddSuccess(response: BaseResponse) {
        dismissLoadingDialog()
        finish()
    }

    override fun onPostAddFailure(message: String) {
        showCustomToast(message)
    }

    fun addProduct(){
        // 정보 가져오기
        var category_id = product_category_id
        var title = binding.etProductNameAdd.text.toString()
        var location = "지역정보 없음" // 장소
        var price = binding.etPrice.text

        var delivery_fee_included= 1
        if(!delivery_checked){
            delivery_fee_included= 0
        }

        var count = 1 // 개수
        var condition = 0 // 중고
        var exchange = 0 // 교환불가
        var detail = binding.etDescription.text.toString()
        
        var safety_pay = 1
        if(pay_checked){
            safety_pay = 0
        }

        // multipart
        tags[0] = tag

        var Category_id = RequestBody.create("text/plain".toMediaTypeOrNull(), category_id.toString())
        var Title = RequestBody.create("text/plain".toMediaTypeOrNull(), title)
        var Location = RequestBody.create("text/plain".toMediaTypeOrNull(), location)
        var Price = RequestBody.create("text/plain".toMediaTypeOrNull(), price.toString())
        var Delivery_fee_included = RequestBody.create("text/plain".toMediaTypeOrNull(), delivery_fee_included.toString())
        var Count = RequestBody.create("text/plain".toMediaTypeOrNull(), count.toString())
        var Condition = RequestBody.create("text/plain".toMediaTypeOrNull(), condition.toString())
        var Exchange = RequestBody.create("text/plain".toMediaTypeOrNull(), exchange.toString())
        var Detail = RequestBody.create("text/plain".toMediaTypeOrNull(), detail)
        var Safety_pay = RequestBody.create("text/plain".toMediaTypeOrNull(), safety_pay.toString())
        var Tags = RequestBody.create("text/plain".toMediaTypeOrNull(), tags[0]!!)

        map["category_id"] = Category_id
        map["title"] = Title
        map["location"] = Location
        map["price"] = Price
        map["delivery_fee_included"] = Delivery_fee_included
        map["count"] = Count
        map["condition"] = Condition
        map["exchange"] = Exchange
        map["detail"] = Detail
        map["safety_pay"] = Safety_pay
        map["tags[0]"] = Tags



        
    }




}