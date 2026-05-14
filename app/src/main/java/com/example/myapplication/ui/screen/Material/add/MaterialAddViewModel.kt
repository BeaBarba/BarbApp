package com.example.myapplication.ui.screen.Material.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.AirConditioner
import com.example.myapplication.data.database.Material
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.data.modules.MachineType
import com.example.myapplication.data.modules.SplitNumber
import com.example.myapplication.data.repository.Repository
import com.example.myapplication.ui.component.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal

data class MaterialAddState(
    val materialId : Int = 0,
    val type : JobType = JobType.NONE,
    val brand : String = "",
    val model : String = "",
    val category : String = "",
    val availableQuantity : String = "",
    val unitMeasurement : String = "",
    val customer : String? = null,
    val serialNumber : String = "",
    val btu : String = "",
    val yearOfInstallation : String = "",
    val machineType : MachineType = MachineType.NONE,
    val splitNumber : SplitNumber = SplitNumber.NONE,
    val gasQty : String = "",
    val gasType : String = "",

    val jobTypeMenu : List<MenuItem> = emptyList(),
    val categorySuggestionsList : List<String> = emptyList(),
    val errorMessage : String? = null
)

interface MaterialAddActions{
    fun populateView(materialId : Int?, serialNumber: String?)
    fun getJobTypeMenu()
    fun setCategory(category : String)
    fun getCategorySuggestions()
    fun setBrand(brand : String)
    fun setModel(model: String)
    fun setQuantity(quantity : String)
    fun setUnit(unit : String)
    fun setCustomer(cf : String)
    fun setSerialNumber(serialNumber : String)
    fun setBtu(btu : String)
    fun setYearInstallation(year : String)
    fun getMachineTypeMenu() : List<MenuItem>
    fun getSplitNumbersMenu() : List<MenuItem>
    fun setGasQty(qty : String)
    fun setGasType(type : String)
    fun saveMaterial(onSuccess : (Int) -> Unit)
    fun resetErrorMessage()
}

