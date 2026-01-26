package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.Private
import kotlinx.coroutines.flow.Flow

@Dao
interface PrivateDAO{

    @Query("SELECT * " +
            "FROM PRIVATI " +
            "WHERE CF = :cf"
    )
    fun getPrivate(cf : String) : Flow<Private?>

    @Upsert
    suspend fun upsertPrivate(privateCustomer: Private)

    @Delete
    suspend fun deletePrivate(privateCustomer: Private)
}