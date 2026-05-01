package com.example.myapplication.ui.screen.Select

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.data.modules.SelectKey
import com.example.myapplication.data.repository.Repository
import com.example.myapplication.ui.NavigationRoute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
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
    val selectKey : SelectKey = SelectKey.AllMaterials,
    val resultKey : String = "selectedIds"
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
                        repository.inventory.materials.collect {
                            val materialCardList = it.map { material ->
                                CardItem(
                                    id = material.material.id.toString(),
                                    name = material.material.category,
                                    description = "${material.material.model}  - ${material.material.brand}",
                                    type = material.material.type,
                                    checked = initialCheckedIds.contains(material.material.id.toString())
                                )
                            }.sortedWith(
                                compareByDescending<CardItem> { it.checked }
                                    .thenBy { it.name }
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

                SelectKey.AllAddresses -> {
                    viewModelScope.launch(Dispatchers.IO) {
                        val addressesCardList = repository.address.addresses.first()
                            .map { address ->
                                CardItem(
                                    id = address.id.toString(),
                                    name = "${address.address} ${address.houseNumber}",
                                    description = "${address.city} (${address.province})",
                                    type = JobType.NONE,
                                    checked = initialCheckedIds.contains(address.id.toString())
                                )
                            }.sortedWith(
                                compareByDescending<CardItem> { it.checked }
                                    .thenBy { it.name }
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
                        val customersCardList = repository.customer.customers.first()
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
                                compareByDescending<CardItem> {it.checked}
                                    .thenBy {it.name}
                            )

                        _state.update {
                            it.copy(
                                searchText = searchText,
                                itemsList = customersCardList,
                                viewList = customersCardList,
                                selectKey = SelectKey.AllCustomers,
                                resultKey = "customers"
                            )
                        }
                    }
                }

                SelectKey.AllPurchaseInvoices -> {
                    viewModelScope.launch(Dispatchers.IO) {
                        repository.accounting.purchaseInvoices.collect{
                            val purchasesCardList = it.map { purchase ->
                                CardItem(
                                    id = purchase.id.toString(),
                                    name = purchase.number,
                                    description = null,
                                    type = JobType.NONE,
                                    checked = initialCheckedIds.contains(purchase.id.toString())
                                )
                            }.sortedWith(
                                compareByDescending<CardItem> {it.checked}
                                    .thenBy {it.name}
                            )

                            _state.update {
                                it.copy(
                                    searchText = searchText,
                                    itemsList = purchasesCardList,
                                    viewList = purchasesCardList,
                                    selectKey = SelectKey.AllPurchaseInvoices
                                )
                            }
                        }
                    }
                }

                SelectKey.AllJobs -> {
                    viewModelScope.launch(Dispatchers.IO) {
                        repository.job.getFlowAllJobsAssignmentDetails().collect{ jobs ->
                            val jobsCardList = jobs.map { job ->
                                val jobId = job.job.id.toString()
                                CardItem(
                                    id = job.job.id.toString(),
                                    name = "${job.address.address} ${job.address.houseNumber}, ${job.address.municipality}",
                                    description = job.job.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                    type = when{
                                        job.job.electric -> {JobType.ELE}
                                        job.job.alarm -> {JobType.ALA}
                                        job.job.airConditioning -> {JobType.CDZ}
                                        else -> {JobType.NONE}
                                    },
                                    checked = initialCheckedIds.contains(jobId)
                                )
                            }.sortedWith(
                                compareByDescending<CardItem> {it.checked}
                                    .thenBy {it.name}
                            )

                            _state.update {
                                it.copy(
                                    searchText = searchText,
                                    itemsList = jobsCardList,
                                    viewList = jobsCardList,
                                    selectKey = SelectKey.AllJobs,
                                    resultKey = "jobs"
                                )
                            }
                        }
                    }
                }

                SelectKey.AllWorksites -> {
                    viewModelScope.launch(Dispatchers.IO) {
                        repository.job.getFlowAllWorksitesAssignmentDetails().collect { worksites ->
                            val worksitesCardList = worksites.map { worksite ->
                                val worksiteId = worksite.workSite.id.toString()
                                CardItem(
                                    id = worksite.workSite.id.toString(),
                                    name = "${worksite.address.address} ${worksite.address.houseNumber}, ${worksite.address.municipality}",
                                    description =
                                        if(worksite.manager != null)
                                            "${worksite.manager.lastName} ${worksite.manager.name}"
                                        else null,
                                    type = JobType.NONE,
                                    checked = initialCheckedIds.contains(worksiteId)
                                )
                            }.sortedWith(
                                compareByDescending<CardItem> { it.checked }
                                    .thenBy { it.name }
                            )

                            _state.update {
                                it.copy(
                                    searchText = searchText,
                                    itemsList = worksitesCardList,
                                    viewList = worksitesCardList,
                                    selectKey = SelectKey.AllWorksites,
                                    resultKey = "worksites"
                                )
                            }
                        }
                    }
                }

                SelectKey.AllBubbles -> {
                    viewModelScope.launch(Dispatchers.IO) {
                        repository.accounting.getAllBubblesFullDetails().collect { bubbles ->
                            val bubblesCardList = bubbles.map {  bubble ->
                                    val bubbleId = bubble.bubble.id.toString()
                                    CardItem(
                                        id = bubbleId,
                                        name = "${bubble.bubble.number} - ${bubble.seller.name}",
                                        description = null,
                                        type = JobType.NONE,
                                        checked = initialCheckedIds.contains(bubbleId)
                                    )
                                }
                                .sortedWith(
                                    compareByDescending<CardItem> { it.checked }
                                        .thenBy { it.name }
                                )

                            _state.update {
                                it.copy(
                                    searchText = searchText,
                                    itemsList = bubblesCardList,
                                    viewList = bubblesCardList,
                                    selectKey = SelectKey.AllBubbles,
                                    resultKey = "bubbles"
                                )
                            }
                        }
                    }
                }

                else -> {}
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

            _state.update { it.copy(itemsList = checkedItems, viewList = checkedItems) }
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
                        it.name.trim().lowercase().contains(string.lowercase(getDefault()))
                    }
                }
            _state.update { it.copy(viewList = filterList) }
        }

        override fun setOnClick(id : Int, id2 : String, navController : NavHostController){
            when(state.value.selectKey){
                SelectKey.AllMaterials -> navController.navigate(NavigationRoute.SingleMaterialSummary(id))
                SelectKey.AllCustomers -> navController.navigate(NavigationRoute.SingleCustomerSummary(id2))
                SelectKey.AllAddresses -> navController.navigate(NavigationRoute.SingleAddressSummary(id))
                SelectKey.AllPurchaseInvoices -> navController.navigate(NavigationRoute.SinglePurchaseInvoiceSummary(id))
                SelectKey.AllBubbles -> navController.navigate(NavigationRoute.SingleBubbleSummary(id))
                else -> {}
            }
        }
    }
}