package org.sopt.carrot16_2.ui.read

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import org.sopt.carrot16_2.R
import org.sopt.carrot16_2.databinding.ActivityReadBinding
import org.sopt.carrot16_2.ui.base.BaseActivity
import org.sopt.carrot16_2.ui.read.adapter.ReadViewPagerAdapter
import org.sopt.carrot16_2.ui.read.viewmodel.ReadViewModel
import kotlin.properties.Delegates

class ReadActivity : BaseActivity<ActivityReadBinding>(R.layout.activity_read) {
    private lateinit var readViewPagerAdapter: ReadViewPagerAdapter
    private val readViewModel by viewModels<ReadViewModel>()
    private var readId by Delegates.notNull<Int>()
    private lateinit var getCreateActivityResult : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.readViewModel = readViewModel

        initReadId()
        initReadItem()
        initViewPagerAdapter()
        initBackBtnClickListener()
        initStateLayoutClickListener()
        initHeartBtnClickListener()
    }

    private fun initReadId() {
        readId = intent.getIntExtra("readId", -1)
    }

    private fun initReadItem() {
        readViewModel.getReadItem(readId)
    }

    private fun initViewPagerAdapter() {
        val readImages = readViewModel.readItem.value?.image
        readViewPagerAdapter = ReadViewPagerAdapter(readImages!!)
        binding.vpReadImage.adapter = readViewPagerAdapter
        binding.vpReadImage.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.tlReadIndicator.setViewPager2(binding.vpReadImage)

    }

    private fun initBackBtnClickListener() {
        binding.btnReadBack.setOnClickListener {
            finish()
        }
    }

    private fun initStateLayoutClickListener() {
        binding.layoutReadState.setOnClickListener {
            StateBottomSheet().show(supportFragmentManager, this.javaClass.name)
        }
    }

    private fun initHeartBtnClickListener() {
        binding.btnReadHeart.setOnClickListener {
            readViewModel.initHeart()
        }
    }
}
