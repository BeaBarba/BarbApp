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

data class HeaderBubble(
    @Embedded val bubble : Bubble,

    @Relation(
        parentColumn = "Venditore",
        entityColumn = "id"
    )
    val seller : Seller,
)

data class DeliveryWithBubbleDetails(
    @Embedded val delivery: Delivery,

    @Relation(
        entity = Bubble::class,
        parentColumn = "Bolla",
        entityColumn = "id"
    )
    val bubble : HeaderBubble
)

data class HeaderPurchaseInvoice(
    @Embedded val purchaseInvoice: PurchaseInvoice,

    @Relation(
        parentColumn = "Venditore",
        entityColumn = "id"
    )
    val seller : Seller,
)

data class PurchaseWithPurchaseInvoiceDetails(
    @Embedded val purchase: Purchase,

    @Relation(
        entity = PurchaseInvoice::class,
        parentColumn = "Fattura",
        entityColumn = "id"
    )
    val purchaseInvoice : HeaderPurchaseInvoice
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

data class MaterialWithOrigin(
    @Embedded val material : Material,

    @Relation(
        entity = PurchaseInvoice::class,
        parentColumn = "id",
        entityColumn = "Materiale"
    )
    val purchase: Purchase?,

    @Relation(
        parentColumn = "id",
        entityColumn = "Materiale"
    )
    val delivery: Delivery?
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
        entity = WorkSite::class,
        parentColumn = "Cantiere",
        entityColumn = "id"
    )
    val workSite: WorkSite?,

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
    val photos : List<Image>,

    @Relation(
        parentColumn = "id",
        entityColumn = "Intervento"
    )
    val revenue: List<Revenue?>
)

data class WorkSiteAssignmentDetails(
    @Embedded val workSite : WorkSite,

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

    @Relation(
        parentColumn = "Responsabile",
        entityColumn = "id"
    )
    val manager : Reference?,
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
    val reference : Reference?,

    @Relation(
        entity = Customer::class,
        parentColumn = "Cliente",
        entityColumn = "CF"
    )
    val customer : CustomerTypeDetails?,

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
        parentColumn = "Venditore",
        entityColumn = "id"
    )
    val seller : Seller,

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
        entity = WorkSite::class,
        parentColumn = "Cantiere",
        entityColumn = "id"
    )
    val workSite : WorkSiteAssignmentDetails?,

    @Relation(
        entity = Job::class,
        parentColumn = "Intervento",
        entityColumn = "id"
    )
    val job : JobAssignmentDetails?
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

data class SingleExpenseFullDetails(
    @Embedded val singleExpense: SingleExpense,

    @Relation(
        parentColumn = "Categoria",
        entityColumn = "id"
    )
    val category : CategoryPurchaseInvoice,

    @Relation(
        entity = PurchaseInvoice::class,
        parentColumn = "Fattura",
        entityColumn = "id"
    )
    val purchaseInvoice: HeaderPurchaseInvoice?,

    @Relation(
        parentColumn = "Pagamento",
        entityColumn = "id"
    )
    val payment: Payment?
)

data class RecurringPaymentDetails(
    @Embedded val recurringPayment: RecurringPayment,

    @Relation(
        parentColumn = "Pagamento",
        entityColumn = "id"
    )
    val payment: Payment
)

data class RecurringExpenseFullDetails(
    @Embedded val recurringExpense: RecurringExpense,

    @Relation(
        parentColumn = "Categoria",
        entityColumn = "id"
    )
    val category : CategoryPurchaseInvoice,

    @Relation(
        entity = PurchaseInvoice::class,
        parentColumn = "Fattura",
        entityColumn = "id"
    )
    val purchaseInvoice: HeaderPurchaseInvoice?,

    @Relation(
        entity = RecurringPayment::class,
        parentColumn = "id",
        entityColumn = "Spesa"
    )
    val payments: List<RecurringPaymentDetails>
)