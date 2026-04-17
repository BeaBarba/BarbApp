package com.example.myapplication.ui.screen.Invoice.allSummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.RevenueFullDetails
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale.getDefault

data class AllInvoiceSummaryState(
    val invoices : List<RevenueFullDetails> = emptyList(),
    val invoicesView : List<RevenueFullDetails> = emptyList(),
    val searchString : String = "",
    val started : Boolean = false
)

interface AllInvoiceSummaryActions{
    fun searchInvoice(searchString : String)
    fun getCustomerName(id : Int) : String
}

class AllInvoicesSummaryViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow(AllInvoiceSummaryState())

    val state = _state.asStateFlow()

    init {
        populateView()
    }

    val actions = object : AllInvoiceSummaryActions {

        override fun searchInvoice(searchString: String) {
            _state.update {
                it.copy(
                    searchString = searchString,
                    invoicesView = searchFilter(searchString, state.value.invoices)
                )
            }
        }

        override fun getCustomerName(id : Int): String {
            val currentState = state.value.invoices.single{ it.revenue.id == id }
            val customerData = currentState.workSite?.customer ?: currentState.job?.customer ?: return "${currentState.revenue.invoice}"
            val customer = when {
                customerData.isPrivate -> {"${customerData.privateCustomer?.lastName} ${customerData.customer.name}"}
                customerData.isCompany -> {"${customerData.companyCustomer?.companyName}"}
                else -> {""}
            }

            return "${currentState.revenue.invoice} - $customer"
        }
    }

    private fun populateView(){
        viewModelScope.launch {
            repository.accounting.getFlowAllRevenuesFullDetails().collect{ invoices ->
                val invoicesList = invoices.sortedWith(
                    compareBy<RevenueFullDetails> { it.revenue.invoice }
                        .thenByDescending { it.revenue.issueDate }
                )
                _state.update {
                    it.copy(
                        invoices = invoicesList,
                        invoicesView = searchFilter("", invoicesList),
                        started = true
                    )
                }
            }
        }
    }

    private fun searchFilter(searchString: String, invoices: List<RevenueFullDetails>) : List<RevenueFullDetails>{
        if(searchString.isBlank()) return invoices

        val query = searchString.trim().lowercase(getDefault())

        return invoices.filter { item ->
            val invoiceMatch = item.revenue.invoice.toString().startsWith(query)

            if(invoiceMatch) return@filter true

            val customer = item.job?.customer ?: item.workSite?.customer

            val nameMatch = customer?.let {
                it.privateCustomer?.lastName?.lowercase()?.startsWith(query) == true ||
                it.companyCustomer?.companyName?.lowercase()?.startsWith(query) == true ||
                it.customer.name.lowercase().startsWith(query)
            } ?: false

            nameMatch
        }
    }
}