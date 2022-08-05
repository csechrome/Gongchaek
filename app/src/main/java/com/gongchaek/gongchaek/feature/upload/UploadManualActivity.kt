package com.gongchaek.gongchaek.feature.upload

import android.content.Intent
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.gongchaek.gongchaek.R
import com.gongchaek.gongchaek.databinding.ActivityUploadManualBinding
import com.gongchaek.gongchaek.global.BaseActivity


class UploadManualActivity :
    BaseActivity<ActivityUploadManualBinding>(ActivityUploadManualBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val imageList = listOf(
            R.drawable.ic_upload_manual_1,
            R.drawable.ic_upload_manual_2,
            R.drawable.ic_upload_manual_3
        )

        binding.vpUploadManual.adapter = ViewPagerUploadManualAdapter(imageList)

        binding.vpUploadManual.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.indicator0.setBackgroundResource(R.drawable.item_dot_gray)
                binding.indicator1.setBackgroundResource(R.drawable.item_dot_gray)
                binding.indicator2.setBackgroundResource(R.drawable.item_dot_gray)
                when(position) {
                    0 -> binding.indicator0.setBackgroundResource(R.drawable.item_dot_blue)
                    1 -> binding.indicator1.setBackgroundResource(R.drawable.item_dot_blue)
                    2 -> binding.indicator2.setBackgroundResource(R.drawable.item_dot_blue)
                }
            }
        })

        binding.btnNext.setOnClickListener {
            startActivity(Intent(this, UploadActivity::class.java))
            finish()
        }
    }
}