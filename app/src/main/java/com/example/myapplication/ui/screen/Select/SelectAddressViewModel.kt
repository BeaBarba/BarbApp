package com.example.myapplication.ui.screen.Select

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SelectAddressState(
    val started : Boolean = false,
    val itemsList : List<CardItem> = emptyList(),
    val idsList : List<String> = emptyList()
)

interface SelectAddressActions{
    fun populateUI()
}

class SelectAddressViewModel(
    private val repository : Repository
) : ViewModel(){
    private val _state = MutableStateFlow(SelectAddressState())

    val state = _state.asStateFlow()

    val actions = object : SelectAddressActions{

        override fun populateUI() {
            if (!state.value.started) {
                viewModelScope.launch(Dispatchers.IO) {
                    val customersCardList = repository.addresses.first()
                        .map { address ->
                            CardItem(
                                id = address.id.toString(),
                                name = address.address + " " + address.houseNumber + ", " + address.city,
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