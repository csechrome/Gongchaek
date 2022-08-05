package com.gongchaek.gongchaek.feature.tabmypage

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import com.gongchaek.gongchaek.global.BaseFragment
import com.gongchaek.gongchaek.util.ItemBookNormal
import com.gongchaek.gongchaek.R
import com.gongchaek.gongchaek.databinding.FragmentMyPageMineBinding
import com.gongchaek.gongchaek.util.hideKeyboard
import com.gongchaek.gongchaek.util.showKeyboard


class MyPageMineFragment :
    BaseFragment<FragmentMyPageMineBinding>(FragmentMyPageMineBinding::inflate) {

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
                requireContext().hideKeyboard()
                handled = true
            }
            false
        }

        // TEST
        val MyBookList = ArrayList<ItemBookNormal>()
        MyBookList.add(
            ItemBookNormal(
                "TEST",
                ContextCompat.getDrawable(requireContext(), R.drawable._test)!!,
                "북한산로 2",
                "1,000원",
                "2주"
            )
        )
        MyBookList.add(
            ItemBookNormal(
                "TEST",
                ContextCompat.getDrawable(requireContext(), R.drawable._test)!!,
                "북한산로 2",
                "1,000원",
                "2주"
            )
        )
        MyBookList.add(
            ItemBookNormal(
                "TEST",
                ContextCompat.getDrawable(requireContext(), R.drawable._test)!!,
                "북한산로 2",
                "1,000원",
                "2주"
            )
        )

//        binding.listMyTownBook.adapter = RecyclerBookNormalAdapter(MyBookList)
    }
}