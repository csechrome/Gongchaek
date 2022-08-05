package com.gongchaek.gongchaek.feature.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import com.gongchaek.gongchaek.databinding.ActivityOnboardingBinding
import com.gongchaek.gongchaek.feature.login.LoginInfoActivity
import com.gongchaek.gongchaek.feature.login.LoginTermActivity
import com.gongchaek.gongchaek.feature.login.SetInterestActivity
import com.gongchaek.gongchaek.feature.main.MainActivity
import com.gongchaek.gongchaek.global.BaseActivity

class OnboardingActivity : BaseActivity<ActivityOnboardingBinding>(ActivityOnboardingBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarTransparent()

        if(pref.getBoolean("autoLogin", false)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.btnStart.setOnClickListener {
            startActivity(Intent(this, LoginTermActivity::class.java))
        }
    }

    private fun setStatusBarTransparent() {
        val window = window
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
}