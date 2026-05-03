package com.example.myapplication.ui.screen.WorkSite.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.Repository
import com.example.myapplication.debug.customers
import com.example.myapplication.ui.component.convertStringToDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

data class WorksiteAddState(
    val worksiteId : Int = 0,
    val customerId : String? = null,
    val addressId : Int? = null,
    val referenceId : Int? = null,
    val referenceName : String = "",
    val referenceLastName : String = "",
    val referenceNumber : String = "",
    val startDate : LocalDate? = null,
    val endDate : LocalDate? = null,

    val started : Boolean = false
)

interface WorksiteAddActions{
    fun populateView(id : Int?)
    fun setCustomer(cf : String)
    fun getCustomers() : List<String>
    fun setAddress(id : String)
    fun getAddresses() : List<String>
    fun setReference(id : String)
    fun getReferences() : List<String>
    fun setReferenceName(name : String)
    fun setReferenceLastName(lastName : String)
    fun setReferenceNumber(number : String)
    fun setStartDate(date : String)
    fun setEndDate(date : String)
    fun save(onSuccess : (Int) -> Unit)
    fun delete(id : Int)
}

class WorksiteAddViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow(WorksiteAddState())

    val state = _state.asStateFlow()

    val actions = object : WorksiteAddActions {

        override fun populateView(id: Int?) {
            if(!state.value.started){
                id?.let{
                    viewModelScope.launch {
                        repository.job.getFlowWorkSiteAssignmentDetailsById(id).collect { worksite ->
                            worksite?.let {
                                _state.update {
                                    it.copy(
                                        worksiteId = worksite.workSite.id,
                                        customerId = worksite.customer?.customer?.cf,
                                        addressId = worksite.address.id,
                                        referenceId = worksite.manager?.id,
                                        startDate = worksite.workSite.startDate,
                                        endDate = worksite.workSite.endDate,
                                        started = true
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        override fun setCustomer(cf: String) {
            val id = cf.ifBlank { null }
            _state.update { it.copy(customerId = id) }
        }

        override fun getCustomers(): List<String> {
            customers =
                if(state.value.customerId != null){
                    listOf(state.value.customerId.toString())
                }else{
                    emptyList()
                }
            return customers
        }

        override fun setAddress(id: String) {
            _state.update { it.copy(addressId = id.toIntOrNull()) }
        }

        override fun getAddresses(): List<String> {
            val addresses =
                if(state.value.addressId != null){
                    listOf(state.value.addressId.toString())
                }else{
                    emptyList()
                }
            return addresses
        }

        override fun setReference(id: String) {
            _state.update { it.copy(referenceId = id.toIntOrNull()) }
        }

        override fun getReferences(): List<String> {
            val references =
                if(state.value.referenceId != null){
                    listOf(state.value.referenceId.toString())
                }else{
                    emptyList()
                }
            return references
        }

        override fun setReferenceName(name: String) {
            _state.update { it.copy(referenceName = name) }
        }

        override fun setReferenceLastName(lastName: String) {
            _state.update { it.copy(referenceLastName = lastName) }
        }

        override fun setReferenceNumber(number: String) {
            _state.update { it.copy(referenceNumber = number) }
        }

        override fun setStartDate(date: String) {
            val startDate = convertStringToDate(date) ?: LocalDate.now()
            _state.update { it.copy(startDate = startDate) }
        }

        override fun setEndDate(date: String) {
            val endDate = convertStringToDate(date)
            if(endDate?.isAfter(state.value.startDate) == true) {
                _state.update { it.copy(endDate = endDate) }
            }
        }

        override fun save(onSuccess: (Int) -> Unit) {
            val s = state.value
            println("DEBUG: VM - " +
                    "worksiteId = ${s.worksiteId} " +
                    "customer = ${s.customerId} " +
                    "address = ${s.addressId} " +
                    "reference = ${s.referenceId} " +
                    "referenceName = ${s.referenceName} " +
                    "referenceLastName = ${s.referenceLastName} " +
                    "referenceNumber = ${s.referenceNumber} " +
                    "startDate = ${s.startDate} " +
                    "endDate = ${s.endDate} "
            )
            onSuccess(0)
        }

        override fun delete(id: Int) {
            TODO("Not yet implemented")
        }
    }
}