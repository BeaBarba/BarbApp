package com.example.myapplication.ui.screen.Bubble.allSummary

import androidx.lifecycle.ViewModel
import com.example.myapplication.debug.Provenienze
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AllBubblesSummaryState(
    val bubbles: List<Provenienze> = listOf(),
    val started: Boolean = false
)

interface AllBubblesSummaryActions {
    fun populate()
}

class AllBubblesSummaryViewModel : ViewModel() {
    private val _state = MutableStateFlow(AllBubblesSummaryState())

    val state = _state.asStateFlow()

    val actions = object : AllBubblesSummaryActions {
        override fun populate() {
            if (!state.value.started) {
                simulateData()
                _state.update { it.copy(started = true) }
            }
        }
    }

    private fun simulateData(): Unit {
        _state.update { it.copy(bubbles = state.value.bubbles + Provenienze(fornitore = "Acciai Italia", quantita = "2000 kg", numeroBolla = "BO-2024-001", fattura = null, data = "01/03/2024")) }
        _state.update { it.copy(bubbles = state.value.bubbles + Provenienze(fornitore = "Elettronica 2000", quantita = "15 unità", numeroBolla = null, fattura = "FA-445566", data = "12/04/2024")) }
        _state.update { it.copy(bubbles = state.value.bubbles + Provenienze(fornitore = "Packaging Sud", quantita = "500 scatole", numeroBolla = "B-889", fattura = "F-990", data = "05/02/2024")) }
        _state.update { it.copy(bubbles = state.value.bubbles + Provenienze(fornitore = "Mario Rossi S.r.l.", quantita = "150 kg", numeroBolla = "BO-2023-001", fattura = null, data = "20/11/2023")) }
        _state.update { it.copy(bubbles = state.value.bubbles + Provenienze(fornitore = "Logistica Nord", quantita = "12 bancali", numeroBolla = "BL-9928", fattura = null, data = "15/06/2024")) }
        _state.update { it.copy(bubbles = state.value.bubbles + Provenienze(fornitore = "Ferramenta Veneta", quantita = "500 viti", numeroBolla = "BOL-12/A", fattura = null, data = "02/01/2024")) }
        _state.update { it.copy(bubbles = state.value.bubbles + Provenienze(fornitore = "Tech Solution SPA", quantita = "5 tablet", numeroBolla = null, fattura = "FT-2024-05", data = "10/05/2024")) }
        _state.update { it.copy(bubbles = state.value.bubbles + Provenienze(fornitore = "Energia Pura", quantita = "1200 kWh", numeroBolla = null, fattura = "FAT-88921", data = "30/09/2024")) }
        _state.update { it.copy(bubbles = state.value.bubbles + Provenienze(fornitore = "Consulenze IT", quantita = "40 ore", numeroBolla = null, fattura = "2023/450", data = "18/12/2023")) }
        _state.update { it.copy(bubbles = state.value.bubbles + Provenienze(fornitore = "Agricola Bio", quantita = "20 litri", numeroBolla = "B-772", fattura = "F-2024-B", data = "07/03/2024")) }
        _state.update { it.copy(bubbles = state.value.bubbles + Provenienze(fornitore = "Edilizia Moderna", quantita = "5 mc sabbia", numeroBolla = "BO-551", fattura = "FA-112", data = "22/08/2024")) }
        _state.update { it.copy(bubbles = state.value.bubbles + Provenienze(fornitore = "Packaging Italia", quantita = "1000 scatole", numeroBolla = "BL-009", fattura = "FT-990", data = "11/07/2024")) }
        _state.update { it.copy(bubbles = state.value.bubbles + Provenienze(fornitore = "Global Export Ltd", quantita = "2 container", numeroBolla = "EXP-7712", fattura = "INV-2024-X1", data = "29/05/2024")) }
    }
}