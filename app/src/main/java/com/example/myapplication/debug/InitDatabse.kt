package com.example.myapplication.debug

import androidx.compose.ui.util.fastForEach
import com.example.myapplication.data.database.Address
import com.example.myapplication.data.database.AirConditioner
import com.example.myapplication.data.database.Bubble
import com.example.myapplication.data.database.CategoryPurchaseInvoice
import com.example.myapplication.data.database.Company
import com.example.myapplication.data.database.Customer
import com.example.myapplication.data.database.CustomerProvision
import com.example.myapplication.data.database.Delivery
import com.example.myapplication.data.database.FutureJobMaterial
import com.example.myapplication.data.database.Image
import com.example.myapplication.data.database.Job
import com.example.myapplication.data.database.Material
import com.example.myapplication.data.database.MaterialUsage
import com.example.myapplication.data.database.Payment
import com.example.myapplication.data.database.PhoneNumber
import com.example.myapplication.data.database.Private
import com.example.myapplication.data.database.PropertyOwnership
import com.example.myapplication.data.database.Purchase
import com.example.myapplication.data.database.PurchaseInvoice
import com.example.myapplication.data.database.RecurringExpense
import com.example.myapplication.data.database.RecurringPayment
import com.example.myapplication.data.database.Reference
import com.example.myapplication.data.database.Referral
import com.example.myapplication.data.database.Revenue
import com.example.myapplication.data.database.Seller
import com.example.myapplication.data.database.SingleExpense
import com.example.myapplication.data.database.WorkSite
import com.example.myapplication.data.modules.FrequencyType
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.data.modules.MachineType
import com.example.myapplication.data.modules.SplitNumber
import com.example.myapplication.data.repository.Repository
import java.time.LocalDate
import java.time.LocalTime

object SeedDatabase {
    suspend fun seedMaterials(repository: Repository) {
        val materials : List<Material> = listOf(
            Material(
                id = 1,
                model = "Sensore PIR 12m",
                brand = "Ajax",
                type = JobType.valueOf("ALA"),
                category = "Rilevatori",
                availableQuantity =  25.0f,
                unitMeasurement = "PZ"
            ),
            Material(
                id = 2,
                model = "Cavo FROR 3x1.5",
                brand = "Baldassari",
                type = JobType.valueOf("ELE"),
                category = "Cavi",
                availableQuantity = 510.0f,
                unitMeasurement = "M"
            ),
            Material(
                id = 3,
                model ="D600",
                brand = "Daikin",
                type = JobType.valueOf("CDZ"),
                category = "Split",
                availableQuantity = 5.0f,
                unitMeasurement = "PZ"
            ),
            Material(
                id = 4,
                model = "Centrale Hub 2",
                brand = "Ajax",
                type = JobType.valueOf("ALA"),
                category = "Centraline",
                availableQuantity = 3.0f,
                unitMeasurement = "PZ"
            ),
            Material(
                id = 5,
                model = "Interruttore Matix",
                brand = "Bticino",
                type = JobType.valueOf("ELE"),
                category = "Frutti",
                availableQuantity = 20.0f,
                unitMeasurement = "PZ"
            ),
            Material(
                id = 6,
                model = "C500",
                brand = "Mitsubishi",
                type = JobType.valueOf("CDZ"),
                category = "Unità Esterne",
                availableQuantity = 2.0f,
                unitMeasurement = "PZ"
            ),
            Material(id = 7, model = "Hub 2 Plus", brand = "Ajax", type = JobType.valueOf("ALA"), category = "Centrali", availableQuantity = 10.0f, unitMeasurement = "PZ"),
            Material(id = 8, model = "MotionProtect", brand = "Ajax", type = JobType.valueOf("ALA"), category = "Sensori", availableQuantity = 45.0f, unitMeasurement = "PZ"),
            Material(id = 9, model = "DoorProtect", brand = "Ajax", type = JobType.valueOf("ALA"), category = "Contatti", availableQuantity = 60.0f, unitMeasurement = "PZ"),
            Material(id = 10, model = "FireProtect", brand = "Ajax", type = JobType.valueOf("ALA"), category = "Fumo", availableQuantity = 15.0f, unitMeasurement = "PZ"),
            Material(id = 11, model = "StreetSiren", brand = "Ajax", type = JobType.valueOf("ALA"), category = "Sirene", availableQuantity = 8.0f, unitMeasurement = "PZ"),
            Material(id = 12, model = "Cavo Allarme 4x0.22", brand = "FROR", type = JobType.valueOf("ALA"), category = "Cavi", availableQuantity = 1000.0f, unitMeasurement = "M"),
            Material(id = 13, model = "Tastiera LCD", brand = "Bentel", type = JobType.valueOf("ALA"), category = "Interfacce", availableQuantity = 5.0f, unitMeasurement = "PZ"),
            Material(id = 14, model = "Sensore Doppia Tech", brand = "Optex", type = JobType.valueOf("ALA"), category = "Esterno", availableQuantity = 12.0f, unitMeasurement = "PZ"),
            Material(id = 15, model = "Batteria 12V 7Ah", brand = "FIAMM", type = JobType.valueOf("ALA"), category = "Ricambi", availableQuantity = 20.0f, unitMeasurement = "PZ"),
            Material(id = 16, model = "Combinatore GSM", brand = "Bentel", type = JobType.valueOf("ALA"), category = "Moduli", availableQuantity = 13.0f, unitMeasurement = "PZ"),
            Material(id = 17, model = "Interruttore 16A", brand = "Bticino", type = JobType.valueOf("ELE"), category = "Frutti", availableQuantity = 200.0f, unitMeasurement = "PZ"),
            Material(id = 18, model = "Presa Schuko", brand = "Vimar", type = JobType.valueOf("ELE"), category = "Frutti", availableQuantity = 150.0f, unitMeasurement = "PZ"),
            Material(id = 19, model = "Magnetotermico 25A", brand = "ABB", type = JobType.valueOf("ELE"), category = "Quadri", availableQuantity = 32.0f, unitMeasurement = "PZ"),
            Material(id = 20, model = "Differenziale 0.03A", brand = "Gewiss", type = JobType.valueOf("ELE"), category = "Quadri", availableQuantity = 25.0f, unitMeasurement = "PZ"),
            Material(id = 21, model = "Cavo FS17 2.5mm", brand = "Prysmian", type = JobType.valueOf("ELE"), category = "Cavi", availableQuantity = 3000.0f, unitMeasurement = "M"),
            Material(id = 22, model = "Corrugato 25mm", brand = "Arnocanali", type = JobType.valueOf("ELE"), category = "Tubi", availableQuantity = 500.0f, unitMeasurement = "M"),
            Material(id = 23, model = "Scatola 503", brand = "Bticino", type = JobType.valueOf("ELE"), category = "Supporti", availableQuantity = 400.0f, unitMeasurement = "PZ"),
            Material(id = 24, model = "Placca 3 Posti", brand = "Vimar", type = JobType.valueOf("ELE"), category = "Finiture", availableQuantity = 300.0f, unitMeasurement = "PZ"),
            Material(id = 25, model = "Relè Passo-Passo", brand = "Finder", type = JobType.valueOf("ELE"), category = "Automazione", availableQuantity = 50.0f, unitMeasurement = "PZ"),
            Material(id = 26, model = "Trasformatore 24V", brand = "MeanWell", type = JobType.valueOf("ELE"), category = "Alimentatori", availableQuantity = 15.0f, unitMeasurement = "PZ"),
            Material(id = 27, model = "Perfera 12000", brand = "Daikin", type = JobType.valueOf("CDZ"), category = "Split", availableQuantity = 12.0f, unitMeasurement = "PZ"),
            Material(id = 28, model = "Kirigamine Zen", brand = "Mitsubishi", type = JobType.valueOf("CDZ"), category = "Split", availableQuantity = 8.0f, unitMeasurement = "PZ"),
            Material(id = 29, model = "Unità Esterna Mono", brand = "Samsung", type = JobType.valueOf("CDZ"), category = "Motori", availableQuantity = 5.0f, unitMeasurement = "PZ"),
            Material(id = 30, model = "Trial Split Esterna", brand = "Daikin", type = JobType.valueOf("CDZ"), category = "Motori", availableQuantity = 3.0f, unitMeasurement = "PZ"),
            Material(id = 31, model = "Canalina 60x45", brand = "Vortice", type = JobType.valueOf("CDZ"), category = "Accessori", availableQuantity = 100.0f, unitMeasurement = "M"),
            Material(id = 32, model = "Tubo Rame 1/4", brand = "Zeta", type = JobType.valueOf("CDZ"), category = "Tubi", availableQuantity = 250.0f, unitMeasurement = "M"),
            Material(id = 33, model = "Tubo Rame 3/8", brand = "Zeta", type = JobType.valueOf("CDZ"), category = "Tubi", availableQuantity = 200.0f, unitMeasurement = "M"),
            Material(id = 34, model = "Pompa Scarico Condensa", brand = "Sauermann", type = JobType.valueOf("CDZ"), category = "Accessori", availableQuantity = 20.0f, unitMeasurement = "PZ"),
            Material(id = 35, model = "Gas R32 5kg", brand = "Refrigerant", type = JobType.valueOf("CDZ"), category = "Gas", availableQuantity = 10.0f, unitMeasurement = "KG"),
            Material(id = 36, model = "Staffe Motore", brand = "Tecnosystemi", type = JobType.valueOf("CDZ"), category = "Supporti", availableQuantity = 40.0f, unitMeasurement = "PZ"),
            Material(id = 37, model = "Dual Split Esterna", brand = "LG", type = JobType.valueOf("CDZ"), category = "Motori", availableQuantity = 4.0f, unitMeasurement = "PZ"),
            Material(id = 38, model = "Sensore Allagamento", brand = "Ajax", type = JobType.valueOf("ALA"), category = "Sensori", availableQuantity = 10.0f, unitMeasurement = "PZ"),
            Material(id = 39, model = "Sirena Interna", brand = "Bentel", type = JobType.valueOf("ALA"), category = "Sirene", availableQuantity = 15.0f, unitMeasurement = "PZ"),
            Material(id = 40, model = "Quadro 24 Moduli", brand = "Gewiss", type = JobType.valueOf("ELE"), category = "Quadri", availableQuantity = 10.0f, unitMeasurement = "PZ"),
            Material(id = 41, model = "Lampada Emergenza", brand = "Beghelli", type = JobType.valueOf("ELE"), category = "Illuminazione", availableQuantity = 20.0f, unitMeasurement = "PZ"),
            Material(id = 42, model = "Faretto LED 10W", brand = "Philips", type = JobType.valueOf("ELE"), category = "Illuminazione", availableQuantity = 50.0f, unitMeasurement = "PZ"),
            Material(id = 43, model = "Termostato Wi-Fi", brand = "Bticino", type = JobType.valueOf("ELE"), category = "Domotica", availableQuantity = 15.0f, unitMeasurement = "PZ"),
            Material(id = 44, model = "Attuatore Tapparelle", brand = "Shelly", type = JobType.valueOf("ELE"), category = "Domotica", availableQuantity = 40.0f, unitMeasurement = "PZ"),
            Material(id = 45, model = "Pulsante Campanello", brand = "Vimar", type = JobType.valueOf("ELE"), category = "Frutti", availableQuantity = 25.0f, unitMeasurement = "PZ"),
            Material(id = 46, model = "Rivelatore Gas", brand = "Beghelli", type = JobType.valueOf("ALA"), category = "Sicurezza", availableQuantity = 8.0f, unitMeasurement = "PZ"),
            Material(id = 47, model = "Clima WindFree", brand = "Samsung", type = JobType.valueOf("CDZ"), category = "Split", availableQuantity = 6.0f, unitMeasurement = "PZ"),
            Material(id = 48, model = "Barriera Infrarosso", brand = "Optex", type = JobType.valueOf("ALA"), category = "Perimetrale", availableQuantity = 4.0f, unitMeasurement = "PZ"),
            Material(id = 49, model = "Alimentatore 12V 5A", brand = "Hearth", type = JobType.valueOf("ALA"), category = "Alimentatori", availableQuantity = 12.0f, unitMeasurement = "PZ"),
            Material(id = 50, model = "Cavo Dati Cat6", brand = "Belden", type = JobType.valueOf("ELE"), category = "Cavi", availableQuantity = 1500.0f, unitMeasurement = "M"),
            Material(id = 51, model = "Spina Industriale 16A", brand = "Scame", type = JobType.valueOf("ELE"), category = "Connettori", availableQuantity = 30.0f, unitMeasurement = "PZ"),
            Material(id = 52, model = "Presa Interbloccata", brand = "Scame", type = JobType.valueOf("ELE"), category = "Connettori", availableQuantity = 10.0f, unitMeasurement = "PZ"),
            Material(id = 53, model = "Condizionatore Portatile", brand = "Olimpia", type = JobType.valueOf("CDZ"), category = "Mobile", availableQuantity = 3.0f, unitMeasurement = "PZ"),
            Material(id = 54, model = "Telecomando Universale", brand = "Melchioni", type = JobType.valueOf("CDZ"), category = "Ricambi", availableQuantity = 50.0f, unitMeasurement = "PZ"),
            Material(id = 55, model = "Filtro Aria Clima", brand = "Daikin", type = JobType.valueOf("CDZ"), category = "Ricambi", availableQuantity = 100.0f, unitMeasurement = "PZ"),
            Material(id = 56, model = "Nastro Isolante", brand = "3M", type = JobType.valueOf("ELE"), category = "Consumabili", availableQuantity = 500.0f, unitMeasurement = "PZ"),
            Material(id = 58, model = "NM09PXOABB", brand = "Nimaco", type = JobType.valueOf("CDZ"), category = "Split", availableQuantity = 1.0f, unitMeasurement = "PZ"),
            Material(id = 59, model = "NM12PXOABB", brand = "Nimaco", type = JobType.valueOf("CDZ"), category = "Split", availableQuantity = 1.0f, unitMeasurement = "PZ"),
            Material(id = 61, model = "Plana", brand = "Vimar", type = JobType.valueOf("ELE"), category = "Interruttore", availableQuantity = 21.0f, unitMeasurement = "PZ"),
            Material(id = 62, model = "JSMKL568PP", brand = "Daikin", type = JobType.valueOf("CDZ"), category = "Split", availableQuantity = 2.0f, unitMeasurement = "PZ"),
            Material(id = 63, model = "JSMKL569OOP", brand = "Daikin", type = JobType.valueOf("CDZ"), category = "Split", availableQuantity = 4.0f, unitMeasurement = "PZ")
        )

        materials.forEach{  material ->
            repository.inventory.upsertMaterial(material)
        }

    }

    suspend fun seedAirConditioner(repository: Repository){
        val airConditioners = listOf(
            AirConditioner(
                serialNumber = "J03APPF20",
                material = 58,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 1.5f,
                gasType = "R32",
                btu = 9000,
                splitNumber = null,
                yearInstallation = null
            ),
            AirConditioner(
                serialNumber = "J03APPF20", // Nota: Seriali duplicati nel dataset originale
                material = 59,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 1.5f,
                gasType = "R32",
                btu = 9000,
                splitNumber = null,
                yearInstallation = null
            ),
            AirConditioner(
                serialNumber = "MAT-9001",
                material = 28,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 0.0f,
                gasType = "R32",
                btu = 1200,
                splitNumber = null,
                yearInstallation = 2023
            ),
            AirConditioner(
                serialNumber = "MAT-9002",
                material = 28,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 0.0f,
                gasType = "R32",
                btu = 12000,
                splitNumber = null,
                yearInstallation = 2023
            ),
            AirConditioner(
                serialNumber = "MAT-9003",
                material = 28,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 0.0f,
                gasType = "R32",
                btu = 12000,
                splitNumber = null,
                yearInstallation = 2023
            ),
            AirConditioner(
                serialNumber = "MAT-9004",
                material = 28,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 0.0f,
                gasType = "R32",
                btu = 12000,
                splitNumber = null,
                yearInstallation = 2023
            ),
            AirConditioner(
                serialNumber = "MAT-9005",
                material = 53,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 0.0f,
                gasType = "R32",
                btu = 9000,
                splitNumber = null,
                yearInstallation = 2022
            ),
            AirConditioner(
                serialNumber = "MAT-9006",
                material = 53,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 0.0f,
                gasType = "R32",
                btu = 9000,
                splitNumber = null,
                yearInstallation = 2022
            ),
            AirConditioner(
                serialNumber = "MAT-9007",
                material = 53,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 0.0f,
                gasType = "R32",
                btu = 9000,
                splitNumber = null,
                yearInstallation = 2022
            ),
            AirConditioner(
                serialNumber = "MAT-9008",
                material = 53,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 0.0f,
                gasType = "R32",
                btu = 9000,
                splitNumber = null,
                yearInstallation = 2022
            ),
            AirConditioner(
                serialNumber = "MAT-9009",
                material = 29,
                machineType = MachineType.valueOf("Esterna"),
                gasQty = 1.2f,
                gasType = "R32",
                btu = 12000,
                splitNumber =  SplitNumber.valueOf("Mono"),
                yearInstallation = 2023
            ),
            AirConditioner(
                serialNumber = "MAT-9010",
                material = 29,
                machineType = MachineType.valueOf("Esterna"),
                gasQty = 1.2f,
                gasType = "R32",
                btu = 12000,
                splitNumber = SplitNumber.valueOf("Mono"),
                yearInstallation = 2023
            ),
            AirConditioner(
                serialNumber = "MAT-9011",
                material = 62,
                machineType = MachineType.valueOf("Esterna"),
                gasQty = 3.0f,
                gasType = "R32",
                btu = 36000,
                splitNumber = SplitNumber.valueOf("Quadri"),
                yearInstallation = 2026
            ),
            AirConditioner(
                serialNumber = "MAT-9013",
                material = 37,
                machineType = MachineType.valueOf("Esterna"),
                gasQty = 1.8f,
                gasType = "R410A",
                btu = 18000,
                splitNumber = SplitNumber.valueOf("Dual"),
                yearInstallation = 2021
            ),
            AirConditioner(
                serialNumber = "MAT-9014",
                material = 37,
                machineType = MachineType.valueOf("Esterna"),
                gasQty = 1.8f,
                gasType = "R410A",
                btu = 18000,
                splitNumber = SplitNumber.valueOf("Dual"),
                yearInstallation = 2021
            ),
            AirConditioner(
                serialNumber = "MAT-9017",
                material = 28,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 0.0f,
                gasType = "R32",
                btu = 12000,
                splitNumber = null,
                yearInstallation = null
            ),
            AirConditioner(
                serialNumber = "MAT-9018",
                material = 28,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 0.0f,
                gasType = "R32",
                btu = 12000,
                splitNumber = null,
                yearInstallation = null
            ),
            AirConditioner(
                serialNumber = "MAT-9019",
                material = 53,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 0.0f,
                gasType = "R32",
                btu = 9000,
                splitNumber = null,
                yearInstallation = null
            ),
            AirConditioner(
                serialNumber = "MAT-9020",
                material = 53,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 0.0f,
                gasType = "R32",
                btu = 9000,
                splitNumber = null,
                yearInstallation = null
            ),
            AirConditioner(
                serialNumber = "MAT-9021",
                material = 29,
                machineType = MachineType.valueOf("Esterna"),
                gasQty = 1.1f,
                gasType = "R32",
                btu = 12000,
                splitNumber = SplitNumber.valueOf("Mono"),
                yearInstallation = 2022
            ),
            AirConditioner(
                serialNumber = "MAT-9022",
                material = 30,
                machineType = MachineType.valueOf("Esterna"),
                gasQty = 2.8f,
                gasType = "R32",
                btu = 28000,
                splitNumber = SplitNumber.valueOf("Trial"),
                yearInstallation = 2023
            ),
            AirConditioner(
                serialNumber = "MAT-9023",
                material = 37,
                machineType = MachineType.valueOf("Esterna"),
                gasQty = 1.5f,
                gasType = "R32",
                btu = 14000,
                splitNumber = SplitNumber.valueOf("Dual"),
                yearInstallation = 2022
            ),
            AirConditioner(
                serialNumber = "MAT-9025",
                material = 28,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 0.0f,
                gasType = "R32",
                btu = 12000,
                splitNumber = null,
                yearInstallation = 2023
            ),
            AirConditioner(
                serialNumber = "MAT-9026",
                material = 53,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 0.0f,
                gasType = "R32",
                btu = 9000,
                splitNumber = null,
                yearInstallation = 2023
            ),
            AirConditioner(
                serialNumber = "MAT-9027",
                material = 29,
                machineType = MachineType.valueOf("Esterna"),
                gasQty = 1.1f,
                gasType = "R32",
                btu = 12000,
                splitNumber = SplitNumber.valueOf("Mono"),
                yearInstallation = 2023
            ),
            AirConditioner(
                serialNumber = "MAT-9028",
                material = 30,
                machineType = MachineType.valueOf("Esterna"),
                gasQty = 2.5f,
                gasType = "R32",
                btu = 24000,
                splitNumber = SplitNumber.valueOf("Trial"),
                yearInstallation = 2023
            ),
            AirConditioner(
                serialNumber = "SN-11223344",
                material = 6,
                machineType = MachineType.valueOf("Esterna"),
                gasQty = 2.4f,
                gasType = "R32",
                btu = 24000,
                splitNumber = SplitNumber.valueOf("Trial"),
                yearInstallation = 2023
            ),
            AirConditioner(
                serialNumber = "SN-99887766",
                material = 3,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 0.8f,
                gasType = "R32",
                btu = 12000,
                splitNumber = null,
                yearInstallation = 2023
            ),
            AirConditioner(
                serialNumber = "SN-DAIKIN-001",
                material = 27,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 0.8f,
                gasType = "R32",
                btu = 12000,
                splitNumber = null,
                yearInstallation = 2023
            ),
            AirConditioner(
                serialNumber = "SN-DK-INT-101",
                material = 27,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 0.0f,
                gasType = "R32",
                btu = 12000,
                splitNumber = null,
                yearInstallation = 2023
            ),
            AirConditioner(
                serialNumber = "SN-DK-INT-102",
                material = 27,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 0.0f,
                gasType = "R32",
                btu = 12000,
                splitNumber = null,
                yearInstallation = 2023
            ),
            AirConditioner(
                serialNumber = "SN-DK-INT-103",
                material = 27,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 0.0f,
                gasType = "R32",
                btu = 12000,
                splitNumber = null,
                yearInstallation = null
            ),
            AirConditioner(
                serialNumber = "SN-DK-INT-104",
                material = 27,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 0.0f,
                gasType = "R32",
                btu = 12000,
                splitNumber = null,
                yearInstallation = 2022
            ),
            AirConditioner(
                serialNumber = "SN-MITS-002",
                material = 63,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 0.9f,
                gasType = "R32",
                btu = 9000,
                splitNumber = null,
                yearInstallation = 2026
            ),
            AirConditioner(
                serialNumber = "SN-MT-INT-201",
                material = 63,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 0.0f,
                gasType = "R32",
                btu = 9000,
                splitNumber = null,
                yearInstallation = 2026
            ),
            AirConditioner(
                serialNumber = "SN-MT-INT-202",
                material = 63,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 0.0f,
                gasType = "R32",
                btu = 9000,
                splitNumber = null,
                yearInstallation = 2026
            ),
            AirConditioner(
                serialNumber = "SN-MT-INT-203",
                material = 63,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 0.0f,
                gasType = "R32",
                btu = 9000,
                splitNumber = null,
                yearInstallation = 2026
            ),
            AirConditioner(
                serialNumber = "SN-OL-MOB-701",
                material = 47,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 0.5f,
                gasType = "R290",
                btu = 9000,
                splitNumber = null,
                yearInstallation = 2022
            ),
            AirConditioner(
                serialNumber = "SN-OL-MOB-702",
                material = 47,
                machineType = MachineType.valueOf("Interna"),
                gasQty = 0.5f,
                gasType = "R290",
                btu = 9000,
                splitNumber = null,
                yearInstallation = 2022
            )
        )

        airConditioners.forEach{ airConditioner ->
            repository.inventory.upsertAirConditioner(airConditioner)
        }
    }

