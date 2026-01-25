package com.example.myapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.data.database.dao.AddressDAO
import com.example.myapplication.data.database.dao.BubbleDAO
import com.example.myapplication.data.database.dao.PurchaseInvoiceDAO
import com.example.myapplication.data.database.dao.SellerDAO

@Database(
    entities = [
        Address::class,
        Seller::class,
        PurchaseInvoice::class,
        Bubble::class
    ],
    version = 2)
@TypeConverters(DateConverters::class)
abstract class appDatabase : RoomDatabase(){
    abstract fun addressDAO() : AddressDAO
    abstract fun bubbleDAO() : BubbleDAO
    abstract fun sellerDAO() : SellerDAO
    abstract fun purchaseInvoiceDAO() : PurchaseInvoiceDAO
}