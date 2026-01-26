package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.RecurringExpense
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
}