    suspend fun seedSeller(repository : Repository){
        val sellers = listOf(
            Seller(
                id = 1,
                name = "Sonepar Italia"
            ),
            Seller(
                id = 2,
                name = "Comoli Ferrari"
            ),
            Seller(
                id = 3,
                name = "Daikin Air Conditioning"
            ),
            Seller(
                id = 4,
                name = "Mitsubishi Electric"
            ),
            Seller(
                id = 5,
                name = "Bticino Spa"
            ),
            Seller(
                id = 6,
                name = "Hearth srl"
            ),
            Seller(
                id = 7,
                name = "Tecnomat"
            ),
            Seller(
                id = 8,
                name = "Würth Italia"
            ),
            Seller(
                id = 9,
                name = "Leroy Merlin"
            ),
            Seller(
                id = 10,
                name = "Vimar SpA"
            ),
            Seller(
                id = 11,
                name = "Buffetti"
            ),
            Seller(
                id = 12,
                name = "U-Power"
            )
        )

        sellers.forEach{ seller ->
            repository.inventory.upsertSeller(seller)
        }
    }

    suspend fun seedPurchaseInvoice(repository : Repository){
        val purchaseInvoices = listOf(
            PurchaseInvoice(id = 1, number = "FAC-2023-001", year = LocalDate.of( 2023,1,1), seller = 1),
            PurchaseInvoice(id = 2, number = "FAC-2023-142", year = LocalDate.of(2023,2,3), seller = 1),
            PurchaseInvoice(id = 3, number = "FAC-2024-010", year = LocalDate.of(2024,4,5), seller = 1),
            PurchaseInvoice(id = 4, number = "FAC-2024-088", year = LocalDate.of(2024,6,7), seller = 1),
            PurchaseInvoice(id = 5, number = "FAC-2024-156", year = LocalDate.of(2024,8,9), seller = 1),
            PurchaseInvoice(id = 6, number = "CF-998/23", year = LocalDate.of(2023,10,11), seller = 2),
            PurchaseInvoice(id = 7, number = "CF-1502/23", year = LocalDate.of(2023,12,13), seller = 2),
            PurchaseInvoice(id = 8, number = "CF-004/24", year = LocalDate.of(2024,1,14), seller = 2),
            PurchaseInvoice(id = 9, number = "CF-210/24", year = LocalDate.of(2024,2,15), seller = 2),
            PurchaseInvoice(id = 10, number = "CF-305/24", year = LocalDate.of(2024,3,16), seller = 2),
            PurchaseInvoice(id = 11, number = "DK_2023_99", year = LocalDate.of(2023,4,17), seller = 3),
            PurchaseInvoice(id = 12, number = "DK_2023_450", year = LocalDate.of(2023,5,18), seller = 3),
            PurchaseInvoice(id = 13, number = "DK_2024_12", year = LocalDate.of(2024,6,19), seller = 3),
            PurchaseInvoice(id = 14, number = "DK_2024_45", year = LocalDate.of(2024,7,20), seller = 3),
            PurchaseInvoice(id = 15, number = "DK_2024_67", year = LocalDate.of(2024,8,21), seller = 3),
            PurchaseInvoice(id = 16, number = "ME-Q1-001", year = LocalDate.of(2023,9,22), seller = 4),
            PurchaseInvoice(id = 17, number = "ME-Q3-088", year = LocalDate.of(2023,10,23), seller = 4),
            PurchaseInvoice(id = 18, number = "ME-2024-001", year = LocalDate.of(2024,11,24), seller = 4),
            PurchaseInvoice(id = 19, number = "ME-2024-022", year = LocalDate.of(2024,12,25), seller = 4),
            PurchaseInvoice(id = 20, number = "ME-2024-055", year = LocalDate.of(2024,1,26), seller = 4),
            PurchaseInvoice(id = 21, number = "BT-000123", year = LocalDate.of(2023,2,27), seller = 5),
            PurchaseInvoice(id = 22, number = "BT-000567", year = LocalDate.of(2023,3,28), seller = 5),
            PurchaseInvoice(id = 23, number = "BT-100001", year = LocalDate.of(2024,4,29), seller = 5),
            PurchaseInvoice(id = 24, number = "BT-100450", year = LocalDate.of(2024,5,30), seller = 5),
            PurchaseInvoice(id = 25, number = "BT-100900", year = LocalDate.of(2024,6,1), seller = 5),
            PurchaseInvoice(id = 26, number = "H-23-01", year = LocalDate.of(2023,7,2), seller = 6),
            PurchaseInvoice(id = 27, number = "H-23-45", year = LocalDate.of(2023,8,3), seller = 6),
            PurchaseInvoice(id = 28, number = "H-24-02", year = LocalDate.of(2024,4,5), seller = 6),
            PurchaseInvoice(id = 29, number = "H-24-15", year = LocalDate.of(2024,4,5), seller = 6),
            PurchaseInvoice(id = 30, number = "H-24-33", year = LocalDate.of(2024,4,5), seller = 6),
            PurchaseInvoice(id = 31, number = "TMAT-0098", year = LocalDate.of(2023,3,2), seller = 7),
            PurchaseInvoice(id = 32, number = "TMAT-0542", year = LocalDate.of(2023,3,2), seller = 7),
            PurchaseInvoice(id = 33, number = "TMAT-1002", year = LocalDate.of(2024,3,2), seller = 7),
            PurchaseInvoice(id = 34, number = "TMAT-1560", year = LocalDate.of(2024,3,2), seller = 7),
            PurchaseInvoice(id = 35, number = "TMAT-1890", year = LocalDate.of(2024,3,2), seller = 7),
            PurchaseInvoice(id = 36, number = "WUR-7788", year = LocalDate.of(2023,3,2), seller = 8),
            PurchaseInvoice(id = 37, number = "WUR-9901", year = LocalDate.of(2023,3,2), seller = 8),
            PurchaseInvoice(id = 38, number = "WUR-1022", year = LocalDate.of(2024,5,15), seller = 8),
            PurchaseInvoice(id = 39, number = "WUR-2344", year = LocalDate.of(2024,5,15), seller = 8),
            PurchaseInvoice(id = 40, number = "WUR-4566", year = LocalDate.of(2024,5,15), seller = 8),
            PurchaseInvoice(id = 41, number = "LM-MI-01", year = LocalDate.of(2023,5,15), seller = 9),
            PurchaseInvoice(id = 42, number = "LM-MI-45", year = LocalDate.of(2023,8,10), seller = 9),
            PurchaseInvoice(id = 43, number = "LM-MI-102", year = LocalDate.of(2024,5,15), seller = 9),
            PurchaseInvoice(id = 44, number = "LM-MI-205", year = LocalDate.of(2024,5,15), seller = 9),
            PurchaseInvoice(id = 45, number = "LM-MI-310", year = LocalDate.of(2024,5,15), seller = 9),
            PurchaseInvoice(id = 46, number = "VIM-2023-A", year = LocalDate.of(2023,5,15), seller = 10),
            PurchaseInvoice(id = 47, number = "VIM-2023-K", year = LocalDate.of(2023,5,15), seller = 10),
            PurchaseInvoice(id = 48, number = "VIM-2024-B", year = LocalDate.of(2024,5,15), seller = 10),
            PurchaseInvoice(id = 49, number = "VIM-2024-M", year = LocalDate.of(2024,5,15), seller = 10),
            PurchaseInvoice(id = 50, number = "VIM-2024-Z", year = LocalDate.of(2024,5,15), seller = 10),
            PurchaseInvoice(id = 51, number = "0111115126483", year = LocalDate.of(2026,5,15), seller = 7),
            PurchaseInvoice(id = 52, number = "25/0001/fe", year = LocalDate.of(2026,5,15), seller = 7),
            PurchaseInvoice(id = 53, number = "CDM0245", year = LocalDate.of(2026,5,15), seller = 11),
            PurchaseInvoice(id = 54, number = "25/FE/0001", year = LocalDate.of(2026,5,15), seller = 12)
        )

        purchaseInvoices.forEach { purchaseInvoice ->
            repository.accounting.upsertPurchaseInvoice(purchaseInvoice)
        }
    }

    suspend fun seedPurchase(repository : Repository){
        val purchases = listOf(
            Purchase(purchaseInvoice = 1, material = 56, quantity = 10.0f, unitPrice = 1.50f, vatNumber = 22),
            Purchase(purchaseInvoice = 2, material = 19, quantity = 2.0f, unitPrice = 10.60f, vatNumber = 10),
            Purchase(purchaseInvoice = 6, material = 25, quantity = 2.0f, unitPrice = 14.00f, vatNumber = 22),
            Purchase(purchaseInvoice = 11, material = 35, quantity = 2.0f, unitPrice = 45.00f, vatNumber = 22),
            Purchase(purchaseInvoice = 16, material = 54, quantity = 1.0f, unitPrice = 45.00f, vatNumber = 22),
            Purchase(purchaseInvoice = 21, material = 24, quantity = 20.0f, unitPrice = 2.30f, vatNumber = 22),
            Purchase(purchaseInvoice = 31, material = 22, quantity = 100.0f, unitPrice = 0.55f, vatNumber = 22),
            Purchase(purchaseInvoice = 36, material = 36, quantity = 5.0f, unitPrice = 15.00f, vatNumber = 22),
            Purchase(purchaseInvoice = 41, material = 41, quantity = 10.0f, unitPrice = 22.50f, vatNumber = 22),
            Purchase(purchaseInvoice = 42, material = 42, quantity = 50.0f, unitPrice = 8.20f, vatNumber = 22),
            Purchase(purchaseInvoice = 46, material = 18, quantity = 50.0f, unitPrice = 2.45f, vatNumber = 22),
            Purchase(purchaseInvoice = 47, material = 45, quantity = 10.0f, unitPrice = 3.80f, vatNumber = 22),
            Purchase(purchaseInvoice = 51, material = 32, quantity = 50.0f, unitPrice = 20.00f, vatNumber = 10)
        )

        purchases.forEach { purchase ->
            repository.inventory.upsertPurchase(purchase)
        }
    }

    suspend fun seedBubble(repository : Repository){
        val bubbles = listOf(
            Bubble(id = 1, number = "DDT-SN-001", seller = 1, purchaseInvoice = 1, date = LocalDate.parse("2023-01-14")),
            Bubble(id = 2, number = "DDT-SN-002", seller = 1, purchaseInvoice = 2, date = LocalDate.parse("2023-05-19")),
            Bubble(id = 3, number = "DDT-SN-003", seller = 1, purchaseInvoice = 3, date = LocalDate.parse("2024-01-09")),
            Bubble(id = 4, number = "DDT-SN-004", seller = 1, purchaseInvoice = 4, date = LocalDate.parse("2024-02-14")),
            Bubble(id = 5, number = "DDT-SN-005", seller = 1, purchaseInvoice = 5, date = LocalDate.parse("2024-02-28")),
            Bubble(id = 6, number = "BOL-CF-23-01", seller = 2, purchaseInvoice = 6, date = LocalDate.parse("2023-02-09")),
            Bubble(id = 7, number = "BOL-CF-23-02", seller = 2, purchaseInvoice = 7, date = LocalDate.parse("2023-06-11")),
            Bubble(id = 8, number = "BOL-CF-24-01", seller = 2, purchaseInvoice = 8, date = LocalDate.parse("2024-01-04")),
            Bubble(id = 9, number = "BOL-CF-24-02", seller = 2, purchaseInvoice = 9, date = LocalDate.parse("2024-02-19")),
            Bubble(id = 10, number = "BOL-CF-24-03", seller = 2, purchaseInvoice = 10, date = LocalDate.parse("2024-03-09")),
            Bubble(id = 11, number = "DAI-LOG-11", seller = 3, purchaseInvoice = 11, date = LocalDate.parse("2023-02-28")),
            Bubble(id = 12, number = "DAI-LOG-12", seller = 3, purchaseInvoice = 12, date = LocalDate.parse("2023-07-20")),
            Bubble(id = 13, number = "DAI-LOG-13", seller = 3, purchaseInvoice = 13, date = LocalDate.parse("2024-01-19")),
            Bubble(id = 14, number = "DAI-LOG-14", seller = 3, purchaseInvoice = 14, date = LocalDate.parse("2024-02-27")),
            Bubble(id = 15, number = "DAI-LOG-15", seller = 3, purchaseInvoice = 15, date = LocalDate.parse("2024-03-14")),
            Bubble(id = 16, number = "MIT-001-X", seller = 4, purchaseInvoice = 16, date = LocalDate.parse("2023-01-28")),
            Bubble(id = 17, number = "MIT-088-X", seller = 4, purchaseInvoice = 17, date = LocalDate.parse("2023-09-14")),
            Bubble(id = 18, number = "MIT-2024-01", seller = 4, purchaseInvoice = 18, date = LocalDate.parse("2024-01-14")),
            Bubble(id = 19, number = "MIT-2024-22", seller = 4, purchaseInvoice = 19, date = LocalDate.parse("2024-02-09")),
            Bubble(id = 20, number = "MIT-2024-55", seller = 4, purchaseInvoice = 20, date = LocalDate.parse("2024-03-19")),
            Bubble(id = 21, number = "BT-DDT-100", seller = 5, purchaseInvoice = 21, date = LocalDate.parse("2023-04-09")),
            Bubble(id = 22, number = "BT-DDT-200", seller = 5, purchaseInvoice = 22, date = LocalDate.parse("2023-10-04")),
            Bubble(id = 23, number = "BT-DDT-300", seller = 5, purchaseInvoice = 23, date = LocalDate.parse("2024-01-11")),
            Bubble(id = 24, number = "BT-DDT-400", seller = 5, purchaseInvoice = 24, date = LocalDate.parse("2024-02-24")),
            Bubble(id = 25, number = "BT-DDT-500", seller = 5, purchaseInvoice = 25, date = LocalDate.parse("2024-03-21")),
            Bubble(id = 26, number = "H-DDT-01", seller = 6, purchaseInvoice = 26, date = LocalDate.parse("2023-02-27")),
            Bubble(id = 27, number = "H-DDT-02", seller = 6, purchaseInvoice = 27, date = LocalDate.parse("2023-11-19")),
            Bubble(id = 28, number = "H-DDT-03", seller = 6, purchaseInvoice = 28, date = LocalDate.parse("2024-01-07")),
            Bubble(id = 29, number = "H-DDT-04", seller = 6, purchaseInvoice = 29, date = LocalDate.parse("2024-02-13")),
            Bubble(id = 30, number = "H-DDT-05", seller = 6, purchaseInvoice = 30, date = LocalDate.parse("2024-03-17")),
            Bubble(id = 31, number = "TMAT-D-31", seller = 7, purchaseInvoice = 31, date = LocalDate.parse("2023-05-04")),
            Bubble(id = 32, number = "TMAT-D-32", seller = 7, purchaseInvoice = 32, date = LocalDate.parse("2023-12-09")),
            Bubble(id = 33, number = "TMAT-D-33", seller = 7, purchaseInvoice = 33, date = LocalDate.parse("2024-01-17")),
            Bubble(id = 34, number = "TMAT-D-34", seller = 7, purchaseInvoice = 34, date = LocalDate.parse("2024-02-04")),
            Bubble(id = 35, number = "TMAT-D-35", seller = 7, purchaseInvoice = 35, date = LocalDate.parse("2024-03-04")),
            Bubble(id = 36, number = "W-BOL-001", seller = 8, purchaseInvoice = 36, date = LocalDate.parse("2023-06-19")),
            Bubble(id = 37, number = "W-BOL-002", seller = 8, purchaseInvoice = 37, date = LocalDate.parse("2023-08-14")),
            Bubble(id = 38, number = "W-BOL-003", seller = 8, purchaseInvoice = 38, date = LocalDate.parse("2024-01-24")),
            Bubble(id = 39, number = "W-BOL-004", seller = 8, purchaseInvoice = 39, date = LocalDate.parse("2024-02-17")),
            Bubble(id = 40, number = "W-BOL-005", seller = 8, purchaseInvoice = 40, date = LocalDate.parse("2024-03-11")),
            Bubble(id = 41, number = "LM-MI-01", seller = 9, purchaseInvoice = 41, date = LocalDate.parse("2023-01-19")),
            Bubble(id = 42, number = "LM-MI-02", seller = 9, purchaseInvoice = 42, date = LocalDate.parse("2023-12-27")),
            Bubble(id = 43, number = "PEND-01", seller = 9, purchaseInvoice = 52, date = LocalDate.parse("2024-04-01")),
            Bubble(id = 44, number = "PEND-02", seller = 9, purchaseInvoice = null, date = LocalDate.parse("2024-04-02")),
            Bubble(id = 45, number = "M-9", seller = 9, purchaseInvoice = null, date = LocalDate.parse("2026-02-10")),
            Bubble(id = 46, number = "EMERG-1", seller = 9, purchaseInvoice = 45, date = LocalDate.parse("2026-02-10")),
            Bubble(id = 47, number = "VIM-101", seller = 10, purchaseInvoice = 46, date = LocalDate.parse("2023-03-14")),
            Bubble(id = 48, number = "VIM-102", seller = 10, purchaseInvoice = 47, date = LocalDate.parse("2023-11-29")),
            Bubble(id = 49, number = "V-X", seller = 10, purchaseInvoice = null, date = LocalDate.parse("2024-04-05")),
            Bubble(id = 50, number = "VIM-TEMP-1", seller = 10, purchaseInvoice = null, date = LocalDate.parse("2026-02-10")),
            Bubble(id = 51, number = "VIM-TEMP-2", seller = 10, purchaseInvoice = null, date = LocalDate.parse("2026-02-10")),
            Bubble(id = 52, number = "VIM-99", seller = 10, purchaseInvoice = null, date = LocalDate.parse("2024-01-15")),
            Bubble(id = 53, number = "VIM-URG", seller = 10, purchaseInvoice = null, date = LocalDate.parse("2026-02-10")),
            Bubble(id = 54, number = "VIM-LAST", seller = 10, purchaseInvoice = null, date = LocalDate.parse("2026-02-10")),
            Bubble(id = 55, number = "BBG-0001A", seller = 9, purchaseInvoice = null, date = LocalDate.parse("2026-02-12"))
        )

        bubbles.forEach { bubble ->
            repository.accounting.upsertBubble(bubble)
        }
    }

    suspend fun seedDelivery(repository : Repository){
        val deliveries = listOf(
            Delivery(bubble = 1, material = 1, quantity = 10.0f, unitPrice = 45.5f, vatNumber = 22),
            Delivery(bubble = 41, material = 1, quantity = 0.1f, unitPrice = 4500.0f, vatNumber = 22),
            Delivery(bubble = 4, material = 2, quantity = 100.0f, unitPrice = 1.1f, vatNumber = 22),
            Delivery(bubble = 55, material = 2, quantity = 10.0f, unitPrice = 5.5f, vatNumber = 22),
            Delivery(bubble = 11, material = 3, quantity = 2.0f, unitPrice = 450.0f, vatNumber = 22),
            Delivery(bubble = 21, material = 4, quantity = 2.0f, unitPrice = 210.0f, vatNumber = 22),
            Delivery(bubble = 2, material = 5, quantity = 50.0f, unitPrice = 3.2f, vatNumber = 22),
            Delivery(bubble = 16, material = 6, quantity = 1.0f, unitPrice = 1800.0f, vatNumber = 22),
            Delivery(bubble = 26, material = 7, quantity = 3.0f, unitPrice = 320.0f, vatNumber = 22),
            Delivery(bubble = 3, material = 8, quantity = 20.0f, unitPrice = 12.8f, vatNumber = 22),
            Delivery(bubble = 5, material = 9, quantity = 30.0f, unitPrice = 8.5f, vatNumber = 22),
            Delivery(bubble = 22, material = 11, quantity = 5.0f, unitPrice = 110.0f, vatNumber = 22),
            Delivery(bubble = 28, material = 12, quantity = 200.0f, unitPrice = 0.6f, vatNumber = 22),
            Delivery(bubble = 38, material = 15, quantity = 1.0f, unitPrice = 15.0f, vatNumber = 4),
            Delivery(bubble = 6, material = 17, quantity = 40.0f, unitPrice = 4.5f, vatNumber = 22),
            Delivery(bubble = 47, material = 18, quantity = 1.5f, unitPrice = 22.0f, vatNumber = 22),
            Delivery(bubble = 8, material = 19, quantity = 10.0f, unitPrice = 25.0f, vatNumber = 22),
            Delivery(bubble = 45, material = 20, quantity = 0.0f, unitPrice = 45.0f, vatNumber = 22),
            Delivery(bubble = 9, material = 21, quantity = 500.0f, unitPrice = 0.95f, vatNumber = 22),
            Delivery(bubble = 42, material = 21, quantity = 5000.0f, unitPrice = 0.35f, vatNumber = 22),
            Delivery(bubble = 32, material = 22, quantity = 100.0f, unitPrice = 0.9f, vatNumber = 22),
            Delivery(bubble = 7, material = 23, quantity = 150.0f, unitPrice = 0.8f, vatNumber = 22),
            Delivery(bubble = 31, material = 24, quantity = 100.0f, unitPrice = 1.2f, vatNumber = 22),
            Delivery(bubble = 10, material = 25, quantity = 20.0f, unitPrice = 15.0f, vatNumber = 22),
            Delivery(bubble = 33, material = 26, quantity = 10.0f, unitPrice = 75.0f, vatNumber = 22),
            Delivery(bubble = 12, material = 27, quantity = 4.0f, unitPrice = 600.0f, vatNumber = 22),
            Delivery(bubble = 17, material = 28, quantity = 3.0f, unitPrice = 850.0f, vatNumber = 22),
            Delivery(bubble = 13, material = 30, quantity = 1.0f, unitPrice = 1200.0f, vatNumber = 22),
            Delivery(bubble = 44, material = 30, quantity = 10.0f, unitPrice = 2500.0f, vatNumber = 22),
            Delivery(bubble = 19, material = 31, quantity = 25.0f, unitPrice = 4.2f, vatNumber = 22),
            Delivery(bubble = 14, material = 32, quantity = 50.0f, unitPrice = 6.5f, vatNumber = 22),
            Delivery(bubble = 15, material = 33, quantity = 50.0f, unitPrice = 9.0f, vatNumber = 22),
            Delivery(bubble = 40, material = 34, quantity = 0.25f, unitPrice = 150.0f, vatNumber = 22),
            Delivery(bubble = 20, material = 35, quantity = 10.0f, unitPrice = 18.0f, vatNumber = 22),
            Delivery(bubble = 36, material = 35, quantity = 0.5f, unitPrice = 120.0f, vatNumber = 22),
            Delivery(bubble = 50, material = 36, quantity = 12.5f, unitPrice = 15.0f, vatNumber = 22),
            Delivery(bubble = 18, material = 37, quantity = 2.0f, unitPrice = 950.0f, vatNumber = 22),
            Delivery(bubble = 29, material = 38, quantity = 12.0f, unitPrice = 35.0f, vatNumber = 22),
            Delivery(bubble = 23, material = 40, quantity = 5.0f, unitPrice = 85.0f, vatNumber = 22),
            Delivery(bubble = 48, material = 42, quantity = 0.5f, unitPrice = 50.0f, vatNumber = 22),
            Delivery(bubble = 24, material = 43, quantity = 8.0f, unitPrice = 130.0f, vatNumber = 22),
            Delivery(bubble = 30, material = 46, quantity = 5.0f, unitPrice = 45.0f, vatNumber = 22),
            Delivery(bubble = 39, material = 47, quantity = 1.0f, unitPrice = 0.0f, vatNumber = 22),
            Delivery(bubble = 27, material = 49, quantity = 6.0f, unitPrice = 55.0f, vatNumber = 22),
            Delivery(bubble = 25, material = 50, quantity = 200.0f, unitPrice = 0.45f, vatNumber = 22),
            Delivery(bubble = 34, material = 51, quantity = 10.0f, unitPrice = 15.0f, vatNumber = 22),
            Delivery(bubble = 35, material = 52, quantity = 5.0f, unitPrice = 85.0f, vatNumber = 22),
            Delivery(bubble = 49, material = 53, quantity = 1.0f, unitPrice = 350.0f, vatNumber = 4),
            Delivery(bubble = 43, material = 54, quantity = 1.0f, unitPrice = 1.5f, vatNumber = 22),
            Delivery(bubble = 46, material = 55, quantity = 100.0f, unitPrice = 12.5f, vatNumber = 10),
            Delivery(bubble = 37, material = 56, quantity = 1000.0f, unitPrice = 0.05f, vatNumber = 10)
        )

        deliveries.forEach{ delivery ->
            repository.inventory.upsertDelivery(delivery)
        }
    }

