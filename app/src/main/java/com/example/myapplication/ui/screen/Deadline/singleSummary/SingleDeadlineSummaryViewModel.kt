package com.example.myapplication.ui.screen.Deadline.singleSummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.HeaderPurchaseInvoice
import com.example.myapplication.data.database.Payment
import com.example.myapplication.data.modules.DeadlineType
import com.example.myapplication.data.modules.FrequencyType
import com.example.myapplication.data.repository.Repository
import com.example.myapplication.ui.component.convertStringToDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.time.LocalDate

data class SingleDeadlineSummaryState(
    val id : Int = 0,
    val type : DeadlineType = DeadlineType.Tipo,
    val name : String = "",
    val frequency : FrequencyType = FrequencyType.Nessuna,
    val price : BigDecimal = BigDecimal.ZERO,
    val category : String = "",
    val deadlineDate : LocalDate? = null,
    val purchaseInvoice: HeaderPurchaseInvoice? = null,
    val seller : String = "",
    val payments : List<Payment?> = emptyList(),

    val showDialog : Boolean = false,
    val selectDeadlinePaymentId: Int? = null,
    val inputDate: String = ""
)

interface SingleDeadlineSummaryActions{
    fun populateView(id : Int, type : DeadlineType)
    fun openDialog(id: Int)
    fun setDatePaid(newDate : String)
    fun onConfirmPayment()
    fun closeDialog()
}

class SingleDeadlineSummaryViewModel(
    private val repository : Repository
) : ViewModel() {

    private val _state = MutableStateFlow(SingleDeadlineSummaryState())

    val state = _state.asStateFlow()

    val actions = object : SingleDeadlineSummaryActions {

        override fun populateView(id: Int, type: DeadlineType) {
            if(type == DeadlineType.Singola){
                viewModelScope.launch {
                    repository.accounting.getFlowSingleExpenseFullDetailsById(id).collect { expense ->
                        expense?.let {
                            _state.update {
                                it.copy(
                                    id = id,
                                    type = DeadlineType.Singola,
                                    name = expense.singleExpense.name,
                                    price = expense.singleExpense.amount.toBigDecimal(),
                                    category = expense.category.name,
                                    deadlineDate = expense.singleExpense.deadlineDate,
                                    purchaseInvoice = expense.purchaseInvoice,
                                    seller = expense.purchaseInvoice?.seller?.name ?: "",
                                    payments = listOf(expense.payment)
                                )
                            }
                        }
                    }
                }
            }else if(type == DeadlineType.Periodica){
                viewModelScope.launch {
                    repository.accounting.getFlowRecurringExpenseFullDetailsById(id).collect{ expense ->
                        expense?.let {
                            _state.update {
                                it.copy(
                                    id = id,
                                    type = DeadlineType.Periodica,
                                    name = expense.recurringExpense.name,
                                    price = expense.recurringExpense.amount.toBigDecimal(),
                                    category = expense.category.name,
                                    frequency = expense.recurringExpense.frequency,
                                    deadlineDate = expense.recurringExpense.endDate,
                                    purchaseInvoice = expense.purchaseInvoice,
                                    seller = expense.purchaseInvoice?.seller?.name ?: "",
                                    payments = expense.payments.map { recurringPayment -> recurringPayment.payment }
                                        .sortedByDescending { item -> item.issueDate }
                                )
                            }
                        }
                    }
                }
            }
        }

        override fun openDialog(id: Int) {
            _state.update {
                it.copy(
                    showDialog = true,
                    selectDeadlinePaymentId = id,
                    inputDate = ""
                )
            }
        }

        override fun setDatePaid(newDate: String) {
            _state.update { it.copy(inputDate = newDate) }
        }

        override fun onConfirmPayment() {
            val currentState = state.value
            val expenseId = if(currentState.id != 0) currentState.id else return
            val paymentId = currentState.selectDeadlinePaymentId ?: return

            val newPaymentDate = convertStringToDate(currentState.inputDate) ?: return

            viewModelScope.launch {
                repository.accounting.editPaymentDateByPaymentId(paymentId, newPaymentDate, expenseId)
                closeDialog()
            }
        }

        override fun closeDialog() {
            _state.update {
                it.copy(
                    showDialog = false,
                    selectDeadlinePaymentId = null,
                )
            }
        }
    }
}