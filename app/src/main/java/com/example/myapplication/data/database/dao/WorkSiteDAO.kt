package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.myapplication.data.database.WorkSite
import com.example.myapplication.data.database.WorkSiteAssignmentDetails
import com.example.myapplication.data.database.WorkSiteFullDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkSiteDAO{

    @Query(
        "SELECT * " +
        "FROM CANTIERI " +
        "WHERE id = :id"
    )
    fun getWorkSite(id : Int) : Flow<WorkSite?>

    @Query(
        "SELECT * " +
        "FROM CANTIERI"
    )
    fun getAllWorkSites() : Flow<List<WorkSite>>

    @Query(
        "UPDATE CANTIERI SET Cliente = :cf WHERE id = :worksiteId"
    )
    suspend fun updateCustomerByWorkSite(worksiteId : Int, cf : String)

    @Upsert
    suspend fun upsertWorkSite(workSite: WorkSite)

    @Delete
    suspend fun deleteWorkSite(workSite: WorkSite)

    @Transaction
    @Query(
        "SELECT * " +
        "FROM CANTIERI " +
        "WHERE id = :id"
    )
    fun getFlowWorkSiteFullDetails(id : Int) : Flow<WorkSiteFullDetails?>

    @Transaction
    @Query(
        "SELECT * " +
        "FROM CANTIERI " +
        "WHERE id = :id"
    )
    suspend fun getWorkSiteFullDetails(id : Int) : WorkSiteFullDetails?

    @Transaction
    @Query(
        "SELECT * " +
        "FROM CANTIERI "
    )
    fun getFlowAllWorkSitesFullDetails() : Flow<List<WorkSiteFullDetails>>

    @Transaction
    @Query(
        "SELECT * " +
        "FROM CANTIERI "
    )
    fun getAllWorkSitesFullDetails() : List<WorkSiteFullDetails>

    @Transaction
    @Query(
        "SELECT * " +
        "FROM CANTIERI "
    )
    fun getAllWorkSitesAssignmentDetails() : Flow<List<WorkSiteAssignmentDetails>>
}