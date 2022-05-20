package org.sopt.carrot16_2.ui.create

import android.os.Bundle
import org.sopt.carrot16_2.R
import org.sopt.carrot16_2.databinding.ActivityCreateBinding
import org.sopt.carrot16_2.model.ImageData
import org.sopt.carrot16_2.ui.base.BaseActivity

class CreateActivity : BaseActivity<ActivityCreateBinding>(R.layout.activity_create) {
    private lateinit var createImageAdapter: CreateImageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initAdapter()
    }

    private fun initAdapter(){
        binding.rvImage.adapter = createImageAdapter
        createImageAdapter.imageList.addAll(
            listOf(
                ImageData("R.drawable.ic_launcher_foreground")
            )
        )
        createImageAdapter.notifyDataSetChanged()
    }

}
