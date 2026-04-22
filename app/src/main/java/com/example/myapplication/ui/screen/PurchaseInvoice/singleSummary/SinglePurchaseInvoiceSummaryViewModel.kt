package com.example.myapplication.ui.screen.PurchaseInvoice.singleSummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.MaterialWithOrigin
import com.example.myapplication.data.database.PurchaseInvoiceFullDetails
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SinglePurchaseInvoiceSummaryState(
    val purchaseInvoiceId : Int = 0,
    val purchaseInvoiceFullDetails : PurchaseInvoiceFullDetails? = null,
    val materials : List<MaterialWithOrigin> = emptyList()
)

interface SinglePurchaseInvoiceSummaryActions{
    fun populateView(id: Int)
}

class SinglePurchaseInvoiceSummaryViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow(SinglePurchaseInvoiceSummaryState())

    val state = _state.asStateFlow()

    val actions = object : SinglePurchaseInvoiceSummaryActions {

        override fun populateView(id: Int) {
            viewModelScope.launch {
                val purchase = repository.accounting.getPurchaseInvoiceById(id)
                purchase?.let {
                    _state.update {
                        it.copy(
                            purchaseInvoiceId = purchase.purchaseInvoice.id,
                            purchaseInvoiceFullDetails = purchase,
                            materials = purchase.materials.map { mat ->
                                MaterialWithOrigin(
                                    material = mat.material,
                                    purchase = mat.purchase,
                                    delivery = null
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}