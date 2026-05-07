package com.example.myapplication.ui.screen.Statistics.materialPriceHistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.Seller
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Item(
    val seller : String,
    val date : LocalDate,
    val price : Float
)

data class MaterialPriceHistoryState(
    val labels : List<String> = emptyList(),
    val timePriceMap: Map<String, Pair<List<Float>, List<Float>>> = emptyMap(),
    val xLabel : Map<Float,String> = emptyMap(),
    val material : String = "",
    val materialId : Int = 0,
)

interface MaterialPriceHistoryActions{
    fun populateView(id : Int)
}

class MaterialPriceHistoryViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow(MaterialPriceHistoryState())

    val state = _state.asStateFlow()

    val actions = object : MaterialPriceHistoryActions {

        override fun populateView(id: Int) {
            viewModelScope.launch {
                val materialHistory = repository.materialPriceHistory(id)

                val materialName = "${materialHistory.first().category} - ${materialHistory.first().model}"

                val timePriceMap = materialHistory
                    .groupBy { it.sellerName }
                    .mapValues { (_, records) ->
                        val dates = records.map { it.date.toEpochDay().toFloat()/*.toEpochDay().toFloat()*/}
                        val prices = records.map { it.price }
                        dates to prices
                    }

                val sellers = timePriceMap.keys.toList()

                val xLabelsMap = materialHistory.associate{ record ->
                    val key = record.date.toEpochDay().toFloat()
                    val label = record.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    key to label
                }

                _state.update {
                    it.copy(
                        material = materialName,
                        materialId = id,
                        labels = sellers,
                        timePriceMap = timePriceMap,
                        xLabel = xLabelsMap
                    )
                }
            }
        }
    }

    private fun yearMonthKey(date: LocalDate) = date.year * 100 + date.monthValue
}