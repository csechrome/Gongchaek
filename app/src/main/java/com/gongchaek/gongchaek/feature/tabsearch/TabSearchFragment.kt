package com.gongchaek.gongchaek.feature.tabsearch

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.gongchaek.gongchaek.databinding.FragmentTabSearchBinding
import com.gongchaek.gongchaek.feature.notification.NotificationActivity
import com.gongchaek.gongchaek.feature.search.SearchActivity
import com.gongchaek.gongchaek.global.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator


class TabSearchFragment :
    BaseFragment<FragmentTabSearchBinding>(FragmentTabSearchBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup TabLayout & ViewPager
        val adapter = ViewPagerMyTownAdapter(requireActivity())
        binding.vpMyTown.adapter = adapter
        TabLayoutMediator(binding.tabMyTown, binding.vpMyTown) { tab, position ->
            when (position) {
                0 -> tab.text = "우리동네 도서"
                1 -> tab.text = "우리동네 서재"
            }
        }.attach()

        binding.tvRegion.text = pref.getString("region", "OO동")

        binding.btnNotification.setOnClickListener {
            startActivity(Intent(requireContext(), NotificationActivity::class.java))
        }

        binding.btnSearch.setOnClickListener {
            startActivity(Intent(requireContext(), SearchActivity::class.java))
        }
    }
}