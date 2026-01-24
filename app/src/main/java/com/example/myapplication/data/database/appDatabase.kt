package com.example.myapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Address::class], version = 1)
abstract class appDatabase : RoomDatabase(){
    abstract fun appDAO() : appDAO
}