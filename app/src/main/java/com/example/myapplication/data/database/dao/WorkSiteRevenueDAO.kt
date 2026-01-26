package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.WorkSiteRevenue
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkSiteRevenueDAO{

    @Query("SELECT * " +
            "FROM GENERA " +
            "WHERE Ricavo = :id"
    )
    fun getWorkSiteRevenue(id : Int) : Flow<WorkSiteRevenue?>

    @Query("SELECT * " +
            "FROM GENERA"
    )
    fun getAllWorkSiteRevenues() : Flow<List<WorkSiteRevenue>>

    @Upsert
    suspend fun upsertWorkSiteRevenue(workSiteRevenue: WorkSiteRevenue)

    @Delete
    suspend fun deleteWorkSiteRevenue(workSiteRevenue: WorkSiteRevenue)
}