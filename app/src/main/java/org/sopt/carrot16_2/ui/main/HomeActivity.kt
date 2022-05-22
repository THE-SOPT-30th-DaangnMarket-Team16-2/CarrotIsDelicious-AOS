package org.sopt.carrot16_2.ui.main

import android.content.Intent
import android.os.Bundle
import org.sopt.carrot16_2.R
import org.sopt.carrot16_2.databinding.ActivityHomeBinding
import org.sopt.carrot16_2.ui.base.BaseActivity
import org.sopt.carrot16_2.ui.create.CreateActivity

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initWriteBtnClickListener()
    }

    private fun initWriteBtnClickListener() {
        binding.btnHomeWrite.setOnClickListener {
            startActivity(Intent(this, CreateActivity::class.java))
        }
    }
}
