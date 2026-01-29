package com.example.myapplication.data.repository

import com.example.myapplication.data.database.Address
import com.example.myapplication.data.database.AirConditioner
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
import com.example.myapplication.data.database.JobPhoto
import com.example.myapplication.data.database.JobRevenue
import com.example.myapplication.data.database.Material
import com.example.myapplication.data.database.MaterialFullDetails
import com.example.myapplication.data.database.MaterialPhoto
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
import com.example.myapplication.data.database.WorkSiteRevenue
import com.example.myapplication.data.database.dao.AddressDAO
import com.example.myapplication.data.database.dao.AirConditionerDAO
import com.example.myapplication.data.database.dao.BubbleDAO
import com.example.myapplication.data.database.dao.CategoryPurchaseInvoiceDAO
import com.example.myapplication.data.database.dao.CompanyDAO
import com.example.myapplication.data.database.dao.CustomerDAO
import com.example.myapplication.data.database.dao.CustomerProvisionDAO
import com.example.myapplication.data.database.dao.DeliveryDAO
import com.example.myapplication.data.database.dao.ImageDAO
import com.example.myapplication.data.database.dao.JobDAO
import com.example.myapplication.data.database.dao.FutureJobMaterialDAO
import com.example.myapplication.data.database.dao.JobPhotoDAO
import com.example.myapplication.data.database.dao.JobRevenueDAO
import com.example.myapplication.data.database.dao.MaterialDAO
import com.example.myapplication.data.database.dao.MaterialPhotoDAO
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
import com.example.myapplication.data.database.dao.WorkSiteRevenueDAO
import kotlinx.coroutines.flow.Flow

