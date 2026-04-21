package com.example.myapplication.ui.screen.Material.singleSummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.MaterialFullDetails
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SingleMaterialSummaryState(
    val started : Boolean = false,
    val materialData : MaterialFullDetails? = null
)

interface SingleMaterialSummaryActions{
    fun populateFromId(materialId : Int)
}

class SingleMaterialSummaryViewModel(
    repository: Repository
) : ViewModel(){
    private val _state = MutableStateFlow(SingleMaterialSummaryState())

    val state = _state.asStateFlow()

    val actions = object : SingleMaterialSummaryActions{

        override fun populateFromId(materialId: Int) {
            if(state.value.started) return

            viewModelScope.launch {
                repository.inventory.getFlowMaterialFullDetailsById(materialId).collect{ data ->
                    if(data != null) {
                        _state.update {
                            it.copy(
                                started = true,
                                materialData = MaterialFullDetails(
                                    material = data.material,
                                    deliveriesWithBubbles = data.deliveriesWithBubbles,
                                    purchaseWithPurchaseInvoice = data.purchaseWithPurchaseInvoice,
                                    photos = data.photos
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}