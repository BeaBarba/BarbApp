package com.example.myapplication.data.repository

import com.example.myapplication.data.database.Address
import com.example.myapplication.data.database.barbAppDAO

class Repository (
    private val dao: barbAppDAO
){
    val address = dao.getAllAddress()

    suspend fun upsert(indirizzo : Address) = dao.upsert(indirizzo)

    suspend fun delete(indirizzo : Address) = dao.delete(indirizzo)
}