package org.sopt.carrot16_2.ui.create

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import org.sopt.carrot16_2.R
import org.sopt.carrot16_2.data.remote.RetrofitBuilder
import org.sopt.carrot16_2.data.remote.entity.request.CreateRequest
import org.sopt.carrot16_2.data.remote.entity.response.CreateResponse
import org.sopt.carrot16_2.databinding.ActivityCreateBinding
import org.sopt.carrot16_2.model.ImageData
import org.sopt.carrot16_2.ui.base.BaseActivity
import org.sopt.carrot16_2.ui.create.viewmodel.CreateViewModel
import org.sopt.carrot16_2.ui.read.ReadActivity
import org.sopt.carrot16_2.util.enqueueUtil
import retrofit2.Call

class CreateActivity : BaseActivity<ActivityCreateBinding>(R.layout.activity_create) {
    private lateinit var createImageAdapter : CreateImageAdapter
    private var radioButtonState = false
    private val createViewModel by viewModels<CreateViewModel>()
    var photoUri : Uri? = null
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                navigateGallery()
            } else {
                Toast.makeText(this,"갤러리 접근 실패",Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.createViewModel = createViewModel


        initAdapter()
        changeEvent()
        initFinishBtnClickListener()
        //completeEvent()
        imageUpload()
    }

    private fun initAdapter() {
        createImageAdapter = CreateImageAdapter()
        binding.rvImage.adapter = createImageAdapter
    }

    private fun changeEvent() {
        binding.btnRadio.setOnClickListener {
            binding.btnRadio?.isSelected = binding.btnRadio.isSelected == false
            radioButtonState = !radioButtonState
            if (radioButtonState) {
                binding.tvSugMoney.setTextColor(getColor(R.color.carrot_and_black2))
            } else {
                binding.tvSugMoney.setTextColor(getColor(R.color.carrot_and_squaregray))
            }
        }
    }

    //완료 버튼 클릭시 데이터 전송
    private fun initFinishBtnClickListener() {
        binding.tvFinish.setOnClickListener {
            val createRequest = CreateRequest(
                imageCount = createImageAdapter.itemCount,
                title = binding.etTitle.text.toString(),
                category = binding.etCategory.text.toString(),
                price = binding.etMoney.text.toString().toInt(),
                isPriceSuggestion = binding.btnRadio.isSelected,
                contents = binding.etWriteText.text.toString()
            )
            val call : Call<CreateResponse> = RetrofitBuilder.createService.postCreate(createRequest)
            call.enqueueUtil(
                onSuccess = {
                    val data = it.data
                    Toast.makeText(this@CreateActivity, "상품 등록 성공",Toast.LENGTH_SHORT).show()
                    Log.e("dk",createRequest.toString())
                    val intent = Intent(this@CreateActivity, ReadActivity::class.java).apply {
                        putExtra("id",data.id)
                    }
                    startActivity(intent)
                    finish()
                },
                onError = {
                    when(it){
                        404 -> Toast.makeText(this@CreateActivity, "요청값을 처리할 수 없습니다",Toast.LENGTH_SHORT).show()
                        500 -> Toast.makeText(this@CreateActivity, "internal server error",Toast.LENGTH_SHORT).show()
                    }

                }
            )
        }
    }

    /**
     * 카메라 연결
     */
    private fun imageUpload() {
        //버튼 클릭시 갤러리 접근하여 이미지 uri 전달
        binding.clCamera.setOnClickListener {
            if(createImageAdapter.itemCount < 10){
                when {
                    ContextCompat.checkSelfPermission(
                        this, Manifest.permission.READ_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED -> {
                        navigateGallery()
                    }
                    shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                        showInContextUI()
                    }
                    else -> {
                        requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                    }
                }
            }
            else{
                Toast.makeText(this,"이미지 개수 10개 초과",Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun navigateGallery() {
        val photoIntent = Intent(Intent.ACTION_PICK) //open the album
        photoIntent.type = "image/*"
        openGallery.launch(photoIntent)
    }


    private val openGallery : ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == RESULT_OK && result.data != null){
            photoUri = result.data?.data
            createImageAdapter.imageList.add(ImageData(photoUri.toString()))
            createImageAdapter.notifyDataSetChanged()
            binding.tvPickedImage.text = createImageAdapter.itemCount.toString()


        }else if(result.resultCode == RESULT_CANCELED){
            Toast.makeText(this,"사진을 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
        }
    }


    private fun showInContextUI() {
        AlertDialog.Builder(this)
            .setTitle("권한 동의 필요")
            .setMessage("프로필 사진 수정을 위해 갤러리 접근 권한이 필요합니다.")
            .setPositiveButton("동의") { _, _ ->
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            .setNegativeButton("거부") { _, _ ->
                Toast.makeText(this, "갤러리 접근 권한이 없습니다.", Toast.LENGTH_SHORT).show()
            }
            .create()
            .show()
    }

}
