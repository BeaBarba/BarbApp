package com.example.myapplication.ui.screen.Address.add

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.String

data class AddressAddState(
    val address: String = "",
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
    val usableArea: String = ""
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
}

class AddressAddViewModel : ViewModel() {
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
            println("Pressed Save Button, data to save: " + state.value)
        }
    }

    private fun checkIfStringIsInt(value: String): Boolean {
        return value.all { char -> char.isDigit() }
    }

    private fun checkIfStringIsDouble(value: String): Boolean {
        return value.toDoubleOrNull() != null || value == ""
    }
}