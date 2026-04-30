package com.example.myapplication.data.repository

import androidx.room.withTransaction
import com.example.myapplication.data.database.AppDatabase
import com.example.myapplication.data.database.Bubble
import com.example.myapplication.data.database.BubbleFullDetails
import com.example.myapplication.data.database.CategoryPurchaseInvoice
import com.example.myapplication.data.database.Payment
import com.example.myapplication.data.database.Purchase
import com.example.myapplication.data.database.PurchaseInvoice
import com.example.myapplication.data.database.PurchaseInvoiceFullDetails
import com.example.myapplication.data.database.RecurringExpense
import com.example.myapplication.data.database.RecurringExpenseFullDetails
import com.example.myapplication.data.database.RecurringPayment
import com.example.myapplication.data.database.Revenue
import com.example.myapplication.data.database.RevenueFullDetails
import com.example.myapplication.data.database.Seller
import com.example.myapplication.data.database.SingleExpense
import com.example.myapplication.data.database.SingleExpenseFullDetails
import com.example.myapplication.data.modules.FrequencyType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate

class AccountingRepository (private val db : AppDatabase) {

    /* Category */
    val categoryPurchaseInvoice = db.categoryPurchaseInvoiceDAO().getAllCategoriesPurchaseInvoice()

    fun getCategoryPurchaseInvoiceById(id: Int): Flow<CategoryPurchaseInvoice?> =
        db.categoryPurchaseInvoiceDAO().getCategoryPurchaseInvoice(id)

    suspend fun upsertCategoryPurchaseInvoice(category: CategoryPurchaseInvoice) : Long =
        db.categoryPurchaseInvoiceDAO().upsertCategoryPurchaseInvoice(category)

    suspend fun deleteCategoryPurchaseInvoice(category: CategoryPurchaseInvoice) =
        db.categoryPurchaseInvoiceDAO().deleteCategoryPurchaseInvoice(category)

    private suspend fun checkCategoryId(category : CategoryPurchaseInvoice) : Int{
        val categoryId =
            if(category.id == 0){
                val newId = upsertCategoryPurchaseInvoice(category).toInt()

                if(newId == -1) 0 else newId
            }else{
                category.id
            }
        return categoryId
    }

    /* Purchase Invoice */
    val purchaseInvoices = db.purchaseInvoiceDAO().getFlowAllPurchaseInvoice()

    fun getFlowPurchaseInvoiceById(id: Int): Flow<PurchaseInvoice?> =
        db.purchaseInvoiceDAO().getFlowPurchaseInvoice(id)

    fun getPurchaseInvoiceFullDetailsById(id: Int): Flow<PurchaseInvoiceFullDetails?> =
        db.purchaseInvoiceDAO().getFlowPurchaseInvoiceFullDetails(id)

    suspend fun getPurchaseInvoiceById(id : Int) : PurchaseInvoiceFullDetails? =
        db.purchaseInvoiceDAO().getPurchaseInvoiceFullDetails(id)

    fun getAllPurchaseInvoicesFullDetails(): Flow<List<PurchaseInvoiceFullDetails>> =
        db.purchaseInvoiceDAO().getFlowAllPurchaseInvoicesFullDetails()

    suspend fun upsertPurchaseInvoice(purchaseInvoice: PurchaseInvoice) =
        db.purchaseInvoiceDAO().upsertPurchaseInvoice(purchaseInvoice)

