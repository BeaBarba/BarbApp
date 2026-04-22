package com.example.myapplication.ui.screen.Invoice.singleSummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.CustomerTypeDetails
import com.example.myapplication.data.database.RevenueFullDetails
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SingleInvoiceSummaryState(
    val started : Boolean = false,
    val invoicedId : Int = 0,
    val invoice : RevenueFullDetails? = null,
    val customer : CustomerTypeDetails? = null,
    val type : JobType = JobType.NONE
)

interface SingleInvoiceSummaryActions{
    fun populateView(id : Int)
    fun getCustomerName() : String
    fun getTypeOfJob()
}

class  SingleInvoiceSummaryViewModel(
    private val repository : Repository
) : ViewModel() {

    private val _state = MutableStateFlow(SingleInvoiceSummaryState())

    val state = _state.asStateFlow()

    val actions = object : SingleInvoiceSummaryActions {

        override fun populateView(id: Int) {
            viewModelScope.launch {
                repository.accounting.getFlowRevenueFullDetailsById(id).collect{ revenue ->
                    revenue?.let {
                        _state.update {
                            it.copy(
                                invoice = revenue,
                                customer = getCustomer(revenue),
                                invoicedId = revenue.revenue.id,
                                started = true
                            )
                        }
                        getTypeOfJob()
                    }
                }
            }
        }

        override fun getCustomerName(): String {
            val currentState = state.value.invoice
            val customer : String
            if(currentState?.workSite != null) {
                customer = when {
                    currentState.workSite.customer?.isPrivate == true -> {
                        "${currentState.workSite.customer.privateCustomer?.lastName} ${currentState.workSite.customer.customer.name}"
                    }
                    currentState.workSite.customer?.isCompany == true -> {"${currentState.workSite.customer.companyCustomer?.companyName}"}
                    else -> {""}
                }
            }else{
                customer = when {
                    currentState?.job?.customer?.isPrivate == true -> {
                        "${currentState.job.customer.privateCustomer?.lastName} ${currentState.job.customer.customer.name}"
                    }
                    currentState?.job?.customer?.isCompany == true -> {"${currentState.job.customer.companyCustomer?.companyName}"}
                    else -> {""}
                }
            }

            return customer
        }

        override fun getTypeOfJob(){
            viewModelScope.launch {
                val type = calculateTypeOfJob()
                _state.update { it.copy(type = type) }
            }
        }
    }

    private fun getCustomer(revenue : RevenueFullDetails) : CustomerTypeDetails?{
        val customer  = when{
            revenue.workSite != null -> {revenue.workSite.customer}
            revenue.job != null -> {revenue.job.customer}
            else -> {null}
        }

        return customer
    }

    private suspend fun calculateTypeOfJob(): JobType {
        val currentState = state.value.invoice
        if(currentState?.job != null){
            return when{
                currentState.job.job.alarm -> {JobType.ALA}
                currentState.job.job.electric -> {JobType.ELE}
                currentState.job.job.airConditioning -> {JobType.CDZ}
                else -> JobType.NONE
            }
        }else if(currentState?.workSite != null){
            val workSite = repository.job.getWorkSiteFullDetailsById(currentState.workSite.workSite.id)
            workSite?.let {
                val countElectric = workSite.jobs.count { it.electric }
                val countAlarm = workSite.jobs.count { it.alarm }
                val countAirConditional = workSite.jobs.count { it.airConditioning }

                val counts = mapOf(
                    JobType.ELE to countElectric,
                    JobType.ALA to countAlarm,
                    JobType.CDZ to countAirConditional
                )

                return counts.maxByOrNull { it.value }?.key ?: JobType.NONE
            }
        }
        return JobType.NONE
    }
}