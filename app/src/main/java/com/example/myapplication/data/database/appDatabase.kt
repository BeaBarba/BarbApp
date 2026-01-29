package com.example.myapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
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

@Database(
    entities = [
        AirConditioner::class,
        Material::class,
        Seller::class,
        PurchaseInvoice::class,
        Purchase::class,
        Bubble::class,
        Delivery::class,
        CategoryPurchaseInvoice::class,
        SingleExpense::class,
        RecurringExpense::class,
        Payment::class,
        RecurringPayment::class,
        Image::class,
        MaterialPhoto::class,
        JobPhoto::class,
        CustomerProvision::class,
        Customer::class,
        Private::class,
        Company::class,
        Reference::class,
        Referral::class,
        PhoneNumber::class,
        PropertyOwnership::class,
        Address::class,
        WorkSite::class,
        Job::class,
        FutureJobMaterial::class,
        MaterialUsage::class,
        Revenue::class,
        WorkSiteRevenue::class,
        JobRevenue::class
    ],
    version = 5
)
@TypeConverters(
    DateConverters::class,
    MachineTypeConverters::class,
    SplitNumberConverters::class,
    FrequencyTypeConverters::class,
    JobTypeConverters::class,
    TimeConverters::class
)
abstract class appDatabase : RoomDatabase(){
    abstract fun airConditionerDAO() : AirConditionerDAO
    abstract fun materialDAO() : MaterialDAO
    abstract fun sellerDAO() : SellerDAO
    abstract fun purchaseInvoiceDAO() : PurchaseInvoiceDAO
    abstract fun purchaseDAO() : PurchaseDAO
    abstract fun bubbleDAO() : BubbleDAO
    abstract fun deliveriesDAO() : DeliveryDAO
    abstract fun categoryPurchaseInvoiceDAO() : CategoryPurchaseInvoiceDAO
    abstract fun singleExpenseDAO() : SingleExpenseDAO
    abstract fun recurringExpenseDAO() : RecurringExpenseDAO
    abstract fun paymentDAO() : PaymentDAO
    abstract fun recurringPaymentDAO() : RecurringPaymentDAO
    abstract fun imageDAO() : ImageDAO
    abstract fun materialPhotoDAO() : MaterialPhotoDAO
    abstract fun jobPhotoDAO() : JobPhotoDAO
    abstract fun customerProvisionDAO() : CustomerProvisionDAO
    abstract fun customerDAO() : CustomerDAO
    abstract fun privateDAO() : PrivateDAO
    abstract fun companyDAO() : CompanyDAO
    abstract fun referenceDAO() : ReferenceDAO
    abstract fun referralDAO() : ReferralDAO
    abstract fun phoneNumberDAO() : PhoneNumberDAO
    abstract fun propertyOwnershipDAO() : PropertyOwnershipDAO
    abstract fun addressDAO() : AddressDAO
    abstract fun workSiteDAO() : WorkSiteDAO
    abstract fun jobDAO() : JobDAO
    abstract fun jobMaterialDAO() : FutureJobMaterialDAO
    abstract fun materialUsageDAO() : MaterialUsageDAO
    abstract fun revenuesDAO() : RevenueDAO
    abstract fun workSiteRevenueDAO() : WorkSiteRevenueDAO
    abstract fun jobRevenueDAO() : JobRevenueDAO
}