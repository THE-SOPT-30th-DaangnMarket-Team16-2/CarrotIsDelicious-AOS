package org.sopt.carrot16_2.ui.create

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.createViewModel = createViewModel


        initAdapter()
        changeEvent()
        initFinishBtnClickListener()
        //completeEvent()
    }

    private fun initAdapter() {
        createImageAdapter = CreateImageAdapter()
        val image: String =
            "https://images.theconversation.com/files/457052/original/file-20220408-15-pl446k.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=1000&fit=clip"
        binding.rvImage.adapter = createImageAdapter
        createImageAdapter.imageList.addAll(
            listOf(
                ImageData(image),
                ImageData(image),
                ImageData(image)
            )
        )
        createImageAdapter.notifyDataSetChanged()
    }

    private fun changeEvent() {
        /*binding.etMoney.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {//커서 올렸을때

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {//바뀌는 도중
                if(s?.length!! >0){
                    binding.tvSugMoney.setTextColor(getColor(R.color.carrot_and_black2))
                    binding.tvWon.setTextColor(getColor(R.color.carrot_and_black2))
                }else {
                    binding.tvSugMoney.setTextColor(getColor(R.color.carrot_and_squaregray))
                    binding.tvWon.setTextColor(getColor(R.color.carrot_and_squaregray))
                }
            }

            override fun afterTextChanged(s: Editable?) {//아에 바뀐후
            }
        })*/
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
                    val intent = Intent(this@CreateActivity, ReadActivity::class.java).apply {
                        putExtra("title",createRequest.title)
                        putExtra("category",createRequest.category)
                        putExtra("price", createRequest.price)
                        putExtra("contents",createRequest.contents)
                        putExtra("isPrice", createRequest.isPriceSuggestion)
                    }
                    Toast.makeText(this@CreateActivity, "게시글 업로드 성공",Toast.LENGTH_SHORT).show()
                    setResult(RESULT_OK,intent)
                    if (!isFinishing) {
                        finish()
                    }
                },
                onError = {
                    Toast.makeText(this@CreateActivity, "게시글 업로드 실패",Toast.LENGTH_SHORT).show()
                }
            )

        }
    }


}
