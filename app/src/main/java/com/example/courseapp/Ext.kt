package com.example.courseapp

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


fun Float.formatThousands() : String {
    val sb = StringBuilder()
    val formatter = Formatter(sb, Locale.US)
    formatter.format("%(,.0f", this)
    return sb.toString()
}

@SuppressLint("SimpleDateFormat")
fun Number.dateToString(pattern: String): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this.toLong()
    return SimpleDateFormat(pattern).format(calendar.time)
}

/*
    Здесь две внешних функции для форматирования чисел и дат. Первую мы будем
    использовать в списке криптовалют для разделения порядков в числах, а вторую для
    перевода дат из UNIX в строковый формат, для использования на графике.
*/
