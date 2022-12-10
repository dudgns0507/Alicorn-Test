package com.github.dudgns0507.alicorn.core

import java.text.SimpleDateFormat
import java.util.Calendar

fun String.getDateString(): String {
    return try {
        val today = Calendar.getInstance().time
        val date = SimpleDateFormat("yyyy/MM/dd").parse(this)

        when (val diff = (today.time - date.time) / (60 * 60 * 24 * 1000)) {
            0L -> "오늘"
            1L -> "어제"
            in 1L..7L -> "${diff}일전"
            else -> this
        }
    } catch (e: Exception) {
        ""
    }
}