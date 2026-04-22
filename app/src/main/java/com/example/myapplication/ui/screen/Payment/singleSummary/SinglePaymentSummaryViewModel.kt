package com.example.myapplication.ui.screen.Payment.singleSummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.RevenueFullDetails
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SinglePaymentSummaryState(
    val started : Boolean = false,
    val invoiceId : Int = 0,
    val revenue: RevenueFullDetails? = null,
    val customerView : Pair<String,String>? = null,
)

interface SinglePaymentSummaryActions{
    fun populateView(paymentId : Int)
}

class SinglePaymentSummaryViewModel(
    private val repository : Repository
) : ViewModel() {

    private val _state = MutableStateFlow(SinglePaymentSummaryState())

    val state = _state.asStateFlow()

    val actions = object : SinglePaymentSummaryActions{

        override fun populateView(paymentId : Int){
            viewModelScope.launch{
                repository.accounting.getFlowRevenueFullDetailsById(paymentId).collect{ item ->
                    item?.let{
                        _state.update {
                            it.copy(
                                started = true,
                                invoiceId = item.revenue.id,
                                revenue = item,
                                customerView = calculateCustomerName(item)
                            )
                        }
                    }
                }
            }
        }
    }

    private fun calculateCustomerName(revenue: RevenueFullDetails?) : Pair<String, String>?{

        val customerData = revenue?.workSite?.customer ?: revenue?.job?.customer ?: return null

        val cf = customerData.customer.cf

        val name = when{
            customerData.isCompany -> customerData.companyCustomer?.companyName
            customerData.isPrivate -> "${customerData.privateCustomer?.lastName} ${
                customerData.customer.name}"
            else -> null
        }

        return name?.let { cf to it }
    }
}
