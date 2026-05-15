package com.example.myapplication.ui.screen.Statistics.jobStatistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.data.repository.Repository
import com.example.myapplication.ui.utilities.convertLongToDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

data class JobStatisticsState(
    val startDate : LocalDate? = null,
    val endDate : LocalDate? = null,
    val values : List<Pair<String, Int>> = emptyList(),
    val showChart : Boolean = false,
)

interface JobStatisticsActions{
    fun setRange(startDate : Long?, endDate : Long?)
}

class JobStatisticsViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow(JobStatisticsState())

    val state = _state.asStateFlow()

    val actions = object : JobStatisticsActions{

        override fun setRange(startDate: Long?, endDate: Long?) {
            if (startDate == null || endDate == null) return

            val start = convertLongToDate(startDate)
            val end = convertLongToDate(endDate)
            viewModelScope.launch {
                val result = repository.jobStatistics(start, end)
                val values = listOf(Pair(JobType.ALA.name, result.alarm), Pair(JobType.ELE.name,result.electric),
                    Pair(JobType.CDZ.name, result.airConditioning))
                _state.update {
                    it.copy(
                        startDate = start,
                        endDate = end,
                        values = values,
                        showChart = true
                    )
                }
            }
        }
    }
}