package com.example.myapplication.ui.screen.WorkSite.singleSummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.Address
import com.example.myapplication.data.database.CustomerTypeDetails
import com.example.myapplication.data.database.Job
import com.example.myapplication.data.database.Reference
import com.example.myapplication.data.database.WorkSite
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SingleWorksiteSummaryState(
    val worksiteId : Int = 0,
    val customer : CustomerTypeDetails? = null,
    val address : Address? = null,
    val worksite : WorkSite? = null,
    val manager : Reference? = null,
    val jobs : List<Job> = emptyList()
)

interface SingleWorksiteSummaryActions{
    fun populateView(id : Int)
    fun getCustomerName() : String
    fun getAddressText() : String
    fun getJobType(job : Job) : JobType
}

class SingleWorksiteSummaryViewModel(
    private val repository : Repository
) : ViewModel() {

    private val _state = MutableStateFlow(SingleWorksiteSummaryState())

    val state = _state.asStateFlow()

    val actions = object : SingleWorksiteSummaryActions{

        override fun populateView(id: Int) {
            viewModelScope.launch {
                repository.job.getFlowWorkSiteFullDetailsById(id).collect{ worksiteDetails ->
                    worksiteDetails?.let {
                        _state.update {
                            it.copy(
                                worksiteId = worksiteDetails.workSiteAssignment.workSite.id,
                                customer = worksiteDetails.workSiteAssignment.customer,
                                address = worksiteDetails.workSiteAssignment.address,
                                worksite = worksiteDetails.workSiteAssignment.workSite,
                                manager = worksiteDetails.workSiteAssignment.manager,
                                jobs = worksiteDetails.jobs
                            )
                        }
                    }
                }
            }
        }

        override fun getCustomerName() : String {
            val customer = state.value.customer ?: return ""
            return when{
                customer.isCompany -> {"${customer.companyCustomer?.companyName}"}
                customer.isPrivate -> {"${customer.privateCustomer?.lastName} ${customer.customer.name}"}
                else -> {"? "}
            }
        }

        override fun getAddressText(): String {
            val address = state.value.address ?: return ""
            return "${address.address} ${address.houseNumber}, ${address.city} (${address.province})"
        }

        override fun getJobType(job: Job): JobType {
            return when{
                job.electric -> {JobType.ELE}
                job.alarm -> {JobType.ALA}
                job.airConditioning -> {JobType.CDZ}
                else -> {JobType.NONE}
            }
        }
    }
}