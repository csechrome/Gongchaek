package com.gongchaek.gongchaek.feature.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import com.gongchaek.gongchaek.R
import com.gongchaek.gongchaek.global.BaseActivity
import com.gongchaek.gongchaek.databinding.ActivitySearchBinding
import com.gongchaek.gongchaek.util.hideKeyboard
import com.gongchaek.gongchaek.util.setDisabled
import com.gongchaek.gongchaek.util.setEnabled
import com.gongchaek.gongchaek.util.showKeyboard
import com.google.android.material.tabs.TabLayoutMediator


class SearchActivity : BaseActivity<ActivitySearchBinding>(ActivitySearchBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showKeyboard()

        // TODO: 미리 정보 다 받아오기

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                phoneBookListAdapter.getFilter().filter(p0) TODO: 검색
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.etSearch.setOnEditorActionListener { textView, action, event ->
            var handled = false

            if (action == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard()
                handled = true
                // TODO: Search
            }

            handled
        }

        // Setup TabLayout & ViewPager
        val adapter = ViewPagerSearchAdapter(this)
        binding.vpSearch.adapter = adapter
        TabLayoutMediator(binding.tabSearch, binding.vpSearch) { tab, position ->
            when (position) {
                0 -> tab.text = "도서"
                1 -> tab.text = "서재"
            }
        }.attach()

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}