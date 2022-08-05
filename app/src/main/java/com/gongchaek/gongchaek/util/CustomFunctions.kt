package com.gongchaek.gongchaek.util

import android.content.Context
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import com.gongchaek.gongchaek.R

fun Context.showKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun Context.hideKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
}

private var toast: Toast? = null

fun Context.showToast(message: String) {
    toast?.cancel()
    toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
    toast?.show()
}

fun Button.setEnabled() {
    this.isEnabled = true
    this.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#0d6fb8"))
    this.setTextColor(Color.parseColor("#ffffff"))
}

fun Button.setDisabled() {
    this.isEnabled = false
    this.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#e9ebec"))
    this.setTextColor(Color.parseColor("#acaca9"))
}

fun Context.showNetworkErrorDialog() {
    showToast("서버와 연결하는 데 실패했어요. 인터넷 연결 상태를 확인해주세요.")
//    AlertDialog.Builder(this)
//        .setTitle("연결 오류")
//        .setMessage("서버와 연결하는 데 실패했어요. 인터넷 연결 상태를 확인해주세요.")
//        .setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which -> })
//        .show()
}