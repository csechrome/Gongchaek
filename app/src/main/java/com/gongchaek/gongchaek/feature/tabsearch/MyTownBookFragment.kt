package com.gongchaek.gongchaek.feature.tabsearch

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.gongchaek.gongchaek.global.BaseFragment
import com.gongchaek.gongchaek.util.ItemBookNormal
import com.gongchaek.gongchaek.R
import com.gongchaek.gongchaek.databinding.FragmentMyTownBookBinding


class MyTownBookFragment : BaseFragment<FragmentMyTownBookBinding>(FragmentMyTownBookBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TEST
        val bookNormalList = ArrayList<ItemBookNormal>()
        bookNormalList.add(ItemBookNormal("TEST", ContextCompat.getDrawable(requireContext(), R.drawable._test)!!, "북한산로 2", "1,000원", "2주"))
        bookNormalList.add(ItemBookNormal("TEST", ContextCompat.getDrawable(requireContext(), R.drawable._test)!!, "북한산로 2", "1,000원", "2주"))
        bookNormalList.add(ItemBookNormal("TEST", ContextCompat.getDrawable(requireContext(), R.drawable._test)!!, "북한산로 2", "1,000원", "2주"))

        binding.listMyTownBook.adapter = RecyclerMyTownBookAdapter(bookNormalList)
    }
}