class MaterialAddViewModel(
    private val repository : Repository
) : ViewModel() {

    private val _state = MutableStateFlow(MaterialAddState())

    val state = _state.asStateFlow()

    val actions = object : MaterialAddActions {

        override fun populateView(materialId: Int?, serialNumber: String?) {
            getJobTypeMenu()
            getCategorySuggestions()
            materialId?.let{
                viewModelScope.launch{
                    val material = repository.inventory.getMaterialWithAirConditionalDetails(materialId)
                    material?.let {
                        _state.update {
                            it.copy(
                                materialId = materialId,
                                type = material.material.type,
                                brand = material.material.brand,
                                model = material.material.model,
                                category = material.material.category,
                                availableQuantity = material.material.availableQuantity.toString(),
                                unitMeasurement = material.material.unitMeasurement
                            )
                        }
                    }
                }

                serialNumber?.let {
                    viewModelScope.launch(Dispatchers.IO) {
                        val airConditioner = repository.inventory.getAirConditionerById(serialNumber, materialId)
                        airConditioner?.let {
                            _state.update {
                                it.copy(
                                    serialNumber = serialNumber,
                                    btu = airConditioner.btu.toString(),
                                    yearOfInstallation = airConditioner.yearInstallation?.toString() ?: "",
                                    machineType = airConditioner.machineType,
                                    splitNumber = airConditioner.splitNumber ?: SplitNumber.NONE,
                                    gasQty = if(airConditioner.gasQty != 0f) airConditioner.gasQty.toString() else "",
                                    gasType = airConditioner.gasType,
                                )
                            }
                        }
                    }
                }
            }
        }

        override fun getJobTypeMenu() {
            val typeMenu = JobType.entries
                .filter{item -> item != JobType.NONE}
                .map { item ->
                    MenuItem(
                        idValues = Pair(1, item.name),
                        name = item.name,
                        onClick = { setJobType(item) }
                    )
                }
            _state.update { it.copy(jobTypeMenu = typeMenu) }
        }

        override fun setCategory(category: String) {
            _state.update { it.copy(category = category) }
        }

        override fun getCategorySuggestions() {
            viewModelScope.launch {
                val categories = repository.inventory.getAllCategoriesOfMaterials()
                    ?.sorted()
                _state.update { it.copy(categorySuggestionsList = categories ?: emptyList()) }
            }
        }

        override fun setBrand(brand: String) {
            _state.update { it.copy(brand = brand) }
        }

        override fun setModel(model: String) {
            _state.update { it.copy(model = model) }
        }

        override fun setQuantity(quantity: String) {
            _state.update { it.copy(availableQuantity = quantity) }
        }

        override fun setUnit(unit: String) {
            _state.update { it.copy(unitMeasurement =  unit) }
        }

        override fun setCustomer(cf: String) {
            _state.update { it.copy(customer = cf) }
        }

        override fun setSerialNumber(serialNumber: String) {
            _state.update { it.copy(serialNumber = serialNumber) }
        }

        override fun setBtu(btu: String) {
            _state.update { it.copy(btu = btu) }
        }

        override fun setYearInstallation(year: String) {
            _state.update { it.copy(yearOfInstallation = year) }
        }

        override fun getMachineTypeMenu() : List<MenuItem> {
            return MachineType.entries
                .map { item ->
                    MenuItem(
                        idValues = Pair(1, item.name),
                        name = item.name,
                        onClick = {setMachineType(item)}
                    )
                }
        }

        override fun getSplitNumbersMenu(): List<MenuItem> {
            return SplitNumber.entries
                .filter { item -> item != SplitNumber.NONE }
                .map { item ->
                    MenuItem(
                        idValues = Pair(1, item.name),
                        name = item.name,
                        onClick = {setSplitNumber(item)}
                    )
                }
        }

        override fun setGasQty(qty: String) {
            _state.update { it.copy(gasQty = qty) }
        }

        override fun setGasType(type: String) {
            _state.update { it.copy(gasType = type) }
        }

        override fun saveMaterial(onSuccess: (Int) -> Unit) {
            val currentState = state.value

            if(checkRequirements()) return

            viewModelScope.launch {
                val material = Material(
                    id = currentState.materialId,
                    model = currentState.model,
                    brand = currentState.brand,
                    type = currentState.type,
                    category = currentState.category,
                    availableQuantity = 0f,
                    unitMeasurement = currentState.unitMeasurement.uppercase()
                )
                val quantity = currentState.availableQuantity.toFloatOrNull() ?: 0f
                val airConditioner = if(currentState.type == JobType.CDZ && currentState.serialNumber.isNotBlank()) {
                    AirConditioner(
                        serialNumber = currentState.serialNumber,
                        material = currentState.materialId,
                        machineType = currentState.machineType,
                        gasQty = if(!checkStringIsBigDecimal(currentState.gasQty)) BigDecimal(currentState.gasQty)
                            .toFloat() else 0f,
                        gasType = currentState.gasType,
                        btu = currentState.btu.toInt(),
                        splitNumber = if(currentState.splitNumber == SplitNumber.NONE) null else currentState.splitNumber,
                        yearInstallation = if(currentState.yearOfInstallation.isBlank()) null else currentState
                            .yearOfInstallation.toInt()
                    )
                }else null

                val materialId = repository.inventory.saveMaterialDetails(
                    material = material,
                    airConditioner = airConditioner,
                    customerCF = currentState.customer,
                    quantity = quantity
                )
                onSuccess(materialId)
            }
        }

        override fun resetErrorMessage() {
            _state.update { it.copy(errorMessage = null) }
        }

    }

    private fun setJobType(type: JobType) {
        _state.update {
            it.copy(
                type = type,
                serialNumber = if(type == JobType.CDZ) it.serialNumber else "",
                btu = if(type == JobType.CDZ) it.btu else "",
                yearOfInstallation = if(type == JobType.CDZ) it.yearOfInstallation else "",
                machineType = if(type == JobType.CDZ) it.machineType else MachineType.NONE,
                splitNumber = if(type == JobType.CDZ) it.splitNumber else SplitNumber.NONE,
                gasQty = if(type == JobType.CDZ) it.gasQty else "",
                gasType = if(type == JobType.CDZ) it.gasType else "",
            )
        }
    }

    private fun setMachineType(type: MachineType) {
        _state.update { it.copy(machineType = type) }
    }

    private fun setSplitNumber(number : SplitNumber){
        _state.update { it.copy(splitNumber = number) }
    }

    private fun checkRequirements() : Boolean {
        val currentState = state.value

        val errorMessage = when {
            currentState.type == JobType.NONE -> "Seleziona il tipo per continuare"
            currentState.category.isBlank() -> "Seleziona un tipo di categoria per continuare"
            currentState.model.isBlank() -> "Definisci il modello per continuare"
            currentState.brand.isBlank() -> "Definisci la marca per continuare"
            currentState.availableQuantity.isBlank() || !checkStringIsBigDecimal(currentState.availableQuantity) -> "Errore nella quantità"
            currentState.unitMeasurement.isBlank() -> "Definisci l'unità di misura per continuare"
            currentState.type == JobType.CDZ && currentState.serialNumber.isNotEmpty() -> {
                when {
                    !checkStringIsInt(currentState.btu) -> "Errore nella compilazione dei btu"
                    currentState.machineType == MachineType.NONE -> "Seleziona il tipo di macchina"
                    currentState.machineType == MachineType.Esterna -> {
                        when {
                            currentState.splitNumber == SplitNumber.NONE -> "Selezionare il numero di split"
                            !checkStringIsBigDecimal(currentState.gasQty) -> "Definire la quantità di gas"
                            currentState.gasType.isBlank() -> "Definire il tipo  di gas"
                            else -> null
                        }
                    }
                    else -> null
                }
            }
            else -> null
        }

        if(errorMessage != null) {
            _state.update { it.copy(errorMessage = errorMessage) }
            return true
        }

        return false
    }
}