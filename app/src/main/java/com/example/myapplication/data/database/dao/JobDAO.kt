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
import java.time.LocalDate

@Dao
interface JobDAO{

    @Query(
        "SELECT * " +
        "FROM INTERVENTI " +
        "WHERE id = :id"
    )
    fun getFlowJob(id : Int) : Flow<Job?>

    @Query(
        "SELECT * " +
        "FROM INTERVENTI"
    )
    fun getFlowAllJobs() : Flow<List<Job>>

    @Query(
        "UPDATE INTERVENTI SET Cliente = :cf WHERE id = :jobId"
    )
    suspend fun updateCustomerByJob(jobId : Int, cf : String)

    @Upsert
    suspend fun upsertJob(job : Job) : Long

    @Delete
    suspend fun deleteJob(job : Job)

    @Transaction
    @Query(
        "SELECT * " +
        "FROM INTERVENTI " +
        "WHERE id = :id"
    )
    fun getFlowJobMaterialFullDetails(id : Int) : Flow<JobMaterialFullDetails?>

    @Transaction
    @Query(
        "SELECT * " +
        "FROM INTERVENTI " +
        "WHERE id = :id"
    )
    fun getFlowJobAssignmentDetails(id : Int) : Flow<JobAssignmentDetails?>

    @Transaction
    @Query(
        "SELECT * " +
        "FROM INTERVENTI "
    )
    fun getFlowAllJobsAssignmentDetails() : Flow<List<JobAssignmentDetails>>

    @Transaction
    @Query(
        "SELECT * " +
        "FROM INTERVENTI " +
        "WHERE id = :id"
    )
    fun getFlowJobFullDetails(id : Int) : Flow<JobFullDetails?>

    @Transaction
    @Query(
        "SELECT * " +
        "FROM INTERVENTI "
    )
    fun getFlowAllJobsFullDetails() : Flow<List<JobFullDetails>>

    @Transaction
    @Query(
        "SELECT * " +
        "FROM INTERVENTI " +
        "WHERE Data > :date"
    )
    fun getFlowAllToScheduleJobsAssignmentDetails(date : LocalDate) : Flow<List<JobAssignmentDetails>>

    @Transaction
    @Query(
        "SELECT * " +
        "FROM INTERVENTI " +
        "WHERE Data = :date"
    )
    fun getFlowAllTodayJobsFullDetails(date : LocalDate) : Flow<List<JobFullDetails>>
}