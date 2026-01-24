package com.example.myapplication.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "addresses",
    indices = [Index(value = ["address", "houseNumber", "city"], unique = true)]
)
data class Address(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "address") val address: String = "",
    @ColumnInfo(name = "houseNumber") val houseNumber: String = "",
    @ColumnInfo(name = "municipality") val municipality: String = "",
    @ColumnInfo(name = "city") val city: String = "",
    @ColumnInfo(name = "province") val province: String = "",
    @ColumnInfo(name = "zip") val zip: String = "",
    @ColumnInfo(name = "sheet") val sheet: String = "",
    @ColumnInfo(name = "map") val map: String = "",
    @ColumnInfo(name = "subordinate") val subordinate: String = "",
    @ColumnInfo(name = "staircase") val staircase: String = "",
    @ColumnInfo(name = "floor") val floor: String = "",
    @ColumnInfo(name = "interior") val interior: String = "",
    @ColumnInfo(name = "yearOfConstruction") val yearOfConstruction: String = "",
    @ColumnInfo(name = "usableArea") val usableArea: String = "",
    @ColumnInfo(name = "units") val units: String = ""
)