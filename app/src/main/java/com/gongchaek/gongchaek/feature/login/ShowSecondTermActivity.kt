package com.gongchaek.gongchaek.feature.login

import android.os.Bundle
import com.gongchaek.gongchaek.databinding.ActivityShowSecondTermBinding
import com.gongchaek.gongchaek.global.BaseActivity

class ShowSecondTermActivity : BaseActivity<ActivityShowSecondTermBinding>(ActivityShowSecondTermBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}