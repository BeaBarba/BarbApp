package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.JobPhoto
import kotlinx.coroutines.flow.Flow

@Dao
interface JobPhotoDAO{

    @Query("SELECT * " +
            "FROM DIMOSTRAZIONI " +
            "WHERE Foto = :photo"
    )
    fun getJobPhoto(photo : Int) : Flow<JobPhoto?>

    @Query("SELECT * " +
            "FROM DIMOSTRAZIONI"
    )
    fun getAllJobPhotos() : Flow<List<JobPhoto>>

    @Upsert
    suspend fun upsertJobPhoto(jobPhoto: JobPhoto)

    @Delete
    suspend fun deleteJobPhoto(jobPhoto: JobPhoto)
}