package com.example.myapplication.ui.screen.Deadline.allSummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.RecurringExpenseFullDetails
import com.example.myapplication.data.database.SingleExpenseFullDetails
import com.example.myapplication.data.modules.DeadlineType
import com.example.myapplication.data.modules.FilterKey
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Locale.getDefault

data class Deadline(
    val id : Int = 0,
    val key : String = "${DeadlineType.Tipo}_$id",
    val type : DeadlineType = DeadlineType.Tipo,
    val name : String = "",
    val category : String,
    val seller : String? = null,
    val amount : Float = 0.0f,
    val deadlineDate : LocalDate = LocalDate.now(),
    val checked : Boolean = false
)

data class AllDeadlineSummaryState(
    val filterKey : FilterKey = FilterKey.ASC_DATE,
    val categoriesList : List<String> = emptyList(),
    val sellers : List<String> = emptyList(),
    val searchText : String = "",
    val deadlines : List<Deadline> = emptyList(),
    val deadlinesView : List<Deadline> = emptyList()
)

interface AllDeadlinesSummaryActions{
    fun filterAll()
    fun filterByCategory(category : String)
    fun filterBySeller(seller : String)
    fun ascendingOrder()
    fun descendingOrder()
    fun searchDeadline(searchText : String)
    fun setChecked(deadline : Deadline)
}

class AllDeadlinesSummaryViewModel(
    private val repository: Repository
) : ViewModel() {

    private  val _state =  MutableStateFlow(AllDeadlineSummaryState())

    val state = _state.asStateFlow()

    init{
        populateDeadlines()
    }

    val actions = object : AllDeadlinesSummaryActions {

        override fun filterAll() {
            _state.update {
                it.copy(
                    deadlinesView = state.value.deadlines.sortedWith(
                        compareBy{it.deadlineDate}
                    ),
                    filterKey = FilterKey.ASC_DATE
                )
            }
        }

        override fun filterByCategory(category: String) {
            _state.update {
                it.copy(
                    deadlinesView = state.value.deadlines.filter { it.category == category },
                    filterKey = FilterKey.ASC_CATEGORY
                )
            }
        }

        override fun filterBySeller(seller: String) {
            _state.update {
                it.copy(
                    deadlinesView = state.value.deadlines.filter { it.seller == seller },
                    filterKey = FilterKey.ASC_SELLER
                )
            }
        }

        override fun ascendingOrder() {
            when(state.value.filterKey){

                FilterKey.DESC_DATE -> { _state.update { it.copy(
                    deadlinesView = state.value.deadlinesView.reversed(),
                    filterKey = FilterKey.ASC_DATE
                ) }}
                FilterKey.DESC_CATEGORY -> {
                    _state.update { it.copy(
                        deadlinesView = state.value.deadlinesView.reversed(),
                        filterKey = FilterKey.ASC_CATEGORY
                    ) }
                }
                FilterKey.DESC_SELLER -> {
                    _state.update { it.copy(
                        deadlinesView = state.value.deadlinesView.reversed(),
                        filterKey = FilterKey.ASC_SELLER
                    ) }
                }
                else -> {}
            }
        }

        override fun descendingOrder()  {
            when(state.value.filterKey){

                FilterKey.ASC_DATE -> { _state.update { it.copy(
                    deadlinesView = state.value.deadlinesView.reversed(),
                    filterKey = FilterKey.DESC_DATE
                ) }}
                FilterKey.ASC_CATEGORY -> {
                    _state.update { it.copy(
                        deadlinesView = state.value.deadlinesView.reversed(),
                        filterKey = FilterKey.DESC_CATEGORY
                    ) }
                }
                FilterKey.ASC_SELLER -> {
                    _state.update { it.copy(
                        deadlinesView = state.value.deadlinesView.reversed(),
                        filterKey = FilterKey.DESC_SELLER
                    ) }
                }
                else -> {}
            }
        }

        override fun searchDeadline(searchText: String) {
            _state.update {
                it.copy(
                    deadlinesView = searchFilter(searchText, state.value.deadlines),
                    searchText = searchText
                )
            }
        }

        override fun setChecked(deadline : Deadline) {
            viewModelScope.launch {
                val paymentId = deadline.key.split("_").last().toInt()

                val newDate = if (!deadline.checked) LocalDate.now() else null

                repository.accounting.updatePaymentDateById(paymentId, newDate)
            }
        }
    }

    private fun populateDeadlines(){
        viewModelScope.launch {
            combine(
                repository.accounting.getFlowAllSingleExpenseFullDetails(),
                repository.accounting.getFlowAllRecurringExpensesFullDetails(),
                repository.inventory.sellers,
                repository.accounting.categoryPurchaseInvoice
            ) { singles, recurring, sellers, categories ->

                val allDeadlines = mutableListOf<Deadline>()

                singles.filterNotNull().forEach { expanse ->
                    allDeadlines.add(mappingSingleExpense(expanse))
                }

                recurring.forEach { expanse ->
                    allDeadlines.addAll(mappingRecurringExpanse(expanse))
                }

                Triple(
                    allDeadlines.sortedBy { it.deadlineDate },
                    sellers.map { it.name },
                    categories.map { it.name })
            }.collect{ (allDeadlines, sellers, categories) ->
                _state.update {
                    it.copy(
                        deadlines = allDeadlines,
                        deadlinesView = searchFilter(state.value.searchText, allDeadlines),
                        categoriesList = categories,
                        sellers = sellers,
                    )
                }
            }
        }
    }

    private fun mappingSingleExpense(expense : SingleExpenseFullDetails) : Deadline{
        return Deadline(
                id = expense.singleExpense.id,
                key = "${DeadlineType.Singola}_${expense.singleExpense.payment}",
                type = DeadlineType.Singola,
                category = expense.category.name,
                seller = expense.purchaseInvoice?.seller?.name,
                name = expense.singleExpense.name,
                amount = expense.singleExpense.amount,
                deadlineDate = expense.singleExpense.deadlineDate,
                checked = expense.payment?.paymentDate != null
            )
    }

    private fun mappingRecurringExpanse(expense : RecurringExpenseFullDetails) : List<Deadline>{
        val deadlinesList = mutableListOf<Deadline>()
        expense.payments.forEach { rate ->
             deadlinesList.add(
                 Deadline(
                    id = rate.recurringPayment.payment,
                    key = "${DeadlineType.Periodica}_${rate.recurringPayment.payment}",
                    type = DeadlineType.Periodica,
                    category = expense.category.name,
                    seller = expense.purchaseInvoice?.seller?.name,
                    name = expense.recurringExpense.name,
                    amount = expense.recurringExpense.amount,
                    deadlineDate = rate.payment.issueDate,
                    checked = rate.payment.paymentDate != null
                 )
            )
        }
        return deadlinesList
    }

    private fun searchFilter(searchString: String, deadline: List<Deadline>) : List<Deadline>{
        if(searchString.isBlank()) return deadline
        val query = searchString.trim().lowercase(getDefault())
        return deadline.filter {
            it.category.lowercase().contains(query) ||
            it.name.lowercase().contains(query) ||
            (it.seller?.lowercase()?.contains(query)) ?: false
        }
    }
}