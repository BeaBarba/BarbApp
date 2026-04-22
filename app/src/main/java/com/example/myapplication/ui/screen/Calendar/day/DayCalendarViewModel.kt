package com.example.myapplication.ui.screen.Calendar.day

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.JobFullDetails
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

data class DayCalendarState(
    val calendar : List<JobFullDetails> = emptyList(),
    val started : Boolean = false
)

interface DayCalendarActions {
    fun populateCalendar(date : LocalDate)
}

class DayCalendarViewModel(
    private val repository: Repository
) : ViewModel() {
    private val _state = MutableStateFlow(DayCalendarState())

    val state = _state.asStateFlow()

    val actions = object : DayCalendarActions {

        override fun populateCalendar(date: LocalDate) {
            viewModelScope.launch {
                repository.job.getFlowAllTodayJobsFullDetailsByDate(date).collect { jobs ->
                    _state.update {
                        it.copy(
                            calendar = jobs,
                            started = true
                        )
                    }
                }

            }
        }
    }
}