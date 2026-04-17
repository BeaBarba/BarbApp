package com.example.myapplication.ui.screen.Material.allSummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.MaterialWithAirConditional
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.util.Locale.getDefault

data class WarehouseState(
    val started : Boolean = false,
    val materials : List<MaterialWithAirConditional> = emptyList(),
    val materialsView : List<MaterialWithAirConditional> = emptyList(),
    val searchString : String = ""
)

interface WarehouseActions{
    fun setSearchString(searchString: String)
    fun decQuantity(id : Int)
    fun incQuantity(id : Int)
}

class WarehouseViewModel (
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow(WarehouseState())

    val state = _state.asStateFlow()

    init{
        populateView()
    }

    val actions = object : WarehouseActions {

        override fun setSearchString(searchString: String) {
            _state.update {
                it.copy(
                    searchString = searchString,
                    materialsView = searchFilter(searchString, it.materials)
                )
            }
        }

        override fun decQuantity(id: Int) {
            viewModelScope.launch {
                repository.inventory.offsetMaterialAvailableQuantity(id, -1.0f)
            }
        }

        override fun incQuantity(id: Int) {
            viewModelScope.launch {
                repository.inventory.offsetMaterialAvailableQuantity(id, 1.0f)
            }
        }
    }

    private fun populateView(){
        viewModelScope.launch {
            repository.inventory.materials.collect{ materials ->
                val materialsList = materials.filter { it.material.availableQuantity > 0 }
                    .sortedWith(
                        compareBy<MaterialWithAirConditional> {it.material.category}
                            .thenBy { it.material.model }
                            .thenBy { it.material.brand }
                    )
                _state.update {
                    it.copy(
                        started = true,
                        materials = materialsList,
                        materialsView = searchFilter(it.searchString, materialsList)
                    )
                }
            }
        }
    }

    private fun searchFilter(searchString : String, list : List<MaterialWithAirConditional>) :
            List<MaterialWithAirConditional>{
        if(searchString.isBlank()) return list

        val query = searchString.trim().lowercase(getDefault())

        return list.filter { item ->
            val materialMatch = item.material.category.lowercase().contains(query) ||
                    item.material.model.lowercase().contains(query) ||
                    item.material.brand.lowercase().contains(query)

            val serialMatch = item.airConditioner.any { ac ->
                ac.serialNumber.lowercase().contains(query)
            }

            materialMatch || serialMatch
        }
    }
}