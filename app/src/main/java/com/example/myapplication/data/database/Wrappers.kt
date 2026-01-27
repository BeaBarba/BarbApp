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
    val phoneNumber: PhoneNumber,

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

data class DeliveryWithMaterialDetails(
    @Embedded val delivery: Delivery,

    @Relation(
        parentColumn = "Materiale",
        entityColumn = "id"
    )
    val material : Material
)