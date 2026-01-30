package com.example.myapplication.ui.screen.Bubble.allSummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.BubbleFullDetails
import com.example.myapplication.data.modules.FilterKey
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AllBubblesSummaryState(
    val started: Boolean = false,
    val bubbles: List<BubbleFullDetails> = emptyList(),
    val filterKey: FilterKey = FilterKey.ASC_DATE
)

interface AllBubblesSummaryActions {
    fun populateBubbles()
    fun dateSort()
    fun sellerSort()
    fun ascendingSort()
    fun descendingSort()
}

class AllBubblesSummaryViewModel(
    private val repository: Repository
) : ViewModel() {
    private val _state = MutableStateFlow(AllBubblesSummaryState())

    val state = _state.asStateFlow()

    val actions = object : AllBubblesSummaryActions {

        override fun populateBubbles() {
            viewModelScope.launch{
                val bubbles = repository.getAllBubblesFullDetails()
                if (!state.value.started) {
                    _state.update {
                        it.copy(
                            bubbles = bubbles,
                            started = true
                        )
                    }
                }
            }
            dateSort()
        }

        override fun dateSort() {
            _state.update {
                it.copy(
                    bubbles = state.value.bubbles.sortedBy { value -> value?.bubble?.date },
                    filterKey = FilterKey.ASC_DATE
                )
            }
        }

        override fun sellerSort() {
            _state.update {
                it.copy(
                    bubbles = state.value.bubbles.sortedBy { value -> value?.seller?.name },
                    filterKey = FilterKey.ASC_SELLER
                )
            }
        }

        override fun ascendingSort() {
            if (state.value.filterKey == FilterKey.DESC_SELLER){
                _state.update {
                    it.copy(
                        bubbles = state.value.bubbles.reversed(),
                        filterKey = FilterKey.ASC_SELLER
                    )
                }
            }
            if (state.value.filterKey == FilterKey.DESC_DATE){
                _state.update {
                    it.copy(
                        bubbles = state.value.bubbles.reversed(),
                        filterKey = FilterKey.ASC_DATE
                    )
                }
            }
        }

        override fun descendingSort() {
            if (state.value.filterKey == FilterKey.ASC_SELLER){
                _state.update {
                    it.copy(
                        bubbles = state.value.bubbles.reversed(),
                        filterKey = FilterKey.DESC_SELLER
                    )
                }
            }else if (state.value.filterKey == FilterKey.ASC_DATE){
                _state.update {
                    it.copy(
                        bubbles = state.value.bubbles.reversed(),
                        filterKey = FilterKey.DESC_DATE
                    )
                }
            }
        }
    }
}