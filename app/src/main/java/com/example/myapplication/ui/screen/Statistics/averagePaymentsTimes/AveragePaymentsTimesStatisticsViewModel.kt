package com.example.myapplication.ui.screen.Statistics.averagePaymentsTimes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.AveragePaymentsTimesStatisticsResult
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AveragePaymentsTimesStatisticsState(
    val started : Boolean = false,
    val values : List<AveragePaymentsTimesStatisticsResult> = emptyList()
)

class AveragePaymentsTimesStatisticsViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow(AveragePaymentsTimesStatisticsState())

    val state = _state.asStateFlow()

    init{
        populateView()
    }

    private fun populateView(){
        if(state.value.started) return

        viewModelScope.launch {
            val result = repository.averagePaymentsTimesStatistics().sortedByDescending { it.days }
            _state.update {
                it.copy(
                    started = true,
                    values = result
                )
            }
        }
    }
}