package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.myapplication.data.database.Revenue
import com.example.myapplication.data.database.RevenueFullDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface RevenueDAO{

    @Query(
        "SELECT * " +
        "FROM RICAVI " +
        "WHERE id = :id"
    )
    fun getFlowRevenue(id : Int) : Flow<Revenue?>

    @Query(
        "SELECT * " +
        "FROM RICAVI"
    )
    fun getFlowAllRevenues() : Flow<List<Revenue>>

    @Query(
        "SELECT * " +
        "FROM RICAVI " +
        "WHERE Intervento = :job"
    )
    suspend fun getRevenueByJob(job : Int) : List<Revenue>

    @Upsert
    suspend fun upsertRevenue(revenue: Revenue)

    @Delete
    suspend fun deleteRevenue(revenue: Revenue)

    @Transaction
    @Query(
        "SELECT * " +
        "FROM RICAVI " +
        "WHERE id = :id"
    )
    fun getFlowRevenueFullDetails(id : Int) : Flow<RevenueFullDetails?>

    @Transaction
    @Query(
        "SELECT * " +
        "FROM RICAVI " +
        "WHERE id = :id"
    )
    suspend fun getRevenueFullDetails(id : Int) : RevenueFullDetails?

    @Transaction
    @Query(
        "SELECT * " +
                "FROM RICAVI "
    )
    fun getFlowAllRevenuesFullDetails() : Flow<List<RevenueFullDetails>>

    @Transaction
    @Query(
        "SELECT * " +
        "FROM RICAVI "
    )
    fun getAllRevenuesFullDetails() : List<RevenueFullDetails>
}