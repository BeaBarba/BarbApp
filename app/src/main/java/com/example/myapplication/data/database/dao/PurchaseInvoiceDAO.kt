package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.myapplication.data.database.PurchaseInvoice
import com.example.myapplication.data.database.PurchaseInvoiceFullDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseInvoiceDAO{
    @Query("SELECT * " +
            "FROM FATTURE_ACQUISTO " +
            "WHERE id = :id"
    )
    fun getFlowPurchaseInvoice(id : Int) : Flow<PurchaseInvoice?>

    @Query("SELECT * " +
            "FROM FATTURE_ACQUISTO"
    )
    fun getFlowAllPurchaseInvoice() : Flow<List<PurchaseInvoice>>

    @Upsert
    suspend fun upsertPurchaseInvoice(seller : PurchaseInvoice)

    @Delete
    suspend fun  deletePurchaseInvoice(seller: PurchaseInvoice)

    @Transaction
    @Query(
        "SELECT * " +
        "FROM FATTURE_ACQUISTO " +
        "WHERE id = :id"
    )
    fun getFlowPurchaseInvoiceFullDetails(id : Int) : Flow<PurchaseInvoiceFullDetails?>

    @Transaction
    @Query(
        "SELECT * " +
        "FROM FATTURE_ACQUISTO " +
        "WHERE id = :id"
    )
    suspend fun getPurchaseInvoiceFullDetails(id : Int) : PurchaseInvoiceFullDetails?

    @Transaction
    @Query(
        "SELECT * " +
        "FROM FATTURE_ACQUISTO "
    )
    fun getFlowAllPurchaseInvoicesFullDetails() : Flow<List<PurchaseInvoiceFullDetails>>
}