package com.example.myapplication.ui.screen.Customer.allSummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.CustomerFullDetails
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.data.repository.Repository
import com.example.myapplication.ui.screen.Deadline.allSummary.Deadline
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale.getDefault

data class AllCustomersSummaryState(
    val started : Boolean = false,
    val customers : List<CustomerFullDetails> = listOf(),
    val startingChar : List<Char> = listOf(),
    val searchString : String = "",
    val customersToView : List<CustomerFullDetails> = listOf(),
    val sortingFunction: (List<Char>) -> List<Char> = {it.sorted()}
)

interface AllCustomersSummaryActions {
    fun setSearchString(searchString : String)
    fun setAscendingSort()
    fun setDescendingSort()
    fun setReferenceFilter()
    fun setAllCustomerFilter()
    fun setAirConditionerFilter()
    fun setAlarmFilter()
    fun setElectricFilter()
}

class AllCustomersSummaryViewModel(
    private val repository : Repository
) : ViewModel()  {

    private val _state = MutableStateFlow(AllCustomersSummaryState())

    val state = _state.asStateFlow()

    init{
        populateCustomers()
    }

    val actions = object : AllCustomersSummaryActions {

        override fun setSearchString(searchString: String) {
            _state.update { it.copy(
                    searchString = searchString,
                    customersToView = searchFilter(searchString, state.value.customers)
                )
            }
            _state.update {it.copy(startingChar = state.value.sortingFunction(getStartingChar(state.value.customersToView)))}
        }

        override fun setAscendingSort() {
            _state.update {it.copy(sortingFunction = { list -> list.sorted()})}
            _state.update {it.copy(startingChar = state.value.sortingFunction(state.value.startingChar))}
        }

        override fun setDescendingSort() {
            _state.update {it.copy(sortingFunction = { list -> list.sorted().reversed() })}
            _state.update {it.copy(startingChar = state.value.sortingFunction(state.value.startingChar))}
        }

        override fun setReferenceFilter() {
        }

        override fun setAllCustomerFilter() {
            val customers = _state.value.customers.sortedBy {
                when{
                    it.isPrivate -> {it.privateCustomer?.lastName}
                    it.isCompany -> {it.companyCustomer?.companyName}
                    else -> it.customer.name
                }

            }
            _state.update {
                it.copy (

                    customersToView =  customers,
                    startingChar = getStartingChar(customers),
                    searchString = "",
                    sortingFunction = { list -> list.sorted()}
                )
            }
        }

        override fun setAirConditionerFilter() {
            val filterList = typeJobFilter(JobType.CDZ, _state.value.customers)
            _state.update {
                it.copy (
                    customersToView = filterList,
                    startingChar = getStartingChar(filterList),
                    searchString = ""
                )
            }
        }

        override fun setAlarmFilter() {
            val filterList = typeJobFilter(JobType.ALA, _state.value.customers)
            _state.update {
                it.copy (
                    customersToView = filterList,
                    startingChar = getStartingChar(filterList),
                    searchString = ""
                )
            }
        }

        override fun setElectricFilter() {
            val filterList = typeJobFilter(JobType.ELE, _state.value.customers)
            _state.update {
                it.copy (
                    customersToView = filterList,
                    startingChar = getStartingChar(filterList),
                    searchString = ""
                )
            }
        }
    }

    private fun populateCustomers() {
        viewModelScope.launch {
            repository.customer.getAllCustomersFullDetails().collect { customerList ->
                _state.update { currentState ->
                    val filterList =
                        if(currentState.searchString.isEmpty()) customerList
                        else searchFilter(currentState.searchString, customerList)

                    currentState.copy(
                        customers = customerList,
                        customersToView = filterList,
                        startingChar = getStartingChar(customerList).sorted(),
                        started = true
                    )
                }
            }
        }
    }

    private fun getStartingChar(customersList : List<CustomerFullDetails>) : List<Char> {
        return customersList.map {
            when{
                it.privateCustomer != null -> {it.privateCustomer.lastName.firstOrNull() ?: '?'}
                it.companyCustomer != null -> {it.companyCustomer.companyName.firstOrNull()?: '?'}
                else -> {it.customer.name.firstOrNull() ?: '?'}
            }
        }.distinct()
    }

    private fun searchFilter(searchString: String, customers: List<CustomerFullDetails>) : List<CustomerFullDetails>{
        if(searchString.isBlank()) return customers
        val query = searchString.trim().lowercase(getDefault())
        return customers.filter {
                it.privateCustomer?.lastName?.lowercase()?.startsWith(query) ?:false ||
                it.companyCustomer?.companyName?.lowercase()?.startsWith(query) ?:false ||
                it.customer.name.lowercase().startsWith(query)
        }
    }

    private fun typeJobFilter(type: JobType, customers: List<CustomerFullDetails>) : List<CustomerFullDetails>{
        return when (type) {
            (JobType.CDZ) -> customers.filter {
                it.jobs.any { job -> job.job.airConditioning }
            }
            (JobType.ELE) -> customers.filter {
                it.jobs.any { job -> job.job.electric }
            }
            (JobType.ALA) -> customers.filter {
                it.jobs.any { job -> job.job.alarm }
            }
            else -> emptyList()
        }
    }
}