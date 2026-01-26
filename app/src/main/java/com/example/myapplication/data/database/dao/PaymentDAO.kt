package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.Payment
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentDAO{

    @Query("SELECT * " +
            "FROM PAGAMENTI " +
            "WHERE id = :id"
    )
    fun getPayment(id : Int) : Flow<Payment?>

    @Query("SELECT * " +
            "FROM PAGAMENTI"
    )
    fun getAllPayments() : Flow<List<Payment>>

    @Upsert
    suspend fun upsertPayment(payment : Payment)

    @Delete
    suspend fun deletePayment(payment : Payment)
}