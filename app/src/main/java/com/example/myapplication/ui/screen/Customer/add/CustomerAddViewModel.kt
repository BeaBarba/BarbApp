package com.example.myapplication.ui.screen.Customer.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.Address
import com.example.myapplication.data.database.Company
import com.example.myapplication.data.database.Customer
import com.example.myapplication.data.database.PhoneNumber
import com.example.myapplication.data.database.Private
import com.example.myapplication.data.database.Reference
import com.example.myapplication.data.database.Referral
import com.example.myapplication.data.modules.CustomerType
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

data class CustomerAddState (
    val id : String = "",
    val name : String = "",
    val email : String = "",
    val averageCollectionTime : Float = 0.0F,
    val collectionCount : Int = 0,
    val referralId : String? = null,
    val addressId : Int = 0,

    val customerType: CustomerType = CustomerType.Privato,

    val phoneNumber : String = "",
    val notePhoneNumber : String = "",

    val address : String = "",
    val houseNumber : String = "",
    val municipality : String = "",
    val city : String = "",
    val province : String = "",
    val zip : String = "",

    val referenceId : Int = 0,
    val referenceName : String = "",
    val referenceLastName : String = "",
    val referencePhoneNumber : String = "",

    val privateLastName : String = "",
    val privateDateBirth: LocalDate = LocalDate.now(),
    val privatePlaceBirth : String = "",

    val companyName : String = "",
    val companyUniqueCode : String = "",
    val companyVatNumber : String = "",

    val started : Boolean = false
)

interface CustomerAddActions {
    fun populateFromEdit(customerId : String)
    fun setCustomerType(customerType : CustomerType)
    fun save()
    fun setCustomerId(id : String)
    fun setCustomerName(name : String)
    fun setPrivateLastName(lastName : String)
    fun setPrivatePlaceBirth(placeBirth : String)
    fun setPrivateDateBirth(dateBirth : LocalDate)
    fun setCompanyName(name : String)
    fun setCompanyUniqueCode(uniqueCode : String)
    fun setCompanyVat(vat : String)
    fun setEmail(email: String)
    fun setPhoneNumber(number : String)
    fun setPhoneNote(note : String)
    fun setAddress(address: String)
    fun setHouseNumber(houseNumber: String)
    fun setMunicipality(municipality: String)
    fun setCity(city: String)
    fun setProvince(province: String)
    fun setZip(zip: String)
    fun setReferral(referralId: String)
    fun setReferenceName(referenceName: String)
    fun setReferenceLastName(referenceLastName: String)
    fun setReferencePhoneNumber(referencePhoneNumber: String)
}

