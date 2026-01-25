package com.example.myapplication.data.repository

import com.example.myapplication.data.database.Address
import com.example.myapplication.data.database.dao.AddressDAO
import com.example.myapplication.data.database.dao.BubbleDAO
import com.example.myapplication.data.database.dao.PurchaseInvoiceDAO
import com.example.myapplication.data.database.dao.SellerDAO
import kotlinx.coroutines.flow.Flow

class Repository (
    private val daoAddress: AddressDAO,
    private val daoBubble : BubbleDAO,
    private val daoSeller : SellerDAO,
    private val daoPurchaseInvoice : PurchaseInvoiceDAO

){
    val addresses = daoAddress.getAllAddresses()

    fun getAddressById(id: Int) : Flow<Address> = daoAddress.getAddress(id)

    suspend fun upsert(address : Address) = daoAddress.upsertAddress(address)

    suspend fun delete(address : Address) = daoAddress.deleteAddress(address)
}