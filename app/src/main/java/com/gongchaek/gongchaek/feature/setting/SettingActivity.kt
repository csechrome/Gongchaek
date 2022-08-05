package com.gongchaek.gongchaek.feature.setting

import android.os.Bundle
import com.gongchaek.gongchaek.databinding.ActivitySettingBinding
import com.gongchaek.gongchaek.global.BaseActivity


class SettingActivity : BaseActivity<ActivitySettingBinding>(ActivitySettingBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}