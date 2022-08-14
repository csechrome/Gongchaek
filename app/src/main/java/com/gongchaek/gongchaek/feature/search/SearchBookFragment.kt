package com.gongchaek.gongchaek.feature.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.gongchaek.gongchaek.R
import com.gongchaek.gongchaek.databinding.FragmentSearchBookBinding
import com.gongchaek.gongchaek.feature.detailpage.DetailPageActivity
import com.gongchaek.gongchaek.feature.tabsearch.RecyclerMyTownBookAdapter
import com.gongchaek.gongchaek.global.BaseFragment
import com.gongchaek.gongchaek.util.ItemBookNormal


class SearchBookFragment : BaseFragment<FragmentSearchBookBinding>(FragmentSearchBookBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TEST
        val bookNormalList = ArrayList<ItemBookNormal>()
        bookNormalList.add(ItemBookNormal("TEST", ContextCompat.getDrawable(requireContext(),
            R.drawable._test
        )!!, "북한산로 2", "1,000원", "2주"))
        bookNormalList.add(ItemBookNormal("TEST", ContextCompat.getDrawable(requireContext(),
            R.drawable._test
        )!!, "북한산로 2", "1,000원", "2주"))
        bookNormalList.add(ItemBookNormal("TEST", ContextCompat.getDrawable(requireContext(),
            R.drawable._test
        )!!, "북한산로 2", "1,000원", "2주"))

        val adapter = RecyclerMyTownBookAdapter(bookNormalList)
        adapter.onItemClick = {
            startActivity(Intent(requireContext(), DetailPageActivity::class.java))
        }
        binding.listSearchBook.adapter = adapter
    }
}