package com.example.myapplication.ui.screen.Job.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.Job
import com.example.myapplication.data.database.MaterialWithAirConditional
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.data.repository.Repository
import com.example.myapplication.ui.component.checkStringIsBigDecimal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime
import java.util.Locale.getDefault

data class JobAddState(
    val started : Boolean = false,
    val jobId: Int? = null,
    val date: LocalDate? = null,
    val startTime: LocalTime? = null,
    val endTime: LocalTime? = null,
    val peopleNumber: Int? = null,
    val description: String? = "",
    val type: JobType = JobType.NONE,
    val customerId: String? = null,
    val addressId: Int? = null,
    val workSite: Int? = null,
    val price: BigDecimal? = null,
    val materialsList : List<Pair<MaterialWithAirConditional,Float>> = emptyList(),
    val materialsView : List<Pair<MaterialWithAirConditional,Float>> = emptyList(),
    val materialsQuantity: List<Pair<Int, Float>> = emptyList(),
    val searchText : String = ""
    //val photos : *ToDo*
)

interface JobAddActions {
    fun populateView(id: Int?)
    fun populateFromEdit(id: Int)
    fun populateMaterials(jobId : Int?)
    fun saveJob(onSuccess: (Int) -> Unit)
    fun saveMaterials()
    fun setCustomers(cf: String)
    fun setAddress(id: Int)
    fun setJobType(type: JobType)
    fun setPeopleNumber(number: String)
    fun setJobDate(date: LocalDate)
    fun setStartTime(time: String)
    fun setEndTime(time: String)
    fun setPrice(price: String)
    fun setDescription(description: String)
    fun searchMaterial(searchText: String)
    fun incQuantity(id : Int)
    fun decQuantity(id : Int)
    fun deleteJob()
}

