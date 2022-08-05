package com.gongchaek.gongchaek.feature.login

import android.app.Dialog
import android.content.Intent
import android.net.http.SslError
import android.os.Bundle
import android.os.Message
import androidx.core.widget.addTextChangedListener
import android.text.InputFilter
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.core.app.ActivityCompat
import com.gongchaek.gongchaek.R
import com.gongchaek.gongchaek.global.BaseActivity
import com.gongchaek.gongchaek.databinding.ActivityLoginInfoBinding
import com.gongchaek.gongchaek.util.showNetworkErrorDialog
import com.gongchaek.gongchaek.util.showToast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import java.util.regex.Pattern
import kotlin.system.exitProcess
import android.net.Uri


class LoginInfoActivity : BaseActivity<ActivityLoginInfoBinding>(ActivityLoginInfoBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.etNickname.filters = arrayOf(
            InputFilter { source, start, end, dest, dstart, dend ->
                val nicknameRegex =
                    "^[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ\\u318D\\u119E\\u11A2\\u2022\\u2025a\\u00B7\\uFE55]+$"
                val nicknamePattern = Pattern.compile(nicknameRegex)
                if (source == "" || nicknamePattern.matcher(source).matches()) {
                    return@InputFilter source
                }
                ""
            })

        binding.etNickname.addTextChangedListener { text ->
            if (text!!.length > 8) {
                binding.etNickname.setText(text.subSequence(0, 8))
                binding.etNickname.setSelection(8)
            }
        }
        binding.etNickname.onFocusChangeListener =
            View.OnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    binding.layoutErrorNickname.visibility = View.GONE
                } else {
                    if (binding.etNickname.text.isEmpty()) {
                        binding.tvErrorNickname.text = "필수 항목입니다."
                        binding.layoutErrorNickname.visibility = View.VISIBLE
                    }
                }
            }

        binding.etLocation.setOnClickListener {
            binding.layoutErrorLocation.visibility = View.GONE
            showKakaoAddressWebView()
        }

        binding.etIntroduction.addTextChangedListener { text ->
            if (text!!.length > 50) {
                binding.etIntroduction.setText(text.subSequence(0, 50))
                binding.etIntroduction.setSelection(50)
            }
        }

        binding.btnNext.setOnClickListener {
            var isCompleted = true
            if (binding.etNickname.text.isEmpty()) {
                binding.tvErrorNickname.text = "필수 항목입니다."
                binding.layoutErrorNickname.visibility = View.VISIBLE
                isCompleted = false
            }
            val nicknameRegex = "^[a-zA-Z0-9가-힣]+$"
            if (!Pattern.matches(
                    nicknameRegex,
                    binding.etNickname.text
                ) && binding.etNickname.text.isNotEmpty()
            ) {
                binding.tvErrorNickname.text = "닉네임에는 온전한 한글만 사용할 수 있습니다."
                binding.layoutErrorNickname.visibility = View.VISIBLE
                isCompleted = false
            }
            if (binding.etLocation.text.isEmpty()) {
                binding.layoutErrorLocation.visibility = View.VISIBLE
                isCompleted = false
            }

            if (isCompleted) {
                val db = FirebaseDatabase.getInstance()
                val ref = db.reference.child("user")

                val nickname = binding.etNickname.text.toString()
                val location = binding.etLocation.text.toString()
                val introduction = binding.etIntroduction.text.toString()

                ref.get().addOnSuccessListener { pinSnapshot ->
                    if (pinSnapshot.hasChild(nickname.lowercase())) {
                        binding.tvErrorNickname.text = "이미 사용 중인 닉네임입니다."
                        binding.layoutErrorNickname.visibility = View.VISIBLE
                        isCompleted = false
                    } else {
                        val intent = Intent(this, SetInterestActivity::class.java)
                        val user = Firebase.auth.currentUser!!

                        val profileUpdates = userProfileChangeRequest {
                            displayName = nickname
                            photoUri = Uri.parse("drawable://" + R.drawable.ic_profile_default)
                        }
                        user!!.updateProfile(profileUpdates)
                        pref.setString("nickname", nickname)
                        pref.setString("location", location)
                        pref.setString("introduction", introduction)

                        startActivity(intent)
                    }
                }.addOnFailureListener {
                    showNetworkErrorDialog()
                }
            }
        }
    }

    private var backKeyPressedTime = 0L

    override fun onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis()
            showToast("뒤로가기 버튼을 한 번 더 누르면 종료돼요")
            return
        } else {
            ActivityCompat.finishAffinity(this)
            exitProcess(0)
        }
    }

    private fun showKakaoAddressWebView() {
        binding.webView.settings.apply {
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            setSupportMultipleWindows(true)
        }

        binding.webView.apply {
            addJavascriptInterface(WebViewData(), "Gongchaek")
            webViewClient = client
            webChromeClient = chromeClient
            loadUrl("https://gongchaek-inc.web.app/")
        }
    }

    private val client: WebViewClient = object : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?) = false

        override fun onReceivedSslError(
            view: WebView?,
            handler: SslErrorHandler?,
            error: SslError?
        ) {
            handler?.proceed()
        }
    }

    private inner class WebViewData {
        @JavascriptInterface
        fun getAddress(roadAddress: String, jibunAddress: String) {

            CoroutineScope(Dispatchers.Default).launch {

                withContext(CoroutineScope(Dispatchers.Main).coroutineContext) {

                    binding.etLocation.setText("$roadAddress")
                    val finder = jibunAddress.split(" ")
                    for (text in finder) {
                        if(text.endsWith("동") || text.endsWith("면") || text.endsWith("리")) {
                            pref.setString("region", text)
                        }
                    }
                }
            }
        }
    }

    private val chromeClient = object : WebChromeClient() {

        override fun onCreateWindow(
            view: WebView?,
            isDialog: Boolean,
            isUserGesture: Boolean,
            resultMsg: Message?
        ): Boolean {

            val newWebView = WebView(this@LoginInfoActivity)

            newWebView.settings.javaScriptEnabled = true

            val dialog = Dialog(this@LoginInfoActivity)

            dialog.setContentView(newWebView)

            val params = dialog.window!!.attributes

            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window!!.attributes = params
            dialog.show()

            newWebView.webChromeClient = object : WebChromeClient() {
                override fun onJsAlert(
                    view: WebView,
                    url: String,
                    message: String,
                    result: JsResult
                ): Boolean {
                    super.onJsAlert(view, url, message, result)
                    return true
                }

                override fun onCloseWindow(window: WebView?) {
                    dialog.dismiss()
                }
            }

            (resultMsg!!.obj as WebView.WebViewTransport).webView = newWebView
            resultMsg.sendToTarget()

            return true
        }
    }
}