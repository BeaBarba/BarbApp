package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.PurchaseInvoice
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseInvoiceDAO{
    @Query("SELECT * " +
            "FROM FATTURE_ACQUISTO " +
            "WHERE id = :id"
    )
    fun getPurchaseInvoice(id : Int) : Flow<PurchaseInvoice>

    @Query("SELECT * " +
            "FROM FATTURE_ACQUISTO"
    )
    fun getAllPurchaseInvoice() : Flow<List<PurchaseInvoice>>

    @Upsert
    suspend fun upsertPurchaseInvoice(seller : PurchaseInvoice)

    @Delete
    suspend fun  deletePurchaseInvoice(seller: PurchaseInvoice)
}