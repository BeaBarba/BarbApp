package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.Delivery
import com.example.myapplication.data.database.Material
import kotlinx.coroutines.flow.Flow

@Dao
interface DeliveryDAO{

    @Query(
        "SELECT * " +
        "FROM RILASCI " +
        "WHERE Bolla = :bubble " +
        "AND Materiale = :material"
    )
    fun getDelivery(bubble : Int, material : Int) : Flow<Delivery?>

    @Query(
        "SELECT * " +
        "FROM RILASCI"
    )
    fun getAllDeliveries() : Flow<List<Delivery>>

    @Upsert
    suspend fun upsertDelivery(delivery : Delivery)

    @Delete
    suspend fun deleteDelivery(deliveries : Delivery)
}
