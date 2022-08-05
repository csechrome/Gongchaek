package com.gongchaek.gongchaek.util

import android.graphics.drawable.Drawable


data class ItemBookNormal(
    var title: String,
    var cover: Drawable,
    var location: String,
    var price: String,
    var term: String,
)

data class ItemBookVertical(
    var title: String,
    var cover: Drawable,
    var location: String,
)

data class ItemBookMyPage(
    var title: String,
    var cover: Drawable,
    var price: String,
    var term: String,
    var borrowerName: String,
    var borrowerProfile: Drawable, // TODO: ?
    var status: String, // TODO ?
    var dueDate: String,
)

data class ItemLibrary(
    var name: String,
    var cover: Drawable,
    var introduction: String,
    var location: String,
    var bookCount: String,
)

data class ItemChatList(
    // TODO
    var name: String,
    var last_msg: String,
    var date: String,
    var count: Int,
)