package com.example.myapplication.data.database

import androidx.room.TypeConverter
import com.example.myapplication.data.modules.FrequencyType
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.data.modules.MachineType
import com.example.myapplication.data.modules.SplitNumber
import java.time.LocalDate
import java.time.LocalTime
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

class MachineTypeConverters{
    @TypeConverter
    fun typeFromString(string: String) : MachineType{
        return MachineType.valueOf(string)
    }

    @TypeConverter
    fun typeFromMachineType(type: MachineType) : String{
        return type.toString()
    }
}

class SplitNumberConverters{
    @TypeConverter
    fun numberFromString(string: String) : SplitNumber {
        return SplitNumber.valueOf(string)
    }

    @TypeConverter
    fun numberFromMachineType(type: SplitNumber) : String{
        return type.toString()
    }
}

class FrequencyTypeConverters{
    @TypeConverter
    fun frequencyFromString(string: String) : FrequencyType {
        return FrequencyType.valueOf(string)
    }

    @TypeConverter
    fun frequencyFromFrequencyType(type: FrequencyType) : String{
        return type.toString()
    }
}

class JobTypeConverters{
    @TypeConverter
    fun typeFromString(string: String) : JobType {
        return JobType.valueOf(string)
    }

    @TypeConverter
    fun typeFromJobType(type: JobType) : String{
        return type.toString()
    }
}

class TimeConverters {
    private val formatter = DateTimeFormatter.ISO_LOCAL_TIME

    @TypeConverter
    fun fromString(value: String?): LocalTime? {
        return value?.let {
            LocalTime.parse(it, formatter)
        }
    }

    @TypeConverter
    fun timeToString(time: LocalTime?): String? {
        return time?.format(formatter)
    }
}