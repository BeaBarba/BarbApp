package com.example.myapplication.ui.screen.Job.allSummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.JobAssignmentDetails
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AllJobsSummaryState(
    val started : Boolean = false,
    val jobs : List<JobAssignmentDetails> = listOf()
)

interface AllJobsSummaryActions{
    fun populateCustomers()
    fun getTypeStringFromJob(job: JobAssignmentDetails) : String
}

class AllJobsSummaryViewModel(
    repository : Repository
) : ViewModel(){

    private val _state = MutableStateFlow(AllJobsSummaryState())

    val state = _state.asStateFlow()

    val actions = object : AllJobsSummaryActions {

        override fun populateCustomers() {
            if (!state.value.started) {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            jobs = repository.getAllJobsAssignmentDetails(),
                            started = true
                        )
                    }
                }
            }
        }

        override fun getTypeStringFromJob(job: JobAssignmentDetails) : String {
            return if (job.job.alarm) {
                JobType.ALA.toString()
            } else if(job.job.airConditioning) {
                JobType.CDZ.toString()
            } else if(job.job.electic) {
                JobType.ELE.toString()
            } else {
                JobType.NONE.toString()
            }
        }
    }
}