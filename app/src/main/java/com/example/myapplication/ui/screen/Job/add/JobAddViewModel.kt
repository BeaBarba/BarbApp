package com.example.myapplication.ui.screen.Job.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.Job
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

data class JobAddState(
    val jobId: Int? = 0,
    val date: LocalDate? = null,
    val startTime: LocalTime? = null,
    val endTime: LocalTime? = null,
    val peopleNumber: Int? = null,
    val description: String? = "",
    val type: JobType = JobType.NONE,
    val customerId: String? = null,
    val addressId: Int? = null,
    val workSite: Int? = null,
    val price: Float? = null,
    val materialsQuantity: List<Pair<Int, Int>> = emptyList(),
    //val photos : *ToDo*


)

interface JobAddActions {
    fun populateFromEdit(id: Int)
    fun saveJob(onSuccess: (Int) -> Unit)
    fun setCustomers(cf: String)
    fun setAddress(id: Int)
    fun setJobType(type: JobType)
    fun setPeopleNumber(number: String)
    fun setJobDate(date: LocalDate)
    fun setStartTime(time: String)
    fun setEndTime(time: String)
    fun setPrice(price: String)
    fun setDescription(description: String)
    fun deleteJob()
}

class JobAddViewModel(
    private val repository: Repository,
) : ViewModel() {

    private val _state = MutableStateFlow(JobAddState())

    val state = _state.asStateFlow()

    val action = object : JobAddActions {

        override fun populateFromEdit(id: Int) {
            viewModelScope.launch {
                repository.job.getJobById(id).collect { job ->
                    if (job != null) {
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
                                    job.electric -> {
                                        JobType.ELE
                                    }

                                    job.alarm -> {
                                        JobType.ALA
                                    }

                                    job.airConditioning -> {
                                        JobType.CDZ
                                    }

                                    else -> JobType.NONE
                                }
                            )
                        }
                    }
                }
            }

            viewModelScope.launch(Dispatchers.IO) {
                state.value.jobId?.let {
                    val revenues = repository.accounting.getRevenueByJobId(id)
                    val price = revenues
                            .map { it.amount.toBigDecimal() }
                            .fold(BigDecimal.ZERO, BigDecimal::add)
                            .toFloat()
                    _state.update { it.copy(price = price) }
                }
            }
        }

        override fun saveJob(onSuccess: (Int) -> Unit) {
            viewModelScope.launch {
                val state = state.value
                if (state.addressId != null && state.customerId != null) {
                    val job = Job(
                        id = state.jobId ?: 0,
                        date = state.date ?: LocalDate.now(),
                        startTime = state.startTime ?: LocalTime.now(),
                        description = state.description,
                        peopleNumber = state.peopleNumber ?: 1,
                        address = state.addressId,
                        endTime = state.endTime,
                        electric = if (state.type == JobType.ELE) true else false,
                        alarm = if (state.type == JobType.ALA) true else false,
                        airConditioning = if (state.type == JobType.CDZ) true else false,
                        customer = state.customerId,
                    )
                    val jobId = repository.job.upsertJob(job).toInt()

                    onSuccess(jobId)
                }
            }
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
            val parsedFloat = price.toFloatOrNull()
            _state.update { it.copy(price = parsedFloat) }
        }

        override fun setDescription(description: String) {
            _state.update { it.copy(description = description) }
        }

        override fun deleteJob() {
            state.value.jobId?.let {
                viewModelScope.launch {
                    repository.job.deleteJob(
                        Job(
                            id = it,
                            date = LocalDate.now(),
                            startTime = LocalTime.now(),
                            description = "",
                            peopleNumber = 0,
                            address = 0,
                            endTime = null,
                            electric = true,
                            alarm = false,
                            airConditioning = false,
                            customer = "",
                            workSite = 0
                        )
                    )
                }
            }
        }
    }
}