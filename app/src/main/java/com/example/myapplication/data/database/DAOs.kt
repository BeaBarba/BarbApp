package com.example.myapplication.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface appDAO{
    @Query("SELECT * " +
            "FROM addresses " +
            "WHERE id = :id"
    )
    fun getAddress(id : Int) : Flow<Address>

    @Upsert
    suspend fun upsert(indirizzo : Address)

    @Delete
    suspend fun  delete(indirizzo: Address)
}