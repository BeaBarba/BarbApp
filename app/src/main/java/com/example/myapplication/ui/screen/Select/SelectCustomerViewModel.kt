package com.example.myapplication.ui.screen.Select

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SelectCustomerState(
    val started : Boolean = false,
    val itemsList : List<CardItem> = emptyList(),
    val idsList : List<String> = emptyList()
)

interface SelectCustomerActions{
    fun populateUI()
}

class SelectCustomerViewModel(
    private val repository : Repository
) : ViewModel(){
    private val _state = MutableStateFlow(SelectCustomerState())

    val state = _state.asStateFlow()

    val actions = object : SelectCustomerActions{

        override fun populateUI() {
            if (!state.value.started) {
                viewModelScope.launch(Dispatchers.IO) {
                    val customersCardList = repository.getAllCustomersFullDetails()
                        .map { customer ->
                            CardItem(
                                id = customer.customer.cf,
                                name =
                                if (customer.privateCustomer != null){
                                    customer.customer.name + " " + customer.privateCustomer.lastName
                                } else{
                                    customer.customer.name
                                },
                                type = JobType.NONE,
                                description = null
                            )
                        }

                    _state.update {
                        it.copy(
                            itemsList = customersCardList
                        )
                    }
                }
            }

        }
    }
}