package com.example.myapplication.ui.screen.Cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.CartDetails
import com.example.myapplication.data.database.Material
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CartState(
    val items : List<CartDetails> = emptyList()
)

interface CartActions{
    fun checkedItem(material : Int, quantity : Float, checked : Boolean)
}

class CartViewModel(
    private val repository: Repository
) : ViewModel(){
    private val _state = MutableStateFlow(CartState())

    val state = _state.asStateFlow()

    init{
        populateCart()
    }

    private fun populateCart(){
        viewModelScope.launch {
            repository.cartItems.collect { updatedItems ->
                _state.update { it.copy(items = updatedItems) }
            }
        }
    }

    val actions = object : CartActions {

        override fun checkedItem(material : Int, quantity : Float, checked : Boolean) {
            val materialToUpdate = state.value.items.find { it.material.id == material }?.material
            if(materialToUpdate != null){
                val adjustment =
                    if(checked) materialToUpdate.availability + quantity
                    else (materialToUpdate.availability - quantity).coerceAtLeast(0f)
                println("DEBUG: qta = ${adjustment}")
                viewModelScope.launch {
                    repository.upsertMaterial(materialToUpdate.copy(availability = adjustment))
                }
            }
        }
    }
}