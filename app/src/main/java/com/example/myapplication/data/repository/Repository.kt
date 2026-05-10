package com.example.myapplication.data.repository

import com.example.myapplication.data.database.AppDatabase
import com.example.myapplication.data.database.JobStatisticsResult
import com.example.myapplication.data.database.MaterialPriceHistoryResult
import com.example.myapplication.data.database.RevenueFromJobTypeResult
import java.time.LocalDate

class Repository (private val db : AppDatabase) {

    val inventory = InventoryRepository(db)
    val accounting = AccountingRepository(db)
    val customer = CustomerRepository(db)
    val address = AddressRepository(db)
    val job = JobRepository(db, inventory, customer, accounting)

    /* Cart */
    val cartItems = db.cartDAO().getCartItems()

    suspend fun materialPriceHistory(id : Int) : List<MaterialPriceHistoryResult> = db.statisticsDAO()
        .getMaterialPriceHistory(id)

    suspend fun jobStatistics(startDate : LocalDate, endDate : LocalDate) : JobStatisticsResult = db.statisticsDAO()
        .getJobStatistics(startDate, endDate)

    suspend fun revenueFromJobType() : RevenueFromJobTypeResult = db.statisticsDAO().getRevenueFromJobType()
}