class JobAddViewModel(
    private val repository: Repository,
) : ViewModel() {

    private val _state = MutableStateFlow(JobAddState())

    val state = _state.asStateFlow()

    val actions = object : JobAddActions {

        override fun populateView(id : Int?) {
            id?.let{
                populateFromEdit(id)
            }
            populateMaterials(id)
        }

        override fun populateFromEdit(id: Int) {
            viewModelScope.launch {
                val job = repository.job.getJobById(id).firstOrNull()

                if (job != null) {
                    val revenues = repository.accounting.getRevenueByJobId(id)
                    val price = revenues
                        .map { it.amount.toBigDecimal() }
                        .fold(BigDecimal.ZERO, BigDecimal::add)
                        .toFloat()

                    _state.update {
                        it.copy(
                            jobId = job.id,
                            date = job.date,
                            startTime = job.startTime,
                            endTime = job.endTime,
                            peopleNumber = job.peopleNumber,
                            description = job.description,
                            customerId = job.customer,
                            addressId = job.address,
                            workSite = job.workSite,
                            type = when {
                                job.electric -> {JobType.ELE}
                                job.alarm -> {JobType.ALA}
                                job.airConditioning -> {JobType.CDZ}
                                else -> JobType.NONE
                            },
                            price = price.toBigDecimal()
                        )
                    }
                }
            }
        }

        override fun populateMaterials(jobId : Int?) {
            if (_state.value.started) return

            viewModelScope.launch {

                val quantityMap: Map<Int, Float> = jobId?.let {

                    val job = repository.job.getJobMaterialFullDetailsById(jobId)

                    val futureMaterial = job?.materialsFuture?.map {
                        it.material.id to it.futureJobMaterial.quantity
                    } ?: emptyList()

                    val materialUsage = job?.materialUsage?.map {
                        it.material.id to it.materialUsage.quantity
                    } ?: emptyList()

                    (futureMaterial + materialUsage)
                        .groupBy({ it.first }, { it.second })
                        .mapValues { it.value.sum() }
                } ?: emptyMap()

                val currentTempQuantity = _state.value.materialsQuantity.toMap()

                val finalQuantityMap = if (jobId == null && currentTempQuantity.isNotEmpty()) {
                    currentTempQuantity
                } else {
                    quantityMap
                }

                val allMaterials = repository.inventory.getAllMaterialsWithAirConditional()

                val materialsList = allMaterials
                    .map { it to (finalQuantityMap[it.material.id] ?: 0.0f) }
                    .sortedWith(
                        compareByDescending<Pair<MaterialWithAirConditional, Float>>
                            { it.second }
                            .thenBy { it.first.material.category }
                            .thenBy { it.first.material.model }
                            .thenBy { it.first.material.brand }
                    )

                _state.update {
                    it.copy(
                        started = true,
                        materialsQuantity = materialsList.filter { item -> item.second > 0.0f }
                            .map { item ->  item.first.material.id to item.second },
                        materialsList = materialsList,
                        materialsView = filterMaterialsList(state.value.searchText, materialsList)
                    )
                }
            }
        }

        override fun saveJob(onSuccess: (Int) -> Unit) {
            viewModelScope.launch {
                val state = _state.value
                if (state.addressId != null && state.customerId != null) {
                    val job = Job(
                        id = state.jobId ?: 0,
                        date = state.date ?: LocalDate.now(),
                        startTime = state.startTime ?: LocalTime.now(),
                        description = state.description,
                        peopleNumber = state.peopleNumber ?: 1,
                        address = state.addressId,
                        endTime = state.endTime,
                        electric = state.type == JobType.ELE,
                        alarm = state.type == JobType.ALA,
                        airConditioning = state.type == JobType.CDZ,
                        customer = state.customerId,
                    )
                    val jobId = repository.job.saveJobComplete(job, state.materialsQuantity)

                    onSuccess(jobId)
                }
            }
        }

        override fun saveMaterials() {
            val quantityMap = state.value.materialsList.filter{ it.second > 0f}
                .map{ it.first.material.id to it.second}

            _state.update{ it.copy(materialsQuantity = quantityMap) }
        }

        override fun setCustomers(cf: String) {
            _state.update { it.copy(customerId = cf) }
        }

        override fun setAddress(id: Int) {
            _state.update { it.copy(addressId = id) }
        }

        override fun setJobType(type: JobType) {
            _state.update { it.copy(type = type) }
        }

        override fun setJobDate(date: LocalDate) {
            _state.update { it.copy(date = date) }
        }

        override fun setPeopleNumber(number: String) {
            val parsedNumber = number.toIntOrNull()

            _state.update { it.copy(peopleNumber = parsedNumber) }
        }

        override fun setStartTime(time: String) {
            if (time.isEmpty()) {
                _state.update { it.copy(startTime = LocalTime.now()) }
            } else {
                val parsedTime = LocalTime.parse(time)
                _state.update { it.copy(startTime = parsedTime) }
            }
        }

        override fun setEndTime(time: String) {
            if (time.isEmpty()) {
                _state.update { it.copy(endTime = LocalTime.now()) }
            } else {
                val parsedTime = LocalTime.parse(time)
                _state.update { it.copy(endTime = parsedTime) }
            }
        }

        override fun setPrice(price: String) {
            val parsedPrice = if(checkStringIsBigDecimal(price)) price.toBigDecimalOrNull() else BigDecimal.ZERO
            _state.update { it.copy(price = parsedPrice) }
        }

        override fun setDescription(description: String) {
            _state.update { it.copy(description = description) }
        }

        override fun searchMaterial(searchText: String) {
            _state.update{
                it.copy(
                    searchText = searchText,
                    materialsView = filterMaterialsList(searchText, it.materialsList)
                )
            }
        }

        override fun incQuantity(id: Int) {
            val materials = state.value.materialsList.map {
                 if (it.first.material.id == id) {
                     it.first to it.second.inc()
                 }else{
                     it.first to it.second
                 }
            }.sortedWith(
                compareByDescending<Pair<MaterialWithAirConditional, Float>>
                { it.second }
                    .thenBy { it.first.material.category }
                    .thenBy { it.first.material.model }
                    .thenBy { it.first.material.brand }
            )
            syncEverything(materials)
        }

        override fun decQuantity(id: Int) {
            val materials = state.value.materialsList.map {
                if (it.first.material.id == id) {
                    it.first to it.second.dec()
                }else{
                    it.first to it.second
                }
            }.sortedWith(
                compareByDescending<Pair<MaterialWithAirConditional, Float>>
                { it.second }
                    .thenBy { it.first.material.category }
                    .thenBy { it.first.material.model }
                    .thenBy { it.first.material.brand }
            )
            syncEverything(materials)
        }

        override fun deleteJob() {
            state.value.jobId?.let {
                viewModelScope.launch {
                    repository.job.deleteJob(
                        Job(
                            id = it,
                            date = state.value.date ?: LocalDate.now(),
                            startTime = state.value.startTime ?: LocalTime.now(),
                            description = state.value.description ?: "",
                            peopleNumber = state.value.peopleNumber ?: 0,
                            address = state.value.addressId ?: 0,
                            endTime = state.value.endTime,
                            electric = state.value.type == JobType.ELE,
                            alarm = state.value.type == JobType.ALA,
                            airConditioning = state.value.type == JobType.CDZ,
                            customer = state.value.customerId,
                            workSite = state.value.workSite
                        )
                    )
                }
            }
        }
    }

    private fun filterMaterialsList(searchText : String, materials : List<Pair<MaterialWithAirConditional,Float>>) : List<Pair<MaterialWithAirConditional,Float>>{
        return materials.filter{it.first.material.category.lowercase().startsWith(searchText.lowercase(
            getDefault()
        ))}
    }

    private fun syncEverything(newList: List<Pair<MaterialWithAirConditional, Float>>) {
        _state.update { currentState ->
            currentState.copy(
                materialsList = newList,
                materialsView = filterMaterialsList(currentState.searchText, newList),
                materialsQuantity = newList.filter { it.second > 0f }
                    .map { it.first.material.id to it.second }
            )
        }
    }
}