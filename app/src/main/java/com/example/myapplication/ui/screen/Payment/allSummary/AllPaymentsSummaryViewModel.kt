package com.example.myapplication.ui.screen.Payment.allSummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.Revenue
import com.example.myapplication.data.database.RevenueFullDetails
import com.example.myapplication.data.modules.FilterKey
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.Locale.getDefault

data class Payment(
    val customer : String = "?",
    val invoiceNumber : String = "",
    val price : String = "",
    val checked : Boolean = false,
    val revenueId : Int = 0
)

data class AllPaymentsSummaryState(
    val started : Boolean = false,
    val payments : List<RevenueFullDetails> = emptyList(),
    val paymentsView : List<Payment> = emptyList(),
    val searchString: String = "",
    val filterKey : FilterKey = FilterKey.ASC_DATE,
    val showDialog : Boolean = false,
    val selectPaymentId: Int? = null,
    val  inputAmount: String = ""
)

interface AllPaymentsSummaryActions{
    fun sortAscendingByDate()
    fun sortDescendingByDate()
    fun sortAscendingByAmount()
    fun sortDescendingByAmount()
    fun searchPayment(searchString : String)
    fun setChecked(id : Int)
    fun closeDialog()
    fun setAmountPaid(newAmount : String)
    fun onConfirmPayment()
}