class Repository (
    private val daoAirConditioner : AirConditionerDAO,
    private val daoMaterial : MaterialDAO,
    private val daoSeller : SellerDAO,
    private val daoPurchaseInvoice : PurchaseInvoiceDAO,
    private val daoPurchase: PurchaseDAO,
    private val daoBubble : BubbleDAO,
    private val daoDelivery : DeliveryDAO,
    private val daoCategoryPurchaseInvoice : CategoryPurchaseInvoiceDAO,
    private val daoSingleExpense : SingleExpenseDAO,
    private val daoRecurringExpense : RecurringExpenseDAO,
    private val daoPayment : PaymentDAO,
    private val daoRecurringPayment : RecurringPaymentDAO,
    private val daoImage : ImageDAO,
    private val daoMaterialPhoto : MaterialPhotoDAO,
    private val daoJobPhoto : JobPhotoDAO,
    private val daoCustomerProvision : CustomerProvisionDAO,
    private val daoCustomer : CustomerDAO,
    private val daoPrivate : PrivateDAO,
    private val daoCompany : CompanyDAO,
    private val daoReference: ReferenceDAO,
    private val daoReferral : ReferralDAO,
    private val daoPhoneNumber : PhoneNumberDAO,
    private val daoPropertyOwnership : PropertyOwnershipDAO,
    private val daoAddress: AddressDAO,
    private val daoWorkSite : WorkSiteDAO,
    private val daoJob : JobDAO,
    private val daoJobMaterial : FutureJobMaterialDAO,
    private val daoMaterialUsage : MaterialUsageDAO,
    private val daoRevenue : RevenueDAO,
    private val daoWorkSiteRevenue : WorkSiteRevenueDAO,
    private val daoJobRevenue : JobRevenueDAO
){

    /* AirConditioner */
    val airConditioners = daoAirConditioner.getAllAirConditioners()

    fun getAirConditionerById(serialNumber : String, material : Int) : Flow<AirConditioner?> = daoAirConditioner.getAirConditioner(serialNumber, material)

    suspend fun upsertBubble(airConditioner : AirConditioner) = daoAirConditioner.upsertAirConditioner(airConditioner)

    suspend fun deleteBubble(airConditioner : AirConditioner) = daoAirConditioner.deleteAirConditioner(airConditioner)

    /* Material */
    val materials = daoMaterial.getAllMaterials()

    fun getMaterialById(id : Int) : Flow<Material?> = daoMaterial.getMaterial(id)

    suspend fun upsertMaterial(material : Material) = daoMaterial.upsertMaterial(material)

    suspend fun deleteMaterial(material: Material) = daoMaterial.deleteMaterial(material)

    suspend fun getAllMaterialsWithAirConditional() : List<MaterialWithAirConditional> = daoMaterial.getAllMaterialsWithAirConditionalDetails()

    fun getMaterialFullDetailsById(id : Int) : Flow<MaterialFullDetails> = daoMaterial.getMaterialFullDetails(id)

    suspend fun getAllMaterialsFullDetails() : List<MaterialFullDetails> = daoMaterial.getAllMaterialsFullDetails()

    /* Seller */
    val sellers = daoSeller.getAllSeller()

    fun getSellerById(id : Int) : Flow<Seller?> = daoSeller.getSeller(id)

    suspend fun upsertSeller(seller: Seller) : Long = daoSeller.upsertSeller(seller)

    suspend fun deleteSeller(seller: Seller) = daoSeller.deleteSeller(seller)

    /* Purchase Invoice */
    val purchaseInvoices = daoPurchaseInvoice.getAllPurchaseInvoice()

    fun getPurchaseInvoiceById(id : Int) : Flow<PurchaseInvoice?> = daoPurchaseInvoice.getPurchaseInvoice(id)

    suspend fun upsertPurchaseInvoice(purchaseInvoice : PurchaseInvoice) = daoPurchaseInvoice.upsertPurchaseInvoice(purchaseInvoice)

    suspend fun deletePurchaseInvoice(purchaseInvoice: PurchaseInvoice) = daoPurchaseInvoice.deletePurchaseInvoice(purchaseInvoice)

    fun getPurchaseInvoiceFullDetailsById(id : Int) : Flow<PurchaseInvoiceFullDetails> = daoPurchaseInvoice.getPurchaseInvoiceFullDetails(id)

    /* Purchase */
    val purchases = daoPurchase.getAllPurchases()

    fun getPurchaseById(purchaseInvoice : Int, material : Int) : Flow<Purchase?> = daoPurchase.getPurchase(purchaseInvoice, material)

    suspend fun upsertPurchase(purchase : Purchase) = daoPurchase.upsertPurchase(purchase)

    suspend fun deletePurchase(purchase : Purchase) = daoPurchase.deletePurchase(purchase)

    /* Bubbles */
    val bubbles = daoBubble.getAllBubbles()

    fun getBubbleById(id: Int) : Flow<Bubble?> = daoBubble.getBubble(id)

    suspend fun upsertBubble(bubble : Bubble) : Long = daoBubble.upsertBubble(bubble)

    suspend fun deleteBubble(bubble : Bubble) = daoBubble.deleteBubble(bubble)

    suspend fun getBubbleFullDetails(bubble : Int) : Flow<BubbleFullDetails> = daoBubble.getBubbleFullDetails(bubble)

    /* Delivery */
    val deliveries = daoDelivery.getAllDeliveries()

    fun getDeliveryById(bubble : Int, material : Int) : Flow<Delivery?> = daoDelivery.getDelivery(bubble, material)

    suspend fun upsertDelivery(delivery: Delivery) = daoDelivery.upsertDelivery(delivery)

    suspend fun deleteDelivery(delivery: Delivery) = daoDelivery.deleteDelivery(delivery)

    /* Category */
    val categoryPurchaseInvoice = daoCategoryPurchaseInvoice.getAllCategoriesPurchaseInvoice()

    fun getCategoryPurchaseInvoiceById(id : Int) : Flow<CategoryPurchaseInvoice?> = daoCategoryPurchaseInvoice.getCategoryPurchaseInvoice(id)

    suspend fun upsertCategoryPurchaseInvoice(category : CategoryPurchaseInvoice) = daoCategoryPurchaseInvoice.upsertCategoryPurchaseInvoice(category)

    suspend fun deleteCategoryPurchaseInvoice(category : CategoryPurchaseInvoice) = daoCategoryPurchaseInvoice.deleteCategoryPurchaseInvoice(category)

    /* SingleExpense */
    val singleExpenses = daoSingleExpense.getAllSingleExpenses()

    fun getSingleExpenseById(id : Int) : Flow<SingleExpense?> = daoSingleExpense.getSingleExpense(id)

    suspend fun upsertSingleExpense(singleExpense: SingleExpense) = daoSingleExpense.upsertSingleExpense(singleExpense)

    suspend fun deleteSingleExpense(singleExpense: SingleExpense) = daoSingleExpense.deleteSingleExpense(singleExpense)

    /* RecurringExpense */
    val recurringExpenses = daoRecurringExpense.getAllRecurringExpenses()

    fun getRecurringExpenseById(id : Int) : Flow<RecurringExpense?> = daoRecurringExpense.getRecurringExpense(id)

    suspend fun upsertRecurringExpense(recurringExpense : RecurringExpense) = daoRecurringExpense.upsertRecurringExpense(recurringExpense)

    suspend fun deleteRecurringExpense(recurringExpense : RecurringExpense) = daoRecurringExpense.deleteRecurringExpense(recurringExpense)

    /* Payment */
    val  payments = daoPayment.getAllPayments()

    fun getPaymentById(id : Int) : Flow<Payment?> = daoPayment.getPayment(id)

    suspend fun upsertPayment(payment : Payment) = daoPayment.upsertPayment(payment)

    suspend fun deletePayment(payment : Payment) = daoPayment.deletePayment(payment)

    /* RecurringPayment */
    val recurringPayments = daoRecurringPayment.getAllRecurringPayments()

    fun getRecurringPaymentById(payment : Int) : Flow<RecurringPayment?> = daoRecurringPayment.getRecurringPayment(payment)

    suspend fun upsertRecurringPayment(recurringPayment: RecurringPayment) = daoRecurringPayment.upsertRecurringPayment(recurringPayment)

    suspend fun deleteRecurringPayment(recurringPayment: RecurringPayment) = daoRecurringPayment.deleteRecurringPayment(recurringPayment)

    /* Image */
    val images = daoImage.getAllImages()

    fun getImageById(id : Int) : Flow<Image?> = daoImage.getImage(id)

    suspend fun upsertImage(image : Image) = daoImage.upsertImage(image)

    suspend fun deleteImage(image : Image) = daoImage.deleteImage(image)

    /* MaterialPhoto */
    val materialPhotos = daoMaterialPhoto.getAllMaterialPhotos()

    fun getMaterialPhotoById(photo : Int) : Flow<MaterialPhoto?> = daoMaterialPhoto.getMaterialPhoto(photo)

    suspend fun upsertMaterialPhoto(materialPhoto: MaterialPhoto) = daoMaterialPhoto.upsertMaterialPhoto(materialPhoto)

    suspend fun deleteMaterialPhoto(materialPhoto: MaterialPhoto) = daoMaterialPhoto.deleteMaterialPhoto(materialPhoto)

    /* JobPhoto */
    val jobPhotos = daoJobPhoto.getAllJobPhotos()

    fun getJobPhotoById(photo : Int) : Flow<JobPhoto?> = daoJobPhoto.getJobPhoto(photo)

    suspend fun upsertJobPhoto(jobPhoto: JobPhoto) = daoJobPhoto.upsertJobPhoto(jobPhoto)

    suspend fun deleteJobPhoto(jobPhoto: JobPhoto) = daoJobPhoto.deleteJobPhoto(jobPhoto)

    /* CustomerProvision */
    val customerProvisions = daoCustomerProvision.getAllCustomerProvisions()

    fun getCustomerProvisionById(material : Int, customer : String) : Flow<CustomerProvision?> = daoCustomerProvision.getCustomerProvision(material, customer)

    suspend fun upsertCustomerProvision(customerProvision: CustomerProvision) = daoCustomerProvision.upsertCustomerProvision(customerProvision)

    suspend fun deleteCustomerProvision(customerProvision: CustomerProvision) = daoCustomerProvision.deleteCustomerProvision(customerProvision)

    /* Customer */
    val customers = daoCustomer.getAllCustomers()

    fun getCustomerById(cf : String) : Flow<Customer?> = daoCustomer.getCustomer(cf)

    suspend fun upsertCustomer(customer: Customer) = daoCustomer.upsertCustomer(customer)

    fun getCustomerFullDetailsById(cf : String) : Flow<CustomerFullDetails?> = daoCustomer.getCustomerFullDetails(cf)

    suspend fun getAllCustomersFullDetails() : List<CustomerFullDetails> =  daoCustomer.getAllCustomersFullDetails()

    /* Private */
    fun getPrivateById(cf : String) : Flow<Private?> = daoPrivate.getPrivate(cf)

    suspend fun upsertPrivate(privateCustomer: Private) = daoPrivate.upsertPrivate(privateCustomer)

    /* Company */
    fun getCompanyById(uniqueCode : String) : Flow<Company?> = daoCompany.getCompany(uniqueCode)

    suspend fun upsertCompany(company: Company) = daoCompany.upsertCompany(company)

    /* Reference */
    val reference = daoReference.getAllReferences()

    fun getReferenceById(id : Int) : Flow<Reference?> = daoReference.getReference(id)

    suspend fun upsertReference(reference: Reference) : Long = daoReference.upsertReference(reference)

    suspend fun deleteReference(reference: Reference) = daoReference.deleteReference(reference)

    /* Referral */
    val refferals = daoReferral.getAllReferrals()

    fun getReferralById(presented : String) : Flow<Referral?> = daoReferral.getReferral(presented)

    suspend fun upsertReferral(referral: Referral) = daoReferral.upsertReferral(referral)

    suspend fun deleteReferral(referral: Referral) = daoReferral.deleteReferral(referral)

    /* PhoneNumber */
    fun getPhoneNumberById(number : String) : Flow<PhoneNumber?> = daoPhoneNumber.getPhoneNumber(number)

    suspend fun upsertPhoneNumber(phoneNumber: PhoneNumber) = daoPhoneNumber.upsertPhoneNumber(phoneNumber)

    suspend fun deletePhoneNumber(phoneNumber: PhoneNumber) = daoPhoneNumber.deletePhoneNumber(phoneNumber)

    /* PropertyOwnership */
    fun getPropertyOwnershipById(
        customerId : String,
        addressId : Int
    ) : Flow<PropertyOwnership?> = daoPropertyOwnership.getPropertyOwnership(customerId, addressId)

    suspend fun upsertPropertyOwnership(propertyOwnership: PropertyOwnership) = daoPropertyOwnership.upsertPropertyOwnership(propertyOwnership)

    suspend fun deletePropertyOwnership(propertyOwnership: PropertyOwnership) = daoPropertyOwnership.deletePropertyOwnership(propertyOwnership)

    /* Address */
    val addresses = daoAddress.getAllAddresses()

    fun getAddressById(id: Int) : Flow<Address?> = daoAddress.getAddress(id)

    suspend fun upsertAddress(address : Address) : Long = daoAddress.upsertAddress(address)

    suspend fun deleteAddress(address : Address) = daoAddress.deleteAddress(address)

    /* WorkSite */
    val workSites = daoWorkSite.getAllWorkSites()

    fun getWorkSiteById(id : Int) : Flow<WorkSite?> = daoWorkSite.getWorkSite(id)

    suspend fun upsertWorkSite(workSite: WorkSite) = daoWorkSite.upsertWorkSite(workSite)

    suspend fun deleteWorkSite(workSite: WorkSite) = daoWorkSite.deleteWorkSite(workSite)

    fun getWorkSiteFullDetailsById(id : Int) : Flow<WorkSiteFullDetails> = daoWorkSite.getWorkSiteFullDetails(id)

    /* Job */
    val jobs = daoJob.getAllJobs()

    fun getJobById(id : Int) : Flow<Job?> = daoJob.getJob(id)

    suspend fun upsertJob(job : Job) = daoJob.upsertJob(job)

    suspend fun deleteJob(job : Job) = daoJob.deleteJob(job)

    fun getJobDoneSummaryById(id : Int) : Flow<JobMaterialFullDetails> = daoJob.getJobMaterialFullDetails(id)

    fun getJobAssignmentDetails(id : Int) : Flow<JobAssignmentDetails> = daoJob.getJobAssignmentDetails(id)

    suspend fun getAllJobsAssignmentDetails() :  List<JobAssignmentDetails> = daoJob.getAllJobsAssignmentDetails()

    fun getJobFullDetails(id : Int) : Flow<JobFullDetails> = daoJob.getJobFullDetails(id)

    suspend fun getAllJobsFullDetails() :  List<JobFullDetails> = daoJob.getAllJobsFullDetails()

    /* FutureJobMaterial */
    val futureJobMaterials = daoJobMaterial.getAllFutureJobMaterials()

    fun getFutureJobMaterialById(material : Int, job : Int) : Flow<FutureJobMaterial?> = daoJobMaterial.getFutureJobMaterial(material, job)

    suspend fun upsertFutureJobMaterial(futureJobMaterial: FutureJobMaterial) = daoJobMaterial.upsertFutureJobMaterial(futureJobMaterial)

    suspend fun deleteFutureJobMaterial(futureJobMaterial: FutureJobMaterial) = daoJobMaterial.deleteFutureJobMaterial(futureJobMaterial)

    /* MaterialUsage */
    val materialsUsage = daoMaterialUsage.getAllMaterialsUsage()

    fun getMaterialUsageById(material : Int, job : Int) : Flow<MaterialUsage?> = daoMaterialUsage.getMaterialUsage(material , job)

    suspend fun upsertMaterialUsage(materialUsage: MaterialUsage) = daoMaterialUsage.upsertMaterialUsage(materialUsage)

    suspend fun deleteMaterialUsage(materialUsage: MaterialUsage) = daoMaterialUsage.deleteMaterialUsage(materialUsage)

    /* Revenue */
    val revenues = daoRevenue.getAllRevenues()

    fun getRevenueById(id : Int) : Flow<Revenue?> = daoRevenue.getRevenue(id)

    suspend fun upsertRevenue(revenue: Revenue) = daoRevenue.upsertRevenue(revenue)

    suspend fun deleteRevenue(revenue: Revenue) = daoRevenue.deleteRevenue(revenue)

    fun getRevenueFullDetailsById(id : Int) : Flow<RevenueFullDetails> = daoRevenue.getRevenueFullDetails(id)

    /* WorkSiteRevenue */
    val workSiteRevenue = daoWorkSiteRevenue.getAllWorkSiteRevenues()

    fun getWorkSiteRevenueById(id : Int) : Flow<WorkSiteRevenue?> = daoWorkSiteRevenue.getWorkSiteRevenue(id)

    suspend fun upsertWorkSiteRevenue(workSiteRevenue: WorkSiteRevenue) = daoWorkSiteRevenue.upsertWorkSiteRevenue(workSiteRevenue)

    suspend fun deleteWorkSiteRevenue(workSiteRevenue: WorkSiteRevenue) = daoWorkSiteRevenue.deleteWorkSiteRevenue(workSiteRevenue)

    /* JobRevenue */
    val jobRevenues = daoJobRevenue.getAllJobRevenues()

    fun getJobRevenueById(id : Int) : Flow<JobRevenue?> = daoJobRevenue.getJobRevenue(id)

    suspend fun upsertJobRevenue(jobRevenue: JobRevenue) = daoJobRevenue.upsertJobRevenue(jobRevenue)

    suspend fun deleteJobRevenue(jobRevenue: JobRevenue) = daoJobRevenue.deleteJobRevenue(jobRevenue)

}