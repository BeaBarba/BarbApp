package com.example.myapplication.ui.screen.Customer.singleSummary

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.modules.CustomerType
import com.example.myapplication.debug.Cliente
import com.example.myapplication.debug.listaClienti
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class SingleCustomerSummaryState(
    val started : Boolean = false,
    val customerData : Cliente = Cliente(
        tipo = CustomerType.Privato.toString(),
        nome = "",
        CF = "",
        cognome = "",
        email = "",
        telefono = "",
        indirizzo = "",
        citta = "",
        comune = "",
        provincia = "",
        cap = "",
        riferimento = "",
        dataNascita = "",
        luogoNascita = "",
        codiceUnivoco = "",
        ragioneSociale = "",
        partitaIVA = ""
    )
)

interface SingleCustomerSummaryActions {
    fun populateCustomerData(customerId: String)
}

class SingleCustomerSummaryViewModel() : ViewModel()  {
    private val _state = MutableStateFlow(SingleCustomerSummaryState())

    val state = _state.asStateFlow()

    val actions = object : SingleCustomerSummaryActions {
        override fun populateCustomerData(customerId: String) {
            if (!state.value.started) {
                _state.update { it.copy(
                    started = true,
                    customerData = listaClienti.get(2)
                )  }
            }
        }
    }
}