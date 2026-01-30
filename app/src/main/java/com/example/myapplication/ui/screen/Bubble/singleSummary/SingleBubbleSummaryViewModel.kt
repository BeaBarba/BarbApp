package com.example.myapplication.ui.screen.Bubble.singleSummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.BubbleFullDetails
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SingleBubbleSummaryState(
    val started: Boolean = false,
    val bubble : BubbleFullDetails? = null,
)

interface SingleBubbleSummaryActions {
    fun populateBubbleData(bubbleId: Int)
}

class SingleBubbleSummaryViewModel(
    private val repository : Repository
) : ViewModel() {

    private val _state = MutableStateFlow(SingleBubbleSummaryState())

    val state = _state.asStateFlow()

    val actions = object : SingleBubbleSummaryActions {

        override fun populateBubbleData(bubbleId: Int) {
            if(state.value.started) return

            viewModelScope.launch {
                _state.update { it.copy(started = true) }
                repository.getBubbleFullDetailsById(bubbleId).collect{ data ->
                    _state.update { it.copy(bubble = data) }
                }
            }
        }
    }
}