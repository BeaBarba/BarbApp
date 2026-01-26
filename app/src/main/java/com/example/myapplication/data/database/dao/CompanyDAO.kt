package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.Company
import kotlinx.coroutines.flow.Flow

@Dao
interface CompanyDAO{

    @Query("SELECT * " +
            "FROM AZIENDE " +
            "WHERE CodiceUnivoco = :uniqueCode"
    )
    fun getCompany(uniqueCode : String) : Flow<Company?>

    @Upsert
    suspend fun upsertCompany(company: Company)

    @Delete
    suspend fun deleteCompany(company: Company)
}