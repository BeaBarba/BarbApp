package com.example.myapplication.data.repository

import com.example.myapplication.data.database.AppDatabase

class Repository (private val db : AppDatabase) {

    val inventory = InventoryRepository(db)
    val accounting = AccountingRepository(db)
    val customer = CustomerRepository(db)
    val address = AddressRepository(db)
    val job = JobRepository(db, inventory)

    /* Cart */
    val cartItems = db.cartDAO().getCartItems()
}