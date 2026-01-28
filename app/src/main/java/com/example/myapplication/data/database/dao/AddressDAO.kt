package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.Address
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressDAO{
    @Query("SELECT * " +
            "FROM INDIRIZZI " +
            "WHERE id = :id"
    )
    fun getAddress(id : Int) : Flow<Address?>

    @Query("SELECT * " +
            "FROM INDIRIZZI"
    )
    fun getAllAddresses() : Flow<List<Address>>

    @Upsert
    suspend fun upsertAddress(address : Address) : Long

    @Delete
    suspend fun  deleteAddress(address: Address)
}