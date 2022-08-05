package com.gongchaek.gongchaek.feature.tabmypage

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.gongchaek.gongchaek.global.BaseFragment
import com.gongchaek.gongchaek.databinding.FragmentTabMyPageBinding
import com.gongchaek.gongchaek.feature.notification.NotificationActivity
import com.gongchaek.gongchaek.feature.setting.SettingActivity
import com.gongchaek.gongchaek.feature.upload.UploadManualActivity
import com.gongchaek.gongchaek.util.showToast
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.database.FirebaseDatabase


class TabMyPageFragment :
    BaseFragment<FragmentTabMyPageBinding>(FragmentTabMyPageBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Bind User Info
        val nickname = pref.getString("nickname", "null")
        val location = pref.getString("location", "null")
        val introduction = pref.getString("introduction", "null")
        val follower = pref.getString("follower", "NA")
        val following = pref.getString("following", "NA")

        binding.tvNickname.text = "${nickname}님의 서재"
        binding.tvLocation.text = location
        binding.tvIntroduction.text = introduction
        binding.tvFollower.text = follower
        binding.tvFollowing.text = following

        // Setup TabLayout & ViewPager
        val adapter = ViewPagerMyPageAdapter(requireActivity())
        binding.vpMypage.adapter = adapter
        TabLayoutMediator(binding.tabMypage, binding.vpMypage) { tab, position ->
            when (position) {
                0 -> tab.text = "내 도서"
                1 -> tab.text = "대여받은 도서"
            }
        }.attach()

        val db = FirebaseDatabase.getInstance()
        val ref = db.reference.child("user").child(nickname)
        ref.get().addOnSuccessListener { DataSnapshot ->
            val myBookNum = DataSnapshot.child("my_book_num").value
            val borrowedBookNum = DataSnapshot.child("borrowed_book_num").value
            binding.vpMypage.adapter = adapter
            TabLayoutMediator(binding.tabMypage, binding.vpMypage) { tab, position ->
                when (position) {
                    0 -> tab.text = "내 도서 (${myBookNum})"
                    1 -> tab.text = "대여받은 도서 (${borrowedBookNum})"
                }
            }.attach()
        }

        binding.btnAdd.setOnClickListener {
            startActivity(Intent(requireContext(), UploadManualActivity::class.java))
        }

        binding.btnNotification.setOnClickListener {
            startActivity(Intent(requireContext(), NotificationActivity::class.java))
        }

        binding.btnSetting.setOnClickListener {
            startActivity(Intent(requireContext(), SettingActivity::class.java))
        }
    }
}