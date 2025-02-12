package com.yapss.my_to_do.domain.usecase

import java.text.SimpleDateFormat
import java.util.*

class FormatDateUseCase {

    private val locale = Locale("en")

    fun formatFullDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd MMMM yyyy", locale)
        return sdf.format(Date(timestamp))
    }

    fun parseDate(date:String):Long{
        val sdf = SimpleDateFormat("dd MMMM yyyy", locale)
        return sdf.parse(date)!!.time
    }

    fun formatTime(timestamp: Long): String {
        val sdf = SimpleDateFormat("HH:mm", locale)
        return sdf.format(Date(timestamp))
    }

    fun formatDateTime(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd MMMM yyyy - HH:mm", locale)
        return sdf.format(Date(timestamp))
    }
}
