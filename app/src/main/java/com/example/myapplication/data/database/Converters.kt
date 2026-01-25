package com.example.myapplication.data.database

import androidx.room.TypeConverter
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.data.modules.MachineType
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