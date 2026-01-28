package com.example.myapplication.ui.screen.Select

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.data.modules.SelectKey
import com.example.myapplication.data.repository.Repository
import com.example.myapplication.debug.addressType
import com.example.myapplication.debug.bubblesType
import com.example.myapplication.debug.invoicesType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CardItem(
    val id : String,
    val name : String,
    val description : String?,
    val type : JobType,
    var checked : Boolean = false
)

data class SelectState(
    val searchText : String = "",
    val itemsList : List<CardItem> = emptyList()
)

interface SelectActions{
    fun populateUI(searchText: String, select : SelectKey)
}

class SelectViewModel(
    private val repository : Repository
) : ViewModel(){
    private val _state = MutableStateFlow(SelectState())

    val state = _state.asStateFlow()

    val actions = object : SelectActions{

        override fun populateUI(searchText: String, select : SelectKey) {
            when(select){
                SelectKey.AllMaterials -> {
                    viewModelScope.launch(Dispatchers.IO) {
                        repository.materials.collect{
                            val materialCardList = it.map { material ->
                                    CardItem(
                                        id = material.id.toString(),
                                        name = material.category,
                                        description = material.model,
                                        type = material.type
                                    )
                                }
                            _state.update {
                                it.copy(
                                    searchText = searchText,
                                    itemsList = materialCardList
                                )
                            }
                        }
                    }
                }

                SelectKey.AllBubbles -> {
                    val bubblesCardList = bubblesType.map{item -> CardItem(id = "0", name = item.name, description = "ciao", type = JobType.valueOf(item.type), checked = item.checked)}
                    _state.update {
                        it.copy(
                            searchText = searchText,
                            itemsList = bubblesCardList
                        )
                    }
                }
                SelectKey.AllInvoices -> {
                    val invoicesCardList = invoicesType.map{item -> CardItem(id = "0", name = item.name, description = "ciao", type = JobType.valueOf(item.type), checked = item.checked)}
                    _state.update {
                        it.copy(
                            searchText = searchText,
                            itemsList = invoicesCardList
                        )
                    }
                }
                SelectKey.AllAddresses -> {
                    val addressesCardList = addressType.map{item -> CardItem(id = "0", name = item.name, description = "ciao", type = JobType.valueOf(item.type), checked = item.checked)}
                    _state.update {
                        it.copy(
                            searchText = searchText,
                            itemsList = addressesCardList
                        )
                    }
                }

                SelectKey.AllCustomers -> {
                    viewModelScope.launch(Dispatchers.IO) {
                        val customersCardList = repository.customerFullDetails()
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
                                searchText = searchText,
                                itemsList = customersCardList
                            )
                        }
                    }
                }

                SelectKey.AllReferences -> {}
                SelectKey.AllPurchaseInvoices -> {}
            }
        }
    }
}