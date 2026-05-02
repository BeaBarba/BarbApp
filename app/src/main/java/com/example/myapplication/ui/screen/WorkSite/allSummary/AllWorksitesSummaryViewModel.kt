package com.example.myapplication.ui.screen.WorkSite.allSummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.WorkSiteAssignmentDetails
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AllWorksitesSummaryState(
    val started : Boolean = false,
    val worksites : List<WorkSiteAssignmentDetails> = emptyList(),
    val worksitesDone : List<WorkSiteAssignmentDetails> = emptyList(),
)

interface AllWorksitesSummaryActions{
    fun populateView()
}

class AllWorksitesSummaryViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow(AllWorksitesSummaryState())

    val state = _state.asStateFlow()

    val actions = object : AllWorksitesSummaryActions {
        override fun populateView() {
            viewModelScope.launch {
                repository.job.getFlowAllWorksitesAssignmentDetails().collect{ worksites ->
                    _state.update {
                        it.copy(
                            started = true,
                            worksites = worksites.filter { it.workSite.endDate == null },
                            worksitesDone = worksites.filter { it.workSite.endDate != null }
                        )
                    }
                }
            }
        }
    }
}