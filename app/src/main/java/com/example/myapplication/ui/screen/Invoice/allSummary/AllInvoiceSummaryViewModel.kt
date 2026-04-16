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
            val customer : String
            if(currentState.workSite != null) {
                customer = when {
                    currentState.workSite.customer?.isPrivate == true -> {
                        "${currentState.workSite.customer.privateCustomer?.lastName} ${currentState.workSite.customer.customer.name}"
                    }
                    currentState.workSite.customer?.isCompany == true -> {"${currentState.workSite.customer.companyCustomer?.companyName}"}
                    else -> {""}
                }
            }else{
                customer = when {
                    currentState.job?.customer?.isPrivate == true -> {
                        "${currentState.job.customer.privateCustomer?.lastName} ${currentState.job.customer.customer.name}"
                    }
                    currentState.job?.customer?.isCompany == true -> {"${currentState.job.customer.companyCustomer?.companyName}"}
                    else -> {""}
                }
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
        return invoices.filter {
            it.revenue.invoice.toString().startsWith(query) ||
            it.job?.customer?.privateCustomer?.lastName?.lowercase()?.startsWith(query) ?: false ||
            it.job?.customer?.companyCustomer?.companyName?.lowercase()?.startsWith(query) ?:false ||
            it.job?.customer?.customer?.name?.lowercase()?.startsWith(query) ?: false ||
            it.workSite?.customer?.privateCustomer?.lastName?.lowercase()?.startsWith(query) ?: false ||
            it.workSite?.customer?.companyCustomer?.companyName?.lowercase()?.startsWith(query) ?:false ||
            it.workSite?.customer?.customer?.name?.lowercase()?.startsWith(query) ?: false
        }
    }
}