package com.example.myapplication.ui.screen.Bubble.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.Bubble
import com.example.myapplication.data.database.Delivery
import com.example.myapplication.data.database.DeliveryWithMaterialDetails
import com.example.myapplication.data.database.Material
import com.example.myapplication.data.database.Seller
import com.example.myapplication.data.repository.Repository
import com.example.myapplication.debug.Prodotto
import com.example.myapplication.debug.materialList
import com.example.myapplication.debug.selectedMaterialResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate



data class BubbleAddState(
    val bubbleId: Int? = null,
    val bubbleNumber: String = "",
    val bubbleDate: LocalDate = LocalDate.now(),
    val selectedSeller: Seller? = null,
    val materials: List<DeliveryWithMaterialDetails> = emptyList(),

    //val materialsSelected: List<DeliveryWithMaterialDetails>,
    val newSeller: Seller? = null,
    val sellers: List<Seller> = emptyList(),
    val started: Boolean = false
)

interface BubbleAddActions {
    fun populateView(bubbleID : Int?)
    fun populateSellers()
    fun populateFromEdit(bubbleId: Int)
    fun saveBubble()
    fun setBubbleNumber(bubbleNumber: String)
    fun setBubbleDate(bubbleDate: LocalDate)
    fun setSeller(selectedSeller: String)
    fun setMaterials(materials: List<String>)
    fun setQuantityMaterial(material: Prodotto, quantity: String)
    fun setUnitPriceMaterial(material: Prodotto, unitPrice: String)
    fun setNewSeller(newSeller: String)
}

class BubbleAddViewModel(
    private val repository : Repository
) : ViewModel() {
    private val _state = MutableStateFlow(BubbleAddState(null))

    val state = _state.asStateFlow()

    val actions = object : BubbleAddActions {

        override fun populateView(bubbleId : Int?) {
            if (!state.value.started) {
                populateSellers()
                bubbleId?.let{populateFromEdit(bubbleId)}
            }
        }

        override fun populateSellers() {
            viewModelScope.launch(Dispatchers.IO) {
                val sellersList = repository.sellers
                    .first()
                sellersList.forEach { seller ->
                    _state.update { it.copy(sellers = state.value.sellers + seller) }
                }
            }
            _state.update { it.copy(sellers = state.value.sellers + Seller(-1, "New")) }

            if (state.value.bubbleId == null)
                setSeller("New")
            _state.update { it.copy(started = true) }
        }

        override fun populateFromEdit(bubbleId: Int) {
            viewModelScope.launch {
                repository.getBubbleFullDetails(bubbleId).collect { fullDetails ->
                    fullDetails.let { data ->

                        _state.update {
                            it.copy(
                                bubbleId = data.bubble.id,
                                bubbleNumber = data.bubble.number,
                                bubbleDate = data.bubble.date,
                                selectedSeller = data.seller,
                                materials = data.deliveriesWithMaterials,
                                newSeller = null,
                                started = true
                            )
                        }
                    }
                }
            }
        }

        override fun saveBubble() {
            //var state = _state.value
            viewModelScope.launch {
                if (_state.value.newSeller != null) {
                    val seller = _state.value.newSeller
                        val sellerInserted = Seller(
                            id = repository.upsertSeller(seller!!).toInt(),
                            name = _state.value.newSeller!!.name
                        )
                        _state.update {
                            it.copy(selectedSeller = sellerInserted)
                        }
                        println("Seller: " +_state.value.selectedSeller)
                    }
                if (_state.value.bubbleId != null) {
                    val newBubble = Bubble(
                        id = _state.value.bubbleId!!,
                        number = _state.value.bubbleNumber,
                        date = _state.value.bubbleDate,
                        seller = _state.value.selectedSeller!!.id,
                        purchaseInvoice = null
                    )
                    repository.upsertBubble(newBubble)
                }else{
                    var newBubble = Bubble(
                        id = 0,
                        number = _state.value.bubbleNumber,
                        date = _state.value.bubbleDate,
                        seller = _state.value.selectedSeller!!.id,
                        purchaseInvoice = null
                    )
                    newBubble.id = repository.upsertBubble(newBubble).toInt()
                }
                if(_state.value.materials.isNotEmpty()) {
                    _state.value.materials.forEach {
                        val delivery = Delivery(
                            bubble = _state.value.bubbleId!!,
                            material = it.material.id,
                            quantity = it.delivery.quantity,
                            unitPrice = it.delivery.unitPrice
                        )
                        repository.upsertDelivery(delivery)
                    }
                }
            }
        }

        override fun setBubbleNumber(bubbleNumber: String) {
            _state.update { it.copy(bubbleNumber = bubbleNumber) }
        }

        override fun setBubbleDate(bubbleDate: LocalDate) {
            _state.update { it.copy(bubbleDate = bubbleDate) }
        }

        override fun setSeller(selectedSeller: String) {
            _state.update { it.copy(selectedSeller = state.value.sellers.firstOrNull({ it.name == selectedSeller})) }
        }

        override fun setNewSeller(newSeller: String) {
            _state.update { it.copy(newSeller = Seller(0, newSeller)) }
        }

        override fun setMaterials(materials: List<String>) {
            /*
            val newMaterials : MutableList<DeliveryWithMaterialDetails>
            materials.map {materialId ->
                viewModelScope.launch {
                    val material = repository.getMaterialById(materialId.toInt()).first()
                    val newDelivery = Delivery(
                        bubble = state.value.bubbleId ?: 0,
                        material = material.id,
                        quantity = 0,

                    )
                    val newMaterialBubble = DeliveryWithMaterialDetails(
                        delivery =
                    )
                    newMaterials.add()
                }
                state.value.materials.firstOrNull { value -> value.nome == it } ?: Prodotto(
                    it,
                    "",
                    0,
                    0.0,
                    0,
                    "",
                    ""
                )
            }.

            _state.update { it.copy(materials = materials) }

             */
        }
        override fun setQuantityMaterial(
            material: Prodotto,
            quantity: String
        ) {
            /*
            if (checkIfStringIsInt(quantity)) {
                val quantityToSet = if (quantity == "") 0 else quantity.toInt()
                val newMaterials = state.value.materials.map {
                    if (it.nome == material.nome)
                        it.copy(quantita = quantityToSet)
                    else
                        it
                }
                _state.update { it.copy(materials = newMaterials) }
            }

             */
        }

        override fun setUnitPriceMaterial(
            material: Prodotto,
            unitPrice: String
        ) {/*
            if (checkIfStringIsDouble(unitPrice)) {
                val unitPriceToSet: Double = if (unitPrice == "") 0.0 else unitPrice.toDouble()
                val newMaterials = state.value.materials.map {
                    if (it.nome == material.nome)
                        it.copy(prezzo = unitPriceToSet)
                    else
                        it
                }
                _state.update { it.copy(materials = newMaterials) }
            }
            */
        }
    }

    private fun checkIfStringIsInt(value: String): Boolean {
        return value.all { char -> char.isDigit() }
    }

    private fun checkIfStringIsDouble(value: String): Boolean {
        return value.toDoubleOrNull() != null || value == ""
    }
}