class AllPaymentsSummaryViewModel(
    private val repository : Repository
) : ViewModel() {

    private val _state = MutableStateFlow(AllPaymentsSummaryState())

    val state = _state.asStateFlow()

    init{
        populateView()
    }

    val actions = object : AllPaymentsSummaryActions{

        override fun sortAscendingByDate() {
            _state.update { currentState ->
                currentState.copy(
                    paymentsView = mapViewList(currentState.payments.sortedWith(compareBy { it.revenue.issueDate })),
                    filterKey = FilterKey.ASC_DATE
                )
            }
        }

        override fun sortDescendingByDate() {
            _state.update { currentState ->
                currentState.copy(
                    paymentsView = mapViewList(currentState.payments.sortedWith(compareByDescending { it.revenue.issueDate })),
                    filterKey = FilterKey.DESC_DATE
                )
            }
        }

        override fun sortAscendingByAmount() {
            _state.update { currentState ->
                currentState.copy(
                    paymentsView = mapViewList(currentState.payments.sortedWith(compareBy { it.revenue.amount })),
                    filterKey = FilterKey.ASC_AMOUNT
                )
            }
        }

        override fun sortDescendingByAmount() {
            _state.update { currentState ->
                currentState.copy(
                    paymentsView = mapViewList(currentState.payments.sortedWith(compareByDescending { it.revenue.amount })),
                    filterKey = FilterKey.DESC_AMOUNT
                )
            }
        }

        override fun searchPayment(searchString: String) {
            _state.update {currentState ->
                currentState.copy(
                    searchString = searchString,
                    paymentsView = mapViewList(searchFilter(searchString, currentState.payments))
                )
            }
        }

        override fun setChecked(id: Int) {
            openDialog(id)
        }

        override fun closeDialog() {
            _state.update {
                it.copy(
                    showDialog = false,
                    selectPaymentId = null,
                )
            }
        }

        override fun setAmountPaid(newAmount: String) {
            _state.update { it.copy(inputAmount = newAmount) }
        }

        override fun onConfirmPayment() {
            val currentState = state.value
            val id = currentState.selectPaymentId ?: return

            val revenue = currentState.payments.find { it.revenue.id == id } ?: return

            val amountPaid = revenue.revenue.amountPaid.toBigDecimal()
            val convertedAmount = checkNewAmount(currentState.inputAmount)
            val newAmountPaid = amountPaid + convertedAmount
            val newPercentage = calculatePercentage(revenue.revenue.amount.toBigDecimal(), newAmountPaid)

            viewModelScope.launch {
                repository.accounting.upsertRevenue(
                    Revenue(
                        id = revenue.revenue.id,
                        invoice = revenue.revenue.invoice,
                        issueDate = revenue.revenue.issueDate,
                        amount = revenue.revenue.amount,
                        amountPaid = newAmountPaid.setScale(2, RoundingMode.HALF_EVEN).toFloat(),
                        percent = newPercentage.toInt(),
                        collectionDate = revenue.revenue.collectionDate,
                        worksite = revenue.revenue.worksite,
                        job = revenue.revenue.job
                    )
                )
                closeDialog()
            }
        }
    }

    private fun populateView(){
        viewModelScope.launch {
            repository.accounting.getFlowAllRevenuesFullDetails().collect{ revenues ->
                _state.update {
                    it.copy(
                        payments = revenues,
                        paymentsView = mapViewList(searchFilter(it.searchString, revenues)),
                        started = true
                    )
                }
            }
        }
    }

    private fun searchFilter(searchString: String, payments : List<RevenueFullDetails>) : List<RevenueFullDetails>{
        if(searchString.isBlank()) return payments
        val query = searchString.trim().lowercase(getDefault())
        return payments.filter {
            it.revenue.invoice.toString().startsWith(query) ||
            it.revenue.amount.toString().startsWith(query) ||
            it.job?.customer?.privateCustomer?.lastName?.lowercase()?.startsWith(query) ?: false ||
            it.job?.customer?.companyCustomer?.companyName?.lowercase()?.startsWith(query) ?:false ||
            it.job?.customer?.customer?.name?.lowercase()?.startsWith(query) ?: false ||
            it.workSite?.customer?.privateCustomer?.lastName?.lowercase()?.startsWith(query) ?: false ||
            it.workSite?.customer?.companyCustomer?.companyName?.lowercase()?.startsWith(query) ?:false ||
            it.workSite?.customer?.customer?.name?.lowercase()?.startsWith(query) ?: false
        }
    }

    private fun getCustomerName(revenue : RevenueFullDetails) : String{
        val customer : String
        if(revenue.workSite != null) {
            customer = when {
                revenue.workSite.customer?.isPrivate == true -> {
                    "${revenue.workSite.customer.privateCustomer?.lastName} ${revenue.workSite.customer.customer
                        .name}"
                }
                revenue.workSite.customer?.isCompany == true -> {"${revenue.workSite.customer.companyCustomer?.companyName}"}
                else -> {""}
            }
        }else{
            customer = when {
                revenue.job?.customer?.isPrivate == true -> {
                    "${revenue.job.customer.privateCustomer?.lastName} ${revenue.job.customer.customer.name}"
                }
                revenue.job?.customer?.isCompany == true -> {"${revenue.job.customer.companyCustomer?.companyName}"}
                else -> {""}
            }
        }

        return customer
    }

    private fun mapViewList(payments : List<RevenueFullDetails>) : List<Payment> {
        return payments.map { revenue ->
            val price = revenue.revenue.amount.toBigDecimal() - revenue.revenue.amountPaid.toBigDecimal()
            Payment(
                customer = getCustomerName(revenue),
                revenueId = revenue.revenue.id,
                invoiceNumber = "${revenue.revenue.invoice}",
                price = "$price",
                checked = revenue.revenue.percent == 100
            )
        }
    }

    private fun openDialog(id: Int) {
        _state.update {
            it.copy(
                showDialog = true,
                selectPaymentId = id,
                inputAmount = ""
            )
        }
    }

    private fun checkNewAmount(value : String) : BigDecimal = value.toBigDecimalOrNull() ?: BigDecimal.ZERO

    private fun calculatePercentage(total : BigDecimal, amount : BigDecimal) : BigDecimal{
        if(total == BigDecimal.ZERO || total.signum() == 0) return BigDecimal.ZERO
        return amount.multiply(BigDecimal("100")).divide(total, 0, RoundingMode.HALF_DOWN)
    }
}