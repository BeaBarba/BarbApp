package com.example.myapplication.ui.screen.Deadline.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.CategoryPurchaseInvoice
import com.example.myapplication.data.database.Payment
import com.example.myapplication.data.database.RecurringExpense
import com.example.myapplication.data.database.SingleExpense
import com.example.myapplication.data.modules.DeadlineType
import com.example.myapplication.data.modules.FrequencyType
import com.example.myapplication.data.repository.Repository
import com.example.myapplication.ui.component.MenuItem
import com.example.myapplication.ui.component.convertStringtoDate
import com.example.myapplication.ui.component.checkStringIsBigDecimal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.time.LocalDate

data class DeadlineAddState(
    val expenseId : Int? = null,
    val expenseType : DeadlineType = DeadlineType.Tipo,
    val frequency : FrequencyType = FrequencyType.Nessuna,
    val category : CategoryPurchaseInvoice? = null,
    val name : String = "",
    val issueDate : LocalDate? = null,
    val deadlineDate : LocalDate? = null,
    val amount : BigDecimal? = null,
    val purchaseInvoiceSelected : List<Int> = emptyList(),
    val paymentsIds : List<Int> = emptyList(),

    val categoryView : String = "New",
    val categoriesMenu : List<MenuItem> = emptyList(),
    val frequencyMenu : List<MenuItem> = emptyList(),
    val expenseTypeMenu : List<MenuItem> = emptyList(),
    val errorMessage : String? = null,
    val started : Boolean = false
)


interface DeadlineAddActions{
    fun populateView(expenseId : Int?, expenseType: DeadlineType, labelNew : String)
    fun setExpenseType(type : Pair<Int,String>)
    fun setFrequency(frequency : FrequencyType)
    fun setCategory(category : Pair<Int,String>)
    fun setNewCategory(categoryName : String)
    fun setName(name : String)
    fun setIssueDate(date : String)
    fun setDeadlineDate(date : String)
    fun setAmount(amount : String)
    fun setPurchaseInvoices(ids : List<String>)
    fun getPurchaseInvoiceSelected() : List<String>
    fun saveNewExpense(onSuccess : (Int, DeadlineType) -> Unit)
    fun checkRequirements() : Boolean
    fun resetErrorMessage()
    fun deleteExpense()
}

