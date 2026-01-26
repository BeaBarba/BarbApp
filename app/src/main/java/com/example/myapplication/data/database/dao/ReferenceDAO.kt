package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert
import androidx.room.Query
import com.example.myapplication.data.database.Reference
import kotlinx.coroutines.flow.Flow

@Dao
interface ReferenceDAO{

    @Query("SELECT * " +
            "FROM RIFERIMENTI " +
            "WHERE id = :id"
    )
    fun getReference(id : Int) : Flow<Reference?>

    @Query("SELECT * " +
            "FROM riferimenti"
    )
    fun getAllReferences() : Flow<List<Reference>>

    @Upsert
    suspend fun upsertReference(reference: Reference)

    @Delete
    suspend fun deleteReference(reference: Reference)
}
