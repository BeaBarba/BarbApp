package com.example.myapplication.ui.screen.Calendar.day

import androidx.lifecycle.ViewModel
import com.example.myapplication.debug.Appuntamento
import com.example.myapplication.debug.appuntamenti
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate

data class DayCalendarState(
    val calendar : List<Appuntamento> = listOf(),
    val started : Boolean = false
)

interface DayCalendarActions {
    fun populateCalendar(date : LocalDate)
}

class DayCalendarViewModel() : ViewModel() {
    private val _state = MutableStateFlow(DayCalendarState())

    val state = _state.asStateFlow()

    val actions = object : DayCalendarActions {
        override fun populateCalendar(date: LocalDate) {
            _state.update { it.copy(calendar = appuntamenti.takeLast(2)) }
            _state.update { it.copy(started = true) }
        }
    }
}