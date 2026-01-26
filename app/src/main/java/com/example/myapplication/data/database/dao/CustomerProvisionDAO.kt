package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert
import androidx.room.Query
import com.example.myapplication.data.database.CustomerProvision
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerProvisionDAO{

    @Query("SELECT * " +
            "FROM FORNITURE " +
            "WHERE Materiale = :material " +
            "AND Cliente = :customer"
    )
    fun getCustomerProvision(material : Int, customer : String) : Flow<CustomerProvision?>

    @Query("SELECT * " +
            "FROM FORNITURE"
    )
    fun getAllCustomerProvisions() : Flow<List<CustomerProvision>>

    @Upsert
    suspend fun upsertCustomerProvision(customerProvision: CustomerProvision)

    @Delete
    suspend fun deleteCustomerProvision(customerProvision: CustomerProvision)
}
