package com.gongchaek.gongchaek.feature.tabsearch

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.gongchaek.gongchaek.global.BaseFragment
import com.gongchaek.gongchaek.util.ItemLibrary
import com.gongchaek.gongchaek.R
import com.gongchaek.gongchaek.databinding.FragmentMyTownLibraryBinding


class MyTownLibraryFragment : BaseFragment<FragmentMyTownLibraryBinding>(FragmentMyTownLibraryBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TEST
        val libraryList = ArrayList<ItemLibrary>()
        libraryList.add(ItemLibrary("TEST", ContextCompat.getDrawable(requireContext(), R.drawable._test)!!, "안녕하세요", "북한산로 2", "20"))
        libraryList.add(ItemLibrary("TEST", ContextCompat.getDrawable(requireContext(), R.drawable._test)!!, "안녕하세요", "북한산로 2", "20"))
        libraryList.add(ItemLibrary("TEST", ContextCompat.getDrawable(requireContext(), R.drawable._test)!!, "안녕하세요", "북한산로 2", "20"))
        libraryList.add(ItemLibrary("TEST", ContextCompat.getDrawable(requireContext(), R.drawable._test)!!, "안녕하세요", "북한산로 2", "20"))
        libraryList.add(ItemLibrary("TEST", ContextCompat.getDrawable(requireContext(), R.drawable._test)!!, "안녕하세요", "북한산로 2", "20"))

        binding.listMyTownLibrary.adapter = RecyclerMyTownLibraryAdapter(libraryList)
    }
}