package com.example.myapplication.ui.screen.Job.singleSummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.JobFullDetails
import com.example.myapplication.data.database.Material
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal

data class SingleJobSummaryState(
    val started : Boolean = false,
    val job : JobFullDetails? = null,
    val price : Float = 0.0f,
    val materials : List<Material> = emptyList()
)

interface SingleJobSummaryActions{
    fun populateJobData(jobId : Int)
}

class SingleJobSummaryViewModel(
    repository: Repository
) : ViewModel(){

    private val _state = MutableStateFlow(SingleJobSummaryState())

    val state = _state.asStateFlow()

    val actions = object : SingleJobSummaryActions{

        override fun populateJobData(jobId: Int) {
            if(state.value.started) return

            viewModelScope.launch {
                repository.job.getJobFullDetails(jobId).collect{ data ->
                    val materials = data?.materialsFuture?.map { it.material }?.plus(
                        data.materialUsage.map { it.material } )

                    _state.update {
                        it.copy(
                            started = true,
                            job = data,
                            price = data?.revenue?.fold(BigDecimal.ZERO) { acc, item ->
                                acc.add(item?.amount?.toBigDecimal() ?: BigDecimal.ZERO)
                            }?.toFloat() ?: BigDecimal.ZERO.toFloat(),
                            materials = materials ?: emptyList()
                        )
                    }
                }
            }
        }
    }
}