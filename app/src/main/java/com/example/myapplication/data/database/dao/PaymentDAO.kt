package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.Payment
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

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

    @Query(
        "UPDATE PAGAMENTI " +
        "SET DataPagamento = :date " +
        "WHERE id = :id"
    )
    suspend fun updatePaymentDate(id : Int, date : LocalDate?)

    @Upsert
    suspend fun upsertPayment(payment : Payment)

    @Delete
    suspend fun deletePayment(payment : Payment)
}