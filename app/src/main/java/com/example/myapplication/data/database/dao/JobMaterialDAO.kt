package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.JobMaterial
import kotlinx.coroutines.flow.Flow

@Dao
interface JobMaterialDAO{

    @Query("SELECT * " +
            "FROM PRENOTAZIONI " +
            "WHERE Materiale = :material " +
            "AND Intervento = :job"
    )
    fun getJobMaterial(material : Int, job: Int) : Flow<JobMaterial?>

    @Query("SELECT * " +
            "FROM PRENOTAZIONI"
    )
    fun getAllJobMaterials() : Flow<List<JobMaterial>>

    @Upsert
    suspend fun upsertJobMaterial(jobMaterial: JobMaterial)

    @Delete
    suspend fun deleteJobMaterial(jobMaterial: JobMaterial)
}