    suspend fun seedCategories(repository : Repository){
        val categories = listOf(
            CategoryPurchaseInvoice(
                id = 1,
                name = "Materiale Elettrico"
            ),
            CategoryPurchaseInvoice(
                id = 2,
                name = "Climatizzazione"
            ),
            CategoryPurchaseInvoice(
                id = 3,
                name = "Sistemi Allarme"
            ),
            CategoryPurchaseInvoice(
                id = 4,
                name = "Carburante"
            ),
            CategoryPurchaseInvoice(
                id = 5,
                name = "Affitto Sede"
            ),
            CategoryPurchaseInvoice(
                id = 6,
                name = "Utenze Luce/Gas"
            ),
            CategoryPurchaseInvoice(
                id = 7,
                name = "Manutenzione Furgoni"
            ),
            CategoryPurchaseInvoice(
                id = 8,
                name = "Cancelleria"
            ),
            CategoryPurchaseInvoice(
                id = 9,
                name = "Assicurazioni"
            ),
            CategoryPurchaseInvoice(
                id = 10,
                name = "Marketing"
            ),
            CategoryPurchaseInvoice(
                id = 11,
                name = "Abbigliamento"
            )
        )

        categories.forEach { category ->
            repository.accounting.upsertCategoryPurchaseInvoice(category)
        }
    }

    suspend fun seedPayment(repository : Repository){
        val payments = listOf(
            Payment(id = 1, issueDate = LocalDate.of(2023, 1, 20), paymentDate = null),
            Payment(id = 2, issueDate = LocalDate.of(2023, 2, 15), paymentDate = LocalDate.of(2023, 2, 15)),
            Payment(id = 3, issueDate = LocalDate.of(2023, 3, 10), paymentDate = LocalDate.of(2023, 3, 10)),
            Payment(id = 4, issueDate = LocalDate.of(2023, 5, 25), paymentDate = LocalDate.of(2023, 5, 25)),
            Payment(id = 5, issueDate = LocalDate.of(2024, 6, 15), paymentDate = null),
            Payment(id = 6, issueDate = LocalDate.of(2023, 8, 1), paymentDate = LocalDate.of(2023, 8, 1)),
            Payment(id = 7, issueDate = LocalDate.of(2023, 10, 12), paymentDate = null),
            Payment(id = 8, issueDate = LocalDate.of(2023, 12, 20), paymentDate = LocalDate.of(2023, 12, 20)),
            Payment(id = 9, issueDate = LocalDate.of(2024, 1, 15), paymentDate = null),
            Payment(id = 10, issueDate = LocalDate.of(2024, 1, 30), paymentDate = LocalDate.of(2024, 1, 30)),
            Payment(id = 11, issueDate = LocalDate.of(2024, 2, 10), paymentDate = null),
            Payment(id = 12, issueDate = LocalDate.of(2024, 2, 28), paymentDate = LocalDate.of(2024, 2, 28)),
            Payment(id = 13, issueDate = LocalDate.of(2024, 3, 15), paymentDate = null),
            Payment(id = 14, issueDate = LocalDate.of(2024, 3, 20), paymentDate = LocalDate.of(2024, 3, 20)),
            Payment(id = 15, issueDate = LocalDate.of(2024, 3, 28), paymentDate = null),
            Payment(id = 16, issueDate = LocalDate.of(2024, 4, 1), paymentDate = LocalDate.of(2024, 4, 1)),
            Payment(id = 17, issueDate = LocalDate.of(2024, 4, 5), paymentDate = null),
            Payment(id = 18, issueDate = LocalDate.of(2026, 2, 10), paymentDate = LocalDate.of(2026,2,10)),
            Payment(id = 19, issueDate = LocalDate.of(2024, 3, 1), paymentDate = null),
            Payment(id = 20, issueDate = LocalDate.of(2024, 4, 10), paymentDate = LocalDate.of(2024,1,20)),
            Payment(id = 21, issueDate = LocalDate.of(2023, 1, 1), paymentDate = null),
            Payment(id = 22, issueDate = LocalDate.of(2024, 5, 15), paymentDate = LocalDate.of(2024, 5, 15)),
            Payment(id = 23, issueDate = LocalDate.of(2026, 2, 10), paymentDate = null),
            Payment(id = 24, issueDate = LocalDate.of(2022, 12, 31), paymentDate = LocalDate.of(2023, 1, 2)),
            Payment(id = 25, issueDate = LocalDate.of(2024, 4, 12), paymentDate = null),
            Payment(id = 26, issueDate = LocalDate.of(2024, 2, 28), paymentDate = LocalDate.of(2024, 2, 28)),
            Payment(id = 27, issueDate = LocalDate.of(2026, 12, 30), paymentDate = null),
            Payment(id = 28, issueDate = LocalDate.of(2026, 2, 9), paymentDate = LocalDate.of(2026, 2, 9)),
            Payment(id = 29, issueDate = LocalDate.of(2026, 3, 1), paymentDate = null),
            Payment(id = 30, issueDate = LocalDate.of(2025, 2, 28), paymentDate = LocalDate.of(2025, 2, 28)),
            Payment(id = 31, issueDate = LocalDate.of(2024, 3, 20), paymentDate = null),
            Payment(id = 32, issueDate = LocalDate.of(2024, 3, 25), paymentDate = LocalDate.of(2024, 3, 25)),
            Payment(id = 33, issueDate = LocalDate.of(2024, 3, 28), paymentDate = null),
            Payment(id = 34, issueDate = LocalDate.of(2024, 3, 31), paymentDate = LocalDate.of(2024, 3, 31)),
            Payment(id = 35, issueDate = LocalDate.of(2024, 4, 1), paymentDate = null),
            Payment(id = 36, issueDate = LocalDate.of(2024, 3, 30), paymentDate = LocalDate.of(2024, 3, 30)),
            Payment(id = 37, issueDate = LocalDate.of(2024, 4, 2), paymentDate = null),
            Payment(id = 38, issueDate = LocalDate.of(2024, 4, 5), paymentDate = LocalDate.of(2024, 4, 5)),
            Payment(id = 39, issueDate = LocalDate.of(2024, 4, 5), paymentDate = null),
            Payment(id = 40, issueDate = LocalDate.of(2026, 2, 10), paymentDate = LocalDate.of(2026, 2, 10)),
            Payment(id = 41, issueDate = LocalDate.of(2022, 12, 31), paymentDate = null),
            Payment(id = 42, issueDate = LocalDate.of(2026, 2, 10), paymentDate = LocalDate.of(2026, 2, 10)),
            Payment(id = 43, issueDate = LocalDate.of(2025, 1, 1), paymentDate = null),
            Payment(id = 44, issueDate = LocalDate.of(2024, 5, 15), paymentDate = LocalDate.of(2024, 5, 15)),
            Payment(id = 45, issueDate = LocalDate.of(2024, 4, 12), paymentDate = null),
            Payment(id = 46, issueDate = LocalDate.of(2026, 2, 12), paymentDate = LocalDate.of(2026, 2, 12)),
            Payment(id = 47, issueDate = LocalDate.of(2026, 12, 30), paymentDate = null),
            Payment(id = 48, issueDate = LocalDate.of(2026, 2, 9), paymentDate = LocalDate.of(2026, 2, 9)),
            Payment(id = 49, issueDate = LocalDate.of(2026, 3, 1), paymentDate = null),
            Payment(id = 50, issueDate = LocalDate.of(2026, 2, 28), paymentDate = LocalDate.of(2026, 2, 28)),
            Payment(id = 51, issueDate = LocalDate.of(2026, 2, 10), paymentDate = null),
            Payment(id = 52, issueDate = LocalDate.of(2026, 2, 10), paymentDate = LocalDate.of(2026, 2, 10)),
            Payment(id = 53, issueDate = LocalDate.of(2026, 2, 28), paymentDate = null),
            Payment(id = 54, issueDate = LocalDate.of(2026, 3, 31), paymentDate = LocalDate.of(2026, 3, 31)),
            Payment(id = 55, issueDate = LocalDate.of(2023, 1, 1), paymentDate = LocalDate.of(2023, 1, 1)),
            Payment(id = 56, issueDate = LocalDate.of(2023, 2, 1), paymentDate = LocalDate.of(2023, 2, 1)),
            Payment(id = 57, issueDate = LocalDate.of(2023, 3, 1), paymentDate = null),
            Payment(id = 58, issueDate = LocalDate.of(2026, 1, 10), paymentDate = LocalDate.of(2026, 1, 15)),
            Payment(id = 59, issueDate = LocalDate.of(2025, 6, 1), paymentDate = LocalDate.of(2025, 6, 1)),
            Payment(id = 60, issueDate = LocalDate.of(2025, 2, 1), paymentDate = LocalDate.of(2025, 2, 2)),
            Payment(id = 61, issueDate = LocalDate.of(2026, 4, 1), paymentDate = null),
            Payment(id = 62, issueDate = LocalDate.of(2026, 4, 5), paymentDate = LocalDate.of(2026, 4, 5)),
            Payment(id = 63, issueDate = LocalDate.of(2026, 3, 15), paymentDate = LocalDate.of(2026, 3, 20)),
            Payment(id = 64, issueDate = LocalDate.of(2026, 4, 1), paymentDate = LocalDate.of(2026, 4, 2)),
            Payment(id = 65, issueDate = LocalDate.of(2026, 4, 8), paymentDate = null),
            Payment(id = 66, issueDate = LocalDate.of(2026, 4, 1), paymentDate = LocalDate.of(2026, 4, 1)),
            Payment(id = 67, issueDate = LocalDate.of(2026, 4, 1), paymentDate = LocalDate.of(2026, 4, 1)),
            Payment(id = 68, issueDate = LocalDate.of(2026, 4, 7), paymentDate = LocalDate.of(2026, 4, 8)),
            Payment(id = 69, issueDate = LocalDate.of(2024, 7, 1), paymentDate = LocalDate.of(2024, 7, 5)),
            Payment(id = 70, issueDate = LocalDate.of(2024, 4, 15), paymentDate = LocalDate.of(2024, 4, 15)),
            Payment(id = 71, issueDate = LocalDate.of(2026, 4, 1), paymentDate = LocalDate.of(2026, 4, 1)),
            Payment(id = 72, issueDate = LocalDate.of(2026, 4, 1), paymentDate = LocalDate.of
                (2026, 4, 1)),
            Payment(id = 73, issueDate = LocalDate.of(2025, 10, 1), paymentDate = LocalDate.of(2025, 10, 5)),
            Payment(id = 74, issueDate = LocalDate.of(2024, 12, 20), paymentDate = LocalDate.of(2024, 12, 22)),
            Payment(id = 75, issueDate = LocalDate.of(2026, 1, 1), paymentDate = LocalDate.of(2026, 1, 10)),
            Payment(id = 76, issueDate = LocalDate.of(2025, 3, 1), paymentDate = null),
            Payment(id = 77, issueDate = LocalDate.of(2025, 9, 20), paymentDate = LocalDate.of(2025, 9, 25)),
            Payment(id = 78, issueDate = LocalDate.of(2025, 5, 15), paymentDate = LocalDate.of(2025, 5, 15)),
            Payment(id = 79, issueDate = LocalDate.of(2026, 4, 1), paymentDate = null),
            Payment(id = 80, issueDate = LocalDate.of(2026, 1, 15), paymentDate = LocalDate.of(2026, 1, 20)),
            Payment(id = 81, issueDate = LocalDate.of(2024, 1, 1), paymentDate = LocalDate.of(2024, 1, 5)),
            Payment(id = 82, issueDate = LocalDate.of(2026, 4, 1), paymentDate = LocalDate.of(2026, 4, 1)), // Backup
            Payment(id = 83, issueDate = LocalDate.of(2026, 4, 1), paymentDate = null), // Marketing
            Payment(id = 84, issueDate = LocalDate.of(2026, 4, 1), paymentDate = LocalDate.of(2026, 4, 2)), // Radio
            Payment(id = 85, issueDate = LocalDate.of(2026, 4, 1), paymentDate = LocalDate.of(2026, 4, 1)),
            Payment(id = 86, issueDate = LocalDate.of(2025, 11, 30), paymentDate = LocalDate.of(2025, 12, 5)),
            Payment(id = 87, issueDate = LocalDate.of(2026, 3, 10), paymentDate = LocalDate.of(2026, 3, 12)),
            Payment(id = 88, issueDate = LocalDate.of(2026, 3, 15), paymentDate = LocalDate.of(2026, 3, 15)),
            Payment(id = 89, issueDate = LocalDate.of(2026, 2, 1), paymentDate = LocalDate.of(2026, 2, 10)),
            Payment(id = 90, issueDate = LocalDate.of(2026, 3, 1), paymentDate = LocalDate.of(2026, 3, 20)),
            Payment(id = 91, issueDate = LocalDate.of(2025, 5, 20), paymentDate = LocalDate.of(2025, 5, 30)),
            Payment(id = 92, issueDate = LocalDate.of(2024, 1, 1), paymentDate = LocalDate.of(2024, 1, 1)),
            Payment(id = 93, issueDate = LocalDate.of(2026, 1, 10), paymentDate = LocalDate.of(2026, 1, 15)),
            Payment(id = 94, issueDate = LocalDate.of(2026, 4, 1), paymentDate = LocalDate.of(2026, 4, 1)),
            Payment(id = 95, issueDate = LocalDate.of(2026, 1, 1), paymentDate = LocalDate.of(2026, 1, 5)),
            Payment(id = 96, issueDate = LocalDate.of(2024, 5, 25), paymentDate = LocalDate.of(2024, 5, 30)),
            Payment(id = 97, issueDate = LocalDate.of(2024, 4, 20), paymentDate = LocalDate.of(2024, 4, 20)),
            Payment(id = 98, issueDate = LocalDate.of(2024, 12, 1), paymentDate = LocalDate.of(2024, 12, 1)),
            Payment(id = 99, issueDate = LocalDate.of(2024, 8, 25), paymentDate = LocalDate.of(2024, 8, 28)),
            Payment(id = 100, issueDate = LocalDate.of(2022, 12, 1), paymentDate = LocalDate.of(2022, 12, 5)),
            Payment(id = 101, issueDate = LocalDate.of(2024, 10, 1), paymentDate = LocalDate.of(2024, 10, 1)),
            Payment(id = 102, issueDate = LocalDate.of(2024, 5, 10), paymentDate = LocalDate.of(2024, 5, 12)),
            Payment(id = 103, issueDate = LocalDate.of(2024, 12, 1), paymentDate = LocalDate.of(2024, 12, 10)),
            Payment(id = 104, issueDate = LocalDate.of(2026, 1, 1), paymentDate = LocalDate.of(2026, 1, 2)),
            Payment(id = 105, issueDate = LocalDate.of(2026, 4, 1), paymentDate = null),
            Payment(id = 106, issueDate = LocalDate.of(2024, 12, 31), paymentDate = LocalDate.of(2025, 1, 5)),
            Payment(id = 107, issueDate = LocalDate.of(2025, 12, 31), paymentDate = null),
            Payment(id = 108, issueDate = LocalDate.of(2025, 1, 31), paymentDate = LocalDate.of(2025, 2, 2)),
            Payment(id = 109, issueDate = LocalDate.of(2025, 2, 28), paymentDate = LocalDate.of(2025, 3, 1)),
            Payment(id = 110, issueDate = LocalDate.of(2026, 1, 31), paymentDate = LocalDate.of(2026, 2, 1)),
            Payment(id = 111, issueDate = LocalDate.of(2026, 2, 28), paymentDate = LocalDate.of(2026, 3, 4)),
            Payment(id = 112, issueDate = LocalDate.of(2026, 3, 31), paymentDate = null),
            Payment(id = 113, issueDate = LocalDate.of(2026, 4, 30), paymentDate = null),
            Payment(id = 114, issueDate = LocalDate.of(2026, 5, 31), paymentDate = null),
            Payment(id = 115, issueDate = LocalDate.of(2026, 1, 31), paymentDate = LocalDate.of(2026, 2, 2)),
            Payment(id = 116, issueDate = LocalDate.of(2026, 2, 28), paymentDate = null),
            Payment(id = 117, issueDate = LocalDate.of(2026, 3, 31), paymentDate = LocalDate.of(2026, 4, 1)),
            Payment(id = 118, issueDate = LocalDate.of(2026, 4, 30), paymentDate = null),
            Payment(id = 119, issueDate = LocalDate.of(2026, 5, 31), paymentDate = null),
            Payment(id = 120, issueDate = LocalDate.of(2026, 6, 30), paymentDate = null),
            Payment(id = 121, issueDate = LocalDate.of(2026, 7, 31), paymentDate = null),
            Payment(id = 122, issueDate = LocalDate.of(2026, 8, 31), paymentDate = null),
            Payment(id = 123, issueDate = LocalDate.of(2026, 9, 30), paymentDate = null),
            Payment(id = 124, issueDate = LocalDate.of(2026, 10, 31), paymentDate = null),
            Payment(id = 125, issueDate = LocalDate.of(2026, 11, 30), paymentDate = null),
            Payment(id = 126, issueDate = LocalDate.of(2026, 12, 31), paymentDate = null),
            Payment(id = 127, issueDate = LocalDate.of(2027, 1, 1), paymentDate = null),
            Payment(id = 128, issueDate = LocalDate.of(2025, 5, 20), paymentDate = LocalDate.of(2025, 5, 25)),
            Payment(id = 129, issueDate = LocalDate.of(2026, 5, 20), paymentDate = null),
            Payment(id = 130, issueDate = LocalDate.of(2026, 3, 31), paymentDate = LocalDate.of(2026, 4, 1)),
            Payment(id = 131, issueDate = LocalDate.of(2026, 4, 30), paymentDate = null),
            Payment(id = 132, issueDate = LocalDate.of(2026, 5, 31), paymentDate = null),
            Payment(id = 133, issueDate = LocalDate.of(2026, 6, 30), paymentDate = null),
        )

        payments.forEach { payment ->
            repository.accounting.upsertPayment(payment)
        }
    }

    suspend fun seedSingleExpense(repository : Repository){
        val expenses = listOf(
            SingleExpense(id = 1, name = "Rifornimento Furgone EF123GH", amount = 85.5f,
                deadlineDate = LocalDate.of(2023, 1, 20), category = 4, purchaseInvoice = 1, payment = 1),
            SingleExpense(id = 2, name = "Cancelleria ufficio", amount = 45f, deadlineDate =
            LocalDate.of(2023, 2, 15), category = 8, purchaseInvoice = 6, payment = 2),
            SingleExpense(id = 3, name = "Riparazione trapano", amount = 120f, deadlineDate =
            LocalDate.of(2023, 3, 10), category = 7, purchaseInvoice = 11, payment = 3),
            SingleExpense(id = 4, name = "Aggiornamento Software ALA", amount = 250f,
                deadlineDate = LocalDate.of(2023, 5, 25), category = 10, purchaseInvoice = 16, payment = 4),
            SingleExpense(id = 5, name = "Materiale promozionale", amount = 300f, deadlineDate =
            LocalDate.of(2023, 8, 1), category = 10, purchaseInvoice = 21, payment = 5),
            SingleExpense(id = 6, name = "Cena aziendale Natale", amount = 450f, deadlineDate =
            LocalDate.of(2023, 12, 20), category = 10, purchaseInvoice = 26, payment = 6),
            SingleExpense(id = 7, name = "Cambio Gomme Invernali", amount = 600f, deadlineDate =
            LocalDate.of(2024, 1, 15), category = 7, purchaseInvoice = 31, payment = 7),
            SingleExpense(id = 8, name = "Rifornimento Metano", amount = 40f, deadlineDate =
            LocalDate.of(2024, 1, 30), category = 4, purchaseInvoice = 36, payment = 8),
            SingleExpense(id = 9, name = "Cartucce stampante", amount = 95f, deadlineDate =
            LocalDate.of(2024, 2, 10), category = 8, purchaseInvoice = 41, payment = 9),
            SingleExpense(id = 10, name = "Kit pronto soccorso", amount = 35f, deadlineDate =
            LocalDate.of(2024, 2, 28), category = 9, purchaseInvoice = 46, payment = 10),
            SingleExpense(id = 11, name = "Viti e tasselli sfusi", amount = 22.5f, deadlineDate =
            LocalDate.of(2024, 3, 15), category = 1, purchaseInvoice = 2, payment = 11),
            SingleExpense(id = 12, name = "Materiale pulizia sede", amount = 18f, deadlineDate =
            LocalDate.of(2024, 3, 20), category = 6, purchaseInvoice = 7, payment = 12),
            SingleExpense(id = 13, name = "Riparazione serratura", amount = 110f, deadlineDate =
            LocalDate.of(2024, 3, 28), category = 5, purchaseInvoice = 12, payment = 13),
            SingleExpense(id = 14, name = "Spese postali", amount = 7.5f, deadlineDate =
            LocalDate.of(2024, 4, 1), category = 8, purchaseInvoice = 17, payment = 14),
            SingleExpense(id = 15, name = "Lampadine ufficio", amount = 30f, deadlineDate =
            LocalDate.of(2024, 4, 5), category = 6, purchaseInvoice = 22, payment = 15),
            SingleExpense(id = 16, name = "Rifornimento Diesel", amount = 95f, deadlineDate =
            LocalDate.of(2023, 2, 15), category = 4, purchaseInvoice = 27, payment = 16),
            SingleExpense(id = 17, name = "Acquisto DPI (Guanti)", amount = 150f, deadlineDate =
            LocalDate.of(2023, 6, 12), category = 1, purchaseInvoice = 32, payment = 17),
            SingleExpense(id = 18, name = "Manutenzione climatizzatore ufficio", amount = 80f,
                deadlineDate = LocalDate.of(2023, 11, 20), category = 2, purchaseInvoice = 37,
                payment = 18),
            SingleExpense(id = 19, name = "Pubblicità Facebook", amount = 100f, deadlineDate =
            LocalDate.of(2024, 1, 15), category = 10, purchaseInvoice = 42, payment = 19),
            SingleExpense(id = 20, name = "Marche da bollo", amount = 32f, deadlineDate =
            LocalDate.of(2024, 2, 20), category = 8, purchaseInvoice = 47, payment = 20),
            SingleExpense(id = 21, name = "Parcheggio cantiere Roma", amount = 15f, deadlineDate
            = LocalDate.of(2023, 1, 20), category = 4, purchaseInvoice = null, payment = 21),
            SingleExpense(id = 22, name = "Caffè e acqua ufficio", amount = 25f, deadlineDate =
            LocalDate.of(2024, 3, 15), category = 8, purchaseInvoice = null, payment = 22),
            SingleExpense(id = 23, name = "Lavaggio furgone", amount = 20f, deadlineDate =
            LocalDate.of(2024, 4, 5), category = 7, purchaseInvoice = null, payment = 23),
            SingleExpense(id = 24, name = "Mancia corriere urgenza", amount = 5f, deadlineDate =
            LocalDate.of(2024, 1, 10), category = 10, purchaseInvoice = null, payment = 24),
            SingleExpense(id = 25, name = "Taxi fiera Milano", amount = 45f, deadlineDate =
            LocalDate.of(2023, 10, 12), category = 10, purchaseInvoice = null, payment = 25),
            SingleExpense(id = 26, name = "Software open source donation", amount = 10f,
                deadlineDate = LocalDate.of(2024, 3, 20), category = 10, purchaseInvoice = null,
                payment = 26),
            SingleExpense(id = 27, name = "Piccola viteria brico", amount = 12.4f, deadlineDate =
            LocalDate.of(2024, 3, 25), category = 1, purchaseInvoice = null, payment = 27),
            SingleExpense(id = 28, name = "Abbonamento parcheggio mensile", amount = 80f,
                deadlineDate = LocalDate.of(2023, 5, 25), category = 4, purchaseInvoice = null,
                payment = 28),
            SingleExpense(id = 29, name = "Pulizia straordinaria vetrate", amount = 200f,
                deadlineDate = LocalDate.of(2024, 2, 15), category = 6, purchaseInvoice = null,
                payment = 29),
            SingleExpense(id = 30, name = "Diritti di segreteria", amount = 16f, deadlineDate =
            LocalDate.of(2023, 3, 10), category = 8, purchaseInvoice = null, payment = 30),
            SingleExpense(id = 31, name = "Acquisto manuale tecnico", amount = 45f, deadlineDate
            = LocalDate.of(2023, 8, 5), category = 10, purchaseInvoice = null, payment = 31),
            SingleExpense(id = 32, name = "Sostituzione tergicristalli", amount = 35f,
                deadlineDate = LocalDate.of(2024, 1, 20), category = 7, purchaseInvoice = null,
                payment = 32),
            SingleExpense(id = 33, name = "Timbro aziendale nuovo", amount = 18f, deadlineDate =
            LocalDate.of(2024, 2, 28), category = 8, purchaseInvoice = null, payment = 33),
            SingleExpense(id = 34, name = "Ricarica telefonica aziendale", amount = 20f,
                deadlineDate = LocalDate.of(2024, 3, 30), category = 6, purchaseInvoice = null,
                payment = 34),
            SingleExpense(id = 35, name = "Spuntino rapido trasferta", amount = 12.5f,
                deadlineDate = LocalDate.of(2024, 4, 2), category = 10, purchaseInvoice = null,
                payment = 35),
            SingleExpense(id = 36, name = "Consulenza legale urgente", amount = 1500f,
                deadlineDate = LocalDate.of(2024, 5, 1), category = 9, purchaseInvoice = 3, payment =
             36),
            SingleExpense(id = 37, name = "Rifornimento extra", amount = 60f, deadlineDate =
            LocalDate.of(2024, 4, 10), category = 4, purchaseInvoice = 8, payment = 37),
            SingleExpense(id = 38, name = "Penale ritardo consegna", amount = 50f, deadlineDate =
            LocalDate.of(2024, 3, 1), category = 10, purchaseInvoice = null, payment = 38),
            SingleExpense(id = 39, name = "Commissioni bancarie", amount = 0.5f, deadlineDate =
            LocalDate.of(2024, 3, 31), category = 8, purchaseInvoice = null, payment = 39),
            SingleExpense(id = 40, name = "Acconto fiera 2025", amount = 2000f, deadlineDate =
            LocalDate.of(2025, 1, 1), category = 10, purchaseInvoice = 13, payment = 40),
            SingleExpense(id = 41, name = "Rimborso spese cliente X", amount = 30f, deadlineDate
            = LocalDate.of(2022, 12, 31), category = 10, purchaseInvoice = null, payment = 41),
            SingleExpense(id = 42, name = "Correzione errore contabile", amount = -10f,
                deadlineDate = LocalDate.of(2026, 2, 10), category = 10, purchaseInvoice = null,
                payment = 42),
            SingleExpense(id = 43, name = "Spesa d'emergenza guasto", amount = 850f, deadlineDate
             = LocalDate.of(2026, 2, 10), category = 7, purchaseInvoice = 18, payment = 43),
            SingleExpense(id = 44, name = "Software licenza perpetua", amount = 1200f,
                deadlineDate = LocalDate.of(2024, 4, 12), category = 10, purchaseInvoice = 23,
                payment = 44),
            SingleExpense(id = 45, name = "Bollo furgone", amount = 155f, deadlineDate =
            LocalDate.of(2024, 1, 1), category = 4, purchaseInvoice = null, payment = 45),
            SingleExpense(id = 46, name = "Sanzione stradale", amount = 168f, deadlineDate =
            LocalDate.of(2024, 5, 15), category = 4, purchaseInvoice = null, payment = 46),
            SingleExpense(id = 47, name = "Acquisto dominio web", amount = 15f, deadlineDate =
            LocalDate.of(2026, 2, 10), category = 10, purchaseInvoice = 28, payment = 47),
            SingleExpense(id = 48, name = "Campionario materiali", amount = 0f, deadlineDate =
            LocalDate.of(2024, 2, 1), category = 10, purchaseInvoice = 33, payment = 48),
            SingleExpense(id = 49, name = "Assicurazione integrativa", amount = 300f,
                deadlineDate = LocalDate.of(2024, 6, 1), category = 9, purchaseInvoice = 38,
                payment = 49),
            SingleExpense(id = 50, name = "Interessi passivi", amount = 4.25f, deadlineDate =
            LocalDate.of(2026, 2, 10), category = 8, purchaseInvoice = null, payment = 50),
            SingleExpense(id = 51, name = "Rifornimento materiale", amount = 20f, deadlineDate =
            LocalDate.of(2026, 2, 12), category = 2, purchaseInvoice = 51, payment = 51),
            SingleExpense(id = 52, name = "F", amount = 1000f, deadlineDate = LocalDate.of(2026,
                2, 12), category = 8, purchaseInvoice = 52, payment = 52),
            SingleExpense(id = 53, name = "Cancelleria", amount = 120.67f, deadlineDate =
            LocalDate.of(2026, 2, 28), category = 8, purchaseInvoice = 53, payment = 53),
            SingleExpense(id = 54, name = "Scarpe", amount = 80f, deadlineDate = LocalDate.of
                (2026, 3, 31), category = 11, purchaseInvoice = 54, payment = 54),
        )

        expenses.forEach { singleExpense ->
            repository.accounting.upsertSingleExpense(singleExpense)
        }
    }

