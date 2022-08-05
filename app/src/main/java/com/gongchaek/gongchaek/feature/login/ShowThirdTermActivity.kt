package com.gongchaek.gongchaek.feature.login

import android.os.Bundle
import com.gongchaek.gongchaek.databinding.ActivityShowThirdTermBinding
import com.gongchaek.gongchaek.global.BaseActivity

class ShowThirdTermActivity : BaseActivity<ActivityShowThirdTermBinding>(ActivityShowThirdTermBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}