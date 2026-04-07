package com.example.myapplication.data.repository

import com.example.myapplication.data.database.AppDatabase
import com.example.myapplication.data.database.Bubble
import com.example.myapplication.data.database.BubbleFullDetails
import com.example.myapplication.data.database.CategoryPurchaseInvoice
import com.example.myapplication.data.database.Payment
import com.example.myapplication.data.database.PurchaseInvoice
import com.example.myapplication.data.database.PurchaseInvoiceFullDetails
import com.example.myapplication.data.database.RecurringExpense
import com.example.myapplication.data.database.RecurringPayment
import com.example.myapplication.data.database.Revenue
import com.example.myapplication.data.database.RevenueFullDetails
import com.example.myapplication.data.database.SingleExpense
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class AccountingRepository (private val db : AppDatabase) {

    /* Purchase Invoice */
    val purchaseInvoices = db.purchaseInvoiceDAO().getAllPurchaseInvoice()

    fun getPurchaseInvoiceById(id: Int): Flow<PurchaseInvoice?> =
        db.purchaseInvoiceDAO().getPurchaseInvoice(id)

    suspend fun upsertPurchaseInvoice(purchaseInvoice: PurchaseInvoice) =
        db.purchaseInvoiceDAO().upsertPurchaseInvoice(purchaseInvoice)

    suspend fun deletePurchaseInvoice(purchaseInvoice: PurchaseInvoice) =
        db.purchaseInvoiceDAO().deletePurchaseInvoice(purchaseInvoice)

    fun getPurchaseInvoiceFullDetailsById(id: Int): Flow<PurchaseInvoiceFullDetails?> =
        db.purchaseInvoiceDAO().getPurchaseInvoiceFullDetails(id)

    fun getAllPurchaseInvoicesFullDetails(): Flow<List<PurchaseInvoiceFullDetails>> =
        db.purchaseInvoiceDAO().getAllPurchaseInvoicesFullDetails()


    /* Bubbles */
    val bubbles = db.bubbleDAO().getAllBubbles()

    fun getBubbleById(id: Int): Flow<Bubble?> = db.bubbleDAO().getBubble(id)

    suspend fun upsertBubble(bubble: Bubble): Long = db.bubbleDAO().upsertBubble(bubble)

    suspend fun deleteBubble(bubble: Bubble) = db.bubbleDAO().deleteBubble(bubble)

    fun getBubbleFullDetailsById(bubble: Int): Flow<BubbleFullDetails?> =
        db.bubbleDAO().getBubbleFullDetails(bubble)

    fun getAllBubblesFullDetails(): Flow<List<BubbleFullDetails>> =
        db.bubbleDAO().getAllBubblesFullDetails()

    /* Category */
    val categoryPurchaseInvoice = db.categoryPurchaseInvoiceDAO().getAllCategoriesPurchaseInvoice()

    fun getCategoryPurchaseInvoiceById(id: Int): Flow<CategoryPurchaseInvoice?> =
        db.categoryPurchaseInvoiceDAO().getCategoryPurchaseInvoice(id)

    suspend fun upsertCategoryPurchaseInvoice(category: CategoryPurchaseInvoice) =
        db.categoryPurchaseInvoiceDAO().upsertCategoryPurchaseInvoice(category)

    suspend fun deleteCategoryPurchaseInvoice(category: CategoryPurchaseInvoice) =
        db.categoryPurchaseInvoiceDAO().deleteCategoryPurchaseInvoice(category)

    /* SingleExpense */
    val singleExpenses = db.singleExpenseDAO().getAllSingleExpenses()

    fun getSingleExpenseById(id: Int): Flow<SingleExpense?> = db.singleExpenseDAO().getSingleExpense(id)

    suspend fun upsertSingleExpense(singleExpense: SingleExpense) =
        db.singleExpenseDAO().upsertSingleExpense(singleExpense)

    suspend fun deleteSingleExpense(singleExpense: SingleExpense) =
        db.singleExpenseDAO().deleteSingleExpense(singleExpense)

    /* RecurringExpense */
    val recurringExpenses = db.recurringExpenseDAO().getAllRecurringExpenses()

    fun getRecurringExpenseById(id: Int): Flow<RecurringExpense?> =
        db.recurringExpenseDAO().getRecurringExpense(id)

    suspend fun upsertRecurringExpense(recurringExpense: RecurringExpense) =
        db.recurringExpenseDAO().upsertRecurringExpense(recurringExpense)

    suspend fun deleteRecurringExpense(recurringExpense: RecurringExpense) =
        db.recurringExpenseDAO().deleteRecurringExpense(recurringExpense)

    /* Payment */
    val payments = db.paymentDAO().getAllPayments()

    fun getPaymentById(id: Int): Flow<Payment?> = db.paymentDAO().getPayment(id)

    suspend fun upsertPayment(payment: Payment) = db.paymentDAO().upsertPayment(payment)

    suspend fun deletePayment(payment: Payment) = db.paymentDAO().deletePayment(payment)

    /* RecurringPayment */
    val recurringPayments = db.recurringPaymentDAO().getAllRecurringPayments()

    fun getRecurringPaymentById(payment: Int): Flow<RecurringPayment?> =
        db.recurringPaymentDAO().getRecurringPayment(payment)

    suspend fun upsertRecurringPayment(recurringPayment: RecurringPayment) =
        db.recurringPaymentDAO().upsertRecurringPayment(recurringPayment)

    suspend fun deleteRecurringPayment(recurringPayment: RecurringPayment) =
        db.recurringPaymentDAO().deleteRecurringPayment(recurringPayment)

    /* Revenue */
    val revenues = db.revenuesDAO().getAllRevenues()

    fun getRevenueById(id: Int): Flow<Revenue?> = db.revenuesDAO().getRevenue(id)

    suspend fun getRevenueByJobId(jobId : Int) : List<Revenue> = withContext(Dispatchers.IO){
        db.revenuesDAO().getRevenueByJob(jobId)
    }

    suspend fun upsertRevenue(revenue: Revenue) = db.revenuesDAO().upsertRevenue(revenue)

    suspend fun deleteRevenue(revenue: Revenue) = db.revenuesDAO().deleteRevenue(revenue)

    fun getRevenueFullDetailsById(id: Int): Flow<RevenueFullDetails?> =
        db.revenuesDAO().getRevenueFullDetails(id)

    suspend fun getAllRevenuesFullDetails(): List<RevenueFullDetails> =
        db.revenuesDAO().getAllRevenuesFullDetails()
}