package org.sopt.carrot16_2.ui.read

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import org.sopt.carrot16_2.R
import org.sopt.carrot16_2.databinding.ActivityReadBinding
import org.sopt.carrot16_2.ui.base.BaseActivity
import org.sopt.carrot16_2.ui.read.adapter.ReadViewPagerAdapter

class ReadActivity : BaseActivity<ActivityReadBinding>(R.layout.activity_read) {
    private lateinit var readViewPagerAdapter: ReadViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewPagerAdapter()
        initBackBtnClickListener()
        initStateLayoutClickListener()
    }

    private fun initViewPagerAdapter() {
        val url1 = "https://icatcare.org/app/uploads/2018/07/Thinking-of-getting-a-cat.png"
        val url2 = "https://i.guim.co.uk/img/media/26392d05302e02f7bf4eb143bb84c8097d09144b/446_167_3683_2210/master/3683.jpg?width=1200&height=1200&quality=85&auto=format&fit=crop&s=49ed3252c0b2ffb49cf8b508892e452d"
        val urlList = listOf(url1, url2)

        readViewPagerAdapter = ReadViewPagerAdapter(urlList)
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
}
