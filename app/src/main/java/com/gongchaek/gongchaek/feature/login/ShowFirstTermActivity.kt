package com.gongchaek.gongchaek.feature.login

import android.os.Bundle
import com.gongchaek.gongchaek.databinding.ActivityShowFirstTermBinding
import com.gongchaek.gongchaek.global.BaseActivity

class ShowFirstTermActivity : BaseActivity<ActivityShowFirstTermBinding>(ActivityShowFirstTermBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}