package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.myapplication.data.database.CartDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDAO{
    @Transaction
    @Query("SELECT M.*, C.Prenotati, C.Mancano " +
            "FROM MATERIALI AS M" +
            "JOIN Carrello AS C ON (M.Id = C.Materiale)"
    )
    fun getCartItems() : Flow<List<CartDetails>>
}