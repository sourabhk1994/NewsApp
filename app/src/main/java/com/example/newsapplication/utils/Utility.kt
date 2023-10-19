package com.example.newsapplication.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utility {

    fun formatDate(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.UK)
        val outputFormat = SimpleDateFormat("yyyy/MM/dd ", Locale.UK)
        try {
            return outputFormat.format(inputFormat.parse(date) ?: "")
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return ""
    }

}