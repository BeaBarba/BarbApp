package com.example.myapplication.data.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Relation

data class CustomerTypeDetails(
    @Embedded val customer: Customer,

    @Relation(
        parentColumn = "CF",
        entityColumn = "CF"
    )
    val privateCustomer: Private?,

    @Relation(
        parentColumn = "CF",
        entityColumn = "CF"
    )
    val companyCustomer: Company?
){
    val isPrivate : Boolean get() = privateCustomer != null
    val isCompany : Boolean get() = companyCustomer != null
}

data class ReferralFullDetails(
    @Embedded val referral: Referral,

    @Relation(
        entity = Customer::class,
        parentColumn = "Presentatore",
        entityColumn = "CF"
    )
    val presenter : CustomerTypeDetails
)

data class CustomerFullDetails(
    @Embedded val customer: Customer,

    @Relation(
        parentColumn = "CF",
        entityColumn = "CF"
    )
    val privateCustomer: Private?,

    @Relation(
        parentColumn = "CF",
        entityColumn = "CF"
    )
    val companyCustomer: Company?,

    @Relation(
        parentColumn = "Residenza",
        entityColumn = "id"
    )
    val address: Address,

    @Relation(
        parentColumn = "Riferimento",
        entityColumn = "id"
    )
    val reference: Reference?,

    @Relation(
        entity = Referral :: class,
        parentColumn = "CF",
        entityColumn = "Presentato"
    )
    val referral: ReferralFullDetails?,

    @Relation(
        entity = Job::class,
        parentColumn = "CF",
        entityColumn = "Cliente"
    )
    val jobs: List<JobAssignmentDetails>,

    @Relation(
        parentColumn = "CF",
        entityColumn = "Cliente"
    )
    val phoneNumber: PhoneNumber?,
){
    val isPrivate : Boolean get() = privateCustomer != null
    val isCompany : Boolean get() = companyCustomer != null
}

data class DeliveryWithMaterialDetails(
    @Embedded val delivery: Delivery,

    @Relation(
        parentColumn = "Materiale",
        entityColumn = "id"
    )
    val material : Material
)

data class BubbleFullDetails(
    @Embedded
    val bubble : Bubble,

    @Relation(
        parentColumn = "Venditore",
        entityColumn = "id"
    )
    val seller : Seller,

    @Relation(
        parentColumn = "Fattura",
        entityColumn = "id"
    )
    val purchaseInvoice: PurchaseInvoice?,

    @Relation(
        entity = Delivery::class,
        parentColumn = "id",
        entityColumn = "Bolla"
    )
    val deliveriesWithMaterials: List<DeliveryWithMaterialDetails>
)

data class MaterialWithAirConditional(
    @Embedded val material : Material,

    @Relation(
        parentColumn = "id",
        entityColumn = "Materiale"
    )
    val airConditioner: List<AirConditioner>
){
    val isAirConditioner : Boolean get() = airConditioner.isNotEmpty()
}

data class DeliveryWithBubbleDetails(
    @Embedded val delivery: Delivery,

    @Relation(
        parentColumn = "Bolla",
        entityColumn = "id"
    )
    val bubble : Bubble
)

data class PurchaseWithPurchaseInvoiceDetails(
    @Embedded val purchase: Purchase,

    @Relation(
        parentColumn = "Fattura",
        entityColumn = "id"
    )
    val purchaseInvoice : PurchaseInvoice
)

data class MaterialFullDetails(
    @Embedded val material : MaterialWithAirConditional,

    @Relation(
        entity = Delivery::class,
        parentColumn = "id",
        entityColumn = "Materiale"
    )
    val deliveriesWithBubbles : List<DeliveryWithBubbleDetails>,

    @Relation(
        entity = Purchase::class,
        parentColumn = "id",
        entityColumn = "Materiale"
    )
    val purchaseWithPurchaseInvoice : List<PurchaseWithPurchaseInvoiceDetails>,

    @Relation(
        parentColumn = "id",
        entityColumn = "Materiale"
    )
    val photos : List<Image>
)

data class MaterialUsageWithMaterialDetails(
    @Embedded val materialUsage : MaterialUsage,

    @Relation(
        parentColumn = "Materiale",
        entityColumn = "id"
    )
    val material : Material
)

