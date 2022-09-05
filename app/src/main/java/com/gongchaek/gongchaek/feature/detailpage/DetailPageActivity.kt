package com.gongchaek.gongchaek.feature.detailpage

import android.content.Intent
import android.os.Bundle
import com.gongchaek.gongchaek.R
import com.gongchaek.gongchaek.databinding.ActivityDetailPageBinding
import com.gongchaek.gongchaek.feature.borrow.BorrowMainActivity
import com.gongchaek.gongchaek.feature.profile.ProfileActivity
import com.gongchaek.gongchaek.feature.tabchat.ChatRoomActivity
import com.gongchaek.gongchaek.global.BaseActivity
import com.gongchaek.gongchaek.util.showToast


class DetailPageActivity : BaseActivity<ActivityDetailPageBinding>(ActivityDetailPageBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.cbFavorite.setOnClickListener {
            if(binding.cbFavorite.isChecked) {
                // TODO: 추가하기
                showToast("'좋아하는 도서'에 추가되었습니다")
            } else {
                // TODO: 삭제하기
                showToast("'좋아하는 도서'에서 삭제되었습니다")
            }
        }

        binding.btnBorrow.setOnClickListener {
            startActivity(Intent(this, BorrowMainActivity::class.java))
        }

        binding.btnChat.setOnClickListener {
            startActivity(Intent(this, ChatRoomActivity::class.java)) // TODO
        }

        binding.btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}