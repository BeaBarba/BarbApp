package com.example.myapplication.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.data.modules.MachineType
import java.time.LocalDate

@Entity(
    tableName = "CONDIZIONATORI",
    primaryKeys = ["Matricola", "Materiale"],
    foreignKeys = [
        ForeignKey(
            entity = Material::class,
            parentColumns = ["id"],
            childColumns = ["Materiale"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class AirConditioners(
    @PrimaryKey
    @ColumnInfo(name = "Matericola") val serialNumber : String,
    @ColumnInfo(name = "Materiale") val material : Int,
    @ColumnInfo(name = "TipoMacchina") val machineType : MachineType,
    @ColumnInfo(name = "QuantitàGas") val gasQty : Float,
    @ColumnInfo(name = "TipoGas") val gasType : String,
    @ColumnInfo(name = "Btu") val btu : Int,
    @ColumnInfo(name = "NumeroSplit") val splitNumber : Int? = null,
    @ColumnInfo(name = "AnnoInstallazione") val yearInstallation : Int? = null
)

@Entity(
    tableName = "MATERIALI",
    primaryKeys = ["Modello", "Marca"]
)
data class Material(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @ColumnInfo(name = "Modello") val model : String,
    @ColumnInfo(name = "Marca") val brand : String,
    @ColumnInfo(name = "Tipologia") val type : JobType,
    @ColumnInfo(name = "Categoria") val category : String,
    @ColumnInfo(name = "Disponibilità") val availability : Float,
)

@Entity(
    tableName = "INDIRIZZI",
    indices = [Index(value = ["Via", "Civico", "Citta"], unique = true)]
)
data class Address(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "Via") val address: String = "",
    @ColumnInfo(name = "Civico") val houseNumber: String = "",
    @ColumnInfo(name = "Comune") val municipality: String = "",
    @ColumnInfo(name = "Citta") val city: String = "",
    @ColumnInfo(name = "Provincia") val province: String = "",
    @ColumnInfo(name = "CAP") val zip: String = "",
    @ColumnInfo(name = "Foglio") val sheet: String? = null,
    @ColumnInfo(name = "Mappale") val map: String? = null,
    @ColumnInfo(name = "Subalterno") val subordinate: String? = null,
    @ColumnInfo(name = "Scala") val staircase: String? = null,
    @ColumnInfo(name = "Piano") val floor: String? = null,
    @ColumnInfo(name = "Interno") val interior: String? = null,
    @ColumnInfo(name = "AnnoCostruzione") val yearOfConstruction: String? = null,
    @ColumnInfo(name = "SuperficieUtile") val usableArea: String? = null,
    @ColumnInfo(name = "UnitaImmobiliari") val units: String? = null
)

@Entity(
    tableName = "VENDITORI"
)
data class Seller(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "Nome") val name : String = ""
)

@Entity(
    tableName = "FATTURE_ACQUISTO",
    indices = [Index(value = ["NumeroIdentificativo", "Anno", "Venditore"], unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = Seller::class,
            parentColumns = ["id"],
            childColumns = ["Venditore"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class PurchaseInvoice(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "NumeroIdentificativo") val number : String = "",
    @ColumnInfo(name = "Anno") val year : LocalDate = LocalDate.now(),
    @ColumnInfo(name = "Venditore") val seller : Int,
)

@Entity(
    tableName = "BOLLE",
    indices = [Index(value = ["NumeroIdentificativo", "Venditore"], unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = Seller::class,
            parentColumns = ["id"],
            childColumns = ["Venditore"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = PurchaseInvoice::class,
            parentColumns = ["id"],
            childColumns = ["Fattura"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class Bubble(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "NumeroIdentificativo") val number : String = "",
    @ColumnInfo(name = "Data") val date : LocalDate = LocalDate.now(),
    @ColumnInfo(name = "Venditore") val seller : Int,
    @ColumnInfo(name = "Fattura") val purchaseInvoice : String? = null
)