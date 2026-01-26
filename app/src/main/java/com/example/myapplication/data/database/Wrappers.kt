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
        entityColumn = "Cliente"
    )
    val jobs: List<Job>
)