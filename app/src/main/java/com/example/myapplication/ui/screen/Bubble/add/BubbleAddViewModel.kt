package com.example.myapplication.ui.screen.Bubble.add

import androidx.lifecycle.ViewModel
import com.example.myapplication.debug.Prodotto
import com.example.myapplication.debug.prodotti
import com.example.myapplication.debug.selectedMaterialResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate

data class Seller(
    val sellerId: Int,
    val sellerName: String
)

data class BubbleAddState(
    val bubbleId: Int? = null,
    val bubbleNumber: String = "",
    val bubbleDate: LocalDate = LocalDate.now(),
    val seller: List<Seller> = emptyList(),
    val selectedSeller: Seller? = null,
    val materials: List<Prodotto> = emptyList(),
    val started: Boolean = false,
    val newSeller: Seller? = null
)

interface BubbleAddActions {
    fun populateSellers()
    fun setBubbleNumber(bubbleNumber: String)
    fun setBubbleDate(bubbleDate: LocalDate)
    fun setSeller(selectedSeller: String)
    fun setMaterials(materials: List<String>)
    fun setQuantityMaterial(material: Prodotto, quantity: String)
    fun setUnitPriceMaterial(material: Prodotto, unitPrice: String)
    fun setVatMaterial(material: Prodotto, vat: String)
    fun setNewSeller(newSeller: String)
    fun saveBubble()
    fun populateFromEdit(bubbleId: Int)
}

class BubbleAddViewModel : ViewModel() {
    private val _state = MutableStateFlow(BubbleAddState(null))

    val state = _state.asStateFlow()


    val actions = object : BubbleAddActions {
        override fun populateSellers() {
            _state.update { it.copy(seller = state.value.seller + Seller(-1, "New")) }
            _state.update { it.copy(seller = state.value.seller + Seller(1, "Seller 1")) }
            _state.update { it.copy(seller = state.value.seller + Seller(2, "Seller 2")) }
            _state.update { it.copy(seller = state.value.seller + Seller(3, "Seller 3")) }
            if (state.value.bubbleId == null)
                setSeller("New")
            _state.update { it.copy(started = true) }
        }

        override fun setBubbleNumber(bubbleNumber: String) {
            _state.update{it.copy(bubbleNumber = bubbleNumber)}
        }

        override fun setBubbleDate(bubbleDate: LocalDate) {
            _state.update{it.copy(bubbleDate = bubbleDate)}
        }

        override fun setSeller(selectedSeller: String) {
            _state.update { it.copy(selectedSeller = state.value.seller.firstOrNull({it.sellerName == selectedSeller})) }
        }

        override fun setMaterials(materials: List<String>) {
            val newMaterials = materials.map {
                state.value.materials.firstOrNull {
                        value -> value.nome == it }?: Prodotto(it,"",0,0.0,0,"", "")
            }
            _state.update {it.copy(materials = newMaterials)}
        }

        override fun setQuantityMaterial(
            material: Prodotto,
            quantity: String
        ) {
            if (checkIfStringIsInt(quantity)) {
                val quantityToSet = if (quantity == "") 0 else quantity.toInt()
                val newMaterials = state.value.materials.map {
                    if (it.nome == material.nome)
                        it.copy(quantita = quantityToSet)
                    else
                        it
                }
                _state.update {it.copy(materials = newMaterials)}
            }
        }

        override fun setUnitPriceMaterial(
            material: Prodotto,
            unitPrice: String
        ) {
            if (checkIfStringIsDouble(unitPrice)) {
                val unitPriceToSet: Double = if (unitPrice == "") 0.0 else unitPrice.toDouble()
                val newMaterials = state.value.materials.map {
                    if (it.nome == material.nome)
                        it.copy(prezzo = unitPriceToSet)
                    else
                        it
                }
                _state.update {it.copy(materials = newMaterials)}
            }
        }

        override fun setVatMaterial(
            material: Prodotto,
            vat: String
        ) {
            if (checkIfStringIsInt(vat)) {
                val vatToSet = if (vat == "") 0 else vat.toInt()
                val newMaterials = state.value.materials.map {
                    if (it.nome == material.nome)
                        it.copy(iva = vatToSet)
                    else
                        it
                }
                _state.update {it.copy(materials = newMaterials)}
            }
        }

        override fun setNewSeller(newSeller: String) {
            _state.update {it.copy(newSeller = Seller(0, newSeller))}
        }

        override fun saveBubble() {
            println(state.value.toString())
            selectedMaterialResult = listOf()
        }

        override fun populateFromEdit(bubbleId: Int) {
            _state.update { it.copy(bubbleNumber = "Prova123") }
            _state.update { it.copy(selectedSeller = state.value.seller.get(1)) }
            _state.update { it.copy(materials = state.value.materials + prodotti.get(0)) }
            _state.update { it.copy(materials = state.value.materials + prodotti.get(1)) }
            selectedMaterialResult = listOf(prodotti[0].nome, prodotti[1].nome).toMutableList()
            _state.update { it.copy(started = true) }
        }

    }

    private fun checkIfStringIsInt(value: String): Boolean {
        return value.all { char -> char.isDigit() }
    }

    private fun checkIfStringIsDouble(value: String): Boolean {
        return value.toDoubleOrNull() != null || value == ""
    }
}