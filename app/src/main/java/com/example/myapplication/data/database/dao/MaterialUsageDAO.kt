package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.MaterialUsage
import kotlinx.coroutines.flow.Flow

@Dao
interface MaterialUsageDAO{

    @Query("SELECT * " +
            "FROM UTILIZZI " +
            "WHERE Materiale = :material " +
            "AND Intervento = :job"
    )
    fun getMaterialUsage(material : Int, job : Int) : Flow<MaterialUsage?>

    @Query("SELECT * " +
            "FROM UTILIZZI"
    )
    fun getAllMaterialsUsage() : Flow<List<MaterialUsage>>

    @Upsert
    suspend fun upsertMaterialUsage(materialUsage: MaterialUsage)

    @Delete
    suspend fun deleteMaterialUsage(materialUsage: MaterialUsage)
}