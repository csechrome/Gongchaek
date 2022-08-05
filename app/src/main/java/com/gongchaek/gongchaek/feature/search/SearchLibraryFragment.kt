package com.gongchaek.gongchaek.feature.search

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.gongchaek.gongchaek.R
import com.gongchaek.gongchaek.databinding.FragmentSearchLibraryBinding
import com.gongchaek.gongchaek.feature.tabsearch.RecyclerMyTownLibraryAdapter
import com.gongchaek.gongchaek.global.BaseFragment
import com.gongchaek.gongchaek.util.ItemLibrary


class SearchLibraryFragment : BaseFragment<FragmentSearchLibraryBinding>(FragmentSearchLibraryBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TEST
        val libraryList = ArrayList<ItemLibrary>()
        libraryList.add(ItemLibrary("TEST", ContextCompat.getDrawable(requireContext(),
            R.drawable._test
        )!!, "안녕하세요.", "북한산로 2", "23"))
        libraryList.add(ItemLibrary("TEST", ContextCompat.getDrawable(requireContext(),
            R.drawable._test
        )!!, "안녕하세요.", "북한산로 2", "23"))
        libraryList.add(ItemLibrary("TEST", ContextCompat.getDrawable(requireContext(),
            R.drawable._test
        )!!, "안녕하세요.", "북한산로 2", "23"))
        libraryList.add(ItemLibrary("TEST", ContextCompat.getDrawable(requireContext(),
            R.drawable._test
        )!!, "안녕하세요.", "북한산로 2", "23"))

        binding.listSearchLibrary.adapter = RecyclerMyTownLibraryAdapter(libraryList)
        // tab search랑 다른 거임
    }
}