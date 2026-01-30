package com.example.myapplication.ui.screen.Customer.allSummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.CustomerFullDetails
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
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
    fun populateCustomers()
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

    val actions = object : AllCustomersSummaryActions {

        override fun populateCustomers() {
            viewModelScope.launch {
                val customers = repository.customers.first().map { customer ->
                    repository.getCustomerFullDetailsById(customer.cf).first()!!
                }
                if (!state.value.started) {
                    _state.update {
                        it.copy(
                            customers = customers,
                            started = true,
                            customersToView = customers
                        )
                    }
                    _state.update { it.copy(startingChar = getStartingChar(state.value.customers).sorted()) }
                }
            }
        }

        override fun setSearchString(searchString: String) {
            _state.update { it.copy(
                    searchString = searchString,
                    customersToView = state.value.customers.filter {
                        it.privateCustomer?.lastName?.lowercase()?.startsWith(searchString.lowercase(getDefault())) ?:false ||
                        it.customer.name.lowercase().startsWith(searchString.lowercase(getDefault()))
                    },
                )
            }
            _state.update {it.copy(startingChar = state.value.sortingFunction(getStartingChar(state.value.customersToView)))}
        }

        override fun setAscendingSort() {
            _state.update {it.copy(sortingFunction = {it.sorted()})}
            _state.update {it.copy(startingChar = state.value.sortingFunction(state.value.startingChar))}
        }

        override fun setDescendingSort() {
            _state.update {it.copy(sortingFunction = {it.sorted().reversed()})}
            _state.update {it.copy(startingChar = state.value.sortingFunction(state.value.startingChar))}
        }

        override fun setReferenceFilter() {

        }

        override fun setAllCustomerFilter() {

        }

        override fun setAirConditionerFilter() {

        }

        override fun setAlarmFilter() {

        }

        override fun setElectricFilter() {

        }
    }

    private fun getStartingChar(customersList : List<CustomerFullDetails>) : List<Char> {
        return customersList.map {
            if (it.privateCustomer != null && it.customer.name !="") {
                it.privateCustomer.lastName.get(0)
            } else {
                it.customer.name.get(0)
            }
        }.distinct()
    }

    private fun customerToPairToView(customer : CustomerFullDetails) : Pair<String,String>{
        return Pair<String, String>(
            first = customer.customer.cf,
            second =
            if (customer.privateCustomer != null) {
                (customer.privateCustomer.lastName + " " + customer.customer.name)
            }else {
                customer.customer.name
            }
        )
    }
}