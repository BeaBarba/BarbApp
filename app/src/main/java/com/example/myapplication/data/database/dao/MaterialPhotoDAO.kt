package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert
import androidx.room.Query
import com.example.myapplication.data.database.MaterialPhoto
import kotlinx.coroutines.flow.Flow

@Dao
interface MaterialPhotoDAO{

    @Query("SELECT * " +
            "FROM RAFFIGURAZIONI " +
            "WHERE Foto = :photo"
    )
    fun getMaterialPhoto(photo : Int) : Flow<MaterialPhoto?>

    @Query("SELECT * " +
            "FROM RAFFIGURAZIONI"
    )
    fun getAllMaterialPhotos() : Flow<List<MaterialPhoto>>

    @Upsert
    suspend fun upsertMaterialPhoto(materialPhoto: MaterialPhoto)

    @Delete
    suspend fun deleteMaterialPhoto(materialPhoto: MaterialPhoto)
}