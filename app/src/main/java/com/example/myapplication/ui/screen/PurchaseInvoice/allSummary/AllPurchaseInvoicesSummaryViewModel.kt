package com.example.myapplication.ui.screen.PurchaseInvoice.allSummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.PurchaseInvoiceFullDetails
import com.example.myapplication.data.modules.FilterKey
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale.getDefault

data class AllPurchaseInvoicesSummaryState(
    val started : Boolean = false,
    val purchaseInvoices : List<PurchaseInvoiceFullDetails> = emptyList(),
    val purchaseInvoicesView : List<PurchaseInvoiceFullDetails> = emptyList(),
    val searchString : String = "",
    val filterKey: FilterKey = FilterKey.ASC_DATE
)

interface AllPurchaseInvoicesSummaryActions{
    fun orderedSeller()
    fun orderedDate()
    fun ascendingSort()
    fun descendingSort()
    fun searchPurchaseInvoice(searchString : String)
}

class AllPurchaseInvoicesSummaryViewModel(
    private val repository: Repository
): ViewModel() {

    private val _state = MutableStateFlow(AllPurchaseInvoicesSummaryState())

    val state = _state.asStateFlow()

    init {
        populateUI()
    }

    private fun populateUI(){
        viewModelScope.launch {
            repository.getAllPurchaseInvoicesFullDetails().collect{ purchaseInvoicesList ->
                _state.update { currentState ->
                    val filterList =
                        if(currentState.searchString.isEmpty()) purchaseInvoicesList
                        else searchFilter(currentState.searchString, purchaseInvoicesList)

                    currentState.copy(
                        started = true,
                        purchaseInvoices = purchaseInvoicesList,
                        purchaseInvoicesView = filterList
                    )
                }
            }
        }
    }

    val actions = object : AllPurchaseInvoicesSummaryActions{

        override fun orderedSeller() {
            val purchaseInvoices = state.value.purchaseInvoices.sortedBy { it.seller.name }
            _state.update { it.copy(purchaseInvoicesView = purchaseInvoices, filterKey = FilterKey.ASC_SELLER) }
        }

        override fun orderedDate() {
            val purchaseInvoices = state.value.purchaseInvoices.sortedBy { it.purchaseInvoice.year }
            _state.update { it.copy(purchaseInvoicesView = purchaseInvoices, filterKey = FilterKey.ASC_DATE) }
        }

        override fun ascendingSort() {
            if (state.value.filterKey == FilterKey.DESC_SELLER){
                _state.update {
                    it.copy(
                        purchaseInvoicesView = state.value.purchaseInvoicesView.reversed(),
                        filterKey = FilterKey.ASC_SELLER
                    )
                }
            }
            if (state.value.filterKey == FilterKey.DESC_DATE){
                _state.update {
                    it.copy(
                        purchaseInvoicesView = state.value.purchaseInvoicesView.reversed(),
                        filterKey = FilterKey.ASC_DATE
                    )
                }
            }
        }

        override fun descendingSort() {
            if (state.value.filterKey == FilterKey.ASC_SELLER){
                _state.update {
                    it.copy(
                        purchaseInvoicesView = state.value.purchaseInvoicesView.reversed(),
                        filterKey = FilterKey.DESC_SELLER
                    )
                }
            }else if (state.value.filterKey == FilterKey.ASC_DATE){
                _state.update {
                    it.copy(
                        purchaseInvoicesView = state.value.purchaseInvoicesView.reversed(),
                        filterKey = FilterKey.DESC_DATE
                    )
                }
            }
        }

        override fun searchPurchaseInvoice(searchString : String){
            val purchaseInvoices = searchFilter(searchString, state.value.purchaseInvoices)
            _state.update { it.copy(searchString = searchString, purchaseInvoicesView = purchaseInvoices) }
        }
    }

    private fun searchFilter(string : String, list : List<PurchaseInvoiceFullDetails>) : List<PurchaseInvoiceFullDetails>{
        return list.filter { it.purchaseInvoice.number.lowercase().startsWith(string.lowercase(getDefault())) }
    }
}