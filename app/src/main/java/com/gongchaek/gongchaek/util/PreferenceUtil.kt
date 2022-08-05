package com.gongchaek.gongchaek.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {
    private val pref: SharedPreferences = context.getSharedPreferences("preferences", MODE_PRIVATE)

    fun getString(key: String, defValue: String): String {
        return pref.getString(key, defValue)!!
    }

    fun setString(key: String, str: String) {
        pref.edit().putString(key, str).apply()
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return pref.getBoolean(key, defValue)
    }

    fun setBoolean(key: String, str: Boolean) {
        pref.edit().putBoolean(key, str).apply()
    }
}