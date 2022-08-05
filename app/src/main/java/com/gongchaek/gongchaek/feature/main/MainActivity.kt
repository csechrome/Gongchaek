package com.gongchaek.gongchaek.feature.main

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.gongchaek.gongchaek.*
import com.gongchaek.gongchaek.databinding.ActivityMainBinding
import com.gongchaek.gongchaek.feature.profile.ProfileFragment
import com.gongchaek.gongchaek.feature.tabchat.TabChatFragment
import com.gongchaek.gongchaek.feature.tabhome.TabHomeFragment
import com.gongchaek.gongchaek.feature.tabmypage.TabMyPageFragment
import com.gongchaek.gongchaek.feature.tabsearch.TabSearchFragment
import com.gongchaek.gongchaek.global.BaseActivity
import com.gongchaek.gongchaek.util.showToast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    View.OnClickListener {

    private var nowFragment = "home"

//    private var home = TabHomeFragment()
//    private var search = TabSearchFragment()
//    private var chat = TabChatFragment()
//    private var mypage = TabMyPageFragment()
//    private var profile = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        initView()

        supportFragmentManager.beginTransaction().replace(R.id.layout_view, TabHomeFragment()).commit()

        val user = Firebase.auth.currentUser
        if (user == null) {
            showToast("계정을 불러오는 데 실패했어요. 다시 로그인해주세요.")
            pref.setBoolean("autoLogin", false)
            finish()
        }

        binding.btnHome.setOnClickListener(this)
        binding.btnSearch.setOnClickListener(this)
        binding.btnChat.setOnClickListener(this)
        binding.btnMypage.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnHome.id -> {
                selectButton(binding.btnHome, R.drawable.ic_menu_home_selected)
                switchFragment("home")
            }
            binding.btnSearch.id -> {
                selectButton(binding.btnSearch, R.drawable.ic_menu_search_selected)
                switchFragment("search")
            }
            binding.btnChat.id -> {
                selectButton(binding.btnChat, R.drawable.ic_menu_chat_selected)
                switchFragment("chat")
            }
            binding.btnMypage.id -> {
                selectButton(binding.btnMypage, R.drawable.ic_menu_mypage_selected)
                switchFragment("mypage")
            }
        }
    }

    private fun selectButton(btn: ImageView, fileNameSelected: Int) {
        binding.btnHome.setImageResource(R.drawable.ic_menu_home_blank)
        binding.btnSearch.setImageResource(R.drawable.ic_menu_search_blank)
        binding.btnChat.setImageResource(R.drawable.ic_menu_chat_blank)
        binding.btnMypage.setImageResource(R.drawable.ic_menu_mypage_blank)
        btn.setImageResource(fileNameSelected)
    }

    private fun switchFragment(to: String) {
        if (nowFragment == to) {
            return
        }

//        supportFragmentManager.beginTransaction().hide(home).commit()
//        supportFragmentManager.beginTransaction().hide(search).commit()
//        supportFragmentManager.beginTransaction().hide(chat).commit()
//        supportFragmentManager.beginTransaction().hide(mypage).commit()
//        supportFragmentManager.beginTransaction().hide(profile).commit()

        when (to) {
            "home" -> supportFragmentManager.beginTransaction().replace(R.id.layout_view, TabHomeFragment()).commit()
            "search" -> supportFragmentManager.beginTransaction().replace(R.id.layout_view, TabSearchFragment()).commit()
            "chat" -> supportFragmentManager.beginTransaction().replace(R.id.layout_view, TabChatFragment()).commit()
            "mypage" -> supportFragmentManager.beginTransaction().replace(R.id.layout_view, TabMyPageFragment()).commit()
        }
        nowFragment = to
    }

//    private fun initView() {
//
//        // Add Tab Pages
//        supportFragmentManager.beginTransaction().add(R.id.layout_view, home).commit()
//        supportFragmentManager.beginTransaction().add(R.id.layout_view, search).commit()
//        supportFragmentManager.beginTransaction().add(R.id.layout_view, chat).commit()
//        supportFragmentManager.beginTransaction().add(R.id.layout_view, mypage).commit()
//        supportFragmentManager.beginTransaction().add(R.id.layout_view, profile).commit()
//
//        // Hide Tab Pages
//        supportFragmentManager.beginTransaction().hide(home).commit()
//        supportFragmentManager.beginTransaction().hide(search).commit()
//        supportFragmentManager.beginTransaction().hide(chat).commit()
//        supportFragmentManager.beginTransaction().hide(mypage).commit()
//        supportFragmentManager.beginTransaction().hide(profile).commit()
//
//        // Init
//        supportFragmentManager.beginTransaction().show(home).commit()
//    }
}