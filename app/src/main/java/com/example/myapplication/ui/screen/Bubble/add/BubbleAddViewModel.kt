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
import kotlinx.coroutines.flow.firstOrNull
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
    val purchaseInvoice: Int? = null,

    val materialsSelected: List<MaterialBubble> = emptyList(),
    val newSeller: Seller? = null,
    val sellers: List<Seller> = emptyList(),
    val started: Boolean = false
)

interface BubbleAddActions {
    fun populateView(bubbleId : Int?)
    fun populateSellers()
    fun populateFromEdit(bubbleId: Int)
    fun populateMaterialsBubble(bubbleId : Int)
    fun saveBubble(onSuccess : (Int) -> Unit)
    fun setBubbleNumber(bubbleNumber: String)
    fun setBubbleDate(bubbleDate: LocalDate)
    fun setSeller(selectedSeller: Int)
    fun setMaterials(materials: List<String>)
    fun setQuantityMaterial(material: Material, quantity: String)
    fun setUnitPriceMaterial(material: Material, unitPrice: String)
    fun setVatNumberMaterial(material : Material, vatNumber: String)
    fun createNewSeller(newSeller: String)
    fun getMaterialIds() : List<String>
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
                    populateMaterialsBubble(bubbleId)
                }
            }
        }

        override fun populateSellers() {
            viewModelScope.launch(Dispatchers.IO) {
                val sellersList = repository.inventory.sellers.first()
                val completeSellerList = sellersList + Seller(-1, "New")

                _state.update { it.copy(sellers = completeSellerList) }
            }

            if (state.value.bubbleId == 0)
                setSeller(-1)
            _state.update { it.copy(started = true) }
        }

        override fun populateFromEdit(bubbleId: Int) {
            viewModelScope.launch {
                repository.accounting.getBubbleFullDetailsById(bubbleId).collect { fullDetails ->
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
                                    purchaseInvoice = data.purchaseInvoice?.id,
                                    started = true
                                )
                            }
                        }
                    }
                }
            }
        }

        override fun populateMaterialsBubble(bubbleId: Int) {
            if (bubbleId == 0) return

            viewModelScope.launch(Dispatchers.IO) {
                val bubble = repository.accounting.getBubbleFullDetailsById(bubbleId).firstOrNull()
                val existingMaterialsDelivery = bubble?.deliveriesWithMaterials ?: emptyList()
                val materialsList = existingMaterialsDelivery.map { item ->
                    MaterialBubble(
                        material = item.material,
                        quantity = item.delivery.quantity,
                        unitPrice = item.delivery.unitPrice,
                        vatNumber = item.delivery.vatNumber,
                        delivery = item.delivery
                    )
                }

                _state.update { it.copy(materialsSelected = materialsList) }
            }
        }

        override fun saveBubble(onSuccess: (Int) -> Unit) {
            viewModelScope.launch {
                val idSeller =
                    if (_state.value.newSeller != null) {
                        val seller = _state.value.newSeller
                        val sellerInserted = Seller(
                            id = repository.inventory.upsertSeller(seller!!).toInt(),
                            name = _state.value.newSeller!!.name
                        )
                        _state.update {
                            it.copy(selectedSeller = sellerInserted)
                        }
                        sellerInserted.id
                    }else{
                        _state.value.selectedSeller?.id ?:0
                    }

                val idBubble : Int =
                    if (_state.value.bubbleId != 0) {
                        val updateBubble = Bubble(
                            id = _state.value.bubbleId,
                            number = _state.value.bubbleNumber,
                            date = _state.value.bubbleDate,
                            seller = idSeller,
                            purchaseInvoice = _state.value.purchaseInvoice
                        )
                        repository.accounting.upsertBubble(updateBubble)
                        updateBubble.id
                    }else{
                        val newBubble = Bubble(
                            id = 0,
                            number = _state.value.bubbleNumber,
                            date = _state.value.bubbleDate,
                            seller = idSeller,
                            purchaseInvoice = null
                        )
                        repository.accounting.upsertBubble(newBubble).toInt()
                    }
                if(_state.value.materialsSelected.isNotEmpty()) {
                    _state.value.materialsSelected.forEach {
                        val delivery = Delivery(
                            bubble = idBubble,
                            material = it.material.id,
                            quantity = it.quantity,
                            unitPrice = it.unitPrice,
                            vatNumber = it.vatNumber
                        )
                        repository.inventory.upsertDelivery(delivery)
                    }
                }
                onSuccess(idBubble)
            }
        }

        override fun setBubbleNumber(bubbleNumber: String) {
            _state.update { it.copy(bubbleNumber = bubbleNumber) }
        }

        override fun setBubbleDate(bubbleDate: LocalDate) {
            _state.update { it.copy(bubbleDate = bubbleDate) }
        }

        override fun setSeller(selectedSeller: Int) {
            _state.update { it.copy(selectedSeller = state.value.sellers.find{ it.id == selectedSeller}) }
        }

        override fun createNewSeller(newSeller: String) {
            _state.update { it.copy(newSeller = Seller(0, newSeller)) }
        }

        override fun setMaterials(materials: List<String>) {
            val inputIds = materials.mapNotNull { it.toIntOrNull() }

            val currentSelected = state.value.materialsSelected
            val preservedMaterials = currentSelected.filter { it.material.id in inputIds }
            val preservedIds = preservedMaterials.map { it.material.id }

            val newIdsToFetch = inputIds.filterNot { it in preservedIds }

            viewModelScope.launch(Dispatchers.IO) {
                val newMaterials = newIdsToFetch.mapNotNull { id ->
                    repository.inventory.getMaterialById(id).firstOrNull()?.let { material ->
                        MaterialBubble(
                            material = material,
                            quantity = 0.0f,
                            unitPrice = 0.0f,
                            vatNumber = 0
                        )
                    }
                }

                val updatedList = preservedMaterials + newMaterials

                _state.update { it.copy(materialsSelected = updatedList) }
            }
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

        override fun getMaterialIds(): List<String> {
            val materialIds = _state.value.materialsSelected.map{item ->
                item.material.id.toString()
            }
            return materialIds
        }

        override fun delete() {
            viewModelScope.launch {
                repository.accounting.deleteBubble(
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