    suspend fun seedRecurringExpense(repository: Repository){
        val recurringExpenses = listOf(
            RecurringExpense(id = 1, name = "Affitto Sede Milano", frequency = FrequencyType.valueOf("Mese"), amount = 1200f, category = 5, endDate = null, purchaseInvoice = 5),
            RecurringExpense(id = 2, name = "Utenze Luce", frequency = FrequencyType.valueOf("Mese"), amount = 150f, category = 6, endDate = null, purchaseInvoice = 10),
            RecurringExpense(id = 3, name = "Utenze Gas", frequency = FrequencyType.valueOf("Mese"), amount = 200f, category = 6, endDate = null, purchaseInvoice = 15),
            RecurringExpense(id = 4, name = "Assicurazione Furgone 1", frequency = FrequencyType
                .valueOf("Anno"), amount = 800f, category = 9, endDate = LocalDate.of(2025, 12, 31), purchaseInvoice = 20),
            RecurringExpense(id = 5, name = "Assicurazione Furgone 2", frequency = FrequencyType.valueOf("Anno"), amount = 800f, category = 9, endDate = LocalDate.of(2025, 10, 15), purchaseInvoice = 25),
            RecurringExpense(id = 6, name = "Abbonamento Gestionale", frequency = FrequencyType.valueOf("Mese"), amount = 45f, category = 10,
                endDate = null, purchaseInvoice = 30),
            RecurringExpense(id = 7, name = "Connessione Fibra Ottica", frequency = FrequencyType
                .valueOf("Mese"), amount = 35f, category = 6, endDate = null, purchaseInvoice = 35),
            RecurringExpense(id = 8, name = "Servizio Pulizie Ufficio", frequency = FrequencyType.valueOf("Settimana"), amount = 120f, category = 6, endDate = LocalDate.of(2024, 12, 31), purchaseInvoice = 40),
            RecurringExpense(id = 9, name = "Manutenzione Antincendio", frequency = FrequencyType.valueOf("Anno"), amount = 150f, category = 3, endDate = LocalDate.of(2026, 1, 1), purchaseInvoice = 45),
            RecurringExpense(id = 10, name = "Commercialista", frequency = FrequencyType.valueOf
                ("Mese"), amount = 250f, category = 9, endDate = null, purchaseInvoice = 4),
            RecurringExpense(id = 11, name = "Hosting Sito Web", frequency = FrequencyType
                .valueOf("Anno"), amount = 120f, category = 10, endDate = LocalDate.of(2025, 6, 1), purchaseInvoice = 9),
            RecurringExpense(id = 12, name = "Licenza Office 365", frequency = FrequencyType
                .valueOf("Mese"), amount = 12.5f, category = 10, endDate = null, purchaseInvoice = 14),
            RecurringExpense(id = 13, name = "Rate Leasing Trapani", frequency = FrequencyType.valueOf("Mese"), amount = 85f, category = 7, endDate = LocalDate.of(2025, 3, 1), purchaseInvoice = 19),
            RecurringExpense(id = 14, name = "Canone POS", frequency = FrequencyType.valueOf
                ("Mese"), amount = 15f, category = 8, endDate = null, purchaseInvoice = 24),
            RecurringExpense(id = 15, name = "Ricarica Estintori", frequency = FrequencyType.valueOf("Anno"), amount = 60f, category = 3, endDate = LocalDate.of(2025, 9, 20), purchaseInvoice = 29),
            RecurringExpense(id = 16, name = "Noleggio Fotocopiatrice", frequency = FrequencyType.valueOf("Mese"), amount = 55f, category = 8, endDate = LocalDate.of(2025, 5, 15), purchaseInvoice = 34),
            RecurringExpense(id = 17, name = "Fornitura Acqua Boccioni", frequency = FrequencyType.valueOf("Mese"), amount = 30f, category = 6, endDate = null, purchaseInvoice = 39),
            RecurringExpense(id = 18, name = "Quota Ordine Professionale", frequency = FrequencyType.valueOf("Anno"), amount = 180f, category = 9, endDate = null, purchaseInvoice = 44),
            RecurringExpense(id = 19, name = "Abbonamento Rivista Tecnica", frequency = FrequencyType.valueOf("Anno"), amount = 65f, category = 10, endDate = LocalDate.of(2024, 12, 31), purchaseInvoice = 49),
            RecurringExpense(id = 20, name = "Sanificazione Periodica", frequency = FrequencyType
                .valueOf("Mese"), amount = 200f, category = 6, endDate = LocalDate.of(2024, 8, 1), purchaseInvoice = 3),
            RecurringExpense(id = 21, name = "Backup Cloud Dati", frequency = FrequencyType.valueOf("Mese"), amount = 20f, category = 10, endDate = null, purchaseInvoice = 8),
            RecurringExpense(id = 22, name = "Manutenzione Sito Marketing", frequency = FrequencyType.valueOf("Mese"), amount = 100f, category = 10, endDate = null, purchaseInvoice = 13),
            RecurringExpense(id = 23, name = "Canone Radio Aziendale", frequency = FrequencyType.valueOf("Mese"), amount = 10f, category = 10, endDate = null, purchaseInvoice = 18),
            RecurringExpense(id = 24, name = "Leasing Furgone 1", frequency = FrequencyType.valueOf("Mese"), amount = 450f, category = 7,
                endDate = LocalDate.of(2026, 12, 31), purchaseInvoice = 23),
            RecurringExpense(id = 25, name = "Leasing Furgone 2", frequency = FrequencyType.valueOf("Mese"), amount = 450f, category = 7, endDate = LocalDate.of(2027, 1, 1), purchaseInvoice = 28),
            RecurringExpense(id = 26, name = "Controllo Caldaia Sede", frequency = FrequencyType.valueOf("Anno"), amount = 100f, category = 2, endDate = LocalDate.of(2025, 11, 30), purchaseInvoice = 33),
            RecurringExpense(id = 27, name = "Abbonamento GPS Furgoni", frequency = FrequencyType.valueOf("Mese"), amount = 30f, category = 7, endDate = null, purchaseInvoice = 38),
            RecurringExpense(id = 28, name = "Sms Marketing Service", frequency = FrequencyType.valueOf("Mese"), amount = 15f, category = 10, endDate = null, purchaseInvoice = 43),
            RecurringExpense(id = 29, name = "Consulenza Sicurezza Lavoro", frequency = FrequencyType.valueOf("Anno"), amount = 400f, category = 9, endDate = null, purchaseInvoice = 48),
            RecurringExpense(id = 30, name = "Rifiuti TARI", frequency = FrequencyType.valueOf("Anno"), amount = 300f, category = 6, endDate = null, purchaseInvoice = 2),
            RecurringExpense(id = 31, name = "Software Calcolo Termico", frequency =
            FrequencyType.valueOf("Anno"), amount = 350f, category = 10, endDate = LocalDate.of(2025, 2, 1), purchaseInvoice = 7),
            RecurringExpense(id = 32, name = "Rinnovo Certificazione ISO", frequency = FrequencyType.valueOf("Anno"), amount = 1200f, category = 9, endDate = LocalDate.of(2026, 5, 20), purchaseInvoice = 12),
            RecurringExpense(id = 33, name = "Servizio Vigilanza Notturna", frequency =
            FrequencyType.valueOf("Mese"), amount = 80f, category = 3, endDate = null, purchaseInvoice = 17),
            RecurringExpense(id = 34, name = "Abbonamento News Legali", frequency = FrequencyType.valueOf("Anno"), amount = 120f, category = 10, endDate = LocalDate.of(2025, 1, 1), purchaseInvoice = 22),
            RecurringExpense(id = 35, name = "Contributo Consorzio", frequency = FrequencyType.valueOf("Anno"), amount = 50f, category = 9, endDate = null, purchaseInvoice = 27),
            RecurringExpense(id = 36, name = "Canone Bancario", frequency = FrequencyType.valueOf("Mese"), amount = 5f, category = 8, endDate = null, purchaseInvoice = null),
            RecurringExpense(id = 37, name = "Assicurazione RC Professionale", frequency = FrequencyType.valueOf("Anno"), amount = 1500f, category = 9, endDate = null, purchaseInvoice = null),
            RecurringExpense(id = 38, name = "Donazione periodica ONLUS", frequency =
            FrequencyType.valueOf("Mese"), amount = 10f, category = 10, endDate = null, purchaseInvoice = null),
            RecurringExpense(id = 39, name = "Corso Formazione Apprendista", frequency = FrequencyType.valueOf("Settimana"), amount = 50f, category = 9, endDate = LocalDate.of(2024, 6, 1), purchaseInvoice = 32),
            RecurringExpense(id = 40, name = "Affitto Deposito Temporaneo", frequency = FrequencyType.valueOf("Settimana"), amount = 100f, category = 5, endDate = LocalDate.of(2024, 4, 30), purchaseInvoice = 37),
            RecurringExpense(id = 41, name = "Software CAD Beta Test", frequency = FrequencyType.valueOf("Mese"), amount = 0f, category = 10, endDate = LocalDate.of(2024, 12, 31), purchaseInvoice = null),
            RecurringExpense(id = 42, name = "Manutenzione Giardino Sede", frequency = FrequencyType.valueOf("Settimana"), amount = 80f, category = 6, endDate = LocalDate.of(2024, 9, 1), purchaseInvoice = 42),
            RecurringExpense(id = 43, name = "Leasing Vecchio Attrezzo", frequency = FrequencyType.valueOf("Mese"), amount = 40f, category = 7, endDate = LocalDate.of(2023, 1, 1), purchaseInvoice = 47),
            RecurringExpense(id = 44, name = "Rata recupero crediti", frequency = FrequencyType.valueOf("Mese"), amount = 200f, category = 8, endDate = LocalDate.of(2024, 10, 1), purchaseInvoice = null),
            RecurringExpense(id = 45, name = "Box Cloud Extra", frequency = FrequencyType.valueOf
                ("Mese"), amount = 2.99f, category = 10,  endDate = null, purchaseInvoice = null),
            RecurringExpense(id = 46, name = "Pubblicità Rivista Locale", frequency = FrequencyType.valueOf("Settimana"), amount = 150f, category = 10, endDate = LocalDate.of(2024, 5, 15), purchaseInvoice = 50),
            RecurringExpense(id = 47, name = "Assicurazione Infortuni", frequency = FrequencyType.valueOf("Anno"), amount = 250f, category = 9, endDate = LocalDate.of(2024, 12, 31), purchaseInvoice = null),
            RecurringExpense(id = 48, name = "Canone Software Domotica", frequency = FrequencyType.valueOf("Anno"), amount = 500f, category = 10, endDate = null, purchaseInvoice = 1),
            RecurringExpense(id = 49, name = "Noleggio Scala Aerea", frequency = FrequencyType
                .valueOf("Settimana"), amount = 300f, category = 7, endDate = LocalDate.of(2024,
                4, 20), purchaseInvoice = 11),
            RecurringExpense(id = 50, name = "Fondo TFR periodico", frequency = FrequencyType.valueOf("Mese"), amount = 500f, category = 9, endDate = null, purchaseInvoice = null),
            RecurringExpense(id = 51, name = "Prestito", frequency = FrequencyType.valueOf
                ("Mese"), amount = 22000f, category = 10, endDate = LocalDate.of(2029, 3, 1), purchaseInvoice = null)
        )

        recurringExpenses.forEach { recurringExpense ->
            repository.accounting.upsertRecurringExpense(recurringExpense)
        }
    }

    suspend fun seedRecurringPayment(repository : Repository){
        val recurringPayments = listOf(
            RecurringPayment(payment = 55, recurringExpense = 1),
            RecurringPayment(payment = 56, recurringExpense = 2),
            RecurringPayment(payment = 57, recurringExpense = 3),
            RecurringPayment(payment = 58, recurringExpense = 4),
            RecurringPayment(payment = 59, recurringExpense = 11),
            RecurringPayment(payment = 60, recurringExpense = 31),
            RecurringPayment(payment = 61, recurringExpense = 6),
            RecurringPayment(payment = 62, recurringExpense = 7),
            RecurringPayment(payment = 63, recurringExpense = 10),
            RecurringPayment(payment = 64, recurringExpense = 12),
            RecurringPayment(payment = 65, recurringExpense = 14),
            RecurringPayment(payment = 66, recurringExpense = 24),
            RecurringPayment(payment = 67, recurringExpense = 51),
            RecurringPayment(payment = 68, recurringExpense = 33),
            RecurringPayment(payment = 69, recurringExpense = 20),
            RecurringPayment(payment = 70, recurringExpense = 49),
            RecurringPayment(payment = 71, recurringExpense = 45),
            RecurringPayment(payment = 72, recurringExpense = 38),
            RecurringPayment(payment = 73, recurringExpense = 5),
            RecurringPayment(payment = 74, recurringExpense = 8),
            RecurringPayment(payment = 75, recurringExpense = 9),
            RecurringPayment(payment = 76, recurringExpense = 13),
            RecurringPayment(payment = 77, recurringExpense = 15),
            RecurringPayment(payment = 78, recurringExpense = 16),
            RecurringPayment(payment = 79, recurringExpense = 17),
            RecurringPayment(payment = 80, recurringExpense = 18),
            RecurringPayment(payment = 81, recurringExpense = 19),
            RecurringPayment(payment = 82, recurringExpense = 21),
            RecurringPayment(payment = 83, recurringExpense = 22),
            RecurringPayment(payment = 84, recurringExpense = 23),
            RecurringPayment(payment = 85, recurringExpense = 25),
            RecurringPayment(payment = 86, recurringExpense = 26),
            RecurringPayment(payment = 87, recurringExpense = 27),
            RecurringPayment(payment = 88, recurringExpense = 28),
            RecurringPayment(payment = 89, recurringExpense = 29),
            RecurringPayment(payment = 90, recurringExpense = 30),
            RecurringPayment(payment = 91, recurringExpense = 32),
            RecurringPayment(payment = 92, recurringExpense = 34),
            RecurringPayment(payment = 93, recurringExpense = 35),
            RecurringPayment(payment = 94, recurringExpense = 36),
            RecurringPayment(payment = 95, recurringExpense = 37),
            RecurringPayment(payment = 96, recurringExpense = 39),
            RecurringPayment(payment = 97, recurringExpense = 40),
            RecurringPayment(payment = 98, recurringExpense = 41),
            RecurringPayment(payment = 99, recurringExpense = 42),
            RecurringPayment(payment = 100, recurringExpense = 43),
            RecurringPayment(payment = 101, recurringExpense = 44),
            RecurringPayment(payment = 102, recurringExpense = 46),
            RecurringPayment(payment = 103, recurringExpense = 47),
            RecurringPayment(payment = 104, recurringExpense = 48),
            RecurringPayment(payment = 105, recurringExpense = 50),
            RecurringPayment(payment = 106, recurringExpense = 4),
            RecurringPayment(payment = 107, recurringExpense = 4),
            RecurringPayment(payment = 108, recurringExpense = 13),
            RecurringPayment(payment = 109, recurringExpense = 13),
            RecurringPayment(payment = 110, recurringExpense = 24),
            RecurringPayment(payment = 111, recurringExpense = 24),
            RecurringPayment(payment = 112, recurringExpense = 24),
            RecurringPayment(payment = 113, recurringExpense = 24),
            RecurringPayment(payment = 114, recurringExpense = 24),
            RecurringPayment(payment = 115, recurringExpense = 25),
            RecurringPayment(payment = 116, recurringExpense = 25),
            RecurringPayment(payment = 117, recurringExpense = 25),
            RecurringPayment(payment = 118, recurringExpense = 25),
            RecurringPayment(payment = 119, recurringExpense = 25),
            RecurringPayment(payment = 120, recurringExpense = 25),
            RecurringPayment(payment = 121, recurringExpense = 25),
            RecurringPayment(payment = 122, recurringExpense = 25),
            RecurringPayment(payment = 123, recurringExpense = 25),
            RecurringPayment(payment = 124, recurringExpense = 25),
            RecurringPayment(payment = 125, recurringExpense = 25),
            RecurringPayment(payment = 126, recurringExpense = 25),
            RecurringPayment(payment = 127, recurringExpense = 25),
            RecurringPayment(payment = 128, recurringExpense = 32),
            RecurringPayment(payment = 129, recurringExpense = 32),
            RecurringPayment(payment = 130, recurringExpense = 51),
            RecurringPayment(payment = 131, recurringExpense = 51),
            RecurringPayment(payment = 132, recurringExpense = 51),
            RecurringPayment(payment = 133, recurringExpense = 51),

        )

        recurringPayments.forEach { recurringPayment ->
            repository.accounting.upsertRecurringPayment(recurringPayment)
        }
    }

