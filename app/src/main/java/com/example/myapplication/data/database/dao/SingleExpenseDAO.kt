package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.myapplication.data.database.SingleExpense
import com.example.myapplication.data.database.SingleExpenseFullDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface SingleExpenseDAO{

    @Query(
        "SELECT * " +
        "FROM SPESE_SINGOLE " +
        "WHERE id = :id"
    )
    fun getSingleExpense(id : Int) : Flow<SingleExpense?>

    @Query(
        "SELECT * " +
        "FROM SPESE_SINGOLE"
    )
    fun getAllSingleExpenses() : Flow<List<SingleExpense>>

    @Query(
        "SELECT * " +
        "FROM SPESE_SINGOLE " +
        "WHERE Fattura = :purchaseInvoiceId"
    )
    suspend fun getSingleExpenseByPurchaseInvoice(purchaseInvoiceId : Int) : List<SingleExpense?>

    @Query(
        "UPDATE SPESE_SINGOLE SET Fattura = null WHERE Fattura = :purchaseInvoiceId"
    )
    suspend fun removePurchaseInvoiceReferenceFromSingleExpenses(purchaseInvoiceId : Int)

    @Upsert
    suspend fun upsertSingleExpense(singleExpense : SingleExpense) : Long

    @Delete
    suspend fun deleteSingleExpense(singleExpense : SingleExpense)

    @Transaction
    @Query(
        "SELECT * " +
        "FROM SPESE_SINGOLE " +
        "WHERE id = :id"
    )
    fun getFlowSingleExpenseFullDetails(id : Int) : Flow<SingleExpenseFullDetails?>

    @Transaction
    @Query(
        "SELECT * " +
        "FROM SPESE_SINGOLE"
    )
    fun getFlowAllSingleExpenseFullDetails() : Flow<List<SingleExpenseFullDetails?>>

    @Transaction
    @Query(
        "SELECT * " +
        "FROM SPESE_SINGOLE " +
        "WHERE id = :id"
    )
    suspend fun getSingleExpenseFullDetails(id: Int) : SingleExpenseFullDetails
}
