package com.example.myapplication.ui.screen.Bubble.allSummary

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.modules.FilterKey
import com.example.myapplication.debug.Provenienze
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate

data class AllBubblesSummaryState(
    val bubbles: List<Provenienze> = listOf(),
    val started: Boolean = false,
    val filterKey: FilterKey = FilterKey.ASC_DATE
)

interface AllBubblesSummaryActions {
    fun populate()
    fun sellerSort()
    fun dateSort()
    fun ascendingSort()
    fun descendingSort()
}

class AllBubblesSummaryViewModel : ViewModel() {
    private val _state = MutableStateFlow(AllBubblesSummaryState())

    val state = _state.asStateFlow()

    val actions = object : AllBubblesSummaryActions {
        override fun populate() {
            if (!state.value.started) {
                simulateData()
                _state.update { it.copy(started = true) }
                dateSort()
            }
        }
        override fun sellerSort() {
            _state.update { it.copy(bubbles = state.value.bubbles.sortedBy { value -> value.fornitore }) }
            _state.update { it.copy(filterKey = FilterKey.ASC_SELLER) }
        }

        override fun dateSort() {
            _state.update { it.copy(bubbles = state.value.bubbles.sortedBy { value -> value.data }) }
            _state.update { it.copy(filterKey = FilterKey.ASC_DATE) }
        }

        override fun ascendingSort() {
            if (state.value.filterKey == FilterKey.DESC_SELLER || state.value.filterKey == FilterKey.DESC_DATE)
                _state.update { it.copy(bubbles = state.value.bubbles.reversed()) }
            _state.update { it.copy(filterKey = if (state.value.filterKey == FilterKey.DESC_DATE) FilterKey.ASC_DATE else FilterKey.ASC_SELLER) }
        }

        override fun descendingSort() {
            if (state.value.filterKey == FilterKey.ASC_SELLER || state.value.filterKey == FilterKey.ASC_DATE)
                _state.update { it.copy(bubbles = state.value.bubbles.reversed()) }
            _state.update { it.copy(filterKey = if (state.value.filterKey == FilterKey.ASC_DATE) FilterKey.DESC_DATE else FilterKey.DESC_SELLER) }
        }
    }


    private fun simulateData(): Unit {
        _state.update { it.copy(bubbles = state.value.bubbles + Provenienze(fornitore = "Acciai Italia", quantita = "2000 kg", numeroBolla = "BO-2024-001", fattura = null, data = LocalDate.of(2024, 3, 1))) }
        _state.update { it.copy(bubbles = state.value.bubbles + Provenienze(fornitore = "Elettronica 2000", quantita = "15 unità", numeroBolla = null, fattura = "FA-445566", data = LocalDate.of(2024, 4, 12))) }
        _state.update { it.copy(bubbles = state.value.bubbles + Provenienze(fornitore = "Packaging Sud", quantita = "500 scatole", numeroBolla = "B-889", fattura = "F-990", data = LocalDate.of(2024, 2, 5))) }
        _state.update { it.copy(bubbles = state.value.bubbles + Provenienze(fornitore = "Mario Rossi S.r.l.", quantita = "150 kg", numeroBolla = "BO-2023-001", fattura = null, data = LocalDate.of(2023, 11, 20))) }
        _state.update { it.copy(bubbles = state.value.bubbles + Provenienze(fornitore = "Logistica Nord", quantita = "12 bancali", numeroBolla = "BL-9928", fattura = null, data = LocalDate.of(2024, 6, 15))) }
        _state.update { it.copy(bubbles = state.value.bubbles + Provenienze(fornitore = "Ferramenta Veneta", quantita = "500 viti", numeroBolla = "BOL-12/A", fattura = null, data = LocalDate.of(2024, 1, 2))) }
        _state.update { it.copy(bubbles = state.value.bubbles + Provenienze(fornitore = "Tech Solution SPA", quantita = "5 tablet", numeroBolla = null, fattura = "FT-2024-05", data = LocalDate.of(2024, 5, 10))) }
        _state.update { it.copy(bubbles = state.value.bubbles + Provenienze(fornitore = "Energia Pura", quantita = "1200 kWh", numeroBolla = null, fattura = "FAT-88921", data = LocalDate.of(2024, 9, 30))) }
        _state.update { it.copy(bubbles = state.value.bubbles + Provenienze(fornitore = "Consulenze IT", quantita = "40 ore", numeroBolla = null, fattura = "2023/450", data = LocalDate.of(2023, 12, 18))) }
        _state.update { it.copy(bubbles = state.value.bubbles + Provenienze(fornitore = "Agricola Bio", quantita = "20 litri", numeroBolla = "B-772", fattura = "F-2024-B", data = LocalDate.of(2024, 3, 7))) }
        _state.update { it.copy(bubbles = state.value.bubbles + Provenienze(fornitore = "Edilizia Moderna", quantita = "5 mc sabbia", numeroBolla = "BO-551", fattura = "FA-112", data = LocalDate.of(2024, 8, 22))) }
        _state.update { it.copy(bubbles = state.value.bubbles + Provenienze(fornitore = "Packaging Italia", quantita = "1000 scatole", numeroBolla = "BL-009", fattura = "FT-990", data = LocalDate.of(2024, 7, 11))) }
        _state.update { it.copy(bubbles = state.value.bubbles + Provenienze(fornitore = "Global Export Ltd", quantita = "2 container", numeroBolla = "EXP-7712", fattura = "INV-2024-X1", data = LocalDate.of(2024, 5, 29))) }
    }
}