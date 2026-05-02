package com.example.myapplication.ui.screen.PurchaseInvoice.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.Material
import com.example.myapplication.data.database.Purchase
import com.example.myapplication.data.database.PurchaseInvoice
import com.example.myapplication.data.database.Seller
import com.example.myapplication.data.repository.Repository
import com.example.myapplication.ui.component.MenuItem
import com.example.myapplication.ui.component.checkStringIsBigDecimal
import com.example.myapplication.ui.component.convertStringToDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.time.LocalDate

data class MaterialPurchase(
    val material: Material,
    val quantity : BigDecimal,
    val unitPrice: BigDecimal,
    val vatNumber : Int,
    val purchase: Purchase? = null
)

data class PurchaseInvoiceAddState(
    val purchaseInvoiceId : Int = 0,
    val number: String = "",
    val seller : Pair<Int,String> = Pair(-1,""),
    val issueDate : LocalDate? = null,
    val bubblesIds : List<Int> = emptyList(),

    val sellersList : List<Seller> = emptyList(),
    val sellersMenu : List<MenuItem> = emptyList(),
    val newSeller : String = "",
    val materials: List<MaterialPurchase> = emptyList(),
    val started : Boolean = false
)

interface PurchaseInvoiceAddActions{
    fun populateView(id : Int?)
    fun checkRequirements() : Boolean
    fun setNewSeller(seller : String)
    fun setNumber(number : String)
    fun setIssueDate(date : String)
    fun setBubbles(bubbles : List<String>)
    fun getBubblesIds() : List<String>
    fun setMaterials(materials : List<String>)
    fun getMaterialsIds() : List<String>
    fun setMaterialQuantity(materialId : Int, quantity : String)
    fun setMaterialPrice(materialId : Int, price : String)
    fun setMaterialVat(materialId : Int, vat : String)
    fun save(onSuccess : (Int) -> Unit)
    fun delete(id : Int)
}

class PurchaseInvoiceAddViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow(PurchaseInvoiceAddState())

    val state = _state.asStateFlow()

    val sellersMenu : StateFlow<List<MenuItem>> = _state.map { currentState ->
        val sellers = currentState.sellersList.map { seller ->
            MenuItem(
                idValues = Pair(seller.id, ""),
                name = seller.name,
                onClick = { setSeller(seller.id, seller.name) }
            )
        }
        val newSeller = MenuItem(
            idValues = Pair(0, ""),
            name = "Nuovo",
            onClick = { setSeller(0, "") }
        )
        sellers + newSeller
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    init{
        loadSellers()
    }

    val actions = object : PurchaseInvoiceAddActions{

        override fun populateView(id : Int?){
            if (!state.value.started) {
                id?.let {
                    viewModelScope.launch(Dispatchers.IO) {
                        val purchaseInvoice = repository.accounting.getPurchaseInvoiceById(id)
                        purchaseInvoice?.let {
                            _state.update {
                                it.copy(
                                    purchaseInvoiceId = purchaseInvoice.purchaseInvoice.id,
                                    number = purchaseInvoice.purchaseInvoice.number,
                                    issueDate = purchaseInvoice.purchaseInvoice.year,
                                    seller = Pair(purchaseInvoice.seller.id, purchaseInvoice.seller.name),
                                    bubblesIds = purchaseInvoice.bubbles.map { bubble -> bubble.id },
                                    materials = purchaseInvoice.materials.map { purchaseMaterial ->
                                        MaterialPurchase(
                                            material = purchaseMaterial.material,
                                            quantity = purchaseMaterial.purchase.quantity.toBigDecimal(),
                                            unitPrice = purchaseMaterial.purchase.unitPrice.toBigDecimal(),
                                            vatNumber = purchaseMaterial.purchase.vatNumber,
                                            purchase = purchaseMaterial.purchase
                                        )
                                    },
                                    started = true
                                )
                            }
                        }
                    }
                }
            }
        }

        override fun checkRequirements(): Boolean {
            val currentState = state.value

            if(currentState.issueDate == null) return false

            if(currentState.seller.first == -1) return false

            if(currentState.number.isBlank()) return false

            return true
        }

        override fun setNewSeller(seller: String) {
            _state.update { it.copy(newSeller = seller) }
        }

        override fun setNumber(number: String) {
            _state.update { it.copy(number = number) }
        }

        override fun setIssueDate(date: String) {
            _state.update { it.copy(issueDate = convertStringToDate(date)) }
        }

        override fun setBubbles(bubbles: List<String>) {
            if(bubbles.isNotEmpty()) {
                val newBubblesIds = bubbles.mapNotNull { it.toIntOrNull() }

            _state.update{it.copy(bubblesIds = newBubblesIds)}
            }else{
                _state.update { it.copy(bubblesIds = emptyList()) }
            }
        }

        override fun getBubblesIds(): List<String> {
            val bubbles = state.value.bubblesIds.map{ it.toString() }
            return bubbles
        }

        override fun setMaterials(materials: List<String>) {
            if(materials.isEmpty()){
                _state.update{ it.copy(materials = emptyList())}
            }else {
                val inputIds = materials.mapNotNull { it.toIntOrNull() }

                val currentSelected = state.value.materials
                val preservedMaterials = currentSelected.filter { it.material.id in inputIds }
                val preservedIds = preservedMaterials.map { it.material.id }

                val newIdsToFetch = inputIds.filterNot { it in preservedIds }

                if (newIdsToFetch.isEmpty()) {
                    _state.update { it.copy(materials = preservedMaterials) }
                } else {

                    viewModelScope.launch(Dispatchers.IO) {
                        val newMaterials = newIdsToFetch.mapNotNull { id ->
                            repository.inventory.getMaterialById(id)?.let { material ->
                                MaterialPurchase(
                                    material = material,
                                    quantity = BigDecimal.ZERO,
                                    unitPrice = BigDecimal.ZERO,
                                    vatNumber = 0
                                )
                            }
                        }

                        val updatedList = preservedMaterials + newMaterials
                        _state.update { it.copy(materials = updatedList) }
                    }
                }
            }
        }

        override fun getMaterialsIds(): List<String> {
            return state.value.materials.map { it.material.id.toString() }
        }

        override fun setMaterialQuantity(materialId: Int, quantity : String) {
            if (checkStringIsBigDecimal(quantity)) {
                val quantityToSet = if (quantity == "") BigDecimal.ZERO else quantity.toBigDecimal()
                val newMaterials = state.value.materials.map {
                    MaterialPurchase(
                        material = it.material,
                        quantity =
                        if(materialId == it.material.id) {
                            quantityToSet
                        }else{
                            it.quantity
                        },
                        unitPrice = it.unitPrice,
                        vatNumber = it.vatNumber
                    )
                }
                _state.update { it.copy(materials = newMaterials) }
            }
        }

        override fun setMaterialPrice(materialId: Int, price: String) {
            if (checkStringIsBigDecimal(price)) {
                val priceToSet = if (price == "") BigDecimal.ZERO else price.toBigDecimal()
                val newMaterials = state.value.materials.map {
                    MaterialPurchase(
                        material = it.material,
                        quantity = it.quantity,
                        unitPrice =
                            if(materialId == it.material.id) {
                                priceToSet
                            }else{
                                it.unitPrice
                            },
                        vatNumber = it.vatNumber
                    )
                }
                _state.update { it.copy(materials = newMaterials) }
            }
        }

        override fun setMaterialVat(materialId: Int, vat: String) {
            if (checkStringIsBigDecimal(vat)) {
                val vatToSet = if (vat == "") 0 else vat.toInt()
                val newMaterials = state.value.materials.map {
                    MaterialPurchase(
                        material = it.material,
                        quantity = it.quantity,
                        unitPrice = it.unitPrice,
                        vatNumber =
                            if(materialId == it.material.id) {
                                vatToSet
                            }else{
                                it.vatNumber
                            }
                    )
                }
                _state.update { it.copy(materials = newMaterials) }
            }
        }

        override fun save(onSuccess: (Int) -> Unit) {
            viewModelScope.launch{
                val currentState = state.value

                val seller : Seller =

                    if(currentState.newSeller.isNotBlank() && currentState.seller.first == 0){
                        Seller(
                            id = 0,
                            name = currentState.newSeller
                        )
                    }else{
                        Seller(
                            id = currentState.seller.first,
                            name = currentState.seller.second
                        )
                    }

                val purchaseInvoice = PurchaseInvoice(
                    id = currentState.purchaseInvoiceId,
                    number = currentState.number,
                    seller = currentState.seller.first,
                    year = currentState.issueDate ?: LocalDate.now()
                )

                val purchases =
                    if(currentState.materials.isNotEmpty()){
                        currentState.materials.map{ material ->
                            Purchase(
                                purchaseInvoice = currentState.purchaseInvoiceId,
                                material = material.material.id,
                                quantity = material.quantity.toFloat(),
                                unitPrice = material.unitPrice.toFloat(),
                                vatNumber = material.vatNumber
                            )
                        }
                    }else{
                        emptyList()
                    }

                val purchaseInvoiceFinalId = repository.accounting.savePurchaseInvoiceComplete(
                    purchaseInvoice,
                    seller,
                    currentState.bubblesIds,
                    purchases
                )

                _state.update { it.copy(purchaseInvoiceId = purchaseInvoiceFinalId) }

                onSuccess(purchaseInvoiceFinalId)
            }
        }

        override fun delete(id: Int) {
            viewModelScope.launch {
                val currentState = state.value
                repository.accounting.deletePurchaseInvoiceComplete(
                    PurchaseInvoice(
                        id = currentState.purchaseInvoiceId,
                        number = currentState.number,
                        year = currentState.issueDate ?: LocalDate.now(),
                        seller = currentState.seller.first
                    )
                )
            }
        }
    }

    private fun loadSellers(){
        viewModelScope.launch(Dispatchers.IO) {
            val sellers = repository.inventory.getAllSellers()
            _state.update { it.copy(sellersList = sellers) }
        }
    }

    private fun setSeller(id : Int, name : String){
        _state.update { it.copy(seller = Pair(id, name))  }
    }
}
