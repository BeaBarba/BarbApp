package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.SingleExpense
import kotlinx.coroutines.flow.Flow

@Dao
interface SingleExpenseDAO{

    @Query("SELECT * " +
            "FROM SPESE_SINGOLE " +
            "WHERE id = :id"
    )
    fun getSingleExpense(id : Int) : Flow<SingleExpense?>

    @Query("SELECT * " +
            "FROM SPESE_SINGOLE"
    )
    fun getAllSingleExpenses() : Flow<List<SingleExpense>>

    @Upsert
    suspend fun upsertSingleExpense(singleExpense : SingleExpense)

    @Delete
    suspend fun deleteSingleExpense(singleExpense : SingleExpense)
}
