package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.PhoneNumber
import kotlinx.coroutines.flow.Flow

@Dao
interface PhoneNumberDAO{

    @Query("SELECT * " +
            "FROM TELEFONI " +
            "WHERE Numero = :number"
    )
    fun getPhoneNumber(number : String) : Flow<PhoneNumber?>

    @Upsert
    suspend fun upsertPhoneNumber(phoneNumber: PhoneNumber)

    @Delete
    suspend fun deletePhoneNumber(phoneNumber: PhoneNumber)
}