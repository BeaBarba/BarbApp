package com.example.myapplication.ui.screen.Cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.CartDetails
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
    fun populateCart()
}

class CartViewModel(
    private val repository: Repository
) : ViewModel(){
    private val _state = MutableStateFlow(CartState())

    val state = _state.asStateFlow()

    val actions = object : CartActions {
        override fun populateCart() {
            viewModelScope.launch {
                _state.update { it.copy(items = repository.cartItems.first())}
            }
        }
    }
}