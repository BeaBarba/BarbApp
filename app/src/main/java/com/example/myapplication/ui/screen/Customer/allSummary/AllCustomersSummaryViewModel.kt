package com.example.myapplication.ui.screen.Customer.allSummary

import androidx.lifecycle.ViewModel
import com.example.myapplication.debug.Cliente
import com.example.myapplication.debug.listaClienti
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Locale.getDefault

data class AllCustomersSummaryState(
    val started : Boolean = false,
    val customers : List<Cliente> = listOf(),
    val startingChar : List<Char> = listOf(),
    val searchString : String = "",
    val customersToView : List<Cliente> = listOf(),
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

class AllCustomersSummaryViewModel() : ViewModel()  {
    private val _state = MutableStateFlow(AllCustomersSummaryState())

    val state = _state.asStateFlow()

    val actions = object : AllCustomersSummaryActions {
        override fun populateCustomers() {
            if (!state.value.started) {
                _state.update { it.copy(customers = listaClienti, started = true, customersToView = listaClienti) }
                _state.update { it.copy(startingChar = getStartingChar(state.value.customers).sorted()) }
            }
        }

        override fun setSearchString(searchString: String) {
            _state.update { it.copy(
                searchString = searchString,
                customersToView = state.value.customers.filter {
                    it.cognome?.lowercase()?.startsWith(searchString.lowercase(getDefault()))?:false ||
                            it.nome.lowercase().startsWith(searchString.lowercase(getDefault()))  },
            ) }
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

    private fun getStartingChar(customersList : List<Cliente>) : List<Char> {
        return customersList.map {  it.cognome?.get(0)?:it.nome.get(0)  }.distinct()
    }
}