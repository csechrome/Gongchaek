package com.gongchaek.gongchaek.feature.login

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.gongchaek.gongchaek.R
import com.gongchaek.gongchaek.databinding.ActivitySetInterestBinding
import com.gongchaek.gongchaek.feature.main.MainActivity
import com.gongchaek.gongchaek.global.BaseActivity
import com.gongchaek.gongchaek.global.BaseFragment
import com.gongchaek.gongchaek.util.setDisabled
import com.gongchaek.gongchaek.util.setEnabled
import com.gongchaek.gongchaek.util.showToast
import com.google.firebase.database.FirebaseDatabase

class SetInterestActivity : BaseActivity<ActivitySetInterestBinding>(ActivitySetInterestBinding::inflate), View.OnClickListener {

    private var interest = BooleanArray(17) { i -> false }
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnCategory1.setOnClickListener(this)
        binding.btnCategory2.setOnClickListener(this)
        binding.btnCategory3.setOnClickListener(this)
        binding.btnCategory4.setOnClickListener(this)
        binding.btnCategory5.setOnClickListener(this)
        binding.btnCategory6.setOnClickListener(this)
        binding.btnCategory7.setOnClickListener(this)
        binding.btnCategory8.setOnClickListener(this)
        binding.btnCategory9.setOnClickListener(this)
        binding.btnCategory10.setOnClickListener(this)
        binding.btnCategory11.setOnClickListener(this)
        binding.btnCategory12.setOnClickListener(this)
        binding.btnCategory13.setOnClickListener(this)
        binding.btnCategory14.setOnClickListener(this)
        binding.btnCategory15.setOnClickListener(this)
        binding.btnCategory16.setOnClickListener(this)

        binding.btnNext.setOnClickListener {

            var tempInterest = ArrayList<Int>()
            for (i in 1 until 16) {
                if(interest[i]) {
                    tempInterest.add(i)
                }
            }
            val stringInterest = tempInterest.joinToString(",")

            val nickname = pref.getString("nickname", "null")
            val location = pref.getString("location", "null")
            val introduction = pref.getString("introduction", "null")
            val region = pref.getString("region", "null")

            val db = FirebaseDatabase.getInstance()
            val ref = db.reference.child("user").child(nickname.lowercase())
            ref.child("location").setValue(location)
            ref.child("introduction").setValue(introduction)
            ref.child("interest").setValue(stringInterest)
            ref.child("region").setValue(region)
            ref.child("follower").setValue("0")
            ref.child("following").setValue("0")
            ref.child("my_book_num").setValue(0)
            ref.child("borrowed_book_num").setValue(0)

            pref.setBoolean ("autoLogin", true)
            showToast("회원가입이 완료되었어요!")
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnCategory1.id -> {
                btnReverse(
                    1,
                    binding.btnCategory1,
                    R.drawable.ic_category_1_blank,
                    R.drawable.ic_category_1_selected
                )
            }
            binding.btnCategory2.id -> {
                btnReverse(
                    2,
                    binding.btnCategory2,
                    R.drawable.ic_category_2_blank,
                    R.drawable.ic_category_2_selected
                )
            }
            binding.btnCategory3.id -> {
                btnReverse(
                    3,
                    binding.btnCategory3,
                    R.drawable.ic_category_3_blank,
                    R.drawable.ic_category_3_selected
                )
            }
            binding.btnCategory4.id -> {
                btnReverse(
                    4,
                    binding.btnCategory4,
                    R.drawable.ic_category_4_blank,
                    R.drawable.ic_category_4_selected
                )
            }
            binding.btnCategory5.id -> {
                btnReverse(
                    5,
                    binding.btnCategory5,
                    R.drawable.ic_category_5_blank,
                    R.drawable.ic_category_5_selected
                )
            }
            binding.btnCategory6.id -> {
                btnReverse(
                    6,
                    binding.btnCategory6,
                    R.drawable.ic_category_6_blank,
                    R.drawable.ic_category_6_selected
                )
            }
            binding.btnCategory7.id -> {
                btnReverse(
                    7,
                    binding.btnCategory7,
                    R.drawable.ic_category_7_blank,
                    R.drawable.ic_category_7_selected
                )
            }
            binding.btnCategory8.id -> {
                btnReverse(
                    8,
                    binding.btnCategory8,
                    R.drawable.ic_category_8_blank,
                    R.drawable.ic_category_8_selected
                )
            }
            binding.btnCategory9.id -> {
                btnReverse(
                    9,
                    binding.btnCategory9,
                    R.drawable.ic_category_9_blank,
                    R.drawable.ic_category_9_selected
                )
            }
            binding.btnCategory10.id -> {
                btnReverse(
                    10,
                    binding.btnCategory10,
                    R.drawable.ic_category_10_blank,
                    R.drawable.ic_category_10_selected
                )
            }
            binding.btnCategory11.id -> {
                btnReverse(
                    11,
                    binding.btnCategory11,
                    R.drawable.ic_category_11_blank,
                    R.drawable.ic_category_11_selected
                )
            }
            binding.btnCategory12.id -> {
                btnReverse(
                    12,
                    binding.btnCategory12,
                    R.drawable.ic_category_12_blank,
                    R.drawable.ic_category_12_selected
                )
            }
            binding.btnCategory13.id -> {
                btnReverse(
                    13,
                    binding.btnCategory13,
                    R.drawable.ic_category_13_blank,
                    R.drawable.ic_category_13_selected
                )
            }
            binding.btnCategory14.id -> {
                btnReverse(
                    14,
                    binding.btnCategory14,
                    R.drawable.ic_category_14_blank,
                    R.drawable.ic_category_14_selected
                )
            }
            binding.btnCategory15.id -> {
                btnReverse(
                    15,
                    binding.btnCategory15,
                    R.drawable.ic_category_15_blank,
                    R.drawable.ic_category_15_selected
                )
            }
            binding.btnCategory16.id -> {
                btnReverse(
                    16,
                    binding.btnCategory16,
                    R.drawable.ic_category_16_blank,
                    R.drawable.ic_category_16_selected
                )
            }
        }
        if (count > 0) {
            binding.btnNext.setEnabled()
        } else {
            binding.btnNext.setDisabled()
        }
    }

    private fun btnReverse(
        index: Int,
        image: ImageView,
        fileNameBlank: Int,
        fileNameSelected: Int
    ) {
        interest[index] = !interest[index]
        if (interest[index]) {
            image.setImageResource(fileNameSelected)
            count++
        } else {
            image.setImageResource(fileNameBlank)
            count--
        }
    }
}