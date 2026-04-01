package com.example.myapplication.data.repository

import androidx.room.withTransaction
import com.example.myapplication.data.database.Address
import com.example.myapplication.data.database.AirConditioner
import com.example.myapplication.data.database.AppDatabase
import com.example.myapplication.data.database.Bubble
import com.example.myapplication.data.database.BubbleFullDetails
import com.example.myapplication.data.database.CategoryPurchaseInvoice
import com.example.myapplication.data.database.Company
import com.example.myapplication.data.database.Customer
import com.example.myapplication.data.database.CustomerFullDetails
import com.example.myapplication.data.database.CustomerProvision
import com.example.myapplication.data.database.Delivery
import com.example.myapplication.data.database.Image
import com.example.myapplication.data.database.Job
import com.example.myapplication.data.database.JobAssignmentDetails
import com.example.myapplication.data.database.FutureJobMaterial
import com.example.myapplication.data.database.JobFullDetails
import com.example.myapplication.data.database.JobMaterialFullDetails
import com.example.myapplication.data.database.Material
import com.example.myapplication.data.database.MaterialFullDetails
import com.example.myapplication.data.database.MaterialUsage
import com.example.myapplication.data.database.MaterialWithAirConditional
import com.example.myapplication.data.database.Payment
import com.example.myapplication.data.database.PhoneNumber
import com.example.myapplication.data.database.Private
import com.example.myapplication.data.database.PropertyOwnership
import com.example.myapplication.data.database.Purchase
import com.example.myapplication.data.database.PurchaseInvoice
import com.example.myapplication.data.database.PurchaseInvoiceFullDetails
import com.example.myapplication.data.database.RecurringExpense
import com.example.myapplication.data.database.RecurringPayment
import com.example.myapplication.data.database.Reference
import com.example.myapplication.data.database.Referral
import com.example.myapplication.data.database.Revenue
import com.example.myapplication.data.database.RevenueFullDetails
import com.example.myapplication.data.database.Seller
import com.example.myapplication.data.database.SingleExpense
import com.example.myapplication.data.database.WorkSite
import com.example.myapplication.data.database.WorkSiteFullDetails
import com.example.myapplication.data.database.dao.AddressDAO
import com.example.myapplication.data.database.dao.AirConditionerDAO
import com.example.myapplication.data.database.dao.BubbleDAO
import com.example.myapplication.data.database.dao.CartDAO
import com.example.myapplication.data.database.dao.CategoryPurchaseInvoiceDAO
import com.example.myapplication.data.database.dao.CompanyDAO
import com.example.myapplication.data.database.dao.CustomerDAO
import com.example.myapplication.data.database.dao.CustomerProvisionDAO
import com.example.myapplication.data.database.dao.DeliveryDAO
import com.example.myapplication.data.database.dao.ImageDAO
import com.example.myapplication.data.database.dao.JobDAO
import com.example.myapplication.data.database.dao.FutureJobMaterialDAO
import com.example.myapplication.data.database.dao.MaterialDAO
import com.example.myapplication.data.database.dao.MaterialUsageDAO
import com.example.myapplication.data.database.dao.PaymentDAO
import com.example.myapplication.data.database.dao.PhoneNumberDAO
import com.example.myapplication.data.database.dao.PrivateDAO
import com.example.myapplication.data.database.dao.PropertyOwnershipDAO
import com.example.myapplication.data.database.dao.PurchaseDAO
import com.example.myapplication.data.database.dao.PurchaseInvoiceDAO
import com.example.myapplication.data.database.dao.RecurringPaymentDAO
import com.example.myapplication.data.database.dao.RecurringExpenseDAO
import com.example.myapplication.data.database.dao.ReferenceDAO
import com.example.myapplication.data.database.dao.ReferralDAO
import com.example.myapplication.data.database.dao.RevenueDAO
import com.example.myapplication.data.database.dao.SellerDAO
import com.example.myapplication.data.database.dao.SingleExpenseDAO
import com.example.myapplication.data.database.dao.WorkSiteDAO
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class Repository (private val db : AppDatabase) {

    /* AirConditioner */
    val airConditioners = db.airConditionerDAO().getAllAirConditioners()

    fun getAirConditionerById(serialNumber: String, material: Int): Flow<AirConditioner?> =
        db.airConditionerDAO().getAirConditioner(serialNumber, material)

    suspend fun upsertAirConditioner(airConditioner: AirConditioner) =
        db.airConditionerDAO().upsertAirConditioner(airConditioner)

    suspend fun deleteAirConditioner(airConditioner: AirConditioner) =
        db.airConditionerDAO().deleteAirConditioner(airConditioner)

    /* Material */
    val materials = db.materialDAO().getAllMaterials()

    fun getMaterialById(id: Int): Flow<Material?> = db.materialDAO().getMaterial(id)

    suspend fun upsertMaterial(material: Material) = db.materialDAO().upsertMaterial(material)

    suspend fun deleteMaterial(material: Material) = db.materialDAO().deleteMaterial(material)

    suspend fun getAllMaterialsWithAirConditional(): List<MaterialWithAirConditional> =
        db.materialDAO().getAllMaterialsWithAirConditionalDetails()

    fun getMaterialFullDetailsById(id: Int): Flow<MaterialFullDetails?> =
        db.materialDAO().getMaterialFullDetails(id)

    suspend fun getAllMaterialsFullDetails(): List<MaterialFullDetails> =
        db.materialDAO().getAllMaterialsFullDetails()

    /* Seller */
    val sellers = db.sellerDAO().getAllSeller()

    fun getSellerById(id: Int): Flow<Seller?> = db.sellerDAO().getSeller(id)

    suspend fun upsertSeller(seller: Seller): Long = db.sellerDAO().upsertSeller(seller)

    suspend fun deleteSeller(seller: Seller) = db.sellerDAO().deleteSeller(seller)

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

    /* Purchase */
    val purchases = db.purchaseDAO().getAllPurchases()

    fun getPurchaseById(purchaseInvoice: Int, material: Int): Flow<Purchase?> =
        db.purchaseDAO().getPurchase(purchaseInvoice, material)

    suspend fun upsertPurchase(purchase: Purchase) = db.purchaseDAO().upsertPurchase(purchase)

    suspend fun deletePurchase(purchase: Purchase) = db.purchaseDAO().deletePurchase(purchase)

    /* Bubbles */
    val bubbles = db.bubbleDAO().getAllBubbles()

    fun getBubbleById(id: Int): Flow<Bubble?> = db.bubbleDAO().getBubble(id)

    suspend fun upsertBubble(bubble: Bubble): Long = db.bubbleDAO().upsertBubble(bubble)

    suspend fun deleteBubble(bubble: Bubble) = db.bubbleDAO().deleteBubble(bubble)

    fun getBubbleFullDetailsById(bubble: Int): Flow<BubbleFullDetails?> =
        db.bubbleDAO().getBubbleFullDetails(bubble)

    fun getAllBubblesFullDetails(): Flow<List<BubbleFullDetails>> =
        db.bubbleDAO().getAllBubblesFullDetails()

    /* Delivery */
    val deliveries = db.deliveriesDAO().getAllDeliveries()

    fun getDeliveryById(bubble: Int, material: Int): Flow<Delivery?> =
        db.deliveriesDAO().getDelivery(bubble, material)

    suspend fun upsertDelivery(delivery: Delivery) = db.deliveriesDAO().upsertDelivery(delivery)

    suspend fun deleteDelivery(delivery: Delivery) = db.deliveriesDAO().deleteDelivery(delivery)

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

    /* Image */
    val images = db.imageDAO().getAllImages()

    fun getImageById(id: Int): Flow<Image?> = db.imageDAO().getImage(id)

    suspend fun upsertImage(image: Image) = db.imageDAO().upsertImage(image)

    suspend fun deleteImage(image: Image) = db.imageDAO().deleteImage(image)

    /* CustomerProvision */
    val customerProvisions = db.customerProvisionDAO().getAllCustomerProvisions()

    fun getCustomerProvisionById(material: Int, customer: String): Flow<CustomerProvision?> =
        db.customerProvisionDAO().getCustomerProvision(material, customer)

    suspend fun upsertCustomerProvision(customerProvision: CustomerProvision) =
        db.customerProvisionDAO().upsertCustomerProvision(customerProvision)

    suspend fun deleteCustomerProvision(customerProvision: CustomerProvision) =
        db.customerProvisionDAO().deleteCustomerProvision(customerProvision)

    /* Customer */
    val customers = db.customerDAO().getAllCustomers()

    fun getCustomerById(cf: String): Flow<Customer?> = db.customerDAO().getCustomer(cf)

    suspend fun upsertCustomer(customer: Customer) = db.customerDAO().upsertCustomer(customer)

    fun getCustomerFullDetailsById(cf: String): Flow<CustomerFullDetails?> =
        db.customerDAO().getCustomerFullDetails(cf)

    fun getAllCustomersFullDetails(): Flow<List<CustomerFullDetails>> =
        db.customerDAO().getAllCustomersFullDetails()

    suspend fun saveCustomerComplete(
        address: Address,
        reference: Reference?,
        customer: Customer,
        phoneNumber: PhoneNumber?,
        privateCustomer: Private?,
        company: Company?,
        referral: Referral?
    ){
        db.withTransaction {
            val addressUpsertResult = db.addressDAO().upsertAddress(address).toInt()
            val addressId = if(addressUpsertResult == -1) address.id else addressUpsertResult

            val referenceUpsertResult = reference?.let { db.referenceDAO().upsertReference(it).toInt() }
            val referenceId = if(referenceUpsertResult == -1) reference.id else referenceUpsertResult

            val customerComplete = customer.copy(residence = addressId, reference = referenceId)
            db.customerDAO().upsertCustomer(customerComplete)

            phoneNumber?.let{ db.phoneNumberDAO().upsertPhoneNumber(it) }

            privateCustomer?.let { db.privateDAO().upsertPrivate(it) }

            company?.let { db.companyDAO().upsertCompany(it) }

            referral?.let { db.referralDAO().upsertReferral(it) }
        }
    }

    /* Private */
    fun getPrivateById(cf: String): Flow<Private?> = db.privateDAO().getPrivate(cf)

    suspend fun upsertPrivate(privateCustomer: Private) = db.privateDAO().upsertPrivate(privateCustomer)

    /* Company */
    fun getCompanyById(uniqueCode: String): Flow<Company?> = db.companyDAO().getCompany(uniqueCode)

    suspend fun upsertCompany(company: Company) = db.companyDAO().upsertCompany(company)

    /* Reference */
    val reference = db.referenceDAO().getAllReferences()

    fun getReferenceById(id: Int): Flow<Reference?> = db.referenceDAO().getReference(id)

    suspend fun upsertReference(reference: Reference): Long =
        db.referenceDAO().upsertReference(reference)

    suspend fun deleteReference(reference: Reference) = db.referenceDAO().deleteReference(reference)

    /* Referral */
    val referrals = db.referralDAO().getAllReferrals()

    fun getReferralById(presented: String): Flow<Referral?> = db.referralDAO().getReferral(presented)

    suspend fun upsertReferral(referral: Referral) = db.referralDAO().upsertReferral(referral)

    suspend fun deleteReferral(referral: Referral) = db.referralDAO().deleteReferral(referral)

    /* PhoneNumber */
    fun getPhoneNumberById(number: String): Flow<PhoneNumber?> =
        db.phoneNumberDAO().getPhoneNumber(number)

    suspend fun upsertPhoneNumber(phoneNumber: PhoneNumber) =
        db.phoneNumberDAO().upsertPhoneNumber(phoneNumber)

    suspend fun deletePhoneNumber(phoneNumber: PhoneNumber) =
        db.phoneNumberDAO().deletePhoneNumber(phoneNumber)

    /* PropertyOwnership */
    fun getPropertyOwnershipById(
        customerId: String,
        addressId: Int
    ): Flow<PropertyOwnership?> = db.propertyOwnershipDAO().getPropertyOwnership(customerId, addressId)

    suspend fun upsertPropertyOwnership(propertyOwnership: PropertyOwnership) =
        db.propertyOwnershipDAO().upsertPropertyOwnership(propertyOwnership)

    suspend fun deletePropertyOwnership(propertyOwnership: PropertyOwnership) =
        db.propertyOwnershipDAO().deletePropertyOwnership(propertyOwnership)

    /* Address */
    val addresses = db.addressDAO().getAllAddresses()

    fun getAddressById(id: Int): Flow<Address?> = db.addressDAO().getAddress(id)

    suspend fun upsertAddress(address: Address): Long = db.addressDAO().upsertAddress(address)

    suspend fun deleteAddress(address: Address) = db.addressDAO().deleteAddress(address)

    /* WorkSite */
    val workSites = db.workSiteDAO().getAllWorkSites()

    fun getWorkSiteById(id: Int): Flow<WorkSite?> = db.workSiteDAO().getWorkSite(id)

    suspend fun upsertWorkSite(workSite: WorkSite) = db.workSiteDAO().upsertWorkSite(workSite)

    suspend fun deleteWorkSite(workSite: WorkSite) = db.workSiteDAO().deleteWorkSite(workSite)

    fun getWorkSiteFullDetailsById(id: Int): Flow<WorkSiteFullDetails?> =
        db.workSiteDAO().getWorkSiteFullDetails(id)

    suspend fun getAllWorkSiteFullDetails(): List<WorkSiteFullDetails> =
        db.workSiteDAO().getAllWorkSitesFullDetails()

    /* Job */
    val jobs = db.jobDAO().getAllJobs()

    fun getJobById(id: Int): Flow<Job?> = db.jobDAO().getJob(id)

    suspend fun upsertJob(job: Job) = db.jobDAO().upsertJob(job)

    suspend fun deleteJob(job: Job) = db.jobDAO().deleteJob(job)

    fun getJobDoneSummaryById(id: Int): Flow<JobMaterialFullDetails?> =
        db.jobDAO().getJobMaterialFullDetails(id)

    fun getJobAssignmentDetails(id: Int): Flow<JobAssignmentDetails?> =
        db.jobDAO().getJobAssignmentDetails(id)

    fun getAllJobsAssignmentDetails(): Flow<List<JobAssignmentDetails>> =
        db.jobDAO().getAllJobsAssignmentDetails()

    fun getJobFullDetails(id: Int): Flow<JobFullDetails?> = db.jobDAO().getJobFullDetails(id)

    fun getAllJobsFullDetails(): Flow<List<JobFullDetails>> = db.jobDAO().getAllJobsFullDetails()

    fun getAllToScheduleJobsAssignmentDetailsByDate(date : LocalDate) : Flow<List<JobAssignmentDetails>> =
        db.jobDAO().getAllToScheduleJobsAssignmentDetails(date)

    fun getAllTodayJobsFullDetailsByDate(date : LocalDate) : Flow<List<JobFullDetails>> =
        db.jobDAO().getAllTodayJobsFullDetails(date)

    /* FutureJobMaterial */
    val futureJobMaterials = db.jobMaterialDAO().getAllFutureJobMaterials()

    fun getFutureJobMaterialById(material: Int, job: Int): Flow<FutureJobMaterial?> =
        db.jobMaterialDAO().getFutureJobMaterial(material, job)

    suspend fun upsertFutureJobMaterial(futureJobMaterial: FutureJobMaterial) =
        db.jobMaterialDAO().upsertFutureJobMaterial(futureJobMaterial)

    suspend fun deleteFutureJobMaterial(futureJobMaterial: FutureJobMaterial) =
        db.jobMaterialDAO().deleteFutureJobMaterial(futureJobMaterial)

    /* MaterialUsage */
    val materialsUsage = db.materialUsageDAO().getAllMaterialsUsage()

    fun getMaterialUsageById(material: Int, job: Int): Flow<MaterialUsage?> =
        db.materialUsageDAO().getMaterialUsage(material, job)

    suspend fun upsertMaterialUsage(materialUsage: MaterialUsage) =
        db.materialUsageDAO().upsertMaterialUsage(materialUsage)

    suspend fun deleteMaterialUsage(materialUsage: MaterialUsage) =
        db.materialUsageDAO().deleteMaterialUsage(materialUsage)

    /* Revenue */
    val revenues = db.revenuesDAO().getAllRevenues()

    fun getRevenueById(id: Int): Flow<Revenue?> = db.revenuesDAO().getRevenue(id)

    suspend fun upsertRevenue(revenue: Revenue) = db.revenuesDAO().upsertRevenue(revenue)

    suspend fun deleteRevenue(revenue: Revenue) = db.revenuesDAO().deleteRevenue(revenue)

    fun getRevenueFullDetailsById(id: Int): Flow<RevenueFullDetails?> =
        db.revenuesDAO().getRevenueFullDetails(id)

    suspend fun getAllRevenuesFullDetails(): List<RevenueFullDetails> =
        db.revenuesDAO().getAllRevenuesFullDetails()

    /* Cart */
    val cartItems = db.cartDAO().getCartItems()
}