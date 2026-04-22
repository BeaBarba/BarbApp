package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.MaterialUsage
import kotlinx.coroutines.flow.Flow

@Dao
interface MaterialUsageDAO{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMaterialUsage(list: List<MaterialUsage>)

    @Query("SELECT * " +
            "FROM UTILIZZI " +
            "WHERE Materiale = :material " +
            "AND Intervento = :job"
    )
    fun getFlowMaterialUsage(material : Int, job : Int) : Flow<MaterialUsage?>

    @Query("SELECT * " +
            "FROM UTILIZZI"
    )
    fun getFlowAllMaterialsUsage() : Flow<List<MaterialUsage>>

    @Upsert
    suspend fun upsertMaterialUsage(materialUsage: MaterialUsage)

    @Delete
    suspend fun deleteMaterialUsage(materialUsage: MaterialUsage)

    @Query(
        "DELETE FROM UTILIZZI WHERE Intervento = :job"
    )
    suspend fun deleteMaterialUsageByJob(job : Int)
}