    suspend fun savePurchaseInvoiceComplete(
        purchaseInvoice : PurchaseInvoice,
        seller : Seller,
        bubblesIds : List<Int>,
        purchases : List<Purchase>
    ) : Int = withContext(Dispatchers.IO){
        db.withTransaction {

            val sellerId : Int =
                if(seller.id == 0 && seller.name.isNotBlank()){
                    db.sellerDAO().upsertSeller(seller).toInt()
                }else{
                    seller.id
                }

            val purchaseInvoiceId = upsertPurchaseInvoice(purchaseInvoice.copy(seller = sellerId)).toInt()
            val purchaseInvoiceFinalId = if(purchaseInvoiceId == -1) purchaseInvoice.id else purchaseInvoiceId

            if(purchaseInvoiceFinalId != 0 && purchaseInvoiceFinalId > 0) clearExistingPurchaseData(purchaseInvoice.id)

            println("DEBUG: Repository - bubblesIds = $bubblesIds")
            if(bubblesIds.isNotEmpty()){
                bubblesIds.forEach { bubble ->
                    db.bubbleDAO().updatePurchaseInvoiceReferenceFromBubble(bubble,purchaseInvoiceFinalId)
                    println("DEBUG: Repository - bubbles = $bubble")
                }
            }

            if(purchases.isNotEmpty()){
                purchases.forEach { purchase ->
                    db.purchaseDAO().upsertPurchase(purchase.copy(purchaseInvoice = purchaseInvoiceFinalId))

                    val material = db.materialDAO().getMaterial(purchase.material)
                    material?.let{
                            mat ->
                        val newQuantity = BigDecimal(mat.availableQuantity.toDouble())
                            .add(BigDecimal(purchase.quantity.toDouble()))
                            .coerceAtLeast(BigDecimal.ZERO)
                            .setScale(2, RoundingMode.HALF_UP)
                            .toFloat()
                        db.materialDAO().upsertMaterial(
                            mat.copy(id = mat.id, availableQuantity = newQuantity)
                        )
                    }
                }
            }

            return@withTransaction purchaseInvoiceFinalId
        }
    }

    suspend fun deletePurchaseInvoiceComplete(purchaseInvoice : PurchaseInvoice)
    = withContext(Dispatchers.IO) {
        db.withTransaction {
            val purchaseInvoiceId = purchaseInvoice.id

            clearExistingPurchaseData(purchaseInvoiceId)

            db.singleExpenseDAO().removePurchaseInvoiceReferenceFromSingleExpenses(purchaseInvoiceId)

            db.recurringExpenseDAO().removePurchaseInvoiceReferenceFromRecurringExpenses(purchaseInvoiceId)

            db.purchaseInvoiceDAO().deletePurchaseInvoice(purchaseInvoice)
        }
    }

    /* Bubbles */
    val bubbles = db.bubbleDAO().getAllBubbles()

    fun getBubbleById(id: Int): Flow<Bubble?> = db.bubbleDAO().getBubble(id)

    suspend fun upsertBubble(bubble: Bubble): Long = db.bubbleDAO().upsertBubble(bubble)

    suspend fun deleteBubble(bubble: Bubble) = db.bubbleDAO().deleteBubble(bubble)

    fun getBubbleFullDetailsById(bubble: Int): Flow<BubbleFullDetails?> =
        db.bubbleDAO().getBubbleFullDetails(bubble)

    fun getAllBubblesFullDetails(): Flow<List<BubbleFullDetails>> =
        db.bubbleDAO().getAllBubblesFullDetails()

    /* SingleExpense */
    val singleExpenses = db.singleExpenseDAO().getAllSingleExpenses()

    fun getSingleExpenseById(id: Int): Flow<SingleExpense?> = db.singleExpenseDAO().getSingleExpense(id)

    fun getFlowSingleExpenseFullDetailsById(singleExpenseId : Int) : Flow<SingleExpenseFullDetails?> =
        db.singleExpenseDAO().getFlowSingleExpenseFullDetails(singleExpenseId)

    fun getFlowAllSingleExpenseFullDetails() : Flow<List<SingleExpenseFullDetails?>> =
        db.singleExpenseDAO().getFlowAllSingleExpenseFullDetails()

    suspend fun getSingleExpenseFullDetailsById(singleExpenseId : Int) : SingleExpenseFullDetails =
        db.singleExpenseDAO().getSingleExpenseFullDetails(singleExpenseId)

