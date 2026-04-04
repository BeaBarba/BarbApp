package com.example.myapplication.data.repository

import com.example.myapplication.data.database.Address
import com.example.myapplication.data.database.AppDatabase
import com.example.myapplication.data.database.PropertyOwnership
import kotlinx.coroutines.flow.Flow

class AddressRepository (private val db : AppDatabase){

    /* PropertyOwnership */
    fun getPropertyOwnershipById(
        customerId: String,
        addressId: Int
    ): Flow<PropertyOwnership?> = db.propertyOwnershipDAO().getPropertyOwnership(customerId, addressId)

    suspend fun upsertPropertyOwnership(propertyOwnership: PropertyOwnership) =
        db.propertyOwnershipDAO().upsertPropertyOwnership(propertyOwnership)

    suspend fun deletePropertyOwnership(propertyOwnership: PropertyOwnership) =
        db.propertyOwnershipDAO().deletePropertyOwnership(propertyOwnership)

    /* Address */
    val addresses = db.addressDAO().getAllAddresses()

    fun getAddressById(id: Int): Flow<Address?> = db.addressDAO().getAddress(id)

    suspend fun upsertAddress(address: Address): Long = db.addressDAO().upsertAddress(address)

    suspend fun deleteAddress(address: Address) = db.addressDAO().deleteAddress(address)

}