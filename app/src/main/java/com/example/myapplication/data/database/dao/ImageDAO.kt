package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.Image
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDAO{

    @Query("SELECT * " +
            "FROM FOTO " +
            "WHERE id = :id"
    )
    fun getImage(id : Int) : Flow<Image?>

    @Query("SELECT * " +
            "FROM FOTO"
    )
    fun getAllImages() : Flow<List<Image>>

    @Upsert
    suspend fun upsertImage(image : Image)

    @Delete
    suspend fun deleteImage(image : Image)
}