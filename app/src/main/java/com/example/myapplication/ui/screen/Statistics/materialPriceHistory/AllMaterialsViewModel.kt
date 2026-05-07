package com.example.myapplication.ui.screen.Statistics.materialPriceHistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.MaterialWithAirConditional
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale.getDefault

data class AllMaterialsState(
    val searchText : String = "",
    val materials : List<MaterialWithAirConditional> = emptyList(),
    val materialsView : List<MaterialWithAirConditional> = emptyList()
)

interface AllMaterialsActions{
    fun search(text : String)
}

class AllMaterialsViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow(AllMaterialsState())

    val state = _state.asStateFlow()

    init{
        populateView()
    }

    val actions = object : AllMaterialsActions{

        override fun search(text: String) {
            val materials = searchFilter(text, state.value.materials)
            _state.update { it.copy(materialsView = materials, searchText = text) }
        }
    }

    private fun populateView(){
        viewModelScope.launch {
            repository.inventory.materials.collect{ materials ->
                val materialsList = materials.sortedWith(
                    compareBy<MaterialWithAirConditional> {it.material.category}
                        .thenBy { it.material.model }
                        .thenBy { it.material.brand }
                )
                _state.update {
                    it.copy(
                        materials = materialsList,
                        materialsView = searchFilter(state.value.searchText, materialsList)
                    )
                }
            }
        }
    }

    private fun searchFilter(searchText : String, materials : List<MaterialWithAirConditional>) : List<MaterialWithAirConditional>{
        if(searchText.isBlank()) return materials

        val query = searchText.trim().lowercase(getDefault())

        return materials.filter { item ->
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