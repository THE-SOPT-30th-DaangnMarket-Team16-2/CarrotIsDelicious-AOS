package org.sopt.carrot16_2.ui.main

import android.content.Intent
import android.os.Bundle
import org.sopt.carrot16_2.R
import org.sopt.carrot16_2.databinding.ActivityHomeBinding
import org.sopt.carrot16_2.model.SellData
import org.sopt.carrot16_2.ui.base.BaseActivity
import org.sopt.carrot16_2.ui.create.CreateActivity

class  HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {
    private lateinit var sellAdapter: SellAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAdapter()
        initWriteBtnClickListener()
    }

    private fun initAdapter(){
        sellAdapter = SellAdapter()
        binding.rvHomeContent.adapter = sellAdapter
        sellAdapter.dataList.addAll(
            listOf(
                SellData("강아지 샘플 사료를 판매합니다", "개봉동","9","3,000"),
                SellData("마스크 판매합니다", "구로구 신도림동","10","8,000"),
                SellData("크림 세트 판매합니다", "개봉동","12","24,000"),
                SellData("강아지 사료 판매합니다", "개봉동","12","10,000"),
                SellData("여름 자켓 판매", "개봉동","13","16,000")
            )
        )
       
    }

    private fun initWriteBtnClickListener() {
        binding.btnHomeWrite.setOnClickListener {
            startActivity(Intent(this, CreateActivity::class.java))
        }
    }
}
