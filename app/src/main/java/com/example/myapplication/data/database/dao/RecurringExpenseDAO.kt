package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.myapplication.data.database.RecurringExpense
import com.example.myapplication.data.database.RecurringExpenseFullDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface RecurringExpenseDAO{

    @Query("SELECT * " +
            "FROM SPESE_PERIODICHE " +
            "WHERE id = :id"
    )
    fun getRecurringExpense(id : Int) : Flow<RecurringExpense?>

    @Query("SELECT * " +
            "FROM SPESE_PERIODICHE"
    )
    fun getAllRecurringExpenses() : Flow<List<RecurringExpense>>


    @Upsert
    suspend fun upsertRecurringExpense(recurringExpense : RecurringExpense)

    @Delete
    suspend fun deleteRecurringExpense(recurringExpense : RecurringExpense)

    @Transaction
    @Query("SELECT * " +
            "FROM SPESE_PERIODICHE " +
            "WHERE id = :id"
    )
    fun getFlowRecurringExpenseFullDetails(id: Int) : Flow<RecurringExpenseFullDetails?>

    @Transaction
    @Query("SELECT * " +
            "FROM SPESE_PERIODICHE"
    )
    fun getFlowAllRecurringExpensesFullDetails() : Flow<List<RecurringExpenseFullDetails>>

    @Transaction
    @Query("SELECT * " +
            "FROM SPESE_PERIODICHE " +
            "WHERE id = :id"
    )
    suspend fun getRecurringExpenseFullDetails(id : Int) : RecurringExpenseFullDetails
}
