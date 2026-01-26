package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.JobRevenue
import kotlinx.coroutines.flow.Flow

@Dao
interface JobRevenueDAO{

    @Query("SELECT * " +
            "FROM PRODUCE " +
            "WHERE Ricavo = :id"
    )
    fun getJobRevenue(id : Int) : Flow<JobRevenue?>

    @Query("SELECT * " +
            "FROM PRODUCE"
    )
    fun getAllJobRevenues() : Flow<List<JobRevenue>>

    @Upsert
    suspend fun upsertJobRevenue(jobRevenue: JobRevenue)

    @Delete
    suspend fun deleteJobRevenue(jobRevenue: JobRevenue)
}