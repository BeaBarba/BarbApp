package com.example.myapplication.ui.screen.Bubble.singleSummary

import androidx.lifecycle.ViewModel
import com.example.myapplication.debug.Prodotto
import com.example.myapplication.debug.prodotti
import com.example.myapplication.ui.screen.Bubble.add.Seller
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate

data class SingleBubbleSummaryState(
    val bubbleId: Int = -1,
    val bubbleNumber: String = "",
    val bubbleDate: LocalDate = LocalDate.now(),
    val seller: Seller? = null,
    val materials: List<Prodotto> = emptyList(),
    val started: Boolean = false
)

interface SingleBubbleSummaryActions {
    fun populateFromId(bubbleId: Int)
}

class SingleBubbleSummaryViewModel : ViewModel() {
    private val _state = MutableStateFlow(SingleBubbleSummaryState())

    val state = _state.asStateFlow()

    val actions = object : SingleBubbleSummaryActions {
        override fun populateFromId(bubbleId: Int) {
            if (!_state.value.started) {
                _state.update { it.copy(bubbleNumber = "Prova123") }
                _state.update { it.copy(seller = Seller(1, "Pippo")) }
                _state.update { it.copy(materials = state.value.materials + prodotti.get(0)) }
                _state.update { it.copy(materials = state.value.materials + prodotti.get(1)) }
                _state.update { it.copy(started = true) }
            }
        }
    }
}