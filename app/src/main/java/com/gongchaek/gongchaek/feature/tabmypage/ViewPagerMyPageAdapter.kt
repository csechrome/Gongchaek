package com.gongchaek.gongchaek.feature.tabmypage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPagerMyPageAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val PAGE_NUM = 2
    override fun getItemCount(): Int = PAGE_NUM

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MyPageMineFragment()
            else -> MyPageBorrowedFragment()
        }
    }
}
