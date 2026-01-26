package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.database.CategoryPurchaseInvoice
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryPurchaseInvoiceDAO{

    @Query("SELECT * " +
            "FROM CATEGORIE " +
            "WHERE id = :id"
    )
    fun getCategoryPurchaseInvoice(id : Int) : Flow<CategoryPurchaseInvoice?>

    @Query("SELECT * " +
            "FROM CATEGORIE"
    )
    fun getAllCategoriesPurchaseInvoice() : Flow<List<CategoryPurchaseInvoice>>

    @Upsert
    suspend fun upsertCategoryPurchaseInvoice(category : CategoryPurchaseInvoice)

    @Delete
    suspend fun deleteCategoryPurchaseInvoice(category : CategoryPurchaseInvoice)
}