    suspend fun upsertSingleExpense(singleExpense: SingleExpense) : Long =
        db.singleExpenseDAO().upsertSingleExpense(singleExpense)

    suspend fun saveSingleExpenseComplete(
        singleExpense: SingleExpense,
        payment : Payment,
        category : CategoryPurchaseInvoice
    ) : Int
    = withContext(Dispatchers.IO){
        db.withTransaction {
            val paymentId =
                if (payment.id == 0) {
                    val newId = upsertPayment(payment).toInt()

                    if (newId == -1) 0 else newId

                } else {
                    payment.id
                }

            val categoryId = checkCategoryId(category)
            val expenseId = upsertSingleExpense(singleExpense.copy(payment = paymentId, category = categoryId)).toInt()
            val newExpenseId = if(expenseId == -1) singleExpense.id else expenseId

            return@withTransaction newExpenseId
        }
    }

    private suspend fun deleteSingleExpense(singleExpense: SingleExpense) =
        db.singleExpenseDAO().deleteSingleExpense(singleExpense)

    suspend fun deleteSingleExpenseComplete(singleExpenseId : Int) =
        withContext(Dispatchers.IO){
            db.withTransaction {
                val singleExpense = getSingleExpenseFullDetailsById(singleExpenseId)

                deleteSingleExpense(singleExpense.singleExpense)

                singleExpense.payment?.let {
                    deletePayment(singleExpense.payment)
                }
            }
        }

    /* RecurringExpense */
    val recurringExpenses = db.recurringExpenseDAO().getAllRecurringExpenses()

    fun getFlowRecurringExpenseById(id: Int): Flow<RecurringExpense?> =
        db.recurringExpenseDAO().getFlowRecurringExpense(id)

    private suspend fun getRecurringExpenseById(id : Int) : RecurringExpense? =
        withContext(Dispatchers.IO){
            db.recurringExpenseDAO().getRecurringExpense(id)
        }

    fun getFlowRecurringExpenseFullDetailsById(recurringExpenseId : Int) : Flow<RecurringExpenseFullDetails?> =
        db.recurringExpenseDAO().getFlowRecurringExpenseFullDetails(recurringExpenseId)

    fun getFlowAllRecurringExpensesFullDetails() : Flow<List<RecurringExpenseFullDetails>> =
        db.recurringExpenseDAO().getFlowAllRecurringExpensesFullDetails()

    suspend fun getRecurringExpenseFullDetailsById(recurringExpenseId : Int) : RecurringExpenseFullDetails =
        db.recurringExpenseDAO().getRecurringExpenseFullDetails(recurringExpenseId)

    suspend fun upsertRecurringExpense(recurringExpense: RecurringExpense) : Long =
        db.recurringExpenseDAO().upsertRecurringExpense(recurringExpense)

    suspend fun saveRecurringExpenseComplete(
        expense : RecurringExpense,
        payment : Payment,
        category: CategoryPurchaseInvoice
    ) : Int = withContext(Dispatchers.IO){
        db.withTransaction {
            val categoryId = checkCategoryId(category)

            val finalExpense = expense.copy(category = categoryId)

            val oldExpense = if (expense.id != 0) getRecurringExpenseById(expense.id) else null

            if (oldExpense != null) {
                val today = LocalDate.now()

                val frequencyChanged = oldExpense.frequency != finalExpense.frequency
                val endDateChanged = oldExpense.endDate != finalExpense.endDate

                if (frequencyChanged || endDateChanged) {
                    val obsoletePayments = getUnpaidFuturePaymentsByExpenseId(expense.id, today)
                    deletePaymentsByIds(obsoletePayments)

                }
            }

            val expenseId = upsertRecurringExpense(finalExpense).toInt()

            val paymentId = upsertPayment(payment).toInt()
            upsertRecurringPayment(RecurringPayment(paymentId, expenseId))

            generateNextOrAllPayments(paymentId, expenseId)

            return@withTransaction expenseId
        }
    }

