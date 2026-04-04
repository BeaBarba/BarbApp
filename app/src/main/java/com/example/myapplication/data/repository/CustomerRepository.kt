package com.example.myapplication.data.repository

import androidx.room.withTransaction
import com.example.myapplication.data.database.Address
import com.example.myapplication.data.database.AppDatabase
import com.example.myapplication.data.database.Company
import com.example.myapplication.data.database.Customer
import com.example.myapplication.data.database.CustomerFullDetails
import com.example.myapplication.data.database.PhoneNumber
import com.example.myapplication.data.database.Private
import com.example.myapplication.data.database.Reference
import com.example.myapplication.data.database.Referral
import kotlinx.coroutines.flow.Flow

class CustomerRepository (private val db : AppDatabase) {

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
}