    suspend fun seedAddress(repository: Repository){
        val addresses = listOf(
            Address(id = 1, address = "Via Roma", houseNumber = "1", municipality = "Milano", city = "Milano", province = "MI", zip = "20100", sheet = "102", map = "450", subordinate = "7", staircase = "A", floor = "3", interior = "12", yearOfConstruction = 1980, usableArea = 120, units = "Appartamento"),
            Address(id = 2, address = "Via Dante", houseNumber = "15", municipality = "Monza", city = "Monza", province = "MB", zip = "20900", sheet = "12", map = "88", subordinate = "1", staircase = "B", floor = "2", interior = "5", yearOfConstruction = 2005, usableArea = 85, units = "Ufficio"),
            Address(id = 3, address = "Corso Italia", houseNumber = "102", municipality = "Torino", city = "Torino", province = "TO", zip = "10100", sheet = "45", map = "1204", subordinate = "501", staircase = "C", floor = "Terra", interior = "1", yearOfConstruction = 2015, usableArea = 200, units = "Negozio"),
            Address(id = 4, address = "Via Garibaldi", houseNumber = "5", municipality = "Rho", city = "Milano", province = "MI", zip = "20017", sheet = "5", map = "302", subordinate = "12", staircase = "Unica", floor = "4", interior = "16", yearOfConstruction = 1960, usableArea = 95, units = "Residenziale"),
            Address(id = 5, address = "Viale Monza", houseNumber = "200", municipality = "Milano", city = "Milano", province = "MI", zip = "20126", sheet = "150", map = "44", subordinate = "8", staircase = "D", floor = "5", interior = "20", yearOfConstruction = 1995, usableArea = 150, units = "Appartamento"),
            Address(id = 6, address = "Via Matteotti", houseNumber = "10", municipality = "Milano", city = "Milano", province = "MI", zip = "20100", sheet = "10", map = "120", subordinate = "1", staircase = null, floor = "T-1", interior = null, yearOfConstruction = 1990, usableArea = 250, units = "Villa Unifamiliare"),
            Address(id = 7, address = "Via Mazzini", houseNumber = "3", municipality = "Bergamo", city = "Bergamo", province = "BG", zip = "24100", sheet = "22", map = "560", subordinate = "2", staircase = null, floor = "T", interior = null, yearOfConstruction = 1970, usableArea = 180, units = "Capannone"),
            Address(id = 8, address = "Via Veneto", houseNumber = "55", municipality = "Roma", city = "Roma", province = "RM", zip = "187", sheet = "405", map = "12", subordinate = "99", staircase = null, floor = "S1", interior = null, yearOfConstruction = 1950, usableArea = 60, units = "Cantina"),
            Address(id = 9, address = "Via Emilia", houseNumber = "90", municipality = "Modena", city = "Modena", province = "MO", zip = "41100", sheet = "88", map = "33", subordinate = "4", staircase = null, floor = "2", interior = null, yearOfConstruction = 2022, usableArea = 110, units = "Appartamento"),
            Address(id = 10, address = "Via Cavour", houseNumber = "11", municipality = "Firenze", city = "Firenze", province = "FI", zip = "50100", sheet = "14", map = "777", subordinate = "1", staircase = null, floor = "1", interior = null, yearOfConstruction = 1900, usableArea = 300, units = "Palazzo Storico"),
            Address(id = 11, address = "Via Manzoni", houseNumber = "4", municipality = "Como", city = "Como", province = "CO", zip = "22100", sheet = null, map = null, subordinate = null, staircase = "A", floor = "2", interior = "4", yearOfConstruction = 1950, usableArea = 75, units = "Appartamento"),
            Address(id = 12, address = "Via Leopardi", houseNumber = "22", municipality = "Lecco", city = "Lecco", province = "LC", zip = "23900", sheet = null, map = null, subordinate = null, staircase = "C", floor = "3", interior = "9", yearOfConstruction = 1985, usableArea = 90, units = "Appartamento"),
            Address(id = 13, address = "Via Foscolo", houseNumber = "9", municipality = "Lodi", city = "Lodi", province = "LO", zip = "26900", sheet = null, map = null, subordinate = null, staircase = "1", floor = "4", interior = "12", yearOfConstruction = 1992, usableArea = 85, units = "Appartamento"),
            Address(id = 14, address = "Via Pascoli", houseNumber = "14", municipality = "Pavia", city = "Pavia", province = "PV", zip = "27100", sheet = null, map = null, subordinate = null, staircase = "Esterna", floor = "1", interior = "2", yearOfConstruction = 1988, usableArea = 70, units = "Ufficio"),
            Address(id = 15, address = "Via Carducci", houseNumber = "6", municipality = "Brescia", city = "Brescia", province = "BS", zip = "25100", sheet = null, map = null, subordinate = null, staircase = "B", floor = "6", interior = "22", yearOfConstruction = 2001, usableArea = 130, units = "Attico"),
            Address(id = 16, address = "Via Boccaccio", houseNumber = "33", municipality = "Milano", city = "Milano", province = "MI", zip = "20123", sheet = null, map = null, subordinate = null, staircase = null, floor = null, interior = null, yearOfConstruction = null, usableArea = null, units = null),
            Address(id = 17, address = "Via Petrarca", houseNumber = "12", municipality = "Monza", city = "Monza", province = "MB", zip = "20900", sheet = null, map = null, subordinate = null, staircase = null, floor = null, interior = null, yearOfConstruction = null, usableArea = null, units = null),
            Address(id = 18, address = "Via Ariosto", houseNumber = "2", municipality = "Rho", city = "Milano", province = "MI", zip = "20017", sheet = null, map = null, subordinate = null, staircase = null, floor = null, interior = null, yearOfConstruction = null, usableArea = null, units = null),
            Address(id = 19, address = "Via Tasso", houseNumber = "71", municipality = "Torino", city = "Torino", province = "TO", zip = "10122", sheet = null, map = null, subordinate = null, staircase = null, floor = null, interior = null, yearOfConstruction = null, usableArea = null, units = null),
            Address(id = 20, address = "Via Verga", houseNumber = "5", municipality = "Catania", city = "Catania", province = "CT", zip = "95100", sheet = null, map = null, subordinate = null, staircase = null, floor = null, interior = null, yearOfConstruction = null, usableArea = null, units = null),
            Address(id = 21, address = "Via Pirandello", houseNumber = "8", municipality = "Agrigento", city = "Agrigento", province = "AG", zip = "92100", sheet = "1", map = "10", subordinate = "5", staircase = "A", floor = "1", interior = "1", yearOfConstruction = 1975, usableArea = 100, units = "Casa"),
            Address(id = 22, address = "Via Svevo", houseNumber = "19", municipality = "Trieste", city = "Trieste", province = "TS", zip = "34100", sheet = "102", map = "44", subordinate = "2", staircase = null, floor = "3", interior = "A", yearOfConstruction = null, usableArea = 115, units = "Appartamento"),
            Address(id = 23, address = "Via Saba", houseNumber = "21", municipality = "Trieste", city = "Trieste", province = "TS", zip = "34100", sheet = null, map = null, subordinate = null, staircase = "B", floor = "2", interior = "4", yearOfConstruction = 2018, usableArea = 90, units = "Appartamento"),
            Address(id = 24, address = "Via Ungaretti", houseNumber = "3", municipality = "Udine", city = "Udine", province = "UD", zip = "33100", sheet = "14", map = "15", subordinate = null, staircase = null, floor = "T", interior = null, yearOfConstruction = null, usableArea = 50, units = "Negozio"),
            Address(id = 25, address = "Via Montale", houseNumber = "11", municipality = "Genova", city = "Genova", province = "GE", zip = "16100", sheet = "44", map = "12", subordinate = "7", staircase = "1", floor = "4", interior = "15", yearOfConstruction = 1968, usableArea = 80, units = "Appartamento"),
            Address(id = 26, address = "Via Quasimodo", houseNumber = "6", municipality = "Siracusa", city = "Siracusa", province = "SR", zip = "96100", sheet = null, map = null, subordinate = null, staircase = "C", floor = "2", interior = "4", yearOfConstruction = 1975, usableArea = 100, units = "Appartamento"),
            Address(id = 27, address = "Via Eco", houseNumber = "10", municipality = "Alessandria", city = "Alessandria", province = "AL", zip = "15100", sheet = "55", map = "3", subordinate = "1", staircase = null, floor = "1", interior = null, yearOfConstruction = 2010, usableArea = 140, units = "Ufficio"),
            Address(id = 28, address = "Via Calvino", houseNumber = "4", municipality = "Sanremo", city = "Imperia", province = "IM", zip = "18038", sheet = "12", map = "4", subordinate = "8", staircase = "Unica", floor = "T", interior = "1", yearOfConstruction = 1980, usableArea = 65, units = "Bilocale"),
            Address(id = 29, address = "Via Pavese", houseNumber = "13", municipality = "Cuneo", city = "Cuneo", province = "CN", zip = "12100", sheet = "8", map = "99", subordinate = "2", staircase = null, floor = "2", interior = null, yearOfConstruction = 1982, usableArea = 110, units = "Appartamento"),
            Address(id = 30, address = "Via Fenoglio", houseNumber = "25", municipality = "Alba", city = "Cuneo", province = "CN", zip = "12051", sheet = null, map = null, subordinate = null, staircase = "A", floor = "3", interior = "6", yearOfConstruction = 2000, usableArea = 95, units = "Appartamento"),
            Address(id = 31, address = "Via Levi", houseNumber = "1", municipality = "Matera", city = "Matera", province = "MT", zip = "75100", sheet = "101", map = "2", subordinate = "4", staircase = null, floor = "T", interior = null, yearOfConstruction = 1950, usableArea = 150, units = "Sassi"),
            Address(id = 32, address = "Via Silone", houseNumber = "9", municipality = "L'Aquila", city = "L'Aquila", province = "AQ", zip = "67100", sheet = "15", map = "44", subordinate = "7", staircase = "B", floor = "2", interior = "4", yearOfConstruction = 2009, usableArea = 85, units = "Appartamento"),
            Address(id = 33, address = "Via Gadda", houseNumber = "18", municipality = "Milano", city = "Milano", province = "MI", zip = "20100", sheet = "102", map = "33", subordinate = "12", staircase = "A", floor = "5", interior = "18", yearOfConstruction = 2015, usableArea = 120, units = "Appartamento"),
            Address(id = 34, address = "Via Buzzati", houseNumber = "27", municipality = "Belluno", city = "Belluno", province = "BL", zip = "32100", sheet = null, map = null, subordinate = null, staircase = "Unica", floor = "1", interior = "1", yearOfConstruction = 1990, usableArea = 75, units = "Appartamento"),
            Address(id = 35, address = "Via Cassola", houseNumber = "2", municipality = "Grosseto", city = "Grosseto", province = "GR", zip = "58100", sheet = "4", map = "11", subordinate = "1", staircase = null, floor = "T", interior = null, yearOfConstruction = 1985, usableArea = 200, units = "Villa"),
            Address(id = 36, address = "Via Pratolini", houseNumber = "5", municipality = "Firenze", city = "Firenze", province = "FI", zip = "50121", sheet = "8", map = "40", subordinate = "5", staircase = "A", floor = "3", interior = "5", yearOfConstruction = 1960, usableArea = 90, units = "Appartamento"),
            Address(id = 37, address = "Via Sciascia", houseNumber = "14", municipality = "Palermo", city = "Palermo", province = "PA", zip = "90100", sheet = "50", map = "2", subordinate = "9", staircase = "C", floor = "4", interior = "12", yearOfConstruction = 1978, usableArea = 110, units = "Appartamento"),
            Address(id = 38, address = "Via Vittorini", houseNumber = "3", municipality = "Siracusa", city = "Siracusa", province = "SR", zip = "96100", sheet = "11", map = "4", subordinate = "2", staircase = null, floor = "T", interior = null, yearOfConstruction = 1995, usableArea = 130, units = "Villetta"),
            Address(id = 39, address = "Via Beccaria", houseNumber = "16", municipality = "Milano", city = "Milano", province = "MI", zip = "20121", sheet = "101", map = "55", subordinate = "8", staircase = "A", floor = "2", interior = "6", yearOfConstruction = 1920, usableArea = 150, units = "Ufficio"),
            Address(id = 40, address = "Via Parini", houseNumber = "8", municipality = "Busto Arsizio", city = "Varese", province = "VA", zip = "21052", sheet = null, map = null, subordinate = null, staircase = "B", floor = "1", interior = "2", yearOfConstruction = 2005, usableArea = 85, units = "Appartamento"),
            Address(id = 41, address = "Via Alfieri", houseNumber = "12", municipality = "Asti", city = "Asti", province = "AT", zip = "14100", sheet = "14", map = "22", subordinate = "3", staircase = null, floor = "2", interior = null, yearOfConstruction = 1999, usableArea = 100, units = "Appartamento"),
            Address(id = 42, address = "Via Goldoni", houseNumber = "20", municipality = "Venezia", city = "Venezia", province = "VE", zip = "30100", sheet = "45", map = "1", subordinate = "7", staircase = "Unica", floor = "1", interior = "2", yearOfConstruction = 1700, usableArea = 250, units = "Palazzina"),
            Address(id = 43, address = "Via Vico", houseNumber = "7", municipality = "Napoli", city = "Napoli", province = "NA", zip = "80100", sheet = "22", map = "4", subordinate = "15", staircase = "A", floor = "3", interior = "9", yearOfConstruction = 1965, usableArea = 80, units = "Appartamento"),
            Address(id = 44, address = "Via Bruno", houseNumber = "9", municipality = "Nola", city = "Napoli", province = "NA", zip = "80035", sheet = null, map = null, subordinate = null, staircase = "B", floor = "T", interior = "1", yearOfConstruction = 2010, usableArea = 70, units = "Negozio"),
            Address(id = 45, address = "Via Campanella", houseNumber = "1", municipality = "Stilo", city = "Reggio Calabria", province = "RC", zip = "89040", sheet = "1", map = "1", subordinate = "1", staircase = null, floor = "T", interior = null, yearOfConstruction = null, usableArea = 50, units = "Casa"),
            Address(id = 46, address = "Via Giotto", houseNumber = "14", municipality = "Padova", city = "Padova", province = "PD", zip = "35100", sheet = "15", map = "120", subordinate = "4", staircase = "C", floor = "2", interior = "8", yearOfConstruction = 1980, usableArea = 95, units = "Appartamento"),
            Address(id = 47, address = "Via Da Vinci", houseNumber = "3", municipality = "Empoli", city = "Firenze", province = "FI", zip = "50053", sheet = "44", map = "5", subordinate = "2", staircase = null, floor = "1", interior = null, yearOfConstruction = 1992, usableArea = 110, units = "Appartamento"),
            Address(id = 48, address = "Via Galilei", houseNumber = "22", municipality = "Pisa", city = "Pisa", province = "PI", zip = "56100", sheet = null, map = null, subordinate = null, staircase = "A", floor = "3", interior = "10", yearOfConstruction = 2005, usableArea = 88, units = "Appartamento"),
            Address(id = 49, address = "Via Fermi", houseNumber = "9", municipality = "Frascati", city = "Roma", province = "RM", zip = "44", sheet = "8", map = "302", subordinate = "5", staircase = null, floor = "T", interior = null, yearOfConstruction = 1975, usableArea = 120, units = "Laboratorio"),
            Address(id = 50, address = "Via Volta", houseNumber = "1", municipality = "Como", city = "Como", province = "CO", zip = "22100", sheet = "10", map = "44", subordinate = "3", staircase = "B", floor = "2", interior = "4", yearOfConstruction = 1960, usableArea = 90, units = "Appartamento"),
            Address(id = 51, address = "Via Torino", houseNumber = "45", municipality = "Milano", city = "Milano", province = "MI", zip = "20121", sheet = "102", map = "551", subordinate = "1", staircase = "A", floor = "1", interior = "1", yearOfConstruction = 2010, usableArea = 95, units = "Appartamento"),
            Address(id = 52, address = "Via Napoli", houseNumber = "12", municipality = "Roma", city = "Roma", province = "RM", zip = "184", sheet = "30", map = "12", subordinate = "5", staircase = null, floor = "3", interior = "12", yearOfConstruction = 1975, usableArea = 110, units = "Appartamento"),
            Address(id = 53, address = "Corso Francia", houseNumber = "201", municipality = "Torino", city = "Torino", province = "TO", zip = "10138", sheet = "44", map = "88", subordinate = "2", staircase = "B", floor = "5", interior = "18", yearOfConstruction = 1998, usableArea = 130, units = "Appartamento"),
            Address(id = 54, address = "Via dell'Indipendenza", houseNumber = "8", municipality = "Bologna", city = "Bologna", province = "BO", zip = "40121", sheet = "12", map = "45", subordinate = "9", staircase = "1", floor = "2", interior = "4", yearOfConstruction = 1920, usableArea = 150, units = "Ufficio"),
            Address(id = 55, address = "Via Libertà", houseNumber = "100", municipality = "Palermo", city = "Palermo", province = "PA", zip = "90143", sheet = "15", map = "202", subordinate = "3", staircase = "C", floor = "4", interior = "10", yearOfConstruction = 1965, usableArea = 140, units = "Appartamento"),
            Address(id = 56, address = "Viale Vespucci", houseNumber = "22", municipality = "Rimini", city = "Rimini", province = "RN", zip = "47921", sheet = "5", map = "110", subordinate = "1", staircase = null, floor = "T", interior = null, yearOfConstruction = 2005, usableArea = 300, units = "Hotel"),
            Address(id = 57, address = "Via Garibaldi", houseNumber = "18", municipality = "Genova", city = "Genova", province = "GE", zip = "16124", sheet = "88", map = "3", subordinate = "14", staircase = "A", floor = "2", interior = "5", yearOfConstruction = 1850, usableArea = 200, units = "Palazzo"),
            Address(id = 58, address = "Via Etnea", houseNumber = "300", municipality = "Catania", city = "Catania", province = "CT", zip = "95124", sheet = "10", map = "15", subordinate = "4", staircase = null, floor = "1", interior = "2", yearOfConstruction = 1950, usableArea = 85, units = "Negozio"),
            Address(id = 59, address = "Via Roma", houseNumber = "99", municipality = "Cagliari", city = "Cagliari", province = "CA", zip = "9124", sheet = "1", map = "505", subordinate = "7", staircase = "D", floor = "3", interior = "11", yearOfConstruction = 1970, usableArea = 90, units = "Appartamento"),
            Address(id = 60, address = "Via Diaz", houseNumber = "5", municipality = "Verona", city = "Verona", province = "VR", zip = "37121", sheet = "22", map = "44", subordinate = "2", staircase = null, floor = "2", interior = null, yearOfConstruction = 1985, usableArea = 120, units = "Appartamento"),
            Address(id = 61, address = "Via XX Settembre", houseNumber = "14", municipality = "Bergamo", city = "Bergamo", province = "BG", zip = "24122", sheet = "14", map = "12", subordinate = "1", staircase = "A", floor = "4", interior = "8", yearOfConstruction = 2012, usableArea = 105, units = "Appartamento"),
            Address(id = 62, address = "Via Zamboni", houseNumber = "33", municipality = "Bologna", city = "Bologna", province = "BO", zip = "40126", sheet = "5", map = "99", subordinate = "12", staircase = null, floor = "1", interior = "1", yearOfConstruction = 1990, usableArea = 70, units = "Ufficio"),
            Address(id = 63, address = "Viale dei Mille", houseNumber = "56", municipality = "Firenze", city = "Firenze", province = "FI", zip = "50131", sheet = "40", map = "10", subordinate = "8", staircase = "B", floor = "2", interior = "4", yearOfConstruction = 1968, usableArea = 95, units = "Appartamento"),
            Address(id = 64, address = "Via Posillipo", houseNumber = "2", municipality = "Napoli", city = "Napoli", province = "NA", zip = "80123", sheet = "18", map = "77", subordinate = "1", staircase = null, floor = "T", interior = null, yearOfConstruction = 1955, usableArea = 250, units = "Villa"),
            Address(id = 65, address = "Via Appia Nuova", houseNumber = "450", municipality = "Roma", city = "Roma", province = "RM", zip = "181", sheet = "205", map = "33", subordinate = "15", staircase = "E", floor = "6", interior = "24", yearOfConstruction = 1982, usableArea = 80, units = "Appartamento"),
            Address(id = 66, address = "Corso Buenos Aires", houseNumber = "15", municipality = "Milano", city = "Milano", province = "MI", zip = "20124", sheet = "11", map = "400", subordinate = "22", staircase = "A", floor = "3", interior = "12", yearOfConstruction = 1930, usableArea = 115, units = "Appartamento"),
            Address(id = 67, address = "Via Baioni", houseNumber = "1", municipality = "Bergamo", city = "Bergamo", province = "BG", zip = "24123", sheet = null, map = null, subordinate = null, staircase = "1", floor = "T", interior = "1", yearOfConstruction = 2020, usableArea = 60, units = "Negozio"),
            Address(id = 68, address = "Via Dante", houseNumber = "88", municipality = "Padova", city = "Padova", province = "PD", zip = "35139", sheet = "12", map = "5", subordinate = "3", staircase = null, floor = "2", interior = "5", yearOfConstruction = 1995, usableArea = 100, units = "Appartamento"),
            Address(id = 69, address = "Via Emilia", houseNumber = "12", municipality = "Parma", city = "Parma", province = "PR", zip = "43121", sheet = "44", map = "102", subordinate = "6", staircase = "B", floor = "1", interior = "2", yearOfConstruction = 2008, usableArea = 140, units = "Appartamento"),
            Address(id = 70, address = "Piazza Duomo", houseNumber = "1", municipality = "Pisa", city = "Pisa", province = "PI", zip = "56126", sheet = "2", map = "1", subordinate = "1", staircase = null, floor = "T", interior = null, yearOfConstruction = 1200, usableArea = 500, units = "Monumento"),
            Address(id = 71, address = "Via Po", houseNumber = "18", municipality = "Torino", city = "Torino", province = "TO", zip = "10123", sheet = "33", map = "44", subordinate = "2", staircase = "A", floor = "2", interior = "4", yearOfConstruction = 1880, usableArea = 160, units = "Ufficio"),
            Address(id = 72, address = "Via Chiaia", houseNumber = "120", municipality = "Napoli", city = "Napoli", province = "NA", zip = "80121", sheet = "10", map = "11", subordinate = "5", staircase = null, floor = "1", interior = "1", yearOfConstruction = 1920, usableArea = 90, units = "Negozio"),
            Address(id = 73, address = "Via Rizzoli", houseNumber = "4", municipality = "Bologna", city = "Bologna", province = "BO", zip = "40125", sheet = "4", map = "8", subordinate = "12", staircase = "C", floor = "3", interior = "9", yearOfConstruction = 1950, usableArea = 130, units = "Appartamento"),
            Address(id = 74, address = "Via Sparano", houseNumber = "10", municipality = "Bari", city = "Bari", province = "BA", zip = "70121", sheet = "15", map = "22", subordinate = "4", staircase = null, floor = "2", interior = "3", yearOfConstruction = 1970, usableArea = 110, units = "Appartamento"),
            Address(id = 75, address = "Via Mazzini", houseNumber = "50", municipality = "Trieste", city = "Trieste", province = "TS", zip = "34121", sheet = "22", map = "10", subordinate = "1", staircase = "B", floor = "4", interior = "12", yearOfConstruction = 1900, usableArea = 125, units = "Appartamento"),
            Address(id = 76, address = "Via Garibaldi", houseNumber = "2", municipality = "Venezia", city = "Venezia", province = "VE", zip = "30122", sheet = "5", map = "44", subordinate = "8", staircase = null, floor = "1", interior = "2", yearOfConstruction = 1750, usableArea = 180, units = "Appartamento"),
            Address(id = 77, address = "Via Fillungo", houseNumber = "15", municipality = "Lucca", city = "Lucca", province = "LU", zip = "55100", sheet = "10", map = "3", subordinate = "2", staircase = null, floor = "T", interior = "1", yearOfConstruction = 1930, usableArea = 75, units = "Negozio"),
            Address(id = 78, address = "Corso Vannucci", houseNumber = "8", municipality = "Perugia", city = "Perugia", province = "PG", zip = "6121", sheet = "33", map = "12", subordinate = "5", staircase = "A", floor = "2", interior = "4", yearOfConstruction = 1960, usableArea = 200, units = "Ufficio"),
            Address(id = 79, address = "Via Scarlatti", houseNumber = "44", municipality = "Napoli", city = "Napoli", province = "NA", zip = "80127", sheet = "50", map = "4", subordinate = "9", staircase = "B", floor = "3", interior = "10", yearOfConstruction = 1980, usableArea = 95, units = "Appartamento"),
            Address(id = 80, address = "Via Tuscolana", houseNumber = "1000", municipality = "Roma", city = "Roma", province = "RM", zip = "173", sheet = "400", map = "12", subordinate = "1", staircase = null, floor = "5", interior = "20", yearOfConstruction = 1978, usableArea = 75, units = "Appartamento"),
            Address(id = 81, address = "Via Indipendenza", houseNumber = "21", municipality = "Como", city = "Como", province = "CO", zip = "22100", sheet = null, map = null, subordinate = null, staircase = "A", floor = "1", interior = "2", yearOfConstruction = 2015, usableArea = 85, units = "Appartamento"),
            Address(id = 82, address = "Via Cavour", houseNumber = "5", municipality = "Arezzo", city = "Arezzo", province = "AR", zip = "52100", sheet = "12", map = "44", subordinate = "3", staircase = null, floor = "2", interior = null, yearOfConstruction = 1992, usableArea = 115, units = "Appartamento"),
            Address(id = 83, address = "Viale Europa", houseNumber = "30", municipality = "Bolzano", city = "Bolzano", province = "BZ", zip = "39100", sheet = "8", map = "101", subordinate = "5", staircase = "C", floor = "4", interior = "16", yearOfConstruction = 2005, usableArea = 90, units = "Appartamento"),
            Address(id = 84, address = "Via Milano", houseNumber = "77", municipality = "Brescia", city = "Brescia", province = "BS", zip = "25126", sheet = "14", map = "2", subordinate = "10", staircase = null, floor = "T", interior = null, yearOfConstruction = 1988, usableArea = 300, units = "Capannone"),
            Address(id = 85, address = "Via Roma", houseNumber = "10", municipality = "Trento", city = "Trento", province = "TN", zip = "38122", sheet = "5", map = "88", subordinate = "2", staircase = "A", floor = "2", interior = "4", yearOfConstruction = 2010, usableArea = 110, units = "Appartamento"),
            Address(id = 86, address = "Via Emilia", houseNumber = "200", municipality = "Rimini", city = "Rimini", province = "RN", zip = "47921", sheet = "10", map = "5", subordinate = "1", staircase = null, floor = "1", interior = null, yearOfConstruction = 2018, usableArea = 120, units = "Ufficio"),
            Address(id = 87, address = "Via dei Condotti", houseNumber = "2", municipality = "Roma", city = "Roma", province = "RM", zip = "187", sheet = "22", map = "1", subordinate = "1", staircase = null, floor = "1", interior = "1", yearOfConstruction = 1800, usableArea = 400, units = "Negozio"),
            Address(id = 88, address = "Corso Giovecca", houseNumber = "15", municipality = "Ferrara", city = "Ferrara", province = "FE", zip = "44121", sheet = "12", map = "44", subordinate = "7", staircase = "B", floor = "2", interior = "5", yearOfConstruction = 1950, usableArea = 130, units = "Appartamento"),
            Address(id = 89, address = "Via Farini", houseNumber = "1", municipality = "Parma", city = "Parma", province = "PR", zip = "43121", sheet = "5", map = "10", subordinate = "4", staircase = "A", floor = "1", interior = "2", yearOfConstruction = 1920, usableArea = 160, units = "Ufficio"),
            Address(id = 90, address = "Via San Vitale", houseNumber = "12", municipality = "Ravenna", city = "Ravenna", province = "RA", zip = "48121", sheet = "8", map = "33", subordinate = "2", staircase = null, floor = "T", interior = null, yearOfConstruction = 1975, usableArea = 90, units = "Appartamento"),
            Address(id = 91, address = "Via Tolmino", houseNumber = "5", municipality = "Milano", city = "Milano", province = "MI", zip = "20148", sheet = "150", map = "12", subordinate = "4", staircase = "D", floor = "3", interior = "10", yearOfConstruction = 1995, usableArea = 85, units = "Appartamento"),
            Address(id = 92, address = "Via Marconi", houseNumber = "22", municipality = "Ancona", city = "Ancona", province = "AN", zip = "60121", sheet = "14", map = "5", subordinate = "1", staircase = null, floor = "2", interior = "3", yearOfConstruction = 1982, usableArea = 105, units = "Appartamento"),
            Address(id = 93, address = "Via del Corso", houseNumber = "100", municipality = "Roma", city = "Roma", province = "RM", zip = "186", sheet = "55", map = "3", subordinate = "8", staircase = "A", floor = "4", interior = "12", yearOfConstruction = 1900, usableArea = 140, units = "Appartamento"),
            Address(id = 94, address = "Piazza Vittoria", houseNumber = "6", municipality = "Pavia", city = "Pavia", province = "PV", zip = "27100", sheet = "10", map = "4", subordinate = "2", staircase = null, floor = "1", interior = null, yearOfConstruction = 1960, usableArea = 200, units = "Ufficio"),
            Address(id = 95, address = "Via Isonzo", houseNumber = "14", municipality = "Latina", city = "Latina", province = "LT", zip = "4100", sheet = "22", map = "8", subordinate = "4", staircase = "B", floor = "2", interior = "6", yearOfConstruction = 2005, usableArea = 95, units = "Appartamento"),
            Address(id = 96, address = "Via Crispi", houseNumber = "1", municipality = "Taranto", city = "Taranto", province = "TA", zip = "74123", sheet = "5", map = "102", subordinate = "9", staircase = null, floor = "3", interior = "9", yearOfConstruction = 1972, usableArea = 80, units = "Appartamento"),
            Address(id = 97, address = "Viale Gramsci", houseNumber = "88", municipality = "Foggia", city = "Foggia", province = "FG", zip = "71121", sheet = "11", map = "14", subordinate = "3", staircase = "C", floor = "1", interior = "2", yearOfConstruction = 1990, usableArea = 115, units = "Appartamento"),
            Address(id = 98, address = "Via Mazzini", houseNumber = "10", municipality = "Sassari", city = "Sassari", province = "SS", zip = "7100", sheet = "8", map = "22", subordinate = "1", staircase = null, floor = "T", interior = null, yearOfConstruction = 1965, usableArea = 150, units = "Negozio"),
            Address(id = 99, address = "Corso Italia", houseNumber = "25", municipality = "Pisa", city = "Pisa", province = "PI", zip = "56125", sheet = "14", map = "55", subordinate = "2", staircase = "A", floor = "2", interior = "4", yearOfConstruction = 1985, usableArea = 100, units = "Appartamento"),
            Address(id = 100, address = "Via Roma", houseNumber = "50", municipality = "Potenza", city = "Potenza", province = "PZ", zip = "85100", sheet = "1", map = "10", subordinate = "1", staircase = null, floor = "1", interior = "1", yearOfConstruction = 1950, usableArea = 85, units = "Appartamento"),
            Address(id = 101, address = "Via Cardinala", houseNumber = "16", municipality = "Spazzate Sassatelli", city = "Imola", province = "BO", zip = "40026", sheet = null, map = null, subordinate = null, staircase = null, floor = null, interior = null, yearOfConstruction = null, usableArea = null, units = null),
            Address(id = 102, address = "Via Delle Biotecnologie", houseNumber = "42", municipality = "Roma", city = "Roma", province = "RM", zip = "144", sheet = null, map = null, subordinate = null, staircase = null, floor = null, interior = null, yearOfConstruction = null, usableArea = null, units = null),
            Address(id = 103, address = "Corso Vittorio Emanuele II", houseNumber = "101", municipality = "Torino", city = "Torino", province = "TO", zip = "10121", sheet = null, map = null, subordinate = null, staircase = null, floor = null, interior = null, yearOfConstruction = null, usableArea = null, units = null),
            Address(id = 104, address = "Via dei Mille", houseNumber = "15", municipality = "Firenze", city = "Firenze", province = "FI", zip = "50131", sheet = null, map = null, subordinate = null, staircase = null, floor = null, interior = null, yearOfConstruction = null, usableArea = null, units = null),
            Address(id = 105, address = "Via del Mare", houseNumber = "8", municipality = "Portoferraio", city = "Portoferraio", province = "LI", zip = "57037", sheet = null, map = null, subordinate = null, staircase = null, floor = null, interior = null, yearOfConstruction = null, usableArea = null, units = null),
            Address(id = 106, address = "Viale Michelangelo", houseNumber = "165", municipality = "Comacchio", city = "Lido Di Spina", province = "FE", zip = "44029", sheet = null, map = null, subordinate = null, staircase = null, floor = null, interior = null, yearOfConstruction = null, usableArea = null, units = null),
            Address(id = 107, address = "Via Toledo", houseNumber = "21", municipality = "Napoli", city = "Napoli", province = "NA", zip = "80134", sheet = null, map = null, subordinate = null, staircase = null, floor = null, interior = null, yearOfConstruction = null, usableArea = null, units = null)
        )

        addresses.forEach { address ->
            repository.address.upsertAddress(address)
        }
    }