    private suspend fun deleteRecurringExpense(recurringExpense: RecurringExpense) =
        db.recurringExpenseDAO().deleteRecurringExpense(recurringExpense)

    suspend fun deleteRecurringExpenseComplete(recurringExpenseId : Int) =
        withContext(Dispatchers.IO){
            db.withTransaction {
                val recurringExpense = getRecurringExpenseFullDetailsById(recurringExpenseId)

                recurringExpense.payments.forEach { payment ->
                    deletePayment(payment.payment)
                }

                deleteRecurringExpense(recurringExpense.recurringExpense)
            }
        }

    /* Payment */
    val payments = db.paymentDAO().getAllPayments()

    fun getFlowPaymentById(id: Int): Flow<Payment?> = db.paymentDAO().getFlowPayment(id)

    private suspend fun getPaymentById(id : Int) : Payment? =
        withContext(Dispatchers.IO) {
            db.paymentDAO().getPayment(id)
        }

    private suspend fun checkExistingNextPayment(date : LocalDate, expenseId : Int) : Boolean =
        withContext(Dispatchers.IO){
            db.paymentDAO().checkExistingNextPayment(date, expenseId) > 0
        }

    private suspend fun getUnpaidFuturePaymentsByExpenseId(expenseId : Int, date : LocalDate) =
        withContext(Dispatchers.IO){
            db.paymentDAO().getUnpaidFuturePaymentsByExpense(expenseId, date)
        }

    suspend fun upsertPayment(payment: Payment) : Long = db.paymentDAO().upsertPayment(payment)

    suspend fun updatePaymentDateById(id : Int, date : LocalDate?) =
        db.paymentDAO().updatePaymentDate(id, date)

    suspend fun editPaymentDateByPaymentId(paymentId : Int, date : LocalDate, expenseId: Int) = withContext(Dispatchers.IO){
        db.withTransaction {
            updatePaymentDateById(paymentId, date)

            generateNextOrAllPayments(paymentId, expenseId)
        }
    }

    private suspend fun deletePayment(payment: Payment) = db.paymentDAO().deletePayment(payment)

    private suspend fun deletePaymentsByIds(ids : List<Int>) =
        withContext(Dispatchers.IO){
            db.paymentDAO().deletePaymentsByIds(ids)
        }

    /* RecurringPayment */
    val recurringPayments = db.recurringPaymentDAO().getAllRecurringPayments()

    fun getRecurringPaymentById(payment: Int): Flow<RecurringPayment?> =
        db.recurringPaymentDAO().getRecurringPayment(payment)

    suspend fun upsertRecurringPayment(recurringPayment: RecurringPayment) =
        db.recurringPaymentDAO().upsertRecurringPayment(recurringPayment)

    suspend fun deleteRecurringPayment(recurringPayment: RecurringPayment) =
        db.recurringPaymentDAO().deleteRecurringPayment(recurringPayment)

    suspend fun generateNextOrAllPayments(paymentId : Int, expenseId : Int)
        = withContext(Dispatchers.IO){
            db.withTransaction {

                val expense = getRecurringExpenseById(expenseId)
                val payment = getPaymentById(paymentId)

                expense?.let {
                    if(expense.endDate != null ){
                        val newDates = calculateNewDates(payment?.issueDate?: LocalDate.now(), expense.endDate, expense.frequency)
                        newDates.forEach { date ->
                            if (!checkExistingNextPayment(date, expense.id)) {
                                val newPaymentId = upsertPayment(
                                    Payment(
                                        id = 0,
                                        issueDate = date,
                                        paymentDate = null
                                    )
                                ).toInt()
                                upsertRecurringPayment(
                                    RecurringPayment(
                                        payment = newPaymentId,
                                        recurringExpense = expenseId
                                    )
                                )
                            }
                        }
                    }else {
                        payment?.let {
                            var lastIssueDate = payment.issueDate
                            val today = LocalDate.now()

                            while (!lastIssueDate.isAfter(today)) {
                                val newDate = calculateNexDate(lastIssueDate, expense.frequency)

                                if (!checkExistingNextPayment(newDate, expenseId)) {
                                    val newPaymentId = upsertPayment(
                                        Payment(
                                            id = 0,
                                            issueDate = newDate,
                                            paymentDate = null
                                        )
                                    ).toInt()
                                    upsertRecurringPayment(
                                        RecurringPayment(
                                            payment = newPaymentId,
                                            recurringExpense = expenseId
                                        )
                                    )
                                }
                                lastIssueDate = newDate
                            }
                        }
                    }
                }
            }
        }

