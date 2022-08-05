package com.gongchaek.gongchaek.feature.detailpage

import android.os.Bundle
import com.gongchaek.gongchaek.R
import com.gongchaek.gongchaek.databinding.ActivityDetailPageBinding
import com.gongchaek.gongchaek.feature.main.MainActivity
import com.gongchaek.gongchaek.feature.profile.ProfileFragment
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

        binding.btnProfile.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.layout_view, ProfileFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}