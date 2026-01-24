package com.example.myapplication.data.repository

import com.example.myapplication.data.database.Address
import com.example.myapplication.data.database.appDAO
import kotlinx.coroutines.flow.Flow

class Repository (
    private val dao: appDAO
){
    val addresses = dao.getAllAddresses()

    fun getAddressById(id: Int) : Flow<Address> = dao.getAddress(id)

    suspend fun upsert(indirizzo : Address) = dao.upsert(indirizzo)

    suspend fun delete(indirizzo : Address) = dao.delete(indirizzo)
}