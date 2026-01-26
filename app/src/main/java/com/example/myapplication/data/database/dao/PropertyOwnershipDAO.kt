package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.PropertyOwnership
import kotlinx.coroutines.flow.Flow

@Dao
interface PropertyOwnershipDAO{

    @Query("SELECT * " +
            "FROM POSSESSI " +
            "WHERE Cliente = :customer " +
            "AND Indirizzo = :address"
    )
    fun getPropertyOwnership(customer : String, address : Int) : Flow<PropertyOwnership?>

    @Upsert
    suspend fun upsertPropertyOwnership(propertyOwnership: PropertyOwnership)

    @Delete
    suspend fun deletePropertyOwnership(propertyOwnership: PropertyOwnership)
}