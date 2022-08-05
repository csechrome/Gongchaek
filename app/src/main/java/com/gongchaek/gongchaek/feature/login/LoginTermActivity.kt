package com.gongchaek.gongchaek.feature.login

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.gongchaek.gongchaek.global.BaseActivity
import com.gongchaek.gongchaek.databinding.ActivityLoginTermBinding

class LoginTermActivity : BaseActivity<ActivityLoginTermBinding>(ActivityLoginTermBinding::inflate), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.cbAgreeAll.setOnClickListener(this)
        binding.cbAgreeFirst.setOnClickListener(this)
        binding.cbAgreeSecond.setOnClickListener(this)
        binding.cbAgreeThird.setOnClickListener(this)
        binding.btnAgreeAll.setOnClickListener(this)
        binding.btnAgreeFirst.setOnClickListener(this)
        binding.btnAgreeSecond.setOnClickListener(this)
        binding.btnAgreeThird.setOnClickListener(this)
        binding.btnShowFirstTerm.setOnClickListener(this)
        binding.btnShowSecondTerm.setOnClickListener(this)
        binding.btnShowThirdTerm.setOnClickListener(this)

        binding.btnExit.setOnClickListener {
            onBackPressed()
        }

        binding.btnNext.setOnClickListener {
            intent = Intent(this, LoginPhoneNumActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.cbAgreeAll.id -> {
                if (binding.cbAgreeAll.isChecked) {
                    binding.cbAgreeAll.isChecked = false
                    binding.cbAgreeFirst.isChecked = false
                    binding.cbAgreeSecond.isChecked = false
                    binding.cbAgreeThird.isChecked = false
                } else {
                    binding.cbAgreeAll.isChecked = true
                    binding.cbAgreeFirst.isChecked = true
                    binding.cbAgreeSecond.isChecked = true
                    binding.cbAgreeThird.isChecked = true
                }
            }
            binding.btnAgreeAll.id -> {
                if (binding.cbAgreeAll.isChecked) {
                    binding.cbAgreeAll.isChecked = false
                    binding.cbAgreeFirst.isChecked = false
                    binding.cbAgreeSecond.isChecked = false
                    binding.cbAgreeThird.isChecked = false
                } else {
                    binding.cbAgreeAll.isChecked = true
                    binding.cbAgreeFirst.isChecked = true
                    binding.cbAgreeSecond.isChecked = true
                    binding.cbAgreeThird.isChecked = true
                }
            }
            binding.btnAgreeFirst.id -> {
                binding.cbAgreeFirst.isChecked = !binding.cbAgreeFirst.isChecked
            }
            binding.btnAgreeSecond.id -> {
                binding.cbAgreeSecond.isChecked = !binding.cbAgreeSecond.isChecked
            }
            binding.btnAgreeThird.id -> {
                binding.cbAgreeThird.isChecked = !binding.cbAgreeThird.isChecked
            }
            binding.btnShowFirstTerm.id -> {
                intent = Intent(this, ShowFirstTermActivity::class.java)
                startActivity(intent)
            }
            binding.btnShowSecondTerm.id -> {
                intent = Intent(this, ShowSecondTermActivity::class.java)
                startActivity(intent)
            }
            binding.btnShowThirdTerm.id -> {
                intent = Intent(this, ShowThirdTermActivity::class.java)
                startActivity(intent)
            }
        }
        checkWhenBtnClicked()
    }

    private fun checkWhenBtnClicked() {
        val term1 = binding.cbAgreeFirst
        val term2 = binding.cbAgreeSecond
        val term3 = binding.cbAgreeThird

        binding.cbAgreeAll.isChecked = term1.isChecked && term2.isChecked && term3.isChecked

        if (binding.cbAgreeAll.isChecked) {
            binding.btnNext.isEnabled = true
            binding.btnNext.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#0d6fb8"))
            binding.btnNext.setTextColor(Color.parseColor("#ffffff"))
        } else {
            binding.btnNext.isEnabled = false
            binding.btnNext.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#e9ebec"))
            binding.btnNext.setTextColor(Color.parseColor("#acaca9"))
        }
    }
}