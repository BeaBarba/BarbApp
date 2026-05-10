package com.example.myapplication.ui.screen.Statistics.revenueFromJobType

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RevenueFromJobState(
    val values : List<Pair<String, Float>> = emptyList(),
    val started : Boolean = false
)

class RevenueFromJobViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow(RevenueFromJobState())

    val state = _state.asStateFlow()

    init{
        populateView()
    }

    private fun populateView(){
        if(state.value.started) return

        viewModelScope.launch {
            val result = repository.revenueFromJobType()
            _state.update {
                it.copy(
                    values = listOf(Pair(JobType.ALA.name, result.alarm), Pair(JobType.ELE.name, result.electric),
                        Pair(JobType.CDZ.name, result.airConditioning)),
                    started = true
                )
            }
        }
    }
}