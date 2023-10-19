package com.example.newsapplication.data.local.database

import androidx.room.TypeConverter
import com.example.newsapplication.data.local.entity.Source

class Converters {
    @TypeConverter
    fun fromSource(value: String?): Source?{
        return value?.let { Source(it) }
    }

    @TypeConverter
    fun sourceToString(source: Source?): String {
        return source?.name.toString()
    }
}