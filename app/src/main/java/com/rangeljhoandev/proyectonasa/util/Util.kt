package com.rangeljhoandev.proyectonasa.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class Util {
    companion object {
        fun modifyDate(date: Date, calendarField: Int, value: Int): Date {
            val calendar = Calendar.getInstance()
            calendar.time = date
            calendar.add(calendarField, value)
            return calendar.time
        }

        fun formatDate(date: Date, format: String): String {
            return SimpleDateFormat(format, Locale.US).format(date)
        }

    }
}