package com.gongchaek.gongchaek.feature.profile

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import com.gongchaek.gongchaek.R
import com.gongchaek.gongchaek.databinding.ActivityProfileBinding
import com.gongchaek.gongchaek.global.BaseActivity
import com.gongchaek.gongchaek.util.hideKeyboard
import com.gongchaek.gongchaek.util.showKeyboard

class ProfileActivity : BaseActivity<ActivityProfileBinding>(ActivityProfileBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Book Search Button Setting
        binding.btnSearchBook.setOnClickListener {
            if (binding.etSearchBook.visibility == View.VISIBLE) {
                binding.etSearchBook.visibility = View.INVISIBLE
                binding.etSearchBook.setText("")
                binding.btnSearchBook.setImageResource(R.drawable.ic_search)
                hideKeyboard()
            } else {
                showKeyboard()
                binding.etSearchBook.visibility = View.VISIBLE
                binding.etSearchBook.requestFocus()
                binding.btnSearchBook.setImageResource(R.drawable.ic_exit_gray)
            }
        }

        binding.etSearchBook.setOnEditorActionListener { textView, action, event ->
            var handled = false

            if (action == EditorInfo.IME_ACTION_DONE) {
                binding.etSearchBook.clearFocus()
                hideKeyboard()
                handled = true
            }

            handled
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}