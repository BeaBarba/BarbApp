package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.Seller
import kotlinx.coroutines.flow.Flow

@Dao
interface SellerDAO{
    @Query("SELECT * " +
            "FROM VENDITORI " +
            "WHERE id = :id"
    )
    fun getSeller(id : Int) : Flow<Seller?>

    @Query("SELECT * " +
            "FROM VENDITORI"
    )
    fun getAllSeller() : Flow<List<Seller>>

    @Upsert
    suspend fun upsertSeller(seller : Seller) : Long

    @Delete
    suspend fun  deleteSeller(seller: Seller)
}