package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.myapplication.data.database.Job
import com.example.myapplication.data.database.JobAssignmentDetails
import com.example.myapplication.data.database.JobFullDetails
import com.example.myapplication.data.database.JobMaterialFullDetails
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

    @Transaction
    @Query(
        "SELECT * " +
        "FROM INTERVENTI " +
        "WHERE id = :id"
    )
    fun getJobMaterialFullDetails(id : Int) : Flow<JobMaterialFullDetails?>

    @Transaction
    @Query(
        "SELECT * " +
        "FROM INTERVENTI " +
        "WHERE id = :id"
    )
    fun getJobAssignmentDetails(id : Int) : Flow<JobAssignmentDetails?>

    @Transaction
    @Query(
        "SELECT * " +
        "FROM INTERVENTI "
    )
    suspend fun getAllJobsAssignmentDetails() : List<JobAssignmentDetails>

    @Transaction
    @Query(
        "SELECT * " +
        "FROM INTERVENTI " +
        "WHERE id = :id"
    )
    fun getJobFullDetails(id : Int) : Flow<JobFullDetails?>

    @Transaction
    @Query(
        "SELECT * " +
        "FROM INTERVENTI "
    )
    suspend fun getAllJobsFullDetails() : List<JobFullDetails>
}