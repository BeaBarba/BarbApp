package com.example.myapplication.ui.screen.Job.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.database.WorkSite
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

data class JobAddState(
    val jobId : Int? = 0,
    val date : LocalDate = LocalDate.now(),
    val startTime : LocalTime? = LocalTime.now(),
    val endTime : LocalTime? = null,
    val peopleNumber : Int = 1,
    val description : String? = null,
    val type : JobType = JobType.NONE,
    val customerId : String? = null,
    val addressId : Int = 0,
    val workSite : Int? = null,
    val price : Float? = 0.0f,
    val materialsQuantity : List<Pair<Int, Int>> = emptyList(),
    //val photos : *ToDo*


)

interface JobAddActions{
    fun populateFromEdit(id : Int)
    fun saveJob(onSuccess : (Int) -> Unit)
    fun setCustomers(cf : String)
    fun setAddress(id : Int)
}

class JobAddViewModel(
    repository : Repository
) : ViewModel(){

    private val _state = MutableStateFlow(JobAddState())

    val state = _state.asStateFlow()

    val action = object : JobAddActions {

        override fun populateFromEdit(id: Int) {
            viewModelScope.launch {
                repository.getJobById(id).collect{ job ->
                    if(job != null) {
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
                                type = when{
                                    job.electric -> {JobType.ELE}
                                    job.alarm -> {JobType.ALA}
                                    job.airConditioning -> {JobType.CDZ}
                                    else -> JobType.NONE
                                }
                            )
                        }
                    }
                }
            }
            if(state.value.jobId != null) {
                viewModelScope.launch {
                    state.value.jobId?.let {
                        repository.getJobFullDetails(it).collect { data ->
                            _state.update {
                                it.copy(
                                    price = data?.revenue?.fold(BigDecimal.ZERO) { acc, item ->
                                        acc.add(item?.amount?.toBigDecimal() ?: BigDecimal.ZERO)
                                    }?.toFloat() ?: BigDecimal.ZERO.toFloat()
                                )
                            }
                        }
                    }
                }
            }
        }

        override fun saveJob(onSuccess : (Int) -> Unit){
            onSuccess(0)
        }

        override fun setCustomers(cf : String){
            _state.update { it.copy(customerId = cf) }
        }

        override fun setAddress(id : Int) {
            _state.update { it.copy(addressId = id) }
        }
    }
}