package com.example.fieldtrack.data.db.util

import androidx.room.TypeConverter
import java.time.LocalDate

class Converters {

    @TypeConverter
    fun fromLocalDate(value: LocalDate?): String? {
        return value?.toString() // ISO-8601, e.g. 2026-03-23
    }

    @TypeConverter
    fun toLocalDate(value: String?): LocalDate? {
        return value?.let(LocalDate::parse)
    }
}