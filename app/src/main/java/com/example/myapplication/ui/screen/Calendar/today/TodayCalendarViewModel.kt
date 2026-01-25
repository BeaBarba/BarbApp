package com.example.myapplication.ui.screen.Calendar.today

import androidx.lifecycle.ViewModel
import com.example.myapplication.debug.Appuntamento
import com.example.myapplication.debug.appuntamenti
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class TodayCalendarState(
    val todayCalendar : List<Appuntamento> = listOf(),
    val toScheduleCalendar : List<Appuntamento> = listOf(),
    val started : Boolean = false
)

interface TodayCalendarActions {
    fun populateTodayCalendar()
}

class TodayCalendarViewModel() : ViewModel() {
    private val _state = MutableStateFlow(TodayCalendarState())

    val state = _state.asStateFlow()

    val actions = object : TodayCalendarActions {
        override fun populateTodayCalendar() {
            if (!_state.value.started) {
                _state.update { it.copy(todayCalendar = appuntamenti.take(6)) }
                _state.update { it.copy(toScheduleCalendar = appuntamenti.take(4)) }
            }
        }
    }
}