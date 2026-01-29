package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.myapplication.data.database.Material
import com.example.myapplication.data.database.MaterialFullDetails
import com.example.myapplication.data.database.MaterialWithAirConditional
import kotlinx.coroutines.flow.Flow

@Dao
interface MaterialDAO{

    @Query("SELECT * " +
            "FROM MATERIALI " +
            "WHERE id = :id"
    )
    fun getMaterial(id : Int) : Flow<Material?>

    @Query("SELECT * " +
            "FROM MATERIALI "
    )
    fun getAllMaterials() : Flow<List<Material>>

    @Upsert
    suspend fun upsertMaterial(material : Material)

    @Delete
    suspend fun deleteMaterial(material : Material)

    @Transaction
    @Query(
        "SELECT * " +
        "FROM MATERIALI"
    )
    suspend fun getAllMaterialsWithAirConditionalDetails() : List<MaterialWithAirConditional>

    @Transaction
    @Query("SELECT * " +
            "FROM MATERIALI " +
            "WHERE id = :id"
    )
    fun getMaterialFullDetails(id : Int) : Flow<MaterialFullDetails>

    @Transaction
    @Query("SELECT * " +
            "FROM MATERIALI "
    )
    suspend fun getAllMaterialsFullDetails() : List<MaterialFullDetails>
}