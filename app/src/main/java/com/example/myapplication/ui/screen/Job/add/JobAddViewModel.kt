package com.example.myapplication.ui.screen.Job.add

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class JobAddState(
    val addressId : Int = 0,
    val customerId : String = ""
)

interface JobAddActions{
    fun handleCustomerSelection(ids : List<String>)
}

class JobAddViewModel(
    repository : Repository
) : ViewModel(){

    private val _state = MutableStateFlow(JobAddState())

    val state = _state.asStateFlow()

    val action = object : JobAddActions {

        override fun handleCustomerSelection(ids: List<String>) {
            _state.update { it.copy(customerId = ids.first()) }
        }
    }
}