package com.example.myapplication.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.myapplication.data.modules.FrequencyType
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.data.modules.MachineType
import com.example.myapplication.data.modules.SplitNumber
import java.time.LocalDate
import java.time.LocalTime

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
data class AirConditioner(
    @ColumnInfo(name = "Matricola") val serialNumber : String,
    @ColumnInfo(name = "Materiale") val material : Int,
    @ColumnInfo(name = "TipoMacchina") val machineType : MachineType,
    @ColumnInfo(name = "QuantitàGas") val gasQty : Float,
    @ColumnInfo(name = "TipoGas") val gasType : String,
    @ColumnInfo(name = "Btu") val btu : Int,
    @ColumnInfo(name = "NumeroSplit") val splitNumber : SplitNumber? = null,
    @ColumnInfo(name = "AnnoInstallazione") val yearInstallation : Int? = null
){
    init{
        if(machineType == MachineType.Esterna){
            require(splitNumber != null){
                "Il numero di split è obbligatorio per le macchine esterne!"
            }
        }
    }
}

@Entity(
    tableName = "MATERIALI",
    indices = [Index(value = ["Modello", "Marca"], unique = true)],
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
    tableName = "ACQUISTI",
    primaryKeys = ["Fattura", "Materiale"],
    foreignKeys = [
        ForeignKey(
            entity = PurchaseInvoice::class,
            parentColumns = ["id"],
            childColumns = ["Fattura"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = Material::class,
            parentColumns = ["id"],
            childColumns = ["Materiale"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class Purchase(
    @ColumnInfo(name = "Fattura") val purchaseInvoice : Int,
    @ColumnInfo(name = "Materiale") val material : Int,
    @ColumnInfo(name = "Quantità") val quantity : Float,
    @ColumnInfo(name = "PrezzoUnitario") val unitPrice : Float
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
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "NumeroIdentificativo") val number : String = "",
    @ColumnInfo(name = "Data") val date : LocalDate = LocalDate.now(),
    @ColumnInfo(name = "Venditore") val seller : Int,
    @ColumnInfo(name = "Fattura") val purchaseInvoice : Int? = null
)

@Entity(
    tableName = "RILASCI",
    primaryKeys = ["Bolla", "Materiale"],
    foreignKeys = [
        ForeignKey(
            entity = Bubble::class,
            parentColumns = ["id"],
            childColumns = ["Bolla"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = Material::class,
            parentColumns = ["id"],
            childColumns = ["Materiale"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class Delivery(
    @ColumnInfo(name = "Bolla") val bubble : Int,
    @ColumnInfo(name = "Materiale") val material : Int,
    @ColumnInfo(name = "Quantità") val quantity : Float,
    @ColumnInfo(name = "PrezzoUnitario") val unitPrice : Float
)

@Entity(
    tableName = "CATEGORIE"
)
data class CategoryPurchaseInvoice(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @ColumnInfo(name = "Nome") val name : String
)

@Entity(
    tableName = "SPESE_SINGOLE",
    foreignKeys = [
        ForeignKey(
            entity = CategoryPurchaseInvoice::class,
            parentColumns = ["id"],
            childColumns = ["Categoria"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = PurchaseInvoice::class,
            parentColumns = ["id"],
            childColumns = ["Fattura"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = Payment::class,
            parentColumns = ["id"],
            childColumns = ["Pagamento"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class SingleExpense(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @ColumnInfo(name = "Nome") val name : String,
    @ColumnInfo(name = "Importo") val amount : Float,
    @ColumnInfo(name = "DataScadenza") val deadlineDate : LocalDate,
    @ColumnInfo(name = "Categoria") val category : Int,
    @ColumnInfo(name = "Fattura") val purchaseInvoice : Int?,
    @ColumnInfo(name = "Pagamento") val payment : Int?
)

@Entity(
    tableName = "SPESE_PERIODICHE",
    foreignKeys = [
        ForeignKey(
            entity = CategoryPurchaseInvoice::class,
            parentColumns = ["id"],
            childColumns = ["Categoria"],
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
data class RecurringExpense(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @ColumnInfo(name = "Nome") val name : String,
    @ColumnInfo(name = "Frequenza") val frequency : FrequencyType,
    @ColumnInfo(name = "Importo") val amount : Float,
    @ColumnInfo(name = "Categoria") val category : Int,
    @ColumnInfo(name = "DataFine") val endDate : LocalDate?,
    @ColumnInfo(name = "Fattura") val purchaseInvoice: Int?
)

@Entity(
    tableName = "PAGAMENTI"
)
data class Payment(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @ColumnInfo(name = "DataEmissione") val issueDate : LocalDate,
    @ColumnInfo(name = "DataPagamento") val paymentDate : LocalDate?
)

@Entity(
    tableName = "SALDI",
    primaryKeys = ["Pagamento"],
    foreignKeys = [
        ForeignKey(
            entity = Payment::class,
            parentColumns = ["id"],
            childColumns = ["Pagamento"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = RecurringExpense::class,
            parentColumns = ["id"],
            childColumns = ["Spesa"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class RecurringPayment(
    @ColumnInfo(name = "Pagamento") val payment: Int,
    @ColumnInfo(name = "Spesa") val recurringExpense: Int
)

@Entity(
    tableName = "FOTO"
)
data class Image(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @ColumnInfo(name = "Percorso") val path : String
)

@Entity(
    tableName = "RAFFIGURAZIONI",
    primaryKeys = ["Foto"],
    foreignKeys = [
        ForeignKey(
            entity = Image::class,
            parentColumns = ["id"],
            childColumns = ["Foto"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = Material::class,
            parentColumns = ["id"],
            childColumns = ["Materiale"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class MaterialPhoto(
    @ColumnInfo(name = "Foto") val photo : Int,
    @ColumnInfo(name = "Materiale") val material : Int
)

@Entity(
    tableName = "DIMOSTRAZIONI",
    primaryKeys = ["Foto"],
    foreignKeys = [
        ForeignKey(
            entity = Image::class,
            parentColumns = ["id"],
            childColumns = ["Foto"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = Job::class,
            parentColumns = ["id"],
            childColumns = ["Intervento"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class JobPhoto(
    @ColumnInfo(name = "Foto") val photo : Int,
    @ColumnInfo(name = "Intervento") val job : Int
)

@Entity(
    tableName = "FORNITURE",
    primaryKeys = ["Materiale", "Cliente"],
    foreignKeys = [
        ForeignKey(
            entity = Material::class,
            parentColumns = ["id"],
            childColumns = ["Materiale"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = Customer::class,
            parentColumns = ["CF"],
            childColumns = ["Cliente"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class CustomerProvision(
    @ColumnInfo(name = "Materiale") val material : Int,
    @ColumnInfo(name = "Cliente") val customer : String,
    @ColumnInfo(name = "Quantità") val quantity : Float
)

@Entity(
    tableName = "CLIENTI",
    primaryKeys = ["CF"],
    foreignKeys = [
        ForeignKey(
            entity = Address::class,
            parentColumns = ["id"],
            childColumns = ["Residenza"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = Reference::class,
            parentColumns = ["id"],
            childColumns = ["Riferimento"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class Customer(
    @ColumnInfo(name = "CF") val cf : String,
    @ColumnInfo(name = "Nome") val name : String,
    @ColumnInfo(name = "Email") val mail : String,
    @ColumnInfo(name = "TempoMedioRiscossione") val avarageCollectionTime : Float,
    @ColumnInfo(name = "NumeroRiscossioni") val collectionCount : Int,
    @ColumnInfo(name = "Residenza") val residence : Int,
    @ColumnInfo(name = "Riferimento") val reference : Int? = null
)

@Entity(
    tableName = "PRIVATI",
    primaryKeys = ["CF"],
    foreignKeys = [
        ForeignKey(
            entity = Customer::class,
            parentColumns = ["CF"],
            childColumns = ["CF"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Private(
    @ColumnInfo(name = "CF") val cf : String,
    @ColumnInfo(name = "Cognome") val lastName : String,
    @ColumnInfo(name = "DataNascita") val dateBirth : LocalDate,
    @ColumnInfo(name = "LuogoNascita") val placeBirth : String
)

@Entity(
    tableName = "AZIENDE",
    primaryKeys = ["CodiceUnivoco"],
    indices = [Index(value = ["CF"], unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = Customer::class,
            parentColumns = ["CF"],
            childColumns = ["CF"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Company(
    @ColumnInfo(name = "CodiceUnivoco") val uniqueCode : String,
    @ColumnInfo(name = "RagioneSociale") val companyName : String,
    @ColumnInfo(name = "PartitaIVA") val vatNumber : String,
    @ColumnInfo(name = "CF") val cf : String
)

@Entity(
    tableName = "RIFERIMENTI"
)
data class Reference(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @ColumnInfo(name = "Nome") val name : String,
    @ColumnInfo(name = "Cognome") val lastName : String,
    @ColumnInfo(name = "Telefono") val phoneNumber : String
)

@Entity(
    tableName = "PRESENTA",
    primaryKeys = ["Presentato"],
    foreignKeys = [
        ForeignKey(
            entity = Customer::class,
            parentColumns = ["CF"],
            childColumns = ["Presentato"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = Customer::class,
            parentColumns = ["CF"],
            childColumns = ["Presentatore"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class Referral(
    @ColumnInfo(name = "Presentato") val presented : String,
    @ColumnInfo(name = "Presentatore") val referral : String
)

@Entity(
    tableName = "TELEFONI",
    primaryKeys = ["Numero"],
    foreignKeys = [
        ForeignKey(
            entity = Customer::class,
            parentColumns = ["CF"],
            childColumns = ["Cliente"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class PhoneNumber(
    @ColumnInfo(name = "Numero") val number : String,
    @ColumnInfo(name = "Testo") val text : String,
    @ColumnInfo(name = "Cliente") val customer: String
)

@Entity(
    tableName = "POSSESSI",
    primaryKeys = ["Cliente", "Indirizzo"],
    foreignKeys = [
        ForeignKey(
            entity = Customer::class,
            parentColumns = ["CF"],
            childColumns = ["Cliente"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = Address::class,
            parentColumns = ["id"],
            childColumns = ["Indirizzo"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class PropertyOwnership(
    @ColumnInfo(name = "Cliente") val customer : String,
    @ColumnInfo(name = "Indirizzo") val address : Int
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
    tableName = "CANTIERI",
    indices = [Index(value = ["DataInizio", "Indirizzo"], unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = Address::class,
            parentColumns = ["id"],
            childColumns = ["Indirizzo"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = Reference::class,
            parentColumns = ["id"],
            childColumns = ["Responsabile"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = Customer::class,
            parentColumns = ["CF"],
            childColumns = ["Cliente"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class WorkSite(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @ColumnInfo(name = "DataInizio") val startDate : LocalDate,
    @ColumnInfo(name = "Indirizzo") val address : Int,
    @ColumnInfo(name = "DataFine") val endDate : LocalDate? = null,
    @ColumnInfo(name = "Responsabile") val manager : Int?,
    @ColumnInfo(name = "Cliente") val customer: String
)

@Entity(
    tableName = "INTERVENTI",
    indices = [Index(value = ["Data", "OraInizio", "Indirizzo"], unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = Address::class,
            parentColumns = ["id"],
            childColumns = ["Indirizzo"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = Customer::class,
            parentColumns = ["CF"],
            childColumns = ["Cliente"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = WorkSite::class,
            parentColumns = ["id"],
            childColumns = ["Cantiere"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class Job(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @ColumnInfo(name = "Data") val date : LocalDate,
    @ColumnInfo(name = "OraInizio") val startTime : LocalTime,
    @ColumnInfo(name = "Descrizione") val description : String,
    @ColumnInfo(name = "NumeroPersone") val peopleNumber : Int,
    @ColumnInfo(name = "Indirizzo") val address : Int,
    @ColumnInfo(name = "OraFine") val endTime : LocalTime? = null,
    @ColumnInfo(name = "Elettrico") val electic : Boolean,
    @ColumnInfo(name = "Allarme") val alarm : Boolean,
    @ColumnInfo(name = "Condizionamento") val airConditioning: Boolean,
    @ColumnInfo(name = "Cliente") val customer: String? = null,
    @ColumnInfo(name = "Cantiere") val workSite : Int? = null
){
    init{
        require(electic == true || alarm == true || airConditioning == true){
            "Almeno un tipo di intervento deve essere selezionato!"
        }
    }
}

@Entity(
    tableName = "PRENOTAZIONI",
    primaryKeys = ["Materiale", "Intervento"],
    foreignKeys = [
        ForeignKey(
            entity = Material::class,
            parentColumns = ["id"],
            childColumns = ["Materiale"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = Job::class,
            parentColumns = ["id"],
            childColumns = ["Intervento"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class JobMaterial(
    @ColumnInfo(name = "Materiale") val material : Int,
    @ColumnInfo(name = "Intervento") val job : Int,
    @ColumnInfo(name = "Quantità") val quantity : Float
)

@Entity(
    tableName = "UTILIZZI",
    primaryKeys = ["Materiale", "Intervento"],
    foreignKeys = [
        ForeignKey(
            entity = Material::class,
            parentColumns = ["id"],
            childColumns = ["Materiale"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = Job::class,
            parentColumns = ["id"],
            childColumns = ["Intervento"],
            onDelete = ForeignKey.NO_ACTION
        ),
    ]
)
data class MaterialUsage(
    @ColumnInfo(name = "Materiale") val material : Int,
    @ColumnInfo(name = "Intervento") val job : Int,
    @ColumnInfo(name = "Quantità") val quantity : Float
)

@Entity(
    tableName = "RICAVI",
    indices = [Index(value = ["Fattura", "DataEmissione"], unique = true)]
)
data class Revenue(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @ColumnInfo(name = "Fattura") val invoice : Int,
    @ColumnInfo(name = "DataEmissione") val issueDate : LocalDate,
    @ColumnInfo(name = "Importo") val amount : Float,
    @ColumnInfo(name = "Percentuale") val percent : Int,
    @ColumnInfo(name = "DataRiscossione") val collectionDate : LocalDate? = null,
)

@Entity(
    tableName = "GENERA",
    primaryKeys = ["Ricavo"],
    foreignKeys = [
        ForeignKey(
            entity = Revenue::class,
            parentColumns = ["id"],
            childColumns = ["Ricavo"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = WorkSite::class,
            parentColumns = ["id"],
            childColumns = ["Cantiere"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class WorkSiteRevenue(
    @ColumnInfo(name = "Ricavo") val revenue : Int,
    @ColumnInfo(name = "Cantiere") val workSite : Int
)

@Entity(
    tableName = "PRODUCE",
    primaryKeys = ["Ricavo"],
    foreignKeys = [
        ForeignKey(
            entity = Revenue::class,
            parentColumns = ["id"],
            childColumns = ["Ricavo"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Job::class,
            parentColumns = ["id"],
            childColumns = ["Intervento"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class JobRevenue(
    @ColumnInfo(name = "Ricavo") val revenue : Int,
    @ColumnInfo(name = "Intervento") val job : Int
)