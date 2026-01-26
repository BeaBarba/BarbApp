package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.Material
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
}