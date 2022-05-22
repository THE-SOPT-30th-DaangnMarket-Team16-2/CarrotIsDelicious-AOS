package org.sopt.carrot16_2.ui.create

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import org.sopt.carrot16_2.R
import org.sopt.carrot16_2.databinding.ActivityCreateBinding
import org.sopt.carrot16_2.model.ImageData
import org.sopt.carrot16_2.ui.base.BaseActivity

class CreateActivity : BaseActivity<ActivityCreateBinding>(R.layout.activity_create) {
    private lateinit var createImageAdapter: CreateImageAdapter
    private var radioButtonState = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAdapter()
        changeEvent()

    }

    private fun initAdapter(){
        createImageAdapter = CreateImageAdapter()
        val image : String = "https://images.theconversation.com/files/457052/original/file-20220408-15-pl446k.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=1000&fit=clip"
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
        binding.btnRadio.setOnClickListener{
            binding.btnRadio?.isSelected = binding.btnRadio.isSelected==false
            radioButtonState = !radioButtonState
            if(radioButtonState) {
                binding.tvSugMoney.setTextColor(getColor(R.color.carrot_and_black2))
            } else {
                binding.tvSugMoney.setTextColor(getColor(R.color.carrot_and_squaregray))
            }
        }
    }

}
