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
    val job : JobFullDetails? = null,
    val price : BigDecimal = BigDecimal.ZERO,
    val materials : List<Pair<Material,Float>> = emptyList()
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
            viewModelScope.launch {
                repository.job.getFlowJobFullDetails(jobId).collect{ data ->

                    val combinedMaterials =
                        (data?.materialsFuture?.map { it.material to it.futureJobMaterial.quantity} ?: emptyList()) +
                        (data?.materialUsage?.map { it.material to it.materialUsage.quantity }  ?: emptyList())

                    val finalMaterials = combinedMaterials.groupBy {it.first.id}
                        .map { (_, pairs) -> pairs.first().first to pairs.sumOf { it.second.toBigDecimal() }.toFloat() }

                    _state.update {
                        it.copy(
                            job = data,
                            price = data?.revenue?.fold(BigDecimal.ZERO) { partialTot, item ->
                                partialTot.add(item?.amount?.toBigDecimal() ?: BigDecimal.ZERO)
                            } ?: BigDecimal.ZERO,
                            materials = finalMaterials
                        )
                    }
                }
            }
        }
    }
}