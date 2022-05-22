package org.sopt.carrot16_2.ui.create

import android.content.Intent
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import org.sopt.carrot16_2.R
import org.sopt.carrot16_2.databinding.ActivityCreateBinding
import org.sopt.carrot16_2.model.ImageData
import org.sopt.carrot16_2.ui.base.BaseActivity
import org.sopt.carrot16_2.ui.read.ReadActivity

class CreateActivity : BaseActivity<ActivityCreateBinding>(R.layout.activity_create) {
    private lateinit var createImageAdapter: CreateImageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAdapter()
        changeEvent()
        initFinishBtnClickListener()
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
        binding.etMoney.addTextChangedListener {
            if (!binding.etMoney.text.isEmpty()) {
                binding.tvSugMoney.setTextColor(getColor(R.color.carrot_and_black2))
                binding.tvWon.setTextColor(getColor(R.color.carrot_and_black2))
            } else {
                binding.tvSugMoney.setTextColor(getColor(R.color.carrot_and_squaregray))
                binding.tvWon.setTextColor(getColor(R.color.carrot_and_squaregray))
            }
        }
        binding.btnRadio.setOnClickListener{
            binding.btnRadio?.isSelected = binding.btnRadio.isSelected==false
        }
    }

    private fun initFinishBtnClickListener() {
        binding.tvFinish.setOnClickListener {
            startActivity(Intent(this, ReadActivity::class.java))
        }
    }

}
