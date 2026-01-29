package com.example.myapplication.data.database

import androidx.room.Embedded
import androidx.room.Relation

data class CustomerFullDetails(
    @Embedded
    val customer: Customer,

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
        parentColumn = "CF",
        entityColumn = "Presentato"
    )
    val referral: Referral?,

    @Relation(
        parentColumn = "CF",
        entityColumn = "Cliente"
    )
    val jobs: List<Job>,

    @Relation(
        parentColumn = "CF",
        entityColumn = "Cliente"
    )
    val phoneNumber: PhoneNumber?,

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
)

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
    val purchaseInvoice: PurchaseInvoice,

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

data class MaterialPhotoWithPhotoDetails(
    @Embedded val materialPhoto: MaterialPhoto,

    @Relation(
        parentColumn = "Foto",
        entityColumn = "id"
    )
    val image : Image
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
        entity = MaterialPhoto::class,
        parentColumn = "id",
        entityColumn = "Materiale"
    )
    val photos : List<MaterialPhotoWithPhotoDetails>
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

data class JobPhotoWithPhotoDetails(
    @Embedded val jobPhoto: JobPhoto,

    @Relation(
        parentColumn = "Foto",
        entityColumn = "id"
    )
    val image : Image
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
        entity = JobPhoto::class,
        parentColumn = "id",
        entityColumn = "Intervento"
    )
    val photos : List<JobPhotoWithPhotoDetails>
)

data class JobAssignmentDetails(
    @Embedded val job : Job,

    @Relation(
        parentColumn = "Cliente",
        entityColumn = "CF"
    )
    val customer : Customer,

    @Relation(
        parentColumn = "Indirizzo",
        entityColumn = "id"
    )
    val address : Address,

    @Relation(
        parentColumn = "Cliente",
        entityColumn = "CF"
    )
    val privateCustomer: Private?,

    @Relation(
        parentColumn = "Cliente",
        entityColumn = "CF"
    )
    val companyCustomer: Company?


){/*
    val isPrivate : Boolean = if(privateCustomer != null){true} else{false}
    val isCompany : Boolean = if(companyCustomer != null){true} else{false}
*/}

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
        entity = JobPhoto::class,
        parentColumn = "id",
        entityColumn = "Intervento"
    )
    val photos : List<JobPhotoWithPhotoDetails>
)

data class WorkSiteFullDetails(
    @Embedded val workSite : WorkSite,

    @Relation(
        parentColumn = "Indirizzo",
        entityColumn = "id"
    )
    val address : Address,

    @Relation(
        parentColumn = "Responsabile",
        entityColumn = "id"
    )
    val reference : Reference,

    @Relation(
        parentColumn = "Cliente",
        entityColumn = "CF"
    )
    val customer : Customer,

    @Relation(
        parentColumn = "id",
        entityColumn = "Cantiere"
    )
    val jobs : List<Job>
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

data class WorkSiteRevenueWithWorkSiteDetails(
    @Embedded val workSiteRevenue: WorkSiteRevenue,

    @Relation(
        parentColumn = "Cantiere",
        entityColumn = "id"
    )
    val workSite: WorkSite
)

data class JobRevenueWithJobDetails(
    @Embedded val jobRevenue: JobRevenue,

    @Relation(
        parentColumn = "Intervento",
        entityColumn = "id"
    )
    val job : Job
)

data class RevenueFullDetails(
    @Embedded val revenue: Revenue,

    @Relation(
        entity = WorkSiteRevenue::class,
        parentColumn = "id",
        entityColumn = "Ricavo"
    )
    val workSite : WorkSiteRevenueWithWorkSiteDetails,

    @Relation(
        entity = JobRevenue::class,
        parentColumn = "id",
        entityColumn = "Ricavo"
    )
    val job : JobRevenueWithJobDetails
)












