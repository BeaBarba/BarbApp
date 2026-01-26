package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.Purchase
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseDAO{
    @Query("SELECT * " +
            "FROM ACQUISTI " +
            "WHERE Fattura = :purchaseInvoice " +
            "AND Materiale = :material"
    )
    fun getPurchase(purchaseInvoice: Int, material : Int) : Flow<Purchase?>

    @Query("SELECT * " +
            "FROM ACQUISTI"
    )
    fun getAllPurchases() : Flow<List<Purchase>>

    @Upsert
    suspend fun upsertPurchase(purchase : Purchase)

    @Delete
    suspend fun deletePurchase(purchase : Purchase)
}