data class FutureJobMaterialWithMaterialDetails(
    @Embedded val futureJobMaterial : FutureJobMaterial,

    @Relation(
        parentColumn = "Materiale",
        entityColumn = "id"
    )
    val material : Material
)

data class JobMaterialFullDetails(
    @Embedded val job : Job,

    @Relation(
        entity = MaterialUsage::class,
        parentColumn = "id",
        entityColumn = "Intervento"
    )
    val materialUsage : List<MaterialUsageWithMaterialDetails>,

    @Relation(
        entity = FutureJobMaterial::class,
        parentColumn = "id",
        entityColumn = "Intervento"
    )
    val materialsFuture : List<FutureJobMaterialWithMaterialDetails>,

    @Relation(
        parentColumn = "id",
        entityColumn = "Intervento"
    )
    val photos : List<Image>
)

data class JobAssignmentDetails(
    @Embedded val job : Job,

    @Relation(
        parentColumn = "Indirizzo",
        entityColumn = "id"
    )
    val address : Address,

    @Relation(
        entity = Customer::class,
        parentColumn = "Cliente",
        entityColumn = "CF"
    )
    val customer : CustomerTypeDetails?,
)

data class JobFullDetails(
    @Embedded val jobDetails: JobAssignmentDetails,

    @Relation(
        entity = MaterialUsage::class,
        parentColumn = "id",
        entityColumn = "Intervento"
    )
    val materialUsage: List<MaterialUsageWithMaterialDetails>,

    @Relation(
        entity = FutureJobMaterial::class,
        parentColumn = "id",
        entityColumn = "Intervento"
    )
    val materialsFuture: List<FutureJobMaterialWithMaterialDetails>,

    @Relation(
        parentColumn = "id",
        entityColumn = "Intervento"
    )
    val photos : List<Image>
)

data class WorkSiteFullDetails(
    @Embedded val workSite : WorkSite,

    @Relation(
        parentColumn = "Indirizzo",
        entityColumn = "id"
    )
    val address : Address,

    @Relation(
        parentColumn = "id",
        entityColumn = "Cantiere"
    )
    val jobs : List<Job>,

    @Relation(
        parentColumn = "Responsabile",
        entityColumn = "id"
    )
    val reference : Reference?,

    @Relation(
        entity = Customer::class,
        parentColumn = "Cliente",
        entityColumn = "CF"
    )
    val customer : CustomerTypeDetails?
)

data class PurchaseWithMaterialDetails(
    @Embedded val purchase: Purchase,

    @Relation(
        parentColumn = "Materiale",
        entityColumn = "id"
    )
    val material : Material
)

data class PurchaseInvoiceFullDetails(
    @Embedded val purchaseInvoice : PurchaseInvoice,

     @Relation(
        parentColumn = "id",
        entityColumn = "Fattura"
    )
    val bubbles : List<Bubble>,

    @Relation(
        entity = Purchase::class,
        parentColumn = "id",
        entityColumn = "Fattura"
    )
    val materials : List<PurchaseWithMaterialDetails>
)

data class RevenueFullDetails(
    @Embedded val revenue: Revenue,

    @Relation(
        parentColumn = "Cantiere",
        entityColumn = "id"
    )
    val workSite : WorkSite?,

    @Relation(
        parentColumn = "Intervento",
        entityColumn = "id"
    )
    val job : Job?
)

data class FutureJobMaterialWithJobDetails(
    @Embedded val futureJobMaterial : FutureJobMaterial,

    @Relation(
        entity = Job::class,
        parentColumn = "Intervento",
        entityColumn = "id"
    )
    val jobAssignment : JobAssignmentDetails
)

data class CartDetails(
    @Embedded val material : Material,

    @ColumnInfo("Prenotati") val reservedItems : Float,
    @ColumnInfo("Mancano") val missingItems : Float,

    @Relation(
        entity = FutureJobMaterial::class,
        parentColumn = "id",
        entityColumn = "Materiale"
    )
    val futureJobMaterial : List<FutureJobMaterialWithJobDetails>
)