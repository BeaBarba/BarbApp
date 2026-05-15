package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import androidx.room.withTransaction
import com.example.myapplication.data.database.Material
import com.example.myapplication.data.database.MaterialFullDetails
import com.example.myapplication.data.database.MaterialWithAirConditional
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal
import java.math.RoundingMode

@Dao
interface MaterialDAO{

    @Query("SELECT * " +
            "FROM MATERIALI " +
            "WHERE id = :id"
    )
    fun getFlowMaterial(id : Int) : Flow<Material?>

    @Query("SELECT * " +
            "FROM MATERIALI " +
            "WHERE id = :id"
    )
    fun getMaterial(id : Int) : Material?

    @Query("SELECT * " +
            "FROM MATERIALI "
    )
    fun getFlowAllMaterials() : Flow<List<Material>>

    @Query(
        "SELECT DISTINCT(Categoria) " +
        "FROM MATERIALI"
    )
    suspend fun getAllCategoriesOfMaterials() : List<String>?

    @Upsert
    suspend fun upsertMaterial(material : Material) : Long

    @Delete
    suspend fun deleteMaterial(material : Material)

    @Transaction
    @Query("SELECT * " +
            "FROM MATERIALI " +
            "WHERE id = :id"
    )
    suspend fun getMaterialWithAirConditionalDetails(id : Int) : MaterialWithAirConditional?


    @Transaction
    @Query(
        "SELECT * " +
        "FROM MATERIALI"
    )
    suspend fun getAllMaterialsWithAirConditionalDetails() : List<MaterialWithAirConditional>

    @Transaction
    @Query("SELECT * " +
            "FROM MATERIALI "
    )
    fun getFlowAllMaterialsWithAirConditionalDetails() : Flow<List<MaterialWithAirConditional>>

    @Transaction
    @Query("SELECT * " +
            "FROM MATERIALI " +
            "WHERE id = :id"
    )
    fun getFlowMaterialFullDetails(id : Int) : Flow<MaterialFullDetails?>

    @Transaction
    @Query("SELECT * " +
            "FROM MATERIALI "
    )
    suspend fun getAllMaterialsFullDetails() : List<MaterialFullDetails>


    suspend fun offsetMaterialAvailableQuantity(materialId : Int, quantity : Float) {
        val material = this.getMaterial(materialId)

        material?.let { mat ->

            val newQuantity = BigDecimal(
                (mat.availableQuantity + quantity)
                    .coerceAtLeast(0.0F)
                    .toDouble()
            ).setScale(2, RoundingMode.HALF_UP).toFloat()

            upsertMaterial(
                mat.copy(id = mat.id, availableQuantity = newQuantity)
            )
        }
    }
}