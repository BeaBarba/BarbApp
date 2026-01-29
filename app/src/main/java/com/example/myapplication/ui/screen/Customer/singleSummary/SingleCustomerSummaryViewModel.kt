package com.example.myapplication.ui.screen.Customer.singleSummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.CustomerFullDetails
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SingleCustomerSummaryState(
    val started : Boolean = false,
    val customerData : CustomerFullDetails? = null
)

interface SingleCustomerSummaryActions {
    fun populateCustomerData(customerId: String)
}

class SingleCustomerSummaryViewModel(
    private val repository : Repository
) : ViewModel()  {
    private val _state = MutableStateFlow(SingleCustomerSummaryState())

    val state = _state.asStateFlow()

    val actions = object : SingleCustomerSummaryActions {

        override fun populateCustomerData(customerId: String) {
            if(state.value.started) return

            viewModelScope.launch {
                _state.update { it.copy(started = true) }
                repository.getCustomerFullDetailsById(customerId).collect { data ->
                    _state.update { it.copy(customerData = data) }
                }
            }
        }
    }
}