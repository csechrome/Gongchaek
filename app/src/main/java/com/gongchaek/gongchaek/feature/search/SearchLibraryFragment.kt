package com.gongchaek.gongchaek.feature.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.gongchaek.gongchaek.R
import com.gongchaek.gongchaek.databinding.FragmentSearchLibraryBinding
import com.gongchaek.gongchaek.feature.profile.ProfileActivity
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

        val adapter = RecyclerMyTownLibraryAdapter(libraryList)
        adapter.onItemClick = {
            startActivity(Intent(activity, ProfileActivity::class.java))
        }
        binding.listSearchLibrary.adapter = adapter

        // tab search랑 다른 거임. 안 만들어도 될 듯 근데 ? ㅁ모르겟어
    }
}