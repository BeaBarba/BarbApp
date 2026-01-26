package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.Customer
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDAO{

    @Query("SELECT * " +
            "FROM CLIENTI " +
            "WHERE CF = :cf"
    )
    fun getCustomer(cf : String) : Flow<Customer?>

    @Query("SELECT * " +
            "FROM CLIENTI"
    )
    fun getAllCustomers() : Flow<List<Customer>>

    @Upsert
    suspend fun upsertCustomer(customer: Customer)

    @Delete
    suspend fun deleteCustomer(customer: Customer)
}