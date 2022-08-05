package com.gongchaek.gongchaek.feature.profile

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import com.gongchaek.gongchaek.R
import com.gongchaek.gongchaek.databinding.FragmentProfileBinding
import com.gongchaek.gongchaek.global.BaseFragment
import com.gongchaek.gongchaek.util.hideKeyboard
import com.gongchaek.gongchaek.util.showKeyboard


class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Book Search Button Setting
        binding.btnSearchBook.setOnClickListener {
            if (binding.etSearchBook.visibility == View.VISIBLE) {
                binding.etSearchBook.visibility = View.INVISIBLE
                binding.etSearchBook.text.clear()
                binding.btnSearchBook.setImageResource(R.drawable.ic_search)
                requireContext().hideKeyboard()
            } else {
                requireContext().showKeyboard()
                binding.etSearchBook.visibility = View.VISIBLE
                binding.etSearchBook.requestFocus()
                binding.btnSearchBook.setImageResource(R.drawable.ic_exit_gray)
            }
        }

        binding.etSearchBook.setOnEditorActionListener { textView, action, event ->
            var handled = false

            if (action == EditorInfo.IME_ACTION_SEARCH) {
                binding.etSearchBook.clearFocus()
                requireContext().hideKeyboard()
                handled = true
            }

            handled
        }
    }
}