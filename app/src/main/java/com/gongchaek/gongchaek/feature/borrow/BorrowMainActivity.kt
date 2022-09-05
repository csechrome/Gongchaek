package com.gongchaek.gongchaek.feature.borrow

import android.os.Bundle
import com.gongchaek.gongchaek.databinding.ActivityBorrowMainBinding
import com.gongchaek.gongchaek.global.BaseActivity

class BorrowMainActivity :
    BaseActivity<ActivityBorrowMainBinding>(ActivityBorrowMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listBorrowPeriod = arrayOf(
            "1일",
            "2일",
            "3일",
            "4일",
            "5일",
            "6일",
            "7일",
            "8일",
            "9일",
            "10일",
            "11일",
            "12일",
            "13일",
            "14일"
        )

        binding.npPeriod.minValue = 1
        binding.npPeriod.maxValue = 14
        binding.npPeriod.value = 7
        binding.npPeriod.wrapSelectorWheel = false
    }
}