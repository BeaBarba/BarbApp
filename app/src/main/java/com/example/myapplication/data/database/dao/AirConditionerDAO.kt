package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.AirConditioner
import kotlinx.coroutines.flow.Flow

@Dao
interface AirConditionerDAO{
    @Query("SELECT * " +
            "FROM CONDIZIONATORI " +
            "WHERE Matricola = :serialNumber " +
            "AND  Materiale = :material"
    )
    fun getAirConditioner(serialNumber : String, material : Int) : Flow<AirConditioner?>

    @Query("SELECT * " +
            "FROM CONDIZIONATORI"
    )
    fun getAllAirConditioners() : Flow<List<AirConditioner>>

    @Upsert
    suspend fun upsertAirConditioner(airConditioner : AirConditioner)


    @Delete
    suspend fun  deleteAirConditioner(airConditioner: AirConditioner)
}
