package com.gongchaek.gongchaek.feature.notification

import android.os.Bundle
import com.gongchaek.gongchaek.databinding.ActivityNotificationBinding
import com.gongchaek.gongchaek.global.BaseActivity


class NotificationActivity : BaseActivity<ActivityNotificationBinding>(ActivityNotificationBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}