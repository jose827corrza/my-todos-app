package com.josedev.mytodos.data

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class Converters {

    @TypeConverter
    fun fromLocalDate(date: LocalDate): String? {
        return date?.format(DateTimeFormatter.ofPattern("yyyy-MMM-dd"))
    }

    @TypeConverter
    fun stringToLocalDate(stringDate: String): LocalDate {
        return LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("yyyy-MMM-dd"))
    }

    @TypeConverter
    fun fromLocalTime(time: LocalTime): String? {
        return time?.toString()
    }

    @TypeConverter
    fun stringToLocalTime(stringTime: String): LocalTime {
//        return LocalTime.parse(stringTime, DateTimeFormatter.ofPattern("H:mm:ss"))
        return LocalTime.parse(stringTime, DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))
    }

}