    suspend fun seedReference(repository: Repository){
        val references = listOf(
            Reference(id = 1, name = "Marco", lastName = "Rossi", phoneNumber = "3331234567"),
            Reference(id = 2, name = "Luca", lastName = "Bianchi", phoneNumber = "3479876543"),
            Reference(id = 3, name = "Anna", lastName = "Verdi", phoneNumber = "3356677889"),
            Reference(id = 4, name = "Giuseppe", lastName = "Neri", phoneNumber = "3312233445"),
            Reference(id = 5, name = "Elena", lastName = "Gialli", phoneNumber = "3201122334"),
            Reference(id = 6, name = "Roberto", lastName = "Blu", phoneNumber = "3405566778"),
            Reference(id = 7, name = "Franco", lastName = "Russo", phoneNumber = "3490011223"),
            Reference(id = 8, name = "Silvia", lastName = "Esposito", phoneNumber = "3386677882"),
            Reference(id = 9, name = "Matteo", lastName = "Romano", phoneNumber = "3341122330"),
            Reference(id = 10, name = "Sara", lastName = "Colombo", phoneNumber = "3456677884"),
            Reference(id = 11, name = "Davide", lastName = "Ricci", phoneNumber = "3337788991"),
            Reference(id = 12, name = "Laura", lastName = "Marino", phoneNumber = "3425566773"),
            Reference(id = 13, name = "Giorgio", lastName = "Greco", phoneNumber = "3310099882"),
            Reference(id = 14, name = "Marta", lastName = "Bruno", phoneNumber = "3284455661"),
            Reference(id = 15, name = "Pietro", lastName = "Galli", phoneNumber = "3471122339"),
            Reference(id = 16, name = "Pinco", lastName = "Pallo", phoneNumber = "3326548943")
        )

        references.forEach { reference ->
            repository.customer.upsertReference(reference)
        }
    }

    suspend fun seedCustomers(repository :Repository){
        val customers = listOf(
            Customer(
                cf = "01234567890",
                name = "Pietro",
                mail = "info@edilmilan.it",
                averageCollectionTime = 30.0f,
                collectionCount = 25,
                residence = 26,
                reference = 7
            ),
            Customer(cf = "01234567891", name = "Luigi", mail = "amministrazione@tecno.it", averageCollectionTime = 60.5f, collectionCount = 12, residence = 27, reference = 8),
            Customer(cf = "01234567892", name = "Carla", mail = "carla@ristorante.it", averageCollectionTime = 15.0f, collectionCount = 40, residence = 28, reference = 9),
            Customer(cf = "01234567893", name = "Paolo", mail = "direzione@logistica.it", averageCollectionTime = 90.0f, collectionCount = 5, residence = 29, reference = null),
            Customer(cf = "01234567894", name = "Sonia", mail = "commerciale@moda.it", averageCollectionTime = 25.5f, collectionCount = 18, residence = 30, reference = 10),
            Customer(cf = "01234567895", name = "Fabio", mail = "fabio@officina.it", averageCollectionTime = 0.0f, collectionCount = 0, residence = 31, reference = 11),
            Customer(cf = "01234567896", name = "Monica", mail = "info@hotelstella.it", averageCollectionTime = 20.0f, collectionCount = 30, residence = 32, reference = 12),
            Customer(cf = "01234567897", name = "Enrico", mail = "enrico@ferramenta.it", averageCollectionTime = 45.0f, collectionCount = 10, residence = 33, reference = 13),
            Customer(cf = "01234567898", name = "Cinzia", mail = "cinzia@studiolegale.it", averageCollectionTime = 10.0f, collectionCount = 50, residence = 34, reference = 14),
            Customer(cf = "01234567899", name = "Massimo", mail = "info@pizzeria.it", averageCollectionTime = 0.0f, collectionCount = 0, residence = 35, reference = 15),
            Customer(cf = "01234567900", name = "Stefania", mail = "ordini@pasticceria.it", averageCollectionTime = 12.0f, collectionCount = 22, residence = 36, reference = 1),
            Customer(cf = "01234567901", name = "Roberto", mail = "r.galli@impresa.it", averageCollectionTime = 35.0f, collectionCount = 8, residence = 37, reference = 2),
            Customer(cf = "01234567902", name = "Alice", mail = "alice@boutique.it", averageCollectionTime = 0.0f, collectionCount = 0, residence = 38, reference = 3),
            Customer(cf = "01234567903", name = "Daniele", mail = "daniele@laboratorio.it", averageCollectionTime = 15.0f, collectionCount = 15, residence = 39, reference = null),
            Customer(cf = "01234567904", name = "Simona", mail = "simona@estetica.it", averageCollectionTime = 28.0f, collectionCount = 6, residence = 40, reference = 4),
            Customer(cf = "01234567905", name = "Giovanni", mail = "giovanni@falegnameria.it", averageCollectionTime = 0.0f, collectionCount = 0, residence = 41, reference = 5),
            Customer(cf = "01234567906", name = "Beatrice", mail = "info@asilo.it", averageCollectionTime = 40.0f, collectionCount = 4, residence = 42, reference = 6),
            Customer(cf = "01234567907", name = "Claudio", mail = "claudio@bar.it", averageCollectionTime = 18.5f, collectionCount = 20, residence = 43, reference = 7),
            Customer(cf = "01234567908", name = "Federica", mail = "fede@fioraio.it", averageCollectionTime = 0.0f, collectionCount = 0, residence = 44, reference = 8),
            Customer(cf = "01234567909", name = "Umberto", mail = "umberto@palestra.it", averageCollectionTime = 50.0f, collectionCount = 10, residence = 45, reference = 9),
            Customer(cf = "01234567910", name = "Ilaria", mail = "ilaria@farmacia.it", averageCollectionTime = 10.0f, collectionCount = 60, residence = 46, reference = 10),
            Customer(cf = "01234567911", name = "Antonio", mail = "antonio@carrozzeria.it", averageCollectionTime = 0.0f, collectionCount = 0, residence = 47, reference = 11),
            Customer(cf = "01234567912", name = "Valeria", mail = "valeria@dentista.it", averageCollectionTime = 30.0f, collectionCount = 5, residence = 48, reference = 12),
            Customer(cf = "01234567913", name = "Giacomo", mail = "giacomo@studio.it", averageCollectionTime = 15.0f, collectionCount = 12, residence = 49, reference = 13),
            Customer(cf = "01234567914", name = "Rosa", mail = "rosa@parrucchiere.it", averageCollectionTime = 0.0f, collectionCount = 0, residence = 50, reference = 14),
            Customer(cf = "12345567890", name = "TechNova", mail = "technova.solutions@legalmail.it", averageCollectionTime = 0.0f, collectionCount = 0, residence = 102, reference = null),
            Customer(cf = "BNCCHR90S05F205J", name = "Chiara", mail = "chiara.bianchi@gmail.com", averageCollectionTime = 5.0f, collectionCount = 1, residence = 15, reference = 12),
            Customer(cf = "BNCHGL92S62F205P", name = "Giulia", mail = "giulia.bianchi.92@example.com", averageCollectionTime = 0.0f, collectionCount = 0, residence = 103, reference = 16),
            Customer(cf = "BNCLCU75B10L219Z", name = "Luca", mail = "luca.bianchi@fastweb.it", averageCollectionTime = 30.2f, collectionCount = 12, residence = 3, reference = 2),
            Customer(cf = "BRNMRT89Q15F205L", name = "Marta", mail = "marta.bruno@outlook.com", averageCollectionTime = 25.0f, collectionCount = 2, residence = 13, reference = 10),
            Customer(cf = "CLMSRA95I20F205P", name = "Sara", mail = "sara.colombo@virgilio.it", averageCollectionTime = 0.0f, collectionCount = 0, residence = 9, reference = 7),
            Customer(cf = "CRSMCO82A01F205G", name = "Marco", mail = "marco.caruso@gmail.com", averageCollectionTime = 22.0f, collectionCount = 4, residence = 18, reference = 15),
            Customer(cf = "DCNALE91G15F205A", name = "Alessandro", mail = "ale.de_luca@tiscali.it", averageCollectionTime = 33.0f, collectionCount = 1, residence = 24, reference = 5),
            Customer(cf = "ESPGNN80A01F839U", name = "Gennaro", mail = "gennaro.expo@napoli.it", averageCollectionTime = 0.0f, collectionCount = 0, residence = 107, reference = null),
            Customer(cf = "ESPSLV88G05F205B", name = "Silvia", mail = "silvia.esposito@email.it", averageCollectionTime = 20.0f, collectionCount = 5, residence = 7, reference = 5),
            Customer(cf = "FRRELE92E25F205D", name = "Elena", mail = "elena.ferrari@outlook.it", averageCollectionTime = 10.0f, collectionCount = 1, residence = 5, reference = 4),
            Customer(cf = "GLLPTR81R25F205K", name = "Pietro", mail = "pietro.galli@tiscali.it", averageCollectionTime = 30.0f, collectionCount = 10, residence = 14, reference = 11),
            Customer(cf = "GLLRBT85D15F205E", name = "Roberto", mail = "roby.gialli@libero.it", averageCollectionTime = 45.0f, collectionCount = 2, residence = 4, reference = 3),
            Customer(cf = "GRCGRG72P05F205M", name = "Giorgio", mail = "giorgio.greco@libero.it", averageCollectionTime = 27.5f, collectionCount = 2, residence = 12, reference = 9),
            Customer(cf = "GTTVAL80H25F205Z", name = "Valentina", mail = "valen.gatti@gmail.com", averageCollectionTime = 0.0f, collectionCount = 0, residence = 25, reference = 6),
            Customer(cf = "LBRGIA93B10F205F", name = "Giulia", mail = "giulia.lombardi@outlook.it", averageCollectionTime = 0.0f, collectionCount = 0, residence = 19, reference = 1),
            Customer(cf = "MNNFRN85D30F205D", name = "Francesca", mail = "fra.manna@email.it", averageCollectionTime = 11.0f, collectionCount = 2, residence = 21, reference = 2),
            Customer(cf = "MRNAND75T15F205I", name = "Andrea", mail = "andrea.marini@email.it", averageCollectionTime = 0.0f, collectionCount = 0, residence = 16, reference = 13),
            Customer(cf = "MRNLRA83M25F205N", name = "Laura", mail = "laura.marino@email.it", averageCollectionTime = 18.2f, collectionCount = 3, residence = 11, reference = null),
            Customer(cf = "MRTLSN78P05D612O", name = "Alessandro", mail = "a.martini.78@example.it", averageCollectionTime = 0.0f, collectionCount = 0, residence = 104, reference = null),
            Customer(cf = "PLMMRT84F05F205B", name = "Martina", mail = "martina.palumbo@libero.it", averageCollectionTime = 19.0f, collectionCount = 7, residence = 23, reference = 4),
            Customer(cf = "RCCDVD78L15F205O", name = "Davide", mail = "davide.ricci@gmail.com", averageCollectionTime = 60.0f, collectionCount = 1, residence = 10, reference = 8),
            Customer(cf = "RMNMTT82H10F205A", name = "Matteo", mail = "matteo.romano@tiscali.it", averageCollectionTime = 12.5f, collectionCount = 8, residence = 8, reference = 6),
            Customer(cf = "RSSFRN70F30F205C", name = "Franco", mail = "franco.russo@gmail.com", averageCollectionTime = 0.0f, collectionCount = 0, residence = 6, reference = null),
            Customer(cf = "RSSMRA80A01F205H", name = "Mario", mail = "mario.rossi@email.it", averageCollectionTime = 15.5f, collectionCount = 4, residence = 1, reference = 1),
            Customer(cf = "RSSMRC85E14H501Z", name = "Marco", mail = "marco.rossi.test@example.it", averageCollectionTime = 0.0f, collectionCount = 0, residence = 101, reference = null),
            Customer(cf = "RZZSTE70C20F205E", name = "Stefano", mail = "stefano.rizzi@fastweb.it", averageCollectionTime = 40.5f, collectionCount = 3, residence = 20, reference = null),
            Customer(cf = "SRTMRE77E25F205C", name = "Emanuele", mail = "ema.serra@gmail.com", averageCollectionTime = 0.0f, collectionCount = 0, residence = 22, reference = 3),
            Customer(cf = "VLNSOF88U25F205H", name = "Sofia", mail = "sofia.valenti@libero.it", averageCollectionTime = 14.0f, collectionCount = 6, residence = 17, reference = 14),
            Customer(cf = "VRDNNA90C20F205G", name = "Anna", mail = "anna.verdi@gmail.com", averageCollectionTime = 0.0f, collectionCount = 0, residence = 2, reference = null)

        )
        customers.forEach { customer ->
            repository.customer.upsertCustomer(customer)
        }
    }

    suspend fun seedPrivate(repository: Repository){
        val privates = listOf(
            Private(cf = "BNCCHR90S05F205J", lastName = "Bianchi", dateBirth = LocalDate.of(1990, 3, 5), placeBirth = "Milano"),
            Private(cf = "BNCHGL92S62F205P", lastName = "Bianchi", dateBirth = LocalDate.of(1992, 11, 22), placeBirth = "Milano"),
            Private(cf = "BNCLCU75B10L219Z", lastName = "Bianchi", dateBirth = LocalDate.of(1975, 2, 10), placeBirth = "Monza"),
            Private(cf = "BRNMRT89Q15F205L", lastName = "Bruno", dateBirth = LocalDate.of(1989, 1, 15), placeBirth = "Palermo"),
            Private(cf = "CLMSRA95I20F205P", lastName = "Colombo", dateBirth = LocalDate.of(1995, 9, 20), placeBirth = "Monza"),
            Private(cf = "CRSMCO82A01F205G", lastName = "Caruso", dateBirth = LocalDate.of(1982, 1, 1), placeBirth = "Napoli"),
            Private(cf = "DCNALE91G15F205A", lastName = "De Luca", dateBirth = LocalDate.of(1991, 7, 15), placeBirth = "Lecce"),
            Private(cf = "ESPGNN80A01F839U", lastName = "Esposito", dateBirth = LocalDate.of(1980, 3, 25), placeBirth = "Napoli"),
            Private(cf = "ESPSLV88G05F205B", lastName = "Esposito", dateBirth = LocalDate.of(1988, 7, 5), placeBirth = "Roma"),
            Private(cf = "FRRELE92E25F205D", lastName = "Ferrari", dateBirth = LocalDate.of(1992, 5, 25), placeBirth = "Pavia"),
            Private(cf = "GLLPTR81R25F205K", lastName = "Galli", dateBirth = LocalDate.of(1981, 2, 25), placeBirth = "Bologna"),
            Private(cf = "GLLRBT85D15F205E", lastName = "Gialli", dateBirth = LocalDate.of(1985, 4, 15), placeBirth = "Milano"),
            Private(cf = "GRCGRG72P05F205M", lastName = "Greco", dateBirth = LocalDate.of(1972, 12, 5), placeBirth = "Bari"),
            Private(cf = "GTTVAL80H25F205Z", lastName = "Gatti", dateBirth = LocalDate.of(1980, 8, 25), placeBirth = "Rimini"),
            Private(cf = "LBRGIA93B10F205F", lastName = "Lombardi", dateBirth = LocalDate.of(1993, 2, 10), placeBirth = "Milano"),
            Private(cf = "MNNFRN85D30F205D", lastName = "Manna", dateBirth = LocalDate.of(1985, 4, 30), placeBirth = "Brescia"),
            Private(cf = "MRNAND75T15F205I", lastName = "Marini", dateBirth = LocalDate.of(1975, 4, 15), placeBirth = "Varese"),
            Private(cf = "MRNLRA83M25F205N", lastName = "Marino", dateBirth = LocalDate.of(1983, 11, 25), placeBirth = "Catania"),
            Private(cf = "MRTLSN78P05D612O", lastName = "Martini", dateBirth = LocalDate.of(1978, 9, 5), placeBirth = "Firenze"),
            Private(cf = "PLMMRT84F05F205B", lastName = "Palumbo", dateBirth = LocalDate.of(1984, 6, 5), placeBirth = "Palermo"),
            Private(cf = "RCCDVD78L15F205O", lastName = "Ricci", dateBirth = LocalDate.of(1978, 10, 15), placeBirth = "Firenze"),
            Private(cf = "RMNMTT82H10F205A", lastName = "Romano", dateBirth = LocalDate.of(1982, 8, 10), placeBirth = "Milano"),
            Private(cf = "RSSFRN70F30F205C", lastName = "Russo", dateBirth = LocalDate.of(1970, 6, 30), placeBirth = "Napoli"),
            Private(cf = "RSSMRA80A01F205H", lastName = "Rossi", dateBirth = LocalDate.of(1980, 1, 1), placeBirth = "Milano"),
            Private(cf = "RSSMRC85E14H501Z", lastName = "Rossi", dateBirth = LocalDate.of(1985, 5, 14), placeBirth = "Roma"),
            Private(cf = "RZZSTE70C20F205E", lastName = "Rizzi", dateBirth = LocalDate.of(1970, 3, 20), placeBirth = "Bergamo"),
            Private(cf = "SRTMRE77E25F205C", lastName = "Serra", dateBirth = LocalDate.of(1977, 5, 25), placeBirth = "Torino"),
            Private(cf = "VLNSOF88U25F205H", lastName = "Valenti", dateBirth = LocalDate.of(1988, 5, 25), placeBirth = "Como"),
            Private(cf = "VRDNNA90C20F205G", lastName = "Verdi", dateBirth = LocalDate.of(1990, 3, 20), placeBirth = "Torino")
        )

        privates.forEach { private ->
            repository.customer.upsertPrivate(private)
        }
    }

