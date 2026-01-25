package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.Bubble
import kotlinx.coroutines.flow.Flow

@Dao
interface BubbleDAO{
    @Query("SELECT * " +
            "FROM BOLLE " +
            "WHERE id = :id"
    )
    fun getBubbleById(id : Int) : Flow<Bubble>

    @Query("SELECT * " +
            "FROM BOLLE"
    )
    fun getAllBubbles() : Flow<List<Bubble>>

    @Upsert
    suspend fun upsertBubble(bubble : Bubble)

    @Delete
    suspend fun deleteBubble(bubble: Bubble)
}