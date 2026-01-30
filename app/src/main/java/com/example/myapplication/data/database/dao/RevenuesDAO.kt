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
    fun getRevenue(id : Int) : Flow<Revenue?>

    @Query(
        "SELECT * " +
        "FROM RICAVI"
    )
    fun getAllRevenues() : Flow<List<Revenue>>

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
    fun getRevenueFullDetails(id : Int) : Flow<RevenueFullDetails?>

    @Transaction
    @Query(
        "SELECT * " +
        "FROM RICAVI "
    )
    fun getAllRevenuesFullDetails() : List<RevenueFullDetails>
}