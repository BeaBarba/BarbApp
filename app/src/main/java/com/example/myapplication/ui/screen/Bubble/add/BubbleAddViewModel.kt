package com.example.myapplication.ui.screen.Bubble.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.Bubble
import com.example.myapplication.data.database.Delivery
import com.example.myapplication.data.database.DeliveryWithMaterialDetails
import com.example.myapplication.data.database.Material
import com.example.myapplication.data.database.Seller
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

data class MaterialBubble(
    val material: Material,
    val quantity : Float,
    val unitPrice: Float,
    val vatNumber : Int,
    val delivery: Delivery? = null
)

data class BubbleAddState(
    val bubbleId: Int = 0,
    val bubbleNumber: String = "",
    val bubbleDate: LocalDate = LocalDate.now(),
    val selectedSeller: Seller? = null,
    val materialsBubble: List<DeliveryWithMaterialDetails> = emptyList(),

    val materialsSelected: List<MaterialBubble> = emptyList(),
    val newSeller: Seller? = null,
    val sellers: List<Seller> = emptyList(),
    val started: Boolean = false
)

interface BubbleAddActions {
    fun populateView(bubbleId : Int?)
    fun populateSellers()
    fun populateFromEdit(bubbleId: Int)
    fun populateMaterialsBubble()
    fun saveBubble()
    fun setBubbleNumber(bubbleNumber: String)
    fun setBubbleDate(bubbleDate: LocalDate)
    fun setSeller(selectedSeller: String)
    fun setMaterials(materials: List<String>)
    fun setQuantityMaterial(material: Material, quantity: String)
    fun setUnitPriceMaterial(material: Material, unitPrice: String)
    fun setVatNumberMaterial(material : Material, vatNumber: String)
    fun setNewSeller(newSeller: String)
    fun delete()
}

