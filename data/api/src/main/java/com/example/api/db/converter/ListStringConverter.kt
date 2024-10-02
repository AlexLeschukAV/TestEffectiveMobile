package com.example.api.db.converter

import androidx.room.TypeConverter

object ListStringConverter {
    @TypeConverter
    fun listToString(list: List<String>?): String? {
        return list?.joinToString(separator = ",")
    }

    @TypeConverter
    fun stringToList(string: String?): List<String>? {
        return string?.split(",")?.map { it.trim() }?.filter { it.isNotEmpty() }
    }
}