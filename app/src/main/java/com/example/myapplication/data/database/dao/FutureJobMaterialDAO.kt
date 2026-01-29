package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.FutureJobMaterial
import kotlinx.coroutines.flow.Flow

@Dao
interface FutureJobMaterialDAO{

    @Query("SELECT * " +
            "FROM PRENOTAZIONI " +
            "WHERE Materiale = :material " +
            "AND Intervento = :job"
    )
    fun getFutureJobMaterial(material : Int, job: Int) : Flow<FutureJobMaterial?>

    @Query("SELECT * " +
            "FROM PRENOTAZIONI"
    )
    fun getAllFutureJobMaterials() : Flow<List<FutureJobMaterial>>

    @Upsert
    suspend fun upsertFutureJobMaterial(futureJobMaterial: FutureJobMaterial)

    @Delete
    suspend fun deleteFutureJobMaterial(futureJobMaterial: FutureJobMaterial)
}