class BubbleAddViewModel(
    private val repository : Repository
) : ViewModel() {
    private val _state = MutableStateFlow(BubbleAddState())

    val state = _state.asStateFlow()

    val actions = object : BubbleAddActions {

        override fun populateView(bubbleId : Int?) {
            if (!state.value.started) {
                populateSellers()
                bubbleId?.let{
                    populateFromEdit(bubbleId)
                    populateMaterialsBubble()
                }
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

            if (state.value.bubbleId == 0)
                setSeller("New")
            _state.update { it.copy(started = true) }
        }

        override fun populateFromEdit(bubbleId: Int) {
            viewModelScope.launch {
                repository.getBubbleFullDetailsById(bubbleId).collect { fullDetails ->
                    fullDetails.let { data ->
                        if(data != null) {
                            _state.update {
                                it.copy(
                                    bubbleId = data.bubble.id,
                                    bubbleNumber = data.bubble.number,
                                    bubbleDate = data.bubble.date,
                                    selectedSeller = data.seller,
                                    materialsBubble = data.deliveriesWithMaterials,
                                    newSeller = null,
                                    started = true
                                )
                            }
                        }
                    }
                }
            }
        }

        override fun populateMaterialsBubble() {
            val materials : MutableList<MaterialBubble> = mutableListOf()
            state.value.materialsBubble.forEach {
                materials.add(
                    MaterialBubble(
                        material = it.material,
                        quantity = 0.0f,
                        unitPrice = 0.0f,
                        vatNumber = 0,
                        delivery = it.delivery
                    )
                )
            }
            _state.update{it.copy(materialsSelected = materials)}
        }

        override fun saveBubble() {
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

                if (_state.value.bubbleId != 0) {
                    val updateBubble = Bubble(
                        id = _state.value.bubbleId,
                        number = _state.value.bubbleNumber,
                        date = _state.value.bubbleDate,
                        seller = _state.value.selectedSeller!!.id,
                        purchaseInvoice = null
                    )
                    repository.upsertBubble(updateBubble)
                }else{
                    val newBubble = Bubble(
                        id = 0,
                        number = _state.value.bubbleNumber,
                        date = _state.value.bubbleDate,
                        seller = _state.value.selectedSeller!!.id,
                        purchaseInvoice = null
                    )
                    newBubble.id = repository.upsertBubble(newBubble).toInt()
                    _state.update { it.copy(bubbleId = newBubble.id) }
                }
                if(_state.value.materialsSelected.isNotEmpty()) {
                    _state.value.materialsSelected.forEach {
                        val delivery = Delivery(
                            bubble = _state.value.bubbleId,
                            material = it.material.id,
                            quantity = it.quantity,
                            unitPrice = it.unitPrice,
                            vatNumber = it.vatNumber
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
            val materialsIds = materials.map { it.toInt() }

            val idsExisting = state.value.materialsSelected.map{it.material.id}.toList()

            val materialsIdsResearch = materialsIds.filterNot { it in idsExisting }

            val newMaterials : MutableList<MaterialBubble> = mutableListOf()
            materialsIdsResearch.map { materialId ->
                viewModelScope.launch {
                    val material = repository.getMaterialById(materialId).first()
                    newMaterials.add(
                        MaterialBubble(
                            material = material!!,
                            quantity = 0.0f,
                            unitPrice = 0.0f,
                            vatNumber = 0
                        )
                    )
                }
            }
            newMaterials.addAll(state.value.materialsSelected.size, state.value.materialsSelected)

            _state.update { it.copy(materialsSelected = newMaterials) }
        }

        override fun setQuantityMaterial(material: Material, quantity: String) {
            if (checkIfStringIsInt(quantity)) {
                val quantityToSet = if (quantity == "") 0f else quantity.toFloat()
                val newMaterials = state.value.materialsSelected.map {
                    MaterialBubble(
                        material = it.material,
                        quantity =
                        if( material.id == it.material.id ) {
                            quantityToSet
                        }else{
                            it.quantity
                        },
                        unitPrice = it.unitPrice,
                        vatNumber = it.vatNumber
                    )
                }
                _state.update { it.copy(materialsSelected = newMaterials) }
            }
        }

        override fun setUnitPriceMaterial(material: Material, unitPrice: String) {
            if (checkIfStringIsFloat(unitPrice)) {
                val unitPriceToSet: Float = if (unitPrice == "") 0.0f else unitPrice.toFloat()
                val newMaterials = state.value.materialsSelected.map {
                    MaterialBubble(
                        material = it.material,
                        quantity = it.quantity,
                        unitPrice =
                            if( material.id == it.material.id ) {
                                unitPriceToSet
                            }else{
                                it.unitPrice
                            },
                        vatNumber = it.vatNumber
                    )
                }
                _state.update { it.copy(materialsSelected = newMaterials) }
            }
        }

        override fun setVatNumberMaterial(material : Material, vatNumber : String){
            if(checkIfStringIsInt(vatNumber)){
                val vatNumberToSet: Int = if (vatNumber == "") 0 else vatNumber.toInt()
                val newMaterials = state.value.materialsSelected.map {
                    MaterialBubble(
                        material = it.material,
                        quantity = it.quantity,
                        unitPrice =it.unitPrice,
                        vatNumber =
                        if( material.id == it.material.id ) {
                            vatNumberToSet
                        }else {
                            it.vatNumber
                        }
                    )
                }
                _state.update { it.copy(materialsSelected = newMaterials) }
            }
        }

        override fun delete() {
            viewModelScope.launch {
                repository.deleteBubble(
                    Bubble(
                        id = state.value.bubbleId,
                        number = state.value.bubbleNumber,
                        date = state.value.bubbleDate,
                        seller = state.value.selectedSeller?.id!!,
                        purchaseInvoice = null
                    )
                )
            }
        }
    }

    private fun checkIfStringIsInt(value: String): Boolean {
        return value.all { char -> char.isDigit() }
    }

    private fun checkIfStringIsFloat(value: String): Boolean {
        return value.toFloatOrNull() != null || value == ""
    }
}