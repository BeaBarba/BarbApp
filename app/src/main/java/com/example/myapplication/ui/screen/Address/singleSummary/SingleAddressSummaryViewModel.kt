package com.example.myapplication.ui.screen.Address.singleSummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SingleAddressSummaryState(
    val addressId: Int = -1,
    val address: String = "",
    val houseNumber : String = "",
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

interface SingleAddressSummaryActions {
    fun populateFromId(addressId: Int)
}

class SingleAddressSummaryViewModel(
    repository : Repository
) : ViewModel() {
    private val _state = MutableStateFlow(SingleAddressSummaryState())

    val state = _state.asStateFlow()

    val actions = object : SingleAddressSummaryActions {
        override fun populateFromId(addressId: Int) {
            viewModelScope.launch {
                repository.getAddressById(addressId).collect { addressEntity ->
                    _state.update {
                        it.copy(
                            addressId = addressEntity.id,
                            address = addressEntity.address,
                            houseNumber = addressEntity.houseNumber,
                            municipality = addressEntity.municipality,
                            city = addressEntity.city,
                            province = addressEntity.province,
                            zip = addressEntity.zip,
                            sheet = addressEntity.sheet?:"",
                            map = addressEntity.map?:"",
                            subordinate = addressEntity.subordinate?:"",
                            staircase = addressEntity.staircase?:"",
                            floor = addressEntity.floor?:"",
                            interior = addressEntity.interior?:"",
                            yearOfConstruction = addressEntity.yearOfConstruction?:"",
                            usableArea = addressEntity.usableArea?:"",
                            units = addressEntity.units?:""
                        )
                    }
                }
            }
        }

        private fun simulateData() {
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
}