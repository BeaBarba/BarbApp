package com.example.myapplication.data.repository

import com.example.myapplication.data.database.AppDatabase
import com.example.myapplication.data.database.dao.MaterialPriceHistory

class Repository (private val db : AppDatabase) {

    val inventory = InventoryRepository(db)
    val accounting = AccountingRepository(db)
    val customer = CustomerRepository(db)
    val address = AddressRepository(db)
    val job = JobRepository(db, inventory, customer, accounting)

    /* Cart */
    val cartItems = db.cartDAO().getCartItems()

    suspend fun materialPriceHistory(id : Int) : List<MaterialPriceHistory> = db.statisticsDAO().getMaterialPriceHistory(id)
}