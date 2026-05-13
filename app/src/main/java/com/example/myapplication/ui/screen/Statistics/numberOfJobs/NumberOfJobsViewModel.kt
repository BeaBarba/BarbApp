package com.example.myapplication.ui.screen.Statistics.numberOfJobs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.NumberOfJobsByReference
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class NumberOfJobsState(
    val started : Boolean = false,
    val values : List<NumberOfJobsByReference> = emptyList()
)

class  NumberOfJobsViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow(NumberOfJobsState())

    val state = _state.asStateFlow()

    init{
        populateView()
    }

    private fun populateView(){
        if(state.value.started) return

        viewModelScope.launch {
            val result = repository.numberOfJobsByReference()
            _state.update {
                it.copy(
                    started = true,
                    values = result
                )
            }
        }
    }
}