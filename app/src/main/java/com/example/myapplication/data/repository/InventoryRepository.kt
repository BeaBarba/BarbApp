package com.example.myapplication.data.repository

import androidx.room.withTransaction
import com.example.myapplication.data.database.AirConditioner
import com.example.myapplication.data.database.AppDatabase
import com.example.myapplication.data.database.CustomerProvision
import com.example.myapplication.data.database.Delivery
import com.example.myapplication.data.database.Material
import com.example.myapplication.data.database.MaterialFullDetails
import com.example.myapplication.data.database.MaterialWithAirConditional
import com.example.myapplication.data.database.Purchase
import com.example.myapplication.data.database.Seller
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class InventoryRepository(private val db : AppDatabase){

    /* AirConditioner */
    val airConditioners = db.airConditionerDAO().getAllAirConditioners()

    fun getAirConditionerById(serialNumber: String, material: Int): Flow<AirConditioner?> =
        db.airConditionerDAO().getAirConditioner(serialNumber, material)

    suspend fun upsertAirConditioner(airConditioner: AirConditioner) =
        db.airConditionerDAO().upsertAirConditioner(airConditioner)

    suspend fun deleteAirConditioner(airConditioner: AirConditioner) =
        db.airConditionerDAO().deleteAirConditioner(airConditioner)

    /* Material */
    val materials = db.materialDAO().getAllMaterials()

    fun getMaterialById(id: Int): Flow<Material?> = db.materialDAO().getMaterial(id)

    fun getMaterialFullDetailsById(id: Int): Flow<MaterialFullDetails?> =
        db.materialDAO().getMaterialFullDetails(id)

    suspend fun incMaterialAvailableQuantity(materialId : Int, quantity : Float) =
        db.withTransaction {
            val material = getMaterialById(materialId).first()

            material.let {mat ->
                db.materialDAO().upsertMaterial(
                    mat!!.copy(id = mat.id, availableQuantity = mat.availableQuantity + quantity)
                )
            }
        }

    suspend fun upsertMaterial(material: Material) = db.materialDAO().upsertMaterial(material)

    suspend fun deleteMaterial(material: Material) = db.materialDAO().deleteMaterial(material)

    suspend fun getAllMaterialsWithAirConditional(): List<MaterialWithAirConditional> =
        db.materialDAO().getAllMaterialsWithAirConditionalDetails()

    suspend fun getAllMaterialsFullDetails(): List<MaterialFullDetails> =
        db.materialDAO().getAllMaterialsFullDetails()

    /* Seller */
    val sellers = db.sellerDAO().getAllSeller()

    fun getSellerById(id: Int): Flow<Seller?> = db.sellerDAO().getSeller(id)

    suspend fun upsertSeller(seller: Seller): Long = db.sellerDAO().upsertSeller(seller)

    suspend fun deleteSeller(seller: Seller) = db.sellerDAO().deleteSeller(seller)

    /* Purchase */
    val purchases = db.purchaseDAO().getAllPurchases()

    fun getPurchaseById(purchaseInvoice: Int, material: Int): Flow<Purchase?> =
        db.purchaseDAO().getPurchase(purchaseInvoice, material)

    suspend fun upsertPurchase(purchase: Purchase) = db.purchaseDAO().upsertPurchase(purchase)

    suspend fun deletePurchase(purchase: Purchase) = db.purchaseDAO().deletePurchase(purchase)

    /* Delivery */
    val deliveries = db.deliveriesDAO().getAllDeliveries()

    fun getDeliveryById(bubble: Int, material: Int): Flow<Delivery?> =
        db.deliveriesDAO().getDelivery(bubble, material)

    suspend fun upsertDelivery(delivery: Delivery) = db.deliveriesDAO().upsertDelivery(delivery)

    suspend fun deleteDelivery(delivery: Delivery) = db.deliveriesDAO().deleteDelivery(delivery)

    /* CustomerProvision */
    val customerProvisions = db.customerProvisionDAO().getAllCustomerProvisions()

    fun getCustomerProvisionById(material: Int, customer: String): Flow<CustomerProvision?> =
        db.customerProvisionDAO().getCustomerProvision(material, customer)

    suspend fun upsertCustomerProvision(customerProvision: CustomerProvision) =
        db.customerProvisionDAO().upsertCustomerProvision(customerProvision)

    suspend fun deleteCustomerProvision(customerProvision: CustomerProvision) =
        db.customerProvisionDAO().deleteCustomerProvision(customerProvision)
}