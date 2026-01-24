package com.example.myapplication.ui.screen.Address.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.Address
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.String

data class AddressAddState(
    val addressId: Int? = null,
    val address: String = "",
    val houseNumber: String = "",
    val municipality: String = "",
    val city: String = "",
    val province: String = "",
    val zip: String = "",
    val staircase: String = "",
    val floor: String = "",
    val interior: String = "",
    val units: String = "",
    val sheet: String = "",
    val map: String = "",
    val subordinate: String = "",
    val yearOfConstruction: String = "",
    val usableArea: String = "",
    val started: Boolean = false
)

interface AddressAddActions {
    fun setAddress(address: String)
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
            if (checkIfStringIsInt(zip)) {
                _state.update { it.copy(zip = zip) }
            }
        }

        override fun setStaircase(staircase: String) {
            if (checkIfStringIsInt(staircase)) {
                _state.update { it.copy(staircase = staircase) }
            }
        }

        override fun setFloor(floor: String) {
            if (checkIfStringIsInt(floor)) {
                _state.update { it.copy(floor = floor) }
            }
        }

        override fun setInterior(interior: String) {
            if (checkIfStringIsInt(interior)) {
                _state.update { it.copy(interior = interior) }
            }
        }

        override fun setUnits(units: String) {
            if (checkIfStringIsInt(units)) {
                _state.update { it.copy(units = units) }
            }
        }

        override fun setSheet(sheet: String) {
            if (checkIfStringIsInt(sheet)) {
                _state.update { it.copy(sheet = sheet) }
            }
        }

        override fun setMap(map: String) {
            if (checkIfStringIsInt(map)) {
                _state.update { it.copy(map = map) }
            }
        }

        override fun setSubordinate(subordinate: String) {
            if (checkIfStringIsInt(subordinate)) {
                _state.update { it.copy(subordinate = subordinate) }
            }
        }

        override fun setYearOfConstruction(yearOfConstruction: String) {
            if (checkIfStringIsInt(yearOfConstruction)) {
                _state.update { it.copy(yearOfConstruction = yearOfConstruction) }
            }
        }

        override fun setUsableArea(usableArea: String) {
            if (checkIfStringIsDouble(usableArea)) {
                _state.update { it.copy(usableArea = usableArea) }
            }
        }

        override fun saveAddress() {
            val stateNow = _state.value
            val newAddress = Address(
                id = 0,
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
                repository.upsert(newAddress)
            }
            println("Pressed Save Button, data to save: " + state.value)
        }

        override fun populateFromEdit(addressId: Int) {
            viewModelScope.launch {
                repository.getAddressById(addressId).collect{ addressEntity ->
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
            /*
            if (!state.value.started) {
                simulateData()
                _state.update { it.copy(started = true) }
            }*/
        }
    }

    private fun checkIfStringIsInt(value: String): Boolean {
        return value.all { char -> char.isDigit() }
    }

    private fun checkIfStringIsDouble(value: String): Boolean {
        return value.toDoubleOrNull() != null || value == ""
    }

    private fun simulateData(): Unit {
        _state.update { it.copy(address = "Via Pasquale Orlandi 21") }
        _state.update { it.copy(municipality = "Medicina") }
        _state.update { it.copy(city = "Bologna") }
        _state.update { it.copy(province = "Bologna") }
        _state.update { it.copy(zip = "40059") }
        _state.update { it.copy(floor = "1") }
        _state.update { it.copy(staircase = "1") }
        _state.update { it.copy(interior = "1") }
        _state.update { it.copy(units = "4") }
        _state.update { it.copy(sheet = "10") }
        _state.update { it.copy(map = "200") }
        _state.update { it.copy(subordinate = "45") }
        _state.update { it.copy(usableArea = "122") }
        _state.update { it.copy(yearOfConstruction = "1900") }
        _state.update { it.copy(addressId = -1) }
    }
}