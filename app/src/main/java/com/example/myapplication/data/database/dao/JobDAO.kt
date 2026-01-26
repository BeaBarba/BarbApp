package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.Job
import kotlinx.coroutines.flow.Flow

@Dao
interface JobDAO{

    @Query("SELECT * " +
            "FROM INTERVENTI " +
            "WHERE id = :id"
    )
    fun getJob(id : Int) : Flow<Job?>

    @Query("SELECT * " +
            "FROM INTERVENTI"
    )
    fun getAllJobs() : Flow<List<Job>>

    @Upsert
    suspend fun upsertJob(job : Job)

    @Delete
    suspend fun deleteJob(job : Job)
}