class CustomerAddViewModel(
    private val repository : Repository
) : ViewModel() {

    private val _state = MutableStateFlow(CustomerAddState())

    val state = _state.asStateFlow()

    val actions = object : CustomerAddActions {

        override fun populateFromEdit(customerId: String) {
            if (!state.value.started) {
                viewModelScope.launch {
                    repository.getCustomerFullDetailsById(customerId).collect { customerEntity ->
                        if (customerEntity != null) {
                            _state.update {
                                it.copy(
                                    id = customerEntity.customer.cf,
                                    name = customerEntity.customer.name,
                                    email = customerEntity.customer.mail,
                                    phoneNumber = customerEntity.phoneNumber?.number ?: "",
                                    notePhoneNumber = customerEntity.phoneNumber?.text ?: "",
                                    addressId = customerEntity.address.id,
                                    address = customerEntity.address.address,
                                    municipality = customerEntity.address.municipality,
                                    city = customerEntity.address.city,
                                    province = customerEntity.address.province,
                                    zip = customerEntity.address.zip,
                                    referralId = customerEntity.referral?.referral,
                                    referenceId = customerEntity.reference?.id ?: 0,
                                    referenceName = customerEntity.reference?.name ?: "",
                                    referenceLastName = customerEntity.reference?.lastName ?: "",
                                    referencePhoneNumber = customerEntity.reference?.phoneNumber?: "",
                                    privateLastName = customerEntity.privateCustomer?.lastName?: "",
                                    privateDateBirth = customerEntity.privateCustomer?.dateBirth?: LocalDate.now(),
                                    privatePlaceBirth = customerEntity.privateCustomer?.placeBirth?: "",
                                    companyName = customerEntity.companyCustomer?.companyName ?: "",
                                    companyVatNumber = customerEntity.companyCustomer?.vatNumber?: "",
                                    companyUniqueCode = customerEntity.companyCustomer?.uniqueCode?: "",
                                    customerType =
                                    if (customerEntity.companyCustomer == null) {
                                        CustomerType.Privato
                                    } else {
                                        CustomerType.Azienda
                                    },
                                    collectionCount = customerEntity.customer.collectionCount,
                                    averageCollectionTime = customerEntity.customer.avarageCollectionTime
                                )
                            }
                        }
                    }
                }
            }
        }

        override fun setCustomerType(customerType: CustomerType) {
            _state.update { it.copy(customerType = customerType) }
        }

        override fun save() {
            viewModelScope.launch {
                val addressId = repository.upsertAddress(
                    Address(
                        state.value.addressId,
                        state.value.address,
                        state.value.houseNumber,
                        state.value.municipality,
                        state.value.city,
                        state.value.province,
                        state.value.zip
                    )
                ).toInt()
                val referenceId = if (state.value.referenceName != "") {
                    repository.upsertReference(
                        Reference(
                            state.value.referenceId,
                            state.value.referenceName,
                            state.value.referenceLastName,
                            state.value.referencePhoneNumber
                        )
                    ).toInt()
                } else null
                repository.upsertCustomer(
                    Customer(
                        state.value.id,
                        state.value.name,
                        state.value.email,
                        state.value.averageCollectionTime,
                        state.value.collectionCount,
                        addressId,
                        referenceId
                    )
                )
                repository.upsertPhoneNumber(
                    PhoneNumber(
                        state.value.phoneNumber,
                        state.value.notePhoneNumber,
                        state.value.id
                    )
                )
                if (state.value.customerType == CustomerType.Privato) {
                    repository.upsertPrivate(
                        Private(
                            state.value.id,
                            state.value.privateLastName,
                            state.value.privateDateBirth,
                            state.value.privatePlaceBirth
                        )
                    )
                } else {
                    repository.upsertCompany(
                        Company(
                            state.value.companyUniqueCode,
                            state.value.companyName,
                            state.value.companyVatNumber,
                            state.value.id
                        )
                    )
                }
                if (state.value.referralId != null) {
                    repository.upsertReferral(
                        Referral(
                            state.value.id,
                            state.value.referralId!!
                        )
                    )
                }
            }
        }

        override fun setCustomerId(id: String) {
            _state.update { it.copy(id = id) }
        }

        override fun setCustomerName(name: String) {
            _state.update { it.copy(name = name) }
        }

        override fun setPrivateLastName(lastName: String) {
            _state.update { it.copy(privateLastName = lastName) }
        }

        override fun setPrivatePlaceBirth(placeBirth: String) {
            _state.update { it.copy(privatePlaceBirth = placeBirth) }
        }

        override fun setPrivateDateBirth(dateBirth: LocalDate) {
            _state.update { it.copy(privateDateBirth = dateBirth) }
        }

        override fun setCompanyName(name: String) {
            _state.update { it.copy(companyName = name) }
        }

        override fun setCompanyUniqueCode(uniqueCode: String) {
            _state.update { it.copy(companyUniqueCode = uniqueCode) }
        }

        override fun setCompanyVat(vat: String) {
            _state.update { it.copy(companyVatNumber = vat) }
        }

        override fun setEmail(email: String) {
            _state.update { it.copy(email = email) }
        }

        override fun setPhoneNumber(number: String) {
            _state.update { it.copy(phoneNumber = number) }
        }

        override fun setPhoneNote(note: String) {
            _state.update { it.copy(notePhoneNumber = note) }
        }

        override fun setAddress(address: String) {
            _state.update { it.copy(address = address) }
        }

        override fun setHouseNumber(houseNumber: String) {
            _state.update { it.copy(houseNumber = houseNumber) }
        }

        override fun setMunicipality(municipality: String) {
            _state.update { it.copy(municipality = municipality) }
        }

        override fun setCity(city: String) {
            _state.update { it.copy(city = city) }
        }

        override fun setProvince(province: String) {
            _state.update { it.copy(province = province) }
        }

        override fun setZip(zip: String) {
            _state.update { it.copy(zip = zip) }
        }

        override fun setReferral(referralId: String) {
            _state.update { it.copy(referralId = referralId) }
        }

        override fun setReferenceName(referenceName: String) {
            _state.update { it.copy(referenceName = referenceName) }
        }

        override fun setReferenceLastName(referenceLastName: String) {
            _state.update { it.copy(referenceLastName = referenceLastName) }
        }

        override fun setReferencePhoneNumber(referencePhoneNumber: String) {
            _state.update { it.copy(referencePhoneNumber = referencePhoneNumber) }
        }
    }
}