    suspend fun seedCompany(repository: Repository){
        val companies = listOf(
            Company(uniqueCode = "ASI2222", companyName = "Asilo Il Grillo", vatNumber = "01234567906", cf = "01234567906"),
            Company(uniqueCode = "BAR3333", companyName = "Bar Centrale snc", vatNumber = "01234567907", cf = "01234567907"),
            Company(uniqueCode = "BOU5656", companyName = "Boutique Alice", vatNumber = "01234567902", cf = "01234567902"),
            Company(uniqueCode = "CAR7777", companyName = "Carrozzeria Antonio", vatNumber = "01234567911", cf = "01234567911"),
            Company(uniqueCode = "DEN8888", companyName = "Studio Dentistico Val.", vatNumber = "01234567912", cf = "01234567912"),
            Company(uniqueCode = "EDI1234", companyName = "Edil Milano SRL", vatNumber = "01234567890", cf = "01234567890"),
            Company(uniqueCode = "EST9090", companyName = "Centro Estetico Simona", vatNumber = "01234567904", cf = "01234567904"),
            Company(uniqueCode = "FAL1111", companyName = "Falegnameria Giovanni", vatNumber = "01234567905", cf = "01234567905"),
            Company(uniqueCode = "FAR6666", companyName = "Farmacia San Luca", vatNumber = "01234567910", cf = "01234567910"),
            Company(uniqueCode = "FER5566", companyName = "Ferramenta Rossi snc", vatNumber = "01234567897", cf = "01234567897"),
            Company(uniqueCode = "FIO4444", companyName = "Fioraio Federica", vatNumber = "01234567908", cf = "01234567908"),
            Company(uniqueCode = "HOT3344", companyName = "Hotel Stella Maris", vatNumber = "01234567896", cf = "01234567896"),
            Company(uniqueCode = "IMP3434", companyName = "Impresa Costruzioni Galli", vatNumber = "01234567901", cf = "01234567901"),
            Company(uniqueCode = "K7X9P3J", companyName = "TechNova Solutions S.r.l.", vatNumber = "12345567890", cf = "12345567890"),
            Company(uniqueCode = "LAB7878", companyName = "Laboratorio Analisi", vatNumber = "01234567903", cf = "01234567903"),
            Company(uniqueCode = "LEG7788", companyName = "Studio Legale Cinzia", vatNumber = "01234567898", cf = "01234567898"),
            Company(uniqueCode = "LOG3456", companyName = "Logistica Express SPA", vatNumber = "01234567893", cf = "01234567893"),
            Company(uniqueCode = "MOD7890", companyName = "Moda Italia SRL", vatNumber = "01234567894", cf = "01234567894"),
            Company(uniqueCode = "OFF1122", companyName = "Auto Officina Fabio", vatNumber = "01234567895", cf = "01234567895"),
            Company(uniqueCode = "PAL5555", companyName = "Palestra BodyMind", vatNumber = "01234567909", cf = "01234567909"),
            Company(uniqueCode = "PAR0000", companyName = "Parrucchiere Rosa", vatNumber = "01234567914", cf = "01234567914"),
            Company(uniqueCode = "PAS1212", companyName = "Pasticceria Dolci", vatNumber = "01234567900", cf = "01234567900"),
            Company(uniqueCode = "PIZ9900", companyName = "Pizzeria La Bufala", vatNumber = "01234567899", cf = "01234567899"),
            Company(uniqueCode = "RIS9012", companyName = "Ristorante Da Mario", vatNumber = "01234567892", cf = "01234567892"),
            Company(uniqueCode = "STU9999", companyName = "Studio Architettura G.", vatNumber = "01234567913", cf = "01234567913"),
            Company(uniqueCode = "TEC5678", companyName = "Tecno Impianti SAS", vatNumber = "01234567891", cf = "01234567891")
        )

        companies.forEach { company ->
            repository.customer.upsertCompany(company)
        }
    }

    suspend fun seedReferral(repository: Repository){
        val referrals = listOf(
            Referral(presented = "BNCLCU75B10L219Z", referral = "01234567890"),
            Referral(presented = "CLMSRA95I20F205P", referral = "01234567890"),
            Referral(presented = "ESPSLV88G05F205B", referral = "01234567890"),
            Referral(presented = "FRRELE92E25F205D", referral = "01234567890"),
            Referral(presented = "RSSMRA80A01F205H", referral = "01234567890"),
            Referral(presented = "01234567911", referral = "01234567896"),
            Referral(presented = "01234567891", referral = "01234567897"),
            Referral(presented = "01234567892", referral = "01234567897"),
            Referral(presented = "01234567893", referral = "01234567897"),
            Referral(presented = "01234567894", referral = "01234567897"),
            Referral(presented = "01234567895", referral = "01234567897"),
            Referral(presented = "01234567912", referral = "01234567898"),
            Referral(presented = "01234567913", referral = "01234567899"),
            Referral(presented = "01234567914", referral = "01234567900"),
            Referral(presented = "01234567899", referral = "CRSMCO82A01F205G"),
            Referral(presented = "01234567905", referral = "DCNALE91G15F205A"),
            Referral(presented = "01234567907", referral = "GLLRBT85D15F205E"),
            Referral(presented = "BNCCHR90S05F205J", referral = "GTTVAL80H25F205Z"),
            Referral(presented = "BRNMRT89Q15F205L", referral = "GTTVAL80H25F205Z"),
            Referral(presented = "GLLPTR81R25F205K", referral = "GTTVAL80H25F205Z"),
            Referral(presented = "GRCGRG72P05F205M", referral = "GTTVAL80H25F205Z"),
            Referral(presented = "MRNLRA83M25F205N", referral = "GTTVAL80H25F205Z"),
            Referral(presented = "01234567900", referral = "LBRGIA93B10F205F"),
            Referral(presented = "01234567902", referral = "MNNFRN85D30F205D"),
            Referral(presented = "01234567896", referral = "MRNAND75T15F205I"),
            Referral(presented = "01234567904", referral = "PLMMRT84F05F205B"),
            Referral(presented = "01234567910", referral = "RCCDVD78L15F205O"),
            Referral(presented = "01234567909", referral = "RMNMTT82H10F205A"),
            Referral(presented = "01234567908", referral = "RSSFRN70F30F205C"),
            Referral(presented = "01234567901", referral = "RZZSTE70C20F205E"),
            Referral(presented = "01234567903", referral = "SRTMRE77E25F205C"),
            Referral(presented = "01234567898", referral = "VLNSOF88U25F205H"),
            Referral(presented = "01234567906", referral = "VRDNNA90C20F205G")
        )

        referrals.forEach { refferal ->
            repository.customer.upsertReferral(refferal)
        }
    }

    suspend fun seedPhoneNumber(repository: Repository){
        val phoneNumbers = listOf(
            PhoneNumber(number = "+39 333 9876543", text = "Cellulare principale", customer = "BNCHGL92S62F205P"),
            PhoneNumber(number = "+39 335 1122334", text = "Personale", customer = "MRTLSN78P05D612O"),
            PhoneNumber(number = "+39 347 1234567", text = "Personale", customer = "RSSMRC85E14H501Z"),
            PhoneNumber(number = "11223344", text = "Reception", customer = "01234567892"),
            PhoneNumber(number = "21234567", text = "Centralino Sede", customer = "01234567890"),
            PhoneNumber(number = "39445566", text = "Ufficio", customer = "01234567891"),
            PhoneNumber(number = "06 9876543", text = "Fisso", customer = "12345567890"),
            PhoneNumber(number = "3201122334", text = "Personale", customer = "FRRELE92E25F205D"),
            PhoneNumber(number = "3331122334", text = "Personale", customer = "RSSMRA80A01F205H"),
            PhoneNumber(number = "3331122335", text = "Moglie (Lucia)", customer = "RSSMRA80A01F205H"),
            PhoneNumber(number = "3341122330", text = "Personale", customer = "RMNMTT82H10F205A"),
            PhoneNumber(number = "3356677889", text = "Personale", customer = "VRDNNA90C20F205G"),
            PhoneNumber(number = "3380009988", text = "Marito (Paolo)", customer = "ESPSLV88G05F205B"),
            PhoneNumber(number = "3386677882", text = "Personale", customer = "ESPSLV88G05F205B"),
            PhoneNumber(number = "3405566778", text = "Personale", customer = "GLLRBT85D15F205E"),
            PhoneNumber(number = "3451259786", text = "Personale", customer = "GRCGRG72P05F205M"),
            PhoneNumber(number = "3456677884", text = "Personale", customer = "CLMSRA95I20F205P"),
            PhoneNumber(number = "3470001122", text = "Padre (Giovanni)", customer = "BNCLCU75B10L219Z"),
            PhoneNumber(number = "3479988776", text = "Personale", customer = "BNCLCU75B10L219Z"),
            PhoneNumber(number = "3490011223", text = "Personale", customer = "RSSFRN70F30F205C")
        )

        phoneNumbers.forEach { phoneNumber ->
            repository.customer.upsertPhoneNumber(phoneNumber)
        }
    }

    suspend fun seedCustomerProvision(repository: Repository){
        val customerProvisions = listOf(
            CustomerProvision(material = 1, customer = "RSSMRA80A01F205H", quantity = 1f),
            CustomerProvision(material = 2, customer = "VRDNNA90C20F205G", quantity = 5f),
            CustomerProvision(material = 3, customer = "BNCLCU75B10L219Z", quantity = 1f),
            CustomerProvision(material = 4, customer = "GLLRBT85D15F205E", quantity = 2f),
            CustomerProvision(material = 5, customer = "FRRELE92E25F205D", quantity = 1f),
            CustomerProvision(material = 6, customer = "RSSFRN70F30F205C", quantity = 12f),
            CustomerProvision(material = 7, customer = "ESPSLV88G05F205B", quantity = 1f),
            CustomerProvision(material = 8, customer = "RMNMTT82H10F205A", quantity = 20f),
            CustomerProvision(material = 9, customer = "CLMSRA95I20F205P", quantity = 1f),
            CustomerProvision(material = 10, customer = "RCCDVD78L15F205O", quantity = 6f),
            CustomerProvision(material = 11, customer = "MRNLRA83M25F205N", quantity = 1f),
            CustomerProvision(material = 12, customer = "GRCGRG72P05F205M", quantity = 10f),
            CustomerProvision(material = 13, customer = "BRNMRT89Q15F205L", quantity = 1f),
            CustomerProvision(material = 14, customer = "GLLPTR81R25F205K", quantity = 4f),
            CustomerProvision(material = 15, customer = "BNCCHR90S05F205J", quantity = 2f),
            CustomerProvision(material = 16, customer = "01234567890", quantity = 3f),
            CustomerProvision(material = 16, customer = "MRNAND75T15F205I", quantity = 4f),
            CustomerProvision(material = 17, customer = "VLNSOF88U25F205H", quantity = 1f),
            CustomerProvision(material = 18, customer = "CRSMCO82A01F205G", quantity = 15f),
            CustomerProvision(material = 19, customer = "LBRGIA93B10F205F", quantity = 15f),
            CustomerProvision(material = 20, customer = "RZZSTE70C20F205E", quantity = 1f),
            CustomerProvision(material = 21, customer = "MNNFRN85D30F205D", quantity = 1f),
            CustomerProvision(material = 22, customer = "SRTMRE77E25F205C", quantity = 2f),
            CustomerProvision(material = 23, customer = "PLMMRT84F05F205B", quantity = 1f),
            CustomerProvision(material = 24, customer = "DCNALE91G15F205A", quantity = 2f),
            CustomerProvision(material = 25, customer = "GTTVAL80H25F205Z", quantity = 4f),
            CustomerProvision(material = 26, customer = "01234567890", quantity = 1f),
            CustomerProvision(material = 27, customer = "01234567891", quantity = 2f),
            CustomerProvision(material = 28, customer = "01234567892", quantity = 6f),
            CustomerProvision(material = 29, customer = "01234567893", quantity = 1f),
            CustomerProvision(material = 30, customer = "01234567894", quantity = 8f),
            CustomerProvision(material = 31, customer = "01234567895", quantity = 10f),
            CustomerProvision(material = 32, customer = "01234567896", quantity = 2f),
            CustomerProvision(material = 33, customer = "01234567897", quantity = 4f),
            CustomerProvision(material = 34, customer = "01234567898", quantity = 1f),
            CustomerProvision(material = 35, customer = "01234567899", quantity = 1f),
            CustomerProvision(material = 36, customer = "01234567900", quantity = 0.5f),
            CustomerProvision(material = 37, customer = "01234567901", quantity = 0.75f),
            CustomerProvision(material = 38, customer = "01234567902", quantity = 1000f),
            CustomerProvision(material = 39, customer = "01234567903", quantity = 1f),
            CustomerProvision(material = 40, customer = "01234567904", quantity = 2.5f),
            CustomerProvision(material = 41, customer = "01234567905", quantity = 1f),
            CustomerProvision(material = 42, customer = "01234567906", quantity = 1f),
            CustomerProvision(material = 43, customer = "01234567907", quantity = 0.2f),
            CustomerProvision(material = 44, customer = "01234567908", quantity = 3f),
            CustomerProvision(material = 45, customer = "01234567909", quantity = 1f),
            CustomerProvision(material = 46, customer = "01234567910", quantity = 5f),
            CustomerProvision(material = 47, customer = "01234567911", quantity = 1f),
            CustomerProvision(material = 48, customer = "01234567912", quantity = 1.25f),
            CustomerProvision(material = 49, customer = "01234567913", quantity = 1f),
            CustomerProvision(material = 50, customer = "01234567914", quantity = 0.5f),
            CustomerProvision(material = 61, customer = "BNCCHR90S05F205J", quantity = 21f)
        )

        customerProvisions.forEach { customerProvision ->
            repository.inventory.upsertCustomerProvision(customerProvision)
        }
    }

    suspend fun seedPropertyOwnership(repository: Repository){
        val propertyOwnerships = listOf(
            PropertyOwnership(customer = "RSSMRA80A01F205H", address = 51),
            PropertyOwnership(customer = "BNCLCU75B10L219Z", address = 53),
            PropertyOwnership(customer = "VRDNNA90C20F205G", address = 54),
            PropertyOwnership(customer = "GLLRBT85D15F205E", address = 55),
            PropertyOwnership(customer = "01234567890", address = 56),
            PropertyOwnership(customer = "01234567890", address = 57),
            PropertyOwnership(customer = "01234567890", address = 58),
            PropertyOwnership(customer = "FRRELE92E25F205D", address = 59),
            PropertyOwnership(customer = "RSSFRN70F30F205C", address = 60),
            PropertyOwnership(customer = "ESPSLV88G05F205B", address = 61),
            PropertyOwnership(customer = "RMNMTT82H10F205A", address = 62),
            PropertyOwnership(customer = "CLMSRA95I20F205P", address = 63),
            PropertyOwnership(customer = "RCCDVD78L15F205O", address = 64),
            PropertyOwnership(customer = "MRNLRA83M25F205N", address = 65),
            PropertyOwnership(customer = "RSSMRA80A01F205H", address = 66),
            PropertyOwnership(customer = "GRCGRG72P05F205M", address = 67),
            PropertyOwnership(customer = "BRNMRT89Q15F205L", address = 68),
            PropertyOwnership(customer = "GLLPTR81R25F205K", address = 69),
            PropertyOwnership(customer = "01234567890", address = 70),
            PropertyOwnership(customer = "BNCLCU75B10L219Z", address = 71),
            PropertyOwnership(customer = "01234567891", address = 72),
            PropertyOwnership(customer = "01234567892", address = 73),
            PropertyOwnership(customer = "01234567893", address = 74),
            PropertyOwnership(customer = "01234567894", address = 75),
            PropertyOwnership(customer = "01234567895", address = 76),
            PropertyOwnership(customer = "01234567896", address = 77),
            PropertyOwnership(customer = "01234567897", address = 78),
            PropertyOwnership(customer = "01234567898", address = 79),
            PropertyOwnership(customer = "01234567899", address = 80),
            PropertyOwnership(customer = "01234567900", address = 81),
            PropertyOwnership(customer = "01234567901", address = 82),
            PropertyOwnership(customer = "01234567902", address = 83),
            PropertyOwnership(customer = "01234567890", address = 84),
            PropertyOwnership(customer = "01234567903", address = 85),
            PropertyOwnership(customer = "01234567904", address = 86),
            PropertyOwnership(customer = "01234567905", address = 87),
            PropertyOwnership(customer = "01234567906", address = 88),
            PropertyOwnership(customer = "01234567907", address = 89),
            PropertyOwnership(customer = "01234567908", address = 90),
            PropertyOwnership(customer = "RSSMRA80A01F205H", address = 91),
            PropertyOwnership(customer = "01234567909", address = 92),
            PropertyOwnership(customer = "01234567910", address = 93),
            PropertyOwnership(customer = "01234567911", address = 94),
            PropertyOwnership(customer = "01234567912", address = 95),
            PropertyOwnership(customer = "01234567913", address = 96),
            PropertyOwnership(customer = "01234567890", address = 97),
            PropertyOwnership(customer = "01234567914", address = 98),
            PropertyOwnership(customer = "01234567914", address = 99),
            PropertyOwnership(customer = "01234567914", address = 100),
            PropertyOwnership(customer = "12345567890", address = 105)
        )

        propertyOwnerships.fastForEach { property ->
            println("Inserimento ownership: Cliente ${property.customer} su Indirizzo ${property.address}")
            repository.address.upsertPropertyOwnership(property)
        }
    }

    suspend fun seedWorksite(repository: Repository){
        val workSites = listOf(
            WorkSite(id = 1, startDate = LocalDate.of(2022, 1, 1), address = 10, endDate = LocalDate.of(2022, 2, 1), manager = 1, customer = "RCCDVD78L15F205O"),
            WorkSite(id = 2, startDate = LocalDate.of(2022, 2, 2), address = 10, endDate = LocalDate.of(2022, 3, 1), manager = 2, customer = "RCCDVD78L15F205O"),
            WorkSite(id = 3, startDate = LocalDate.of(2024, 1, 1), address = 10, endDate = null, manager = 3, customer = "RCCDVD78L15F205O"),
            WorkSite(id = 4, startDate = LocalDate.of(2023, 5, 1), address = 51, endDate = LocalDate.of(2023, 6, 1), manager = 15, customer = "RSSMRA80A01F205H"),
            WorkSite(id = 5, startDate = LocalDate.of(2023, 5, 10), address = 52, endDate = LocalDate.of(2023, 6, 10), manager = 15, customer = "VRDNNA90C20F205G"),
            WorkSite(id = 6, startDate = LocalDate.of(2023, 5, 15), address = 53, endDate = LocalDate.of(2023, 6, 15), manager = 15, customer = "BNCLCU75B10L219Z"),
            WorkSite(id = 7, startDate = LocalDate.of(2023, 5, 20), address = 54, endDate = LocalDate.of(2023, 6, 20), manager = 15, customer = "VRDNNA90C20F205G"),
            WorkSite(id = 8, startDate = LocalDate.of(2024, 2, 10), address = 5, endDate = null, manager = null, customer = "FRRELE92E25F205D"),
            WorkSite(id = 9, startDate = LocalDate.of(2024, 3, 15), address = 12, endDate = null, manager = null, customer = "GRCGRG72P05F205M"),
            WorkSite(id = 10, startDate = LocalDate.of(2023, 10, 10), address = 15, endDate = LocalDate.of(2023, 10, 10), manager = 5, customer = "BNCCHR90S05F205J"),
            WorkSite(id = 11, startDate = LocalDate.of(2023, 1, 10), address = 56, endDate = LocalDate.of(2023, 12, 31), manager = 6, customer = "01234567890"),
            WorkSite(id = 12, startDate = LocalDate.of(2023, 1, 10), address = 57, endDate = null, manager = 7, customer = "01234567890"),
            WorkSite(id = 13, startDate = LocalDate.of(2023, 1, 10), address = 58, endDate = null, manager = 8, customer = "01234567890"),
            WorkSite(id = 14, startDate = LocalDate.of(2024, 1, 10), address = 70, endDate = null, manager = 9, customer = "01234567890"),
            WorkSite(id = 15, startDate = LocalDate.of(2024, 1, 10), address = 84, endDate = null, manager = 10, customer = "01234567890"),
            WorkSite(id = 16, startDate = LocalDate.of(2023, 3, 1), address = 1, endDate = LocalDate.of(2023, 5, 1), manager = 1, customer = "RSSMRA80A01F205H"),
            WorkSite(id = 17, startDate = LocalDate.of(2023, 4, 15), address = 2, endDate = LocalDate.of(2023, 7, 20), manager = 2, customer = "VRDNNA90C20F205G"),
            WorkSite(id = 18, startDate = LocalDate.of(2023, 6, 1), address = 3, endDate = LocalDate.of(2023, 9, 15), manager = 4, customer = "BNCLCU75B10L219Z"),
            WorkSite(id = 19, startDate = LocalDate.of(2023, 8, 20), address = 4, endDate = LocalDate.of(2023, 11, 30), manager = 5, customer = "GLLRBT85D15F205E"),
            WorkSite(id = 20, startDate = LocalDate.of(2024, 1, 5), address = 6, endDate = null, manager = 6, customer = "RSSFRN70F30F205C"),
            WorkSite(id = 21, startDate = LocalDate.of(2024, 1, 12), address = 7, endDate = null, manager = 7, customer = "ESPSLV88G05F205B"),
            WorkSite(id = 22, startDate = LocalDate.of(2023, 2, 10), address = 8, endDate = LocalDate.of(2023, 4, 10), manager = 8, customer = "RMNMTT82H10F205A"),
            WorkSite(id = 23, startDate = LocalDate.of(2023, 5, 25), address = 9, endDate = LocalDate.of(2023, 8, 5), manager = 9, customer = "CLMSRA95I20F205P"),
            WorkSite(id = 24, startDate = LocalDate.of(2024, 1, 20), address = 11, endDate = null, manager = 11, customer = "MRNLRA83M25F205N"),
            WorkSite(id = 25, startDate = LocalDate.of(2023, 7, 1), address = 13, endDate = LocalDate.of(2023, 10, 1), manager = 13, customer = "BRNMRT89Q15F205L"),
            WorkSite(id = 26, startDate = LocalDate.of(2023, 9, 10), address = 14, endDate = LocalDate.of(2023, 12, 10), manager = 14, customer = "GLLPTR81R25F205K"),
            WorkSite(id = 27, startDate = LocalDate.of(2023, 11, 1), address = 26, endDate = LocalDate.of(2024, 2, 1), manager = 1, customer = "01234567890"),
            WorkSite(id = 28, startDate = LocalDate.of(2023, 12, 15), address = 27, endDate = LocalDate.of(2024, 3, 15), manager = 2, customer = "01234567891"),
            WorkSite(id = 29, startDate = LocalDate.of(2024, 2, 1), address = 28, endDate = null, manager = 3, customer = "01234567892"),
            WorkSite(id = 30, startDate = LocalDate.of(2024, 2, 20), address = 29, endDate = null, manager = 4, customer = "01234567893"),
            WorkSite(id = 31, startDate = LocalDate.of(2024, 3, 1), address = 30, endDate = null, manager = 5, customer = "01234567894"),
            WorkSite(id = 32, startDate = LocalDate.of(2023, 4, 1), address = 31, endDate = LocalDate.of(2023, 5, 1), manager = 6, customer = "01234567895"),
            WorkSite(id = 33, startDate = LocalDate.of(2023, 5, 15), address = 32, endDate = LocalDate.of(2023, 8, 15), manager = 7, customer = "01234567896"),
            WorkSite(id = 34, startDate = LocalDate.of(2023, 7, 1), address = 33, endDate = LocalDate.of(2023, 9, 1), manager = 8, customer = "01234567897"),
            WorkSite(id = 35, startDate = LocalDate.of(2023, 8, 15), address = 34, endDate = LocalDate.of(2023, 11, 15), manager = 9, customer = "01234567898"),
            WorkSite(id = 36, startDate = LocalDate.of(2024, 4, 1), address = 35, endDate = null, manager = 10, customer = "01234567899"),
            WorkSite(id = 37, startDate = LocalDate.of(2024, 4, 15), address = 36, endDate = null, manager = 11, customer = "01234567900"),
            WorkSite(id = 38, startDate = LocalDate.of(2024, 5, 1), address = 37, endDate = null, manager = 12, customer = "01234567901"),
            WorkSite(id = 39, startDate = LocalDate.of(2023, 6, 1), address = 38, endDate = LocalDate.of(2023, 7, 1), manager = 13, customer = "01234567902"),
            WorkSite(id = 40, startDate = LocalDate.of(2023, 8, 20), address = 39, endDate = LocalDate.of(2023, 10, 20), manager = 14, customer = "01234567903"),
            WorkSite(id = 41, startDate = LocalDate.of(2023, 11, 5), address = 40, endDate = LocalDate.of(2024, 1, 5), manager = 15, customer = "01234567904"),
            WorkSite(id = 42, startDate = LocalDate.of(2024, 5, 10), address = 41, endDate = null, manager = 1, customer = "01234567905"),
            WorkSite(id = 43, startDate = LocalDate.of(2024, 5, 20), address = 42, endDate = null, manager = 2, customer = "01234567906"),
            WorkSite(id = 44, startDate = LocalDate.of(2024, 6, 1), address = 43, endDate = null, manager = 3, customer = "01234567907"),
            WorkSite(id = 45, startDate = LocalDate.of(2023, 2, 1), address = 44, endDate = LocalDate.of(2023, 3, 1), manager = 4, customer = "01234567908"),
            WorkSite(id = 46, startDate = LocalDate.of(2023, 4, 10), address = 45, endDate = LocalDate.of(2023, 6, 10), manager = 5, customer = "01234567909"),
            WorkSite(id = 47, startDate = LocalDate.of(2023, 7, 15), address = 46, endDate = LocalDate.of(2023, 9, 15), manager = 6, customer = "01234567910"),
            WorkSite(id = 48, startDate = LocalDate.of(2024, 1, 10), address = 47, endDate = null, manager = 7, customer = "01234567911"),
            WorkSite(id = 49, startDate = LocalDate.of(2024, 2, 15), address = 48, endDate = null, manager = 8, customer = "01234567912"),
            WorkSite(id = 50, startDate = LocalDate.of(2024, 3, 20), address = 49, endDate = null, manager = 9, customer = "01234567913"),
            WorkSite(id = 51, startDate = LocalDate.of(2024, 4, 10), address = 50, endDate = null, manager = 10, customer = "01234567914"),
            WorkSite(id = 52, startDate = LocalDate.of(2026, 2, 17), address = 105, endDate = null, manager = null, customer = "BNCCHR90S05F205J"),
            WorkSite(id = 54, startDate = LocalDate.of(2026, 2, 17), address = 106, endDate = null, manager = 16, customer = null),
            WorkSite(id = 55, startDate = LocalDate.of(2026, 5, 15), address = 107, endDate = null, manager = null, customer = "ESPGNN80A01F839U")
        )

        workSites.forEach { worksite ->
            repository.job.upsertWorkSite(worksite)
        }
    }

