package com.example.myapplication.data.database

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateConverters{
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE

    @TypeConverter
    fun dateFromString(string: String?) : LocalDate? = string?.let{
        LocalDate.parse(it, formatter)
    }

    @TypeConverter
    fun dateToString(date: LocalDate?) : String? = date?.format(formatter)
}