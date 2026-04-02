package com.example.myapplication.ui.screen.Select

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.myapplication.data.database.Material
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.data.modules.SelectKey
import com.example.myapplication.data.repository.Repository
import com.example.myapplication.debug.addressType
import com.example.myapplication.debug.bubblesType
import com.example.myapplication.debug.customersType
import com.example.myapplication.debug.invoicesType
import com.example.myapplication.ui.NavigationRoute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale.getDefault

data class CardItem(
    val id : String,
    val name : String,
    val description : String?,
    val type : JobType,
    val checked : Boolean = false
)

data class SelectState(
    val searchText : String = "",
    val itemsList : List<CardItem> = emptyList(),
    val viewList : List<CardItem> = emptyList(),
    val idsList : List<String> = emptyList(),
    val selectKey : SelectKey = SelectKey.AllMaterials
)

interface SelectActions{
    fun populateUI(searchText: String, select : SelectKey, initialCheckedIds: List<String>)
    fun setCheckedItems(ids : List<String>)
    fun setChecked(id : String)
    fun search(string : String)
    fun setOnClick(id : Int = 0, id2 : String = "", navController : NavHostController)
}

class SelectViewModel(
    private val repository : Repository
) : ViewModel(){
    private val _state = MutableStateFlow(SelectState())

    val state = _state.asStateFlow()

    val actions = object : SelectActions{

        override fun populateUI(searchText: String, select : SelectKey, initialCheckedIds: List<String>) {
            when(select){
                SelectKey.AllMaterials -> {
                    viewModelScope.launch(Dispatchers.IO) {
                        repository.materials.collect{
                            val materialCardList = it.map { material ->
                                    CardItem(
                                        id = material.id.toString(),
                                        name = material.category,
                                        description = material.model + " - " + material.brand,
                                        type = material.type,
                                        checked = initialCheckedIds.contains(material.id.toString())
                                    )
                                }.sortedWith(
                                    compareBy<CardItem>({it.name})
                                        .thenBy { it.description }
                                )

                            _state.update {
                                it.copy(
                                    searchText = searchText,
                                    itemsList = materialCardList,
                                    viewList = materialCardList,
                                    selectKey = SelectKey.AllMaterials
                                )
                            }
                        }
                    }
                }

                SelectKey.AllBubbles -> {
                    val bubblesCardList = bubblesType.map{item ->
                        CardItem(
                            id = "0",
                            name = item.name,
                            description = "ciao",
                            type = JobType.valueOf(item.type),
                            checked = item.checked
                        )
                    }
                    _state.update {
                        it.copy(
                            searchText = searchText,
                            itemsList = bubblesCardList
                        )
                    }
                }
                SelectKey.AllInvoices -> {
                    val invoicesCardList = invoicesType.map{item ->
                        CardItem(
                            id = "0",
                            name = item.name,
                            description = "ciao",
                            type = JobType.valueOf(item.type),
                            checked = item.checked
                        )
                    }
                    _state.update {
                        it.copy(
                            searchText = searchText,
                            itemsList = invoicesCardList
                        )
                    }
                }

                SelectKey.AllAddresses -> {
                    viewModelScope.launch(Dispatchers.IO) {
                        val addressesCardList = repository.addresses.first()
                            .map { address ->
                                CardItem(
                                    id = address.id.toString(),
                                    name = "${address.address} ${address.houseNumber}",
                                    description = "${address.city} (${address.province})",
                                    type = JobType.NONE,
                                    checked = initialCheckedIds.contains(address.id.toString())
                                )
                            }.sortedWith(
                                compareBy({ it.name })
                            )

                        _state.update {
                            it.copy(
                                searchText = searchText,
                                itemsList = addressesCardList,
                                viewList = addressesCardList,
                                selectKey = SelectKey.AllAddresses
                            )
                        }
                    }
                }

                SelectKey.AllCustomers -> {
                    viewModelScope.launch(Dispatchers.IO) {
                        val customersCardList = repository.customers.first()
                            .map { customer ->
                                CardItem(
                                    id = customer.customer.cf,
                                    name =
                                        if (customer.isPrivate){
                                            "${customer.privateCustomer?.lastName} ${customer.customer.name}"
                                        } else{
                                            "${customer.companyCustomer?.companyName}"
                                        },
                                    type = JobType.NONE,
                                    description = null,
                                    checked = initialCheckedIds.contains(customer.customer.cf)
                                )
                            }.sortedWith(
                                compareBy {it.name}
                            )

                        _state.update {
                            it.copy(
                                searchText = searchText,
                                itemsList = customersCardList,
                                viewList = customersCardList,
                                selectKey = SelectKey.AllCustomers
                            )
                        }
                    }
                }

                SelectKey.AllReferences -> {
                    val customersCardList = customersType.map{item -> CardItem(id = "0", name = item.name, description = "ciao", type = JobType.valueOf(item.type), checked = item.checked)}
                    _state.update {
                        it.copy(
                            searchText = searchText,
                            itemsList = customersCardList
                        )
                    }
                }
                SelectKey.AllPurchaseInvoices -> {}
            }
        }

        override fun setCheckedItems(ids : List<String>){
            val checkedItems = _state.value.itemsList.map { item ->
                CardItem(
                    id = item.id,
                    name = item.name,
                    description = item.description,
                    type = item.type,
                    checked = ids.contains(item.id)
                )
            }

            _state.update { it.copy(viewList = checkedItems) }
        }

        override fun setChecked(id: String) {
            val updatedList = _state.value.itemsList.map { item ->
                if (item.id == id) {
                    item.copy(checked = !item.checked)
                } else {
                    item
                }
            }

            _state.update { it.copy(itemsList = updatedList, viewList = updatedList) }
        }

        override fun search(string: String) {
            val filterList =
                if(string.isEmpty()) {
                    _state.value.itemsList
                }else{
                    _state.value.itemsList.filter {
                        it.name.lowercase().startsWith(string.lowercase(getDefault()))
                    }
                }
            _state.update { it.copy(viewList = filterList) }
        }

        override fun setOnClick(id : Int, id2 : String, navController : NavHostController){
            when(state.value.selectKey){
                SelectKey.AllMaterials -> navController.navigate(NavigationRoute.SingleMaterialSummary(id))
                SelectKey.AllCustomers -> navController.navigate(NavigationRoute.SingleCustomerSummary(id2))
                SelectKey.AllAddresses -> navController.navigate(NavigationRoute.SingleAddressSummary(id))
                else -> {}
            }
        }
    }
}