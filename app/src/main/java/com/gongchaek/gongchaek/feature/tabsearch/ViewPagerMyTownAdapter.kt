package com.gongchaek.gongchaek.feature.tabsearch

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gongchaek.gongchaek.feature.tabsearch.MyTownBookFragment
import com.gongchaek.gongchaek.feature.tabsearch.MyTownLibraryFragment


class ViewPagerMyTownAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val PAGE_NUM = 2
    override fun getItemCount(): Int = PAGE_NUM

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MyTownBookFragment()
            else -> MyTownLibraryFragment()
        }
    }
}
