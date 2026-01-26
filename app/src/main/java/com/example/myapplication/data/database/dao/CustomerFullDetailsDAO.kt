package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.myapplication.data.database.CustomerFullDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerFullDetailsDAO {
    @Transaction
    @Query("SELECT * " +
            "FROM CLIENTI " +
            "WHERE CF = :cf"
    )
    fun getCustomerFullDetails(cf: String) : Flow<CustomerFullDetails?>
}