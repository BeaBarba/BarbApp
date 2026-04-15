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
    fun getFlowPayment(id : Int) : Flow<Payment?>

    @Query("SELECT * " +
            "FROM PAGAMENTI " +
            "WHERE id = :id"
    )
    suspend fun getPayment(id : Int) : Payment?

    @Query("SELECT * " +
            "FROM PAGAMENTI"
    )
    fun getAllPayments() : Flow<List<Payment>>

    @Query(
        "SELECT COUNT(*) " +
                "FROM PAGAMENTI AS P " +
                "JOIN SALDI AS S ON (P.Id = S.Pagamento) " +
                "JOIN SPESE_PERIODICHE AS SP ON (SP.Id = S.Spesa) " +
                "WHERE P.DataEmissione = :date " +
                "AND SP.Id = :expenseId"
    )
    suspend fun checkExistingNextPayment(date : LocalDate, expenseId : Int) : Int

    @Query(
        "SELECT P.id " +
        "FROM PAGAMENTI AS P " +
            "JOIN SALDI AS S ON (P.Id = S.Pagamento) " +
            "JOIN SPESE_PERIODICHE AS SP ON (SP.Id = S.Spesa) " +
        "WHERE SP.Id = :expenseId " +
            "AND P.DataPagamento IS NULL " +
            "AND P.DataEmissione >= :date"
    )
    suspend fun getUnpaidFuturePaymentsByExpense(expenseId: Int, date : LocalDate) : List<Int>

    @Upsert
    suspend fun upsertPayment(payment : Payment) : Long

    @Query(
        "UPDATE PAGAMENTI " +
                "SET DataPagamento = :date " +
                "WHERE id = :id"
    )
    suspend fun updatePaymentDate(id : Int, date : LocalDate?)

    @Delete
    suspend fun deletePayment(payment : Payment)

    @Query(
        "DELETE FROM PAGAMENTI WHERE Id IN (:ids) "
    )
    suspend fun deletePaymentsByIds(ids : List<Int>)
}