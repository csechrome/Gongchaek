package com.gongchaek.gongchaek.feature.upload

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import androidx.viewpager2.widget.ViewPager2
import com.gongchaek.gongchaek.R
import com.gongchaek.gongchaek.databinding.ActivityUploadHintBinding
import com.gongchaek.gongchaek.global.BaseActivity
import android.view.MotionEvent





class UploadHintActivity : BaseActivity<ActivityUploadHintBinding>(ActivityUploadHintBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)

        val imageList = listOf(
            R.drawable.ic_upload_hint_1,
            R.drawable.ic_upload_hint_2
        )

        binding.vpUploadHint.adapter = ViewPagerUploadHintAdapter(imageList)

        binding.vpUploadHint.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.indicator0.setBackgroundResource(R.drawable.item_dot_gray)
                binding.indicator1.setBackgroundResource(R.drawable.item_dot_gray)
                when(position) {
                    0 -> binding.indicator0.setBackgroundResource(R.drawable.item_dot_blue)
                    1 -> binding.indicator1.setBackgroundResource(R.drawable.item_dot_blue)
                }
            }
        })

        binding.layoutDoNotShowAgain.setOnClickListener {
            if(binding.cbDoNotShowAgain.isChecked) {
                binding.cbDoNotShowAgain.isChecked = false
                binding.tvDoNotShowAgain.setTextColor(Color.parseColor("#787878"))
            } else {
                binding.cbDoNotShowAgain.isChecked = true
                binding.tvDoNotShowAgain.setTextColor(Color.parseColor("#0d6fb8"))
            }
        }

        binding.cbDoNotShowAgain.setOnClickListener {
            if(binding.cbDoNotShowAgain.isChecked) {
                binding.tvDoNotShowAgain.setTextColor(Color.parseColor("#0d6fb8"))
            } else {
                binding.tvDoNotShowAgain.setTextColor(Color.parseColor("#787878"))
            }
        }

        binding.btnExit.setOnClickListener {
            if(binding.cbDoNotShowAgain.isChecked) {
                pref.setBoolean("showUploadHint", false)
            }
            finish()
        }
    }

    // 바깥 레이어 클릭해도 안닫히게
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return event.action != MotionEvent.ACTION_OUTSIDE
    }
}