class DeadlineAddViewModel(
    private val repository : Repository
) : ViewModel() {

    private val _state = MutableStateFlow(DeadlineAddState())

    val state = _state.asStateFlow()

    val actions = object : DeadlineAddActions{

        override fun populateView(expenseId : Int?, expenseType: DeadlineType, labelNew : String){

            if(state.value.started) return

            val deadlineTypes = getDeadlineTypeMenu()
            val frequencies = getFrequencyMenu()
            viewModelScope.launch {
                val categories = repository.accounting.categoryPurchaseInvoice.first()
                val categoriesMenu = categories.map { category ->
                    MenuItem(
                        idValues = Pair(category.id, category.name),
                        name = category.name,
                        onClick = { setCategory(Pair(category.id, category.name)) }
                    )
                }.sortedWith(
                    compareBy{it.name}
                ) +
                MenuItem(
                    idValues = Pair(0, labelNew),
                    name = labelNew,
                    onClick = { setCategory(Pair(0, labelNew)) }
                )

                var loadedState = DeadlineAddState()
                expenseId?.let{ id ->
                    when (expenseType){
                        DeadlineType.Singola -> {
                            val expense = repository.accounting.getSingleExpenseFullDetailsById(expenseId)
                            loadedState = loadedState.copy(
                                expenseId = id,
                                expenseType = expenseType,
                                category = expense.category,
                                name = expense.singleExpense.name,
                                issueDate = expense.payment?.issueDate,
                                deadlineDate = expense.singleExpense.deadlineDate,
                                amount = expense.singleExpense.amount.toBigDecimal(),
                                purchaseInvoiceSelected = expense.purchaseInvoice?.let{ listOf(it.purchaseInvoice.id)
                                } ?: emptyList(),
                                paymentsIds = expense.payment?. let{ listOf(it.id)} ?: emptyList()
                            )
                        }
                        DeadlineType.Periodica -> {
                            val expense = repository.accounting.getRecurringExpenseFullDetailsById(expenseId)
                            val payments = expense.payments.map { it.payment.id }
                            loadedState = loadedState.copy(
                                expenseId = id,
                                expenseType = expenseType,
                                frequency = expense.recurringExpense.frequency,
                                category = expense.category,
                                name = expense.recurringExpense.name,
                                issueDate = expense.payments.minOfOrNull{it.payment.issueDate},
                                deadlineDate = expense.recurringExpense.endDate,
                                amount = expense.recurringExpense.amount.toBigDecimal(),
                                purchaseInvoiceSelected = expense.purchaseInvoice?.let { listOf(it.purchaseInvoice.id) } ?: emptyList(),
                                paymentsIds = payments
                            )
                        }
                        else -> {}
                    }
                }

                _state.update { currentState ->
                    loadedState.copy(
                        expenseTypeMenu = deadlineTypes,
                        frequencyMenu = frequencies,
                        categoriesMenu = categoriesMenu,
                        categoryView = loadedState.category?.name ?: currentState.categoryView,
                        started = true
                    )
                }
            }
        }

        override fun setExpenseType(type: Pair<Int, String>) {
            _state.update { it.copy(expenseType = DeadlineType.valueOf(type.second)) }
        }

        override fun setFrequency(frequency : FrequencyType) {
            _state.update { it.copy(frequency = frequency) }
        }

        override fun setCategory(category: Pair<Int, String>) {
            _state.update {
                it.copy(
                    categoryView = category.second,
                    category = CategoryPurchaseInvoice(
                        id = category.first,
                        name = category.second
                    )
                )
            }
        }

        override fun setNewCategory(categoryName: String) {
            _state.update {
                it.copy(
                    categoryView = categoryName,
                    category = CategoryPurchaseInvoice(
                        id = 0,
                        name = categoryName
                    )
                )
            }
        }

        override fun setName(name : String){
            _state.update{ it.copy(name = name) }
        }

        override fun setIssueDate(date: String) {
            val dateConverted = convertStringtoDate(date)
            _state.update { it.copy(issueDate = dateConverted) }
        }

        override fun setDeadlineDate(date: String) {
            val dateConverted = convertStringtoDate(date)
            _state.update { it.copy(deadlineDate = dateConverted) }
        }

        override fun setAmount(amount: String) {
            if(checkStringIsBigDecimal(amount)){
                val amountToSet = if(amount.isBlank()) null else amount.toBigDecimal()
                _state.update{it.copy(amount = amountToSet)}
            }
        }

        override fun setPurchaseInvoices(ids: List<String>) {
            val inputIds = ids.mapNotNull { it.toIntOrNull() }

            val currentSelected = state.value.purchaseInvoiceSelected
            val preservedIds = currentSelected.filter { it in inputIds }

            val newIdsToFetch = inputIds.filterNot { it in preservedIds }

            val updatedList = preservedIds + newIdsToFetch

            _state.update { it.copy(purchaseInvoiceSelected = updatedList) }
        }

        override fun getPurchaseInvoiceSelected(): List<String> {
            return state.value.purchaseInvoiceSelected.map{it.toString()}
        }

        override fun saveNewExpense(onSuccess : (Int, DeadlineType) -> Unit) {
            val currentState = state.value

            if(checkRequirements()) return

            viewModelScope.launch {
                val category = CategoryPurchaseInvoice(
                    id = currentState.category?.id ?: 0,
                    name = currentState.category?.name ?: ""
                )

                when(currentState.expenseType){
                    DeadlineType.Singola -> {
                        val payment = Payment(
                                id = currentState.paymentsIds.firstOrNull() ?: 0,
                                issueDate = currentState.issueDate ?: LocalDate.now(),
                                paymentDate = null
                            )
                        val newExpense = SingleExpense(
                                id = currentState.expenseId ?: 0,
                                name = currentState.name,
                                amount = currentState.amount?.toFloat() ?: 0.0f,
                                deadlineDate = currentState.deadlineDate ?: LocalDate.now(),
                                category = currentState.category!!.id,
                                purchaseInvoice = currentState.purchaseInvoiceSelected.firstOrNull(),
                                payment = null
                            )

                        val newExpenseId =  repository.accounting.saveSingleExpenseComplete(newExpense, payment, category)
                        onSuccess(newExpenseId, DeadlineType.Singola)
                    }
                    DeadlineType.Periodica -> {
                        val payment = Payment(
                                id = 0,
                                issueDate = currentState.issueDate ?: LocalDate.now(),
                                paymentDate = null
                            )

                        val newExpense = RecurringExpense(
                                id = currentState.expenseId ?: 0,
                                name = currentState.name,
                                frequency = currentState.frequency,
                                amount = currentState.amount?.toFloat() ?: 0.0f,
                                category = currentState.category!!.id,
                                endDate = currentState.deadlineDate,
                                purchaseInvoice = currentState.purchaseInvoiceSelected.firstOrNull()
                            )
                        val newExpenseId = repository.accounting.saveRecurringExpenseComplete(
                                payment = payment,
                                expense = newExpense,
                                category = category
                        )
                        onSuccess(newExpenseId, DeadlineType.Periodica)
                    }
                    else -> {}
                }
            }
        }

        override fun resetErrorMessage() {
            _state.update { it.copy(errorMessage = null) }
        }

        override fun checkRequirements() : Boolean {
            val currentState = state.value

            if (currentState.expenseType == DeadlineType.Tipo) {
                _state.update { it.copy(errorMessage = "Seleziona un tipo di spesa per continuare") }
                return true
            }

            if (currentState.category == null) {
                _state.update { it.copy(errorMessage = "Seleziona un tipo di categoria per continuare") }
                return true
            }

            if (currentState.issueDate == null) {
                _state.update { it.copy(errorMessage = "Seleziona la data di emissione per continuare") }
                return true
            }

            if (currentState.frequency == FrequencyType.Nessuna && currentState.expenseType == DeadlineType.Periodica) {
                _state.update { it.copy(errorMessage = "Seleziona la frequenza dei pagamenti per continuare") }
                return true
            }

            if (currentState.amount == null || currentState.amount < BigDecimal.ZERO) {
                _state.update { it.copy(errorMessage = "Definisci l'importo per continuare") }
                return true
            }

            return false
        }

        override fun deleteExpense() {
            viewModelScope.launch {
                val currentState = state.value

                when (currentState.expenseType) {
                    DeadlineType.Singola -> {
                        currentState.expenseId?.let {
                            repository.accounting.deleteSingleExpenseComplete(currentState.expenseId)
                        }
                    }
                    DeadlineType.Periodica ->{
                        currentState.expenseId?.let{
                            repository.accounting.deleteRecurringExpenseComplete(currentState.expenseId)
                        }
                    }
                    else -> {}
                }
            }
        }

    }

    private fun getDeadlineTypeMenu() : List<MenuItem>{
        return DeadlineType.entries.map { entry ->
            MenuItem(
                idValues = Pair(0,entry.name),
                name = entry.name,
                onClick = { actions.setExpenseType(Pair(0,entry.name)) }
            )
        }
    }

    private fun getFrequencyMenu() : List<MenuItem>{
        return FrequencyType.entries.map { entry ->
            MenuItem(
                idValues = Pair(0,entry.name),
                name = entry.name,
                onClick = { actions.setFrequency(entry) }
            )
        }
    }
}



