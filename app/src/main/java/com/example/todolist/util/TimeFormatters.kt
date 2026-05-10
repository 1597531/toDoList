package com.example.todolist.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object TimeFormatters {
    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

    fun formatMillis(millis: Long): String = formatter.format(Date(millis))
}
