package com.example.myapplication.ui.screen.Job.allSummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.JobAssignmentDetails
import com.example.myapplication.data.modules.FilterKey
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Locale.getDefault

data class AllJobsSummaryState(
    val started : Boolean = false,
    val jobs : List<JobAssignmentDetails> = listOf(),
    val jobsView : List<JobAssignmentDetails> = listOf(),
    val searchString : String = "",
    val filterKey: FilterKey = FilterKey.ASC_DATE
)

interface AllJobsSummaryActions{
    fun populateJobs()
    fun getTypeStringFromJob(job: JobAssignmentDetails) : String
    fun filterAllJobs()
    fun filterCompletedJobs()
    fun filterToBeSchedule()
    fun filterElectricJobs()
    fun filterAirConditioningJobs()
    fun filterAlarmJobs()
    fun ascendingOrder()
    fun descendingOrder()
    fun searchJob(string : String)
}

class AllJobsSummaryViewModel(
    repository : Repository
) : ViewModel(){

    private val _state = MutableStateFlow(AllJobsSummaryState())

    val state = _state.asStateFlow()

    val actions = object : AllJobsSummaryActions {

        override fun populateJobs() {
            if (!state.value.started) {
                viewModelScope.launch {
                    repository.job.getAllJobsAssignmentDetails().collect{ jobsList ->
                        _state.update { currentState ->
                            val filterList =
                                if(currentState.searchString.isEmpty()) jobsList
                                else searchFilter(currentState.searchString, jobsList)

                            currentState.copy(
                                jobs = jobsList,
                                jobsView = filterList,
                                started = true
                            )
                        }
                    }
                }
            }
        }

        override fun getTypeStringFromJob(job: JobAssignmentDetails) : String {
            return if (job.job.alarm) {
                JobType.ALA.toString()
            } else if(job.job.airConditioning) {
                JobType.CDZ.toString()
            } else if(job.job.electric) {
                JobType.ELE.toString()
            } else {
                JobType.NONE.toString()
            }
        }

        override fun filterAllJobs() {
            val jobs = _state.value.jobs.sortedBy { it.address.address }
            _state.update { it.copy(jobsView = jobs, filterKey = FilterKey.ASC_DATE) }
        }

        override fun filterCompletedJobs() {
            val jobs = _state.value.jobs.filter { it.job.date.isBefore(LocalDate.now()) }
            _state.update { it.copy(jobsView = jobs, filterKey = FilterKey.ASC_DATE) }
        }

        override fun filterToBeSchedule() {
            val jobs = _state.value.jobs.filter { it.job.date.isAfter(LocalDate.now()) }
            _state.update { it.copy(jobsView = jobs, filterKey = FilterKey.ASC_DATE) }
        }

        override fun filterElectricJobs() {
            val jobs = _state.value.jobs.filter { it.job.electric }
            _state.update { it.copy(jobsView = jobs, filterKey = FilterKey.ASC_DATE) }
        }

        override fun filterAirConditioningJobs() {
            val jobs = _state.value.jobs.filter { it.job.airConditioning }
            _state.update { it.copy(jobsView = jobs, filterKey = FilterKey.ASC_DATE) }
        }

        override fun filterAlarmJobs() {
            val jobs = _state.value.jobs.filter { it.job.alarm }
            _state.update { it.copy(jobsView = jobs, filterKey = FilterKey.ASC_DATE) }
        }

        override fun ascendingOrder() {
            val jobs = _state.value.jobsView.sortedBy { it.job.date }
            _state.update { it.copy(jobsView = jobs, filterKey = FilterKey.ASC_DATE) }
        }

        override fun descendingOrder() {
            val jobs = _state.value.jobsView.sortedBy { it.job.date }.reversed()
            _state.update { it.copy(jobsView = jobs, filterKey = FilterKey.DESC_DATE) }
        }

        override fun searchJob(string: String) {
            _state.update { it.copy(jobsView = searchFilter(string, state.value.jobs), searchString = string) }
        }
    }

    private fun searchFilter(searchString: String, jobs : List<JobAssignmentDetails>) : List<JobAssignmentDetails>{
        return jobs.filter { it.address.address.lowercase().startsWith(searchString.lowercase(getDefault())) }
    }
}