package com.example.myapplication.ui.screen.Invoice.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.Revenue
import com.example.myapplication.data.database.RevenueFullDetails
import com.example.myapplication.data.repository.Repository
import com.example.myapplication.ui.component.checkStringIsBigDecimal
import com.example.myapplication.ui.component.convertStringToDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class InvoiceAddState(
    val revenueId : Int = 0,
    val customerCf : String? = null,
    val invoiceNumber : String = "",
    val issueDate : String = "",
    val amount : String = "",
    val job : Int? = null,
    val worksite : Int? = null,

    val revenue: RevenueFullDetails? = null,
    val started : Boolean = false
)

interface InvoiceAddActions{
    fun populateView(id : Int?)
    fun setCustomer(cf : String)
    fun setInvoiceNumber(number : String)
    fun setIssueDate(date : String)
    fun setAmount(amount : String)
    fun setJob(id : Int)
    fun setWorksite(id : Int)
    fun saveInvoice(onSuccess : (Int) -> Unit)
    fun deleteInvoice(id : Int, onSuccess: () -> Unit)
}

class InvoiceAddViewModel(
    private val repository : Repository
) : ViewModel() {

    private val _state = MutableStateFlow(InvoiceAddState())

    val state = _state.asStateFlow()

    val actions = object : InvoiceAddActions{

        override fun populateView(id: Int?) {
            if (!state.value.started) {
                id?.let {
                    viewModelScope.launch {
                        val revenue = repository.accounting.getRevenueFullDetailsById(id)

                        revenue?.let {
                            _state.update {
                                it.copy(
                                    revenueId = revenue.revenue.id,
                                    customerCf = revenue.job?.job?.customer ?: revenue.workSite?.workSite?.customer
                                    ?: "",
                                    invoiceNumber = revenue.revenue.invoice.toString(),
                                    issueDate = revenue.revenue.issueDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                    amount = revenue.revenue.amount.toString(),
                                    job = revenue.revenue.job,
                                    worksite = revenue.revenue.worksite,
                                    revenue = revenue,
                                    started = true
                                )
                            }
                        }
                    }
                }
            }
        }

        override fun setCustomer(cf: String) {
            _state.update { it.copy(customerCf = cf) }
        }

        override fun setInvoiceNumber(number: String) {
            _state.update { it.copy(invoiceNumber = number) }
        }

        override fun setIssueDate(date: String) {
            _state.update { it.copy(issueDate = date) }
        }

        override fun setAmount(amount: String) {
            _state.update { it.copy(amount = amount) }
        }

        override fun setWorksite(id: Int) {
            _state.update { it.copy(worksite = id) }
        }

        override fun setJob(id: Int) {
            _state.update { it.copy(job = id) }
        }

        override fun saveInvoice(onSuccess: (Int) -> Unit) {
            val currentState = state.value
            viewModelScope.launch {
                val amount =
                    if(currentState.amount.isNotBlank() && checkStringIsBigDecimal(currentState.amount)){
                        BigDecimal(currentState.amount).toFloat()
                    }else{
                        currentState.revenue?.revenue?.amount ?: BigDecimal.ZERO.toFloat()
                    }

                val revenueFinalId = repository.accounting.saveRevenueComplete(
                    revenue = Revenue(
                        id = currentState.revenueId,
                        invoice = currentState.invoiceNumber.toIntOrNull() ?: currentState.revenue?.revenue?.invoice ?: 0,
                        issueDate = convertStringToDate(currentState.issueDate) ?: currentState.revenue?.revenue?.issueDate ?: LocalDate.now(),
                        amount = amount,
                        amountPaid = currentState.revenue?.revenue?.amountPaid ?: BigDecimal.ZERO.toFloat(),
                        percent = currentState.revenue?.revenue?.percent ?: 0,
                        collectionDate = currentState.revenue?.revenue?.collectionDate,
                        worksite = currentState.worksite ?: currentState.revenue?.workSite?.workSite?.id,
                        job = currentState.job ?: currentState.revenue?.job?.job?.id
                    ),
                    customer = currentState.customerCf ?: ""
                )

                onSuccess(revenueFinalId)
            }
        }

        override fun deleteInvoice(id: Int, onSuccess: () -> Unit) {
            val currentState = state.value

            if (currentState.revenueId != 0 && currentState.revenue != null) {

                viewModelScope.launch {
                    repository.accounting.deleteRevenue(
                        Revenue(
                            id = currentState.revenueId,
                            invoice = currentState.revenue.revenue.invoice,
                            issueDate = currentState.revenue.revenue.issueDate,
                            amount = currentState.revenue.revenue.amount,
                            job = currentState.revenue.revenue.job,
                            worksite = currentState.revenue.revenue.worksite,
                            amountPaid = currentState.revenue.revenue.amountPaid,
                            percent = currentState.revenue.revenue.percent
                        )
                    )

                    onSuccess()
                }
            }else{
                onSuccess()
            }
        }
    }
}