    suspend fun seedJob(repository: Repository){
        val jobs = listOf(
            Job(id = 1, date = LocalDate.of(2023, 10, 10), startTime = LocalTime.of(8, 30, 0), description = "Installazione rapida prese", peopleNumber = 2, address = 15, endTime = LocalTime.of(12, 30, 0), electric = true, alarm = false, airConditioning = false, customer = "BNCCHR90S05F205J", workSite = 10),
            Job(id = 2, date = LocalDate.of(2023, 10, 10), startTime = LocalTime.of(14, 0, 0), description = "Collaudo finale impianto", peopleNumber = 1, address = 15, endTime = LocalTime.of(18, 0, 0), electric = true, alarm = true, airConditioning = false, customer = "BNCCHR90S05F205J", workSite = 10),
            Job(id = 3, date = LocalDate.of(2023, 5, 10), startTime = LocalTime.of(8, 0, 0), description = "Cablaggio strutturato uffici", peopleNumber = 4, address = 56, endTime = LocalTime.of(17, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "01234567890", workSite = 11),
            Job(id = 4, date = LocalDate.of(2024, 2, 15), startTime = LocalTime.of(9, 0, 0), description = "Manutenzione climatizzatori industriali", peopleNumber = 2, address = 70, endTime = LocalTime.of(13, 0, 0), electric = false, alarm = false, airConditioning = true, customer = "01234567890", workSite = 14),
            Job(id = 5, date = LocalDate.of(2024, 3, 20), startTime = LocalTime.of(10, 0, 0), description = "Verifica sensori perimetrali", peopleNumber = 1, address = 84, endTime = LocalTime.of(12, 0, 0), electric = false, alarm = true, airConditioning = false, customer = "01234567890", workSite = 15),
            Job(id = 6, date = LocalDate.of(2024, 1, 15), startTime = LocalTime.of(8, 30, 0), description = "Riparazione citofono guasto", peopleNumber = 1, address = 1, endTime = LocalTime.of(10, 30, 0), electric = true, alarm = false, airConditioning = false, customer = "RSSMRA80A01F205H", workSite = null),
            Job(id = 7, date = LocalDate.of(2024, 1, 20), startTime = LocalTime.of(14, 30, 0), description = "Sostituzione batteria allarme", peopleNumber = 1, address = 2, endTime = LocalTime.of(16, 0, 0), electric = false, alarm = true, airConditioning = false, customer = "VRDNNA90C20F205G", workSite = null),
            Job(id = 8, date = LocalDate.of(2023, 6, 15), startTime = LocalTime.of(8, 0, 0), description = "Rifacimento completo elettrico e condizionamento", peopleNumber = 3, address = 3, endTime = LocalTime.of(18, 0, 0), electric = true, alarm = false, airConditioning = true, customer = "BNCLCU75B10L219Z", workSite = 18),
            Job(id = 9, date = LocalDate.of(2022, 1, 10), startTime = LocalTime.of(8, 0, 0), description = "Tracciatura impianti", peopleNumber = 2, address = 10, endTime = LocalTime.of(12, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "RCCDVD78L15F205O", workSite = 3),
            Job(id = 10, date = LocalDate.of(2022, 1, 10), startTime = LocalTime.of(13, 0, 0), description = "Posa tubazioni", peopleNumber = 2, address = 10, endTime = LocalTime.of(17, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "RCCDVD78L15F205O", workSite = 3),
            Job(id = 11, date = LocalDate.of(2024, 3, 1), startTime = LocalTime.of(8, 30, 0), description = "Sopralluogo tecnico per nuovo impianto", peopleNumber = 2, address = 5, endTime = LocalTime.of(12, 0, 0), electric = true, alarm = true, airConditioning = true, customer = "FRRELE92E25F205D", workSite = 8),
            Job(id = 12, date = LocalDate.of(2024, 3, 25), startTime = LocalTime.of(9, 0, 0), description = "Sostituzione termostato", peopleNumber = 1, address = 12, endTime = LocalTime.of(11, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "GRCGRG72P05F205M", workSite = 9),
            Job(id = 13, date = LocalDate.of(2023, 11, 20), startTime = LocalTime.of(10, 0, 0), description = "Controllo periodico allarme", peopleNumber = 1, address = 4, endTime = LocalTime.of(12, 0, 0), electric = false, alarm = true, airConditioning = false, customer = "GLLRBT85D15F205E", workSite = 19),
            Job(id = 14, date = LocalDate.of(2024, 2, 10), startTime = LocalTime.of(8, 0, 0), description = "Installazione split camera", peopleNumber = 1, address = 7, endTime = LocalTime.of(10, 0, 0), electric = false, alarm = false, airConditioning = true, customer = "ESPSLV88G05F205B", workSite = 21),
            Job(id = 15, date = LocalDate.of(2023, 9, 5), startTime = LocalTime.of(14, 0, 0), description = "Allacciamento cucina", peopleNumber = 2, address = 9, endTime = LocalTime.of(16, 30, 0), electric = true, alarm = false, airConditioning = false, customer = "CLMSRA95I20F205P", workSite = 23),
            Job(id = 16, date = LocalDate.of(2023, 12, 5), startTime = LocalTime.of(8, 30, 0), description = "Montaggio corpi illuminanti esterni", peopleNumber = 2, address = 14, endTime = LocalTime.of(13, 30, 0), electric = true, alarm = false, airConditioning = false, customer = "GLLPTR81R25F205K", workSite = 26),
            Job(id = 17, date = LocalDate.of(2023, 4, 15), startTime = LocalTime.of(8, 0, 0), description = "Revisione quadri elettrici officina", peopleNumber = 2, address = 31, endTime = LocalTime.of(12, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "01234567895", workSite = 32),
            Job(id = 18, date = LocalDate.of(2023, 6, 10), startTime = LocalTime.of(21, 0, 0), description = "Intervento emergenza notturna guasto luci", peopleNumber = 2, address = 32, endTime = LocalTime.of(23, 30, 0), electric = true, alarm = false, airConditioning = false, customer = "01234567896", workSite = 33),
            Job(id = 19, date = LocalDate.of(2023, 10, 1), startTime = LocalTime.of(9, 0, 0), description = "Configurazione rete telecamere IP", peopleNumber = 3, address = 34, endTime = LocalTime.of(18, 0, 0), electric = false, alarm = true, airConditioning = false, customer = "01234567898", workSite = 35),
            Job(id = 20, date = LocalDate.of(2026, 2, 12), startTime = LocalTime.of(8, 0, 0), description = "Smontato lampadari", peopleNumber = 2, address = 12, endTime = LocalTime.of(17, 33, 21), electric = true, alarm = false, airConditioning = false, customer = "GRCGRG72P05F205M", workSite = null),
            Job(id = 21, date = LocalDate.of(2026, 2, 17), startTime = LocalTime.of(9, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 105, endTime = LocalTime.of(11, 0, 0), electric = true, alarm = false, airConditioning = true, customer = "BNCCHR90S05F205J", workSite = 52),
            Job(id = 22, date = LocalDate.of(2026, 2, 17), startTime = LocalTime.of(9, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 106, endTime = LocalTime.of(11, 0, 0), electric = true, alarm = false, airConditioning = false, customer = null, workSite = 54),
            Job(id = 95, date = LocalDate.of(2022, 3, 2), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 10, endTime = LocalTime.of(9, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "RCCDVD78L15F205O", workSite = 3),
            Job(id = 96, date = LocalDate.of(2022, 3, 3), startTime = LocalTime.of(8, 0, 0), description = "Visto " +
                    "lavoro", peopleNumber = 4, address = 64, endTime = LocalTime.of(9, 0, 0), electric = true, alarm =
            false, airConditioning = false, customer = "RCCDVD78L15F205O", workSite = null),
            Job(id = 97, date = LocalDate.of(2022, 3, 4), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 3, address = 51, endTime = LocalTime.of(9, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "RSSMRA80A01F205H", workSite = 4),
            Job(id = 98, date = LocalDate.of(2023, 3, 2), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 54, endTime = LocalTime.of(9, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "VRDNNA90C20F205G", workSite = 5),
            Job(id = 99, date = LocalDate.of(2022, 5, 2), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 3, address = 71, endTime = LocalTime.of(9, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "BNCLCU75B10L219Z", workSite = 6),
            Job(id = 100, date = LocalDate.of(2022, 9, 2), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 4, address = 2, endTime = LocalTime.of(9, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "VRDNNA90C20F205G", workSite = 7),
            Job(id = 103, date = LocalDate.of(2023, 6, 2), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 56, endTime = LocalTime.of(9, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "01234567890", workSite = 12),
            Job(id = 104, date = LocalDate.of(2024, 1, 2), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 3, address = 57, endTime = LocalTime.of(9, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "01234567890", workSite = 13),
            Job(id = 105, date = LocalDate.of(2025, 12, 2), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 4, address = 66, endTime = LocalTime.of(9, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "RSSMRA80A01F205H", workSite = 16),
            Job(id = 106, date = LocalDate.of(2022, 3, 2), startTime = LocalTime.of(11, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 2, endTime = LocalTime.of(12, 30, 0), electric = false, alarm = true, airConditioning = false, customer = "VRDNNA90C20F205G", workSite = 17),
            Job(id = 107, date = LocalDate.of(2022, 8, 2), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 60, endTime = LocalTime.of(9, 0, 0), electric = false, alarm = true, airConditioning = false, customer = "RSSFRN70F30F205C", workSite = 20),
            Job(id = 108, date = LocalDate.of(2022, 3, 7), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 62, endTime = LocalTime.of(9, 0, 0), electric = false, alarm = true, airConditioning = false, customer = "RMNMTT82H10F205A", workSite = 22),
            Job(id = 109, date = LocalDate.of(2022, 4, 8), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 65, endTime = LocalTime.of(9, 0, 0), electric = false, alarm = true, airConditioning = false, customer = "MRNLRA83M25F205N", workSite = 24),
            Job(id = 110, date = LocalDate.of(2022, 5, 9), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 3, address = 68, endTime = LocalTime.of(9, 0, 0), electric = false, alarm = true, airConditioning = false, customer = "BRNMRT89Q15F205L", workSite = 25),
            Job(id = 111, date = LocalDate.of(2022, 6, 12), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 4, address = 97, endTime = LocalTime.of(9, 0, 0), electric = false, alarm = true, airConditioning = false, customer = "01234567890", workSite = 27),
            Job(id = 112, date = LocalDate.of(2022, 7, 13), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 72, endTime = LocalTime.of(9, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "01234567891", workSite = 28),
            Job(id = 113, date = LocalDate.of(2022, 8, 14), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 73, endTime = LocalTime.of(9, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "01234567892", workSite = 29),
            Job(id = 114, date = LocalDate.of(2022, 9, 12), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 74, endTime = LocalTime.of(9, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "01234567893", workSite = 30),
            Job(id = 115, date = LocalDate.of(2022, 10, 15), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 75, endTime = LocalTime.of(9, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "01234567894", workSite = 31),
            Job(id = 116, date = LocalDate.of(2022, 11, 16), startTime = LocalTime.of(17, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 78, endTime = LocalTime.of(18, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "01234567897", workSite = 34),
            Job(id = 117, date = LocalDate.of(2022, 12, 17), startTime = LocalTime.of(18, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 80, endTime = LocalTime.of(19, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "01234567899", workSite = 36),
            Job(id = 118, date = LocalDate.of(2023, 2, 2), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 81, endTime = LocalTime.of(9, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "01234567900", workSite = 37),
            Job(id = 119, date = LocalDate.of(2023, 4, 3), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 82, endTime = LocalTime.of(9, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "01234567901", workSite = 38),
            Job(id = 120, date = LocalDate.of(2023, 6, 4), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 3, address = 83, endTime = LocalTime.of(9, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "01234567902", workSite = 39),
            Job(id = 121, date = LocalDate.of(2023, 8, 5), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 85, endTime = LocalTime.of(9, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "01234567903", workSite = 40),
            Job(id = 122, date = LocalDate.of(2023, 10, 6), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 86, endTime = LocalTime.of(9, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "01234567904", workSite = 41),
            Job(id = 123, date = LocalDate.of(2023, 12, 7), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 87, endTime = LocalTime.of(9, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "01234567905", workSite = 42),
            Job(id = 124, date = LocalDate.of(2024, 1, 1), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 88, endTime = LocalTime.of(9, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "01234567906", workSite = 43),
            Job(id = 125, date = LocalDate.of(2024, 3, 31), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 89, endTime = LocalTime.of(9, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "01234567907", workSite = 44),
            Job(id = 127, date = LocalDate.of(2024, 5, 3), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 90, endTime = LocalTime.of(9, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "01234567908", workSite = 45),
            Job(id = 128, date = LocalDate.of(2024, 7, 29), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 92, endTime = LocalTime.of(9, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "01234567909", workSite = 46),
            Job(id = 129, date = LocalDate.of(2024, 9, 5), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 93, endTime = LocalTime.of(9, 0, 0), electric = false, alarm = false, airConditioning = true, customer = "01234567910", workSite = 47),
            Job(id = 130, date = LocalDate.of(2024, 11, 27), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 94, endTime = LocalTime.of(9, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "01234567911", workSite = 48),
            Job(id = 131, date = LocalDate.of(2025, 1, 2), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 4, address = 95, endTime = LocalTime.of(9, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "01234567912", workSite = 49),
            Job(id = 132, date = LocalDate.of(2025, 2, 3), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 96, endTime = LocalTime.of(9, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "01234567913", workSite = 50),
            Job(id = 133, date = LocalDate.of(2025, 3, 4), startTime = LocalTime.of(8, 0, 0), description = "Visto lavoro", peopleNumber = 2, address = 100, endTime = LocalTime.of(9, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "01234567914", workSite = 51),
            Job(id = 135, date = LocalDate.of(2026, 2, 18), startTime = LocalTime.of(14, 0, 0), description = "", peopleNumber = 2, address = 12, endTime = LocalTime.of(18, 0, 0), electric = false, alarm = false, airConditioning = true, customer = "GRCGRG72P05F205M", workSite = null),
            Job(id = 136, date = LocalDate.of(2026, 5, 15), startTime = LocalTime.of(8, 0, 0), description = "Rifacimento impianto elettrico", peopleNumber = 2, address = 107, endTime = LocalTime.of(18, 0, 0), electric = true, alarm = false, airConditioning = false, customer = "ESPGNN80A01F839U", workSite = 55)
        )

        jobs.forEach { job ->
            repository.job.upsertJob(job)
        }
    }

    suspend fun seedImage(repository: Repository){
        val images = listOf(
            Image(id = 1, path = "/img/interventi/int1_pre.jpg", material = null, job = 1),
            Image(id = 2, path = "/img/interventi/int1_post.jpg", material = null, job = 1),
            Image(id = 3, path = "/img/interventi/collaudo_allarme_15.jpg", material = null, job = 2),
            Image(id = 4, path = "/img/materiali/ajax_hub2_front.png", material = 4, job = null),
            Image(id = 5, path = "/img/materiali/daikin_d600_label.png", material = 3, job = null),
            Image(id = 6, path = "/img/materiali/bticino_matix_switch.jpg", material = 5, job = null),
            Image(id = 7, path = "/img/installazioni/mitsubishi_c500_montato.jpg", material = 6, job = 10),
            Image(id = 8, path = "/img/installazioni/ajax_pir_posizionato.jpg", material = 1, job = 1),
            Image(id = 9, path = "/img/cantieri/edilmilan_quadro_finito.jpg", material = 40, job = 3),
            Image(id = 10, path = "/img/cantieri/cablaggio_cat6_test.jpg", material = 50, job = 3),
            Image(id = 11, path = "/img/manutenzioni/batteria_fiamm_sostituita.jpg", material = 15, job = 7),
            Image(id = 12, path = "/img/manutenzioni/termostato_bticino_wifi.jpg", material = 43, job = 12),
            Image(id = 13, path = "/img/materiali/perfera_12000_ang1.jpg", material = 27, job = null),
            Image(id = 14, path = "/img/materiali/perfera_12000_ang2.jpg", material = 27, job = null),
            Image(id = 15, path = "/img/interventi/emergenza_notturna_1.jpg", material = null, job = 18),
            Image(id = 16, path = "/img/interventi/emergenza_notturna_2.jpg", material = null, job = 18),
            Image(id = 17, path = "/img/stock/nastro_3m_bulk.jpg", material = 56, job = null),
            Image(id = 18, path = "/img/stock/corrugato_25mm_bobina.jpg", material = 22, job = null)
        )

        images.forEach { image ->
            repository.job.upsertImage(image)
        }
    }

    suspend fun seedFutureJobMaterial(repository: Repository){
        val futureJobMaterials = listOf(
            FutureJobMaterial(job = 2, material = 1, quantity = 2f),
            FutureJobMaterial(job = 6, material = 2, quantity = 10f),
            FutureJobMaterial(job = 136, material = 5, quantity = 80f),
            FutureJobMaterial(job = 2, material = 11, quantity = 1f),
            FutureJobMaterial(job = 7, material = 15, quantity = 1f),
            FutureJobMaterial(job = 18, material = 17, quantity = 5f),
            FutureJobMaterial(job = 8, material = 19, quantity = 2f),
            FutureJobMaterial(job = 3, material = 21, quantity = 50f),
            FutureJobMaterial(job = 10, material = 22, quantity = 100f),
            FutureJobMaterial(job = 8, material = 27, quantity = 2f),
            FutureJobMaterial(job = 8, material = 32, quantity = 15f),
            FutureJobMaterial(job = 8, material = 33, quantity = 15f),
            FutureJobMaterial(job = 4, material = 35, quantity = 1.5f),
            FutureJobMaterial(job = 3, material = 40, quantity = 1f),
            FutureJobMaterial(job = 18, material = 41, quantity = 2f),
            FutureJobMaterial(job = 3, material = 50, quantity = 150f),
            FutureJobMaterial(job = 4, material = 55, quantity = 4f),
            FutureJobMaterial(job = 6, material = 56, quantity = 0.5f)
        )

        futureJobMaterials.forEach { futureJobMaterial ->
            repository.job.upsertFutureJobMaterial(futureJobMaterial)
        }
    }

    suspend fun seedMaterialUsage(repository: Repository){
        val materialUsages = listOf(
            MaterialUsage(material = 1, job = 2, quantity = 2f),
            MaterialUsage(material = 2, job = 6, quantity = 8.5f),
            MaterialUsage(material = 5, job = 1, quantity = 6f),
            MaterialUsage(material = 11, job = 2, quantity = 1f),
            MaterialUsage(material = 15, job = 7, quantity = 1f),
            MaterialUsage(material = 17, job = 18, quantity = 5f),
            MaterialUsage(material = 18, job = 1, quantity = 4f),
            MaterialUsage(material = 19, job = 8, quantity = 2f),
            MaterialUsage(material = 21, job = 3, quantity = 48f),
            MaterialUsage(material = 22, job = 10, quantity = 95f),
            MaterialUsage(material = 23, job = 1, quantity = 7f),
            MaterialUsage(material = 27, job = 8, quantity = 2f),
            MaterialUsage(material = 32, job = 8, quantity = 14.5f),
            MaterialUsage(material = 33, job = 8, quantity = 14.5f),
            MaterialUsage(material = 35, job = 4, quantity = 1.8f),
            MaterialUsage(material = 40, job = 3, quantity = 1f),
            MaterialUsage(material = 41, job = 18, quantity = 2f),
            MaterialUsage(material = 50, job = 3, quantity = 142.5f),
            MaterialUsage(material = 55, job = 4, quantity = 4f),
            MaterialUsage(material = 56, job = 6, quantity = 0.3f),
            MaterialUsage(material = 62, job = 135, quantity = 1f),
            MaterialUsage(material = 63, job = 135, quantity = 4f)
        )

        materialUsages.forEach { materialUsage ->
            repository.job.upsertMaterialUsage(materialUsage)
        }
    }

    suspend fun seedRevenue(repository: Repository){
        val revenues = listOf(
            Revenue(id = 1, invoice = 101, issueDate = LocalDate.of(2023, 10, 15), amount = 450f,  amountPaid = 450f,
                percent = 100, collectionDate = LocalDate.of(2023, 10, 20), worksite = 10, job = null),
            Revenue(id = 2, invoice = 102, issueDate = LocalDate.of(2023, 5, 20), amount = 2500f,  amountPaid =
            750f, percent = 30, collectionDate = LocalDate.of(2023, 6, 15), worksite = 11, job = null),
            Revenue(id = 3, invoice = 103, issueDate = LocalDate.of(2022, 3, 5), amount = 1200f, amountPaid = 1200f,
                percent = 100, collectionDate = LocalDate.of(2022, 4, 10), worksite = 1, job = null),
            Revenue(id = 4, invoice = 104, issueDate = LocalDate.of(2023, 6, 30), amount = 3500f, amountPaid = 1750f, percent = 50, collectionDate = null, worksite = 18, job = null),
            Revenue(id = 5, invoice = 105, issueDate = LocalDate.of(2023, 5, 5), amount = 850f,  amountPaid = 850f, percent = 100, collectionDate = LocalDate.of(2023, 5, 20), worksite = 32, job = null),
            Revenue(id = 6, invoice = 201, issueDate = LocalDate.of(2024, 1, 16), amount = 85.5f,  amountPaid = 85.5f, percent = 100, collectionDate = LocalDate.of(2024, 1, 16), worksite = null, job = 6),
            Revenue(id = 7, invoice = 202, issueDate = LocalDate.of(2024, 1, 21), amount = 120f,  amountPaid = 120f, percent = 100, collectionDate = LocalDate.of(2024, 2, 5), worksite = null, job = 7),
            Revenue(id = 8, invoice = 203, issueDate = LocalDate.of(2023, 6, 11), amount = 350f,  amountPaid = 350f, percent = 100, collectionDate = LocalDate.of(2023, 6, 11), worksite = null, job = 18),
            Revenue(id = 9, invoice = 204, issueDate = LocalDate.of(2026, 2, 11), amount = 150f,  amountPaid = 150f, percent = 100, collectionDate = null, worksite = null, job = 9),
            Revenue(id = 10, invoice = 106, issueDate = LocalDate.of(2023, 4, 1), amount = 5000f,  amountPaid = 3000f, percent = 60, collectionDate = LocalDate.of(2023, 4, 15), worksite = 13, job = null),
            Revenue(id = 11, invoice = 107, issueDate = LocalDate.of(2023, 10, 5), amount = 3300f,  amountPaid = 1320f, percent = 40, collectionDate = LocalDate.of(2023, 11, 1), worksite = 13, job = null),
            Revenue(id = 12, invoice = 1, issueDate = LocalDate.of(2026, 2, 17), amount = 2526.0f,  amountPaid = 0f,
                percent = 0, collectionDate = null, worksite = 54, job = null),
            Revenue(id = 13, invoice = 2, issueDate = LocalDate.of(2026, 2, 18), amount = 6000f,  amountPaid = 2400f, percent = 40, collectionDate = LocalDate.of(2026, 3, 4), worksite = null, job = 135),
            Revenue(id = 14, invoice = 100, issueDate = LocalDate.of(2022, 3, 17), amount = 3000f,  amountPaid = 2700f, percent = 90, collectionDate = LocalDate.of(2024, 5, 2), worksite = 2, job = null),
            Revenue(id = 15, invoice = 6, issueDate = LocalDate.of(2024, 1, 20), amount = 200f,  amountPaid = 100f, percent = 50, collectionDate = LocalDate.of(2024, 1, 25), worksite = null, job = 6)
        )

        revenues.forEach { revenue ->
            repository.accounting.upsertRevenue(revenue)
        }
    }
}
