package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.Referral
import kotlinx.coroutines.flow.Flow

@Dao
interface ReferralDAO{

    @Query("SELECT * " +
            "FROM PRESENTA " +
            "WHERE Presentato = :presented"
    )
    fun getReferral(presented : String) : Flow<Referral?>

    @Query("SELECT * " +
            "FROM PRESENTA "
    )
    fun getAllReferrals() : Flow<List<Referral>>

    @Upsert
    suspend fun upsertReferral(referral: Referral)

    @Delete
    suspend fun deleteReferral(referral: Referral)
}
