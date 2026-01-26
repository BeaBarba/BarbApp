package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.RecurringPayment
import kotlinx.coroutines.flow.Flow

@Dao
interface RecurringPaymentDAO{

    @Query("SELECT * " +
            "FROM SALDI " +
            "WHERE Pagamento = :payment"
    )
    fun getRecurringPayment(payment : Int) : Flow<RecurringPayment?>

    @Query("SELECT * " +
            "FROM SALDI"
    )
    fun getAllRecurringPayments() : Flow<List<RecurringPayment>>

    @Upsert
    suspend fun upsertRecurringPayment(recurringPayment: RecurringPayment)

    @Delete
    suspend fun deleteRecurringPayment(recurringPayment: RecurringPayment)
}