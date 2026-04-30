package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.myapplication.data.database.Bubble
import com.example.myapplication.data.database.BubbleFullDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface BubbleDAO{
    @Query(
        "SELECT * " +
        "FROM BOLLE " +
        "WHERE id = :id"
    )
    fun getBubble(id : Int) : Flow<Bubble?>

    @Query(
        "SELECT * " +
        "FROM BOLLE"
    )
    fun getAllBubbles() : Flow<List<Bubble>>

    @Query(
        "UPDATE BOLLE SET Fattura = null WHERE Fattura = :purchaseInvoiceId"
    )
    suspend fun removePurchaseInvoiceReferenceFromBubbles(purchaseInvoiceId : Int)

    @Query(
        "UPDATE BOLLE SET Fattura = :purchaseInvoiceId WHERE id = :bubbleId"
    )
    suspend fun updatePurchaseInvoiceReferenceFromBubble(bubbleId : Int, purchaseInvoiceId: Int)

    @Upsert
    suspend fun upsertBubble(bubble : Bubble) : Long

    @Delete
    suspend fun deleteBubble(bubble: Bubble)

    @Transaction
    @Query(
        "SELECT * " +
        "FROM BOLLE " +
        "WHERE id = :bubble"
    )
    fun getBubbleFullDetails(bubble : Int) : Flow<BubbleFullDetails?>

    @Transaction
    @Query(
        "SELECT * " +
        "FROM BOLLE "
    )
    fun getAllBubblesFullDetails() : Flow<List<BubbleFullDetails>>
}