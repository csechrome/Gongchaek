package com.gongchaek.gongchaek.feature.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import com.gongchaek.gongchaek.databinding.ActivityLoginPhoneNumBinding
import com.gongchaek.gongchaek.global.BaseActivity
import com.gongchaek.gongchaek.util.hideKeyboard
import com.gongchaek.gongchaek.util.setDisabled
import com.gongchaek.gongchaek.util.setEnabled
import com.gongchaek.gongchaek.util.showKeyboard
import java.util.regex.Pattern


class LoginPhoneNumActivity : BaseActivity<ActivityLoginPhoneNumBinding>(ActivityLoginPhoneNumBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showKeyboard()

        binding.etPhoneNum.addTextChangedListener { text ->
            val phoneRegex = "^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$"
            if (Pattern.matches(phoneRegex, binding.etPhoneNum.text)) {
                binding.btnNext.setEnabled()
            } else {
                binding.btnNext.setDisabled()
            }
        }

        binding.btnNext.setOnClickListener {
            hideKeyboard()
            val phoneNum = "+82" + binding.etPhoneNum.text.substring(1)
            val intent = Intent(this, LoginAuthActivity::class.java)
            intent.putExtra("phoneNum", phoneNum)
            startActivity(intent)
        }

        binding.btnContact.setOnClickListener {
            // TODO: 카카오채널로 연결
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}