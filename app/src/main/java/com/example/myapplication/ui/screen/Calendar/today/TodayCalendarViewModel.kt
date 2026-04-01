package com.example.myapplication.ui.screen.Calendar.today

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.JobAssignmentDetails
import com.example.myapplication.data.database.JobFullDetails
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

data class TodayCalendarState(
    val todayCalendar : List<JobFullDetails> = emptyList(),
    val toScheduleCalendar : List<JobAssignmentDetails> = emptyList(),
    val started : Boolean = false
)

class TodayCalendarViewModel(
    private val repository: Repository
) : ViewModel() {
    private val _state = MutableStateFlow(TodayCalendarState())

    val state = _state.asStateFlow()

    init{
        populateTodayCalendar()
    }

    private fun populateTodayCalendar() {

        if (state.value.started) return

        viewModelScope.launch {
            repository.getAllTodayJobsFullDetailsByDate(LocalDate.now()).collect { jobs ->
                _state.update { it.copy(started = true, todayCalendar = jobs) }
            }
        }
        viewModelScope.launch {
            repository.getAllToScheduleJobsAssignmentDetailsByDate(LocalDate.now()).collect{ jobs ->
                _state.update { it.copy(started = true, toScheduleCalendar = jobs) }
            }
        }
    }
}