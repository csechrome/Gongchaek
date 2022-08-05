package com.gongchaek.gongchaek.feature.login


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.gongchaek.gongchaek.*
import com.gongchaek.gongchaek.databinding.ActivityLoginAuthBinding
import com.gongchaek.gongchaek.feature.main.MainActivity
import com.gongchaek.gongchaek.global.BaseActivity
import com.gongchaek.gongchaek.util.*
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit


class LoginAuthActivity : BaseActivity<ActivityLoginAuthBinding>(ActivityLoginAuthBinding::inflate) {

    private var verificationId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showKeyboard()

        binding.etAuthNum.addTextChangedListener { text ->
            if (text.isNullOrEmpty()) {
                binding.btnNext.setDisabled()
            } else {
                binding.btnNext.setEnabled()
            }
        }

        val phoneNum = intent.getStringExtra("phoneNum")!!
        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {}
            override fun onVerificationFailed(e: FirebaseException) {}
            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                this@LoginAuthActivity.verificationId = verificationId
            }
        }

        val options = PhoneAuthOptions.newBuilder(Firebase.auth)
            .setPhoneNumber(phoneNum)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

        binding.btnNext.setOnClickListener {
            binding.btnNext.setDisabled()
            binding.layoutErrorMsg.isVisible = false
            if (intent.hasExtra("phoneNum")) {
                val phoneAuthCredential = PhoneAuthProvider.getCredential(
                    verificationId,
                    binding.etAuthNum.text.toString()
                )
                signInWithPhoneAuthCredential(phoneAuthCredential)
            } else {
                showToast("연결이 끊겼어요. 인증을 다시 요청해주세요.")
                finish()
            }
            binding.btnNext.setEnabled()
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        Firebase.auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    hideKeyboard()

                    val phoneNum = "0" + intent.getStringExtra("phoneNum")!!.substring(3)
                    val user = Firebase.auth.currentUser!!
                    pref.setString("phoneNum", phoneNum)

                    val intent = if(user.displayName != null) {
                        pref.setBoolean("autoLogin", true)
                        Intent(this, MainActivity::class.java)
                    } else {
                        Intent(this, LoginInfoActivity::class.java)
                    }
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        binding.layoutErrorMsg.isVisible = true
                    }
                }
            }
    }
}