    /* Revenue */
    val revenues = db.revenuesDAO().getFlowAllRevenues()

    fun getFlowRevenueById(id: Int): Flow<Revenue?> = db.revenuesDAO().getFlowRevenue(id)

    suspend fun getRevenueByJobId(jobId : Int) : List<Revenue> = withContext(Dispatchers.IO){
        db.revenuesDAO().getRevenueByJob(jobId)
    }

    suspend fun upsertRevenue(revenue: Revenue) = db.revenuesDAO().upsertRevenue(revenue)

    suspend fun deleteRevenue(revenue: Revenue) = db.revenuesDAO().deleteRevenue(revenue)

    fun getFlowRevenueFullDetailsById(id: Int): Flow<RevenueFullDetails?> =
        db.revenuesDAO().getFlowRevenueFullDetails(id)

    suspend fun getRevenueFullDetailsById(id: Int): RevenueFullDetails? =
        db.revenuesDAO().getRevenueFullDetails(id)

    fun getFlowAllRevenuesFullDetails(): Flow<List<RevenueFullDetails>> =
        db.revenuesDAO().getFlowAllRevenuesFullDetails()

    suspend fun getAllRevenuesFullDetails(): List<RevenueFullDetails> =
        withContext(Dispatchers.IO) {
            db.revenuesDAO().getAllRevenuesFullDetails()
        }

    private fun calculateNewDates(issueDate : LocalDate, endDate: LocalDate, frequency : FrequencyType) :
            List<LocalDate>{
        val dates = mutableListOf<LocalDate>()
        var nextDate = calculateNexDate(issueDate, frequency)

        while (!nextDate.isAfter(endDate)) {
            dates.add(nextDate)
            nextDate = calculateNexDate(nextDate, frequency)
        }
        return dates
    }

    private fun calculateNexDate(currentDate : LocalDate, frequency : FrequencyType) : LocalDate{
        return when (frequency) {
            FrequencyType.Settimana -> currentDate.plusWeeks(1)
            FrequencyType.Mese -> currentDate.plusMonths(1)
            FrequencyType.Anno -> currentDate.plusYears(1)
            else -> currentDate
        }
    }

    private suspend fun clearExistingPurchaseData(purchaseInvoiceId : Int){
        val materialsQuantity = db.purchaseDAO().getMaterialsPurchaseByPurchaseInvoice(purchaseInvoiceId)

        materialsQuantity.forEach { (materialId, quantity) ->

            val material = db.materialDAO().getMaterial(materialId)

            material?.let { mat ->
                val newQuantity = BigDecimal(mat.availableQuantity.toDouble())
                    .subtract(BigDecimal(quantity.toDouble()))
                    .coerceAtLeast(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP)
                    .toFloat()
                db.materialDAO().upsertMaterial(
                    mat.copy(id = mat.id, availableQuantity = newQuantity)
                )
            }
        }

        db.bubbleDAO().removePurchaseInvoiceReferenceFromBubbles(purchaseInvoiceId)

        db.purchaseDAO().deletePurchasesByPurchaseInvoice(purchaseInvoiceId)
    }
}