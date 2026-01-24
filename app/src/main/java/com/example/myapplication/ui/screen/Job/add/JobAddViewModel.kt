package com.example.myapplication.ui.screen.Job.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.Address
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class JobAddState(
    val addresses : List<Address>
)

interface JobAddActions{
    //fun getAddresses()
}

class JobAddViewModel(
    repository : Repository
) : ViewModel(){

    val state = repository.addresses.map{ JobAddState(it) }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = JobAddState(addresses = emptyList())
    )

    val action = object : JobAddActions {

    }
}