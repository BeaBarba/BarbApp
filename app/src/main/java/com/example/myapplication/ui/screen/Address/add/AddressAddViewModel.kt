package com.example.myapplication.ui.screen.Address.add

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.Address
import com.example.myapplication.data.repository.Repository
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale.getDefault
import kotlin.String

data class AddressAddState(
    val addressId: Int? = null,
    val address: String = "",
    val houseNumber: String = "",
    val municipality: String = "",
    val city: String = "",
    val province: String = "",
    val zip: String = "",
    val staircase: String? = null,
    val floor: String? = null,
    val interior: String? = null,
    val units: String? = null,
    val sheet: String? = null,
    val map: String? = null,
    val subordinate: String? = null,
    val yearOfConstruction: Int? = null,
    val usableArea: Int? = null
)

interface AddressAddActions {
    fun setAddress(address: String)
    fun setHouseNumber(houseNumber : String)
    fun setMunicipality(municipality: String)
    fun setCity(city: String)
    fun setProvince(province: String)
    fun setZip(zip: String)
    fun setStaircase(staircase: String)
    fun setFloor(floor: String)
    fun setInterior(interior: String)
    fun setUnits(units: String)
    fun setSheet(sheet: String)
    fun setMap(map: String)
    fun setSubordinate(subordinate: String)
    fun setYearOfConstruction(yearOfConstruction: String)
    fun setUsableArea(usableArea: String)
    fun getLocationAddress(context : Context)
    fun saveAddress()
    fun populateFromEdit(addressId: Int)
}

class AddressAddViewModel(
    private val repository : Repository
) : ViewModel() {
    private val _state = MutableStateFlow(AddressAddState())

    val state = _state.asStateFlow()

    val actions = object : AddressAddActions {

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
            if (zip.isEmpty() || checkIfStringIsInt(zip)) {
                _state.update { it.copy(zip = zip) }
            }
        }

        override fun setStaircase(staircase: String) {
            _state.update { it.copy(staircase = staircase) }
        }

        override fun setFloor(floor: String) {
            if (floor.isEmpty() || checkIfStringIsInt(floor)) {
                _state.update { it.copy(floor = floor) }
            }
        }

        override fun setInterior(interior: String) {
            if (interior.isEmpty() || checkIfStringIsInt(interior)) {
                _state.update { it.copy(interior = interior) }
            }
        }

        override fun setUnits(units: String) {
            _state.update { it.copy(units = units) }
        }

        override fun setSheet(sheet: String) {
            if (sheet.isEmpty() || checkIfStringIsInt(sheet)) {
                _state.update { it.copy(sheet = sheet) }
            }
        }

        override fun setMap(map: String) {
            if (map.isEmpty() || checkIfStringIsInt(map)) {
                _state.update { it.copy(map = map) }
            }
        }

        override fun setSubordinate(subordinate: String) {
            if (subordinate.isEmpty() || checkIfStringIsInt(subordinate)) {
                _state.update { it.copy(subordinate = subordinate) }
            }
        }

        override fun setYearOfConstruction(yearOfConstruction: String) {
            if (yearOfConstruction.isEmpty()) {
                _state.update { it.copy(yearOfConstruction = null) }
                return
            }

            val newValue = yearOfConstruction.toIntOrNull()

            if (newValue != null) {
                _state.update { it.copy(yearOfConstruction = newValue) }
            }
        }

        override fun setUsableArea(usableArea: String) {
            if (usableArea.isEmpty()) {
                _state.update { it.copy(usableArea = null) }
                return
            }

            val newValue = usableArea.toIntOrNull()

            if (newValue != null) {
                _state.update { it.copy(usableArea = newValue) }
            }
        }

        override fun getLocationAddress(context: Context) {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    location?.let {
                        val geocoder = Geocoder(context, getDefault())
                        val geocodeListener = Geocoder.GeocodeListener { geoAddress ->
                            if (geoAddress.isNotEmpty()) {
                                val address = geoAddress[0]
                                _state.update { state ->
                                    state.copy(
                                        address = address.thoroughfare ?: "",
                                        houseNumber = address.subThoroughfare ?: "",
                                        municipality = address.subLocality ?: address.locality ?: "",
                                        city = address.locality ?: "" ,
                                        zip = address.postalCode ?: "",
                                        province = address.subAdminArea ?: ""
                                    )
                                }
                            }
                        }
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            geocoder.getFromLocation(it.latitude, it.longitude, 1, geocodeListener)
                        } else {
                            @Suppress("DEPRECATION")
                            val geoAddresses = geocoder.getFromLocation(it.latitude, it.longitude, 1)
                            if (!geoAddresses.isNullOrEmpty()) {
                                val address = geoAddresses[0]
                                _state.update { state ->
                                    state.copy(
                                        address = address.thoroughfare ?: "",
                                        houseNumber = address.subThoroughfare ?: "",
                                        municipality = address.subLocality ?: address.locality ?: "",
                                        city = address.locality ?: "" ,
                                        zip = address.postalCode ?: "",
                                        province = address.subAdminArea ?: ""
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }


        override fun saveAddress() {
            val stateNow = _state.value
            val newAddress = Address(
                id = stateNow.addressId ?: 0,
                address = stateNow.address,
                houseNumber = stateNow.houseNumber,
                municipality = stateNow.municipality,
                city = stateNow.city,
                province = stateNow.province,
                zip = stateNow.zip,
                sheet = stateNow.sheet,
                map = stateNow.map,
                subordinate = stateNow.subordinate,
                staircase = stateNow.staircase,
                floor = stateNow.floor,
                interior = stateNow.interior,
                yearOfConstruction = stateNow.yearOfConstruction,
                usableArea = stateNow.usableArea,
                units = stateNow.units
            )
            viewModelScope.launch {
                repository.address.upsertAddress(newAddress)
            }
            println("Pressed Save Button, data to save: " + state.value)
        }

        override fun populateFromEdit(addressId: Int) {
            viewModelScope.launch {
                repository.address.getAddressById(addressId).collect{ addressEntity ->
                    if(addressEntity != null) {
                        _state.update {
                            it.copy(
                                addressId = addressEntity.id,
                                address = addressEntity.address,
                                houseNumber = addressEntity.houseNumber,
                                municipality = addressEntity.municipality,
                                city = addressEntity.city,
                                province = addressEntity.province,
                                zip = addressEntity.zip,
                                sheet = addressEntity.sheet,
                                map = addressEntity.map,
                                subordinate = addressEntity.subordinate,
                                staircase = addressEntity.staircase,
                                floor = addressEntity.floor,
                                interior = addressEntity.interior,
                                yearOfConstruction = addressEntity.yearOfConstruction,
                                usableArea = addressEntity.usableArea,
                                units = addressEntity.units
                            )
                        }
                    }
                }
            }
        }
    }

    private fun checkIfStringIsInt(value: String): Boolean {
        return value.all { char -> char.isDigit() }
    }
}