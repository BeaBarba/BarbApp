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

data class Series(
    val seller : Seller,
    val date : LocalDate,
    val price : Float
)

data class MaterialPriceHistoryState(
    val labels : List<String> = emptyList(),
    val times : List<Int> = emptyList(),
    val series : List<List<Float>> = emptyList(),
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
            /*viewModelScope.launch {
                val materialHistory = repository.materialPriceHistory(id)
                val material = materialHistory?.first()
                val materialName = "${material?.category} - ${material?.model}"

                var labels : List<String>
                var times : List<Int>
                var series : List<Float>
                materialHistory.forEach {

                }
                _state.update {
                    it.copy(
                        material = materialName,
                        materialId = id,

                    )
                }
                println("DEBUG: VM - $materialHistory")
            }*/

            viewModelScope.launch {
                repository.inventory.getFlowMaterialFullDetailsById(id).collect { material ->
                    val bubbles = material?.deliveriesWithBubbles?.map {
                        Series(
                            date = it.bubble.bubble.date,
                            seller = it.bubble.seller,
                            price = it.delivery.unitPrice
                        )
                    } ?: emptyList()
                    println("DEBUG: VM - bubbles = $bubbles")

                    val purchaseInvoice = material?.purchaseWithPurchaseInvoice?.map {
                        Series(
                            date = it.purchaseInvoice.purchaseInvoice.year,
                            seller = it.purchaseInvoice.seller,
                            price = it.purchase.unitPrice
                        )
                    } ?: emptyList()
                    println("DEBUG: VM - purchase = $purchaseInvoice")

                    val list = bubbles + purchaseInvoice
                    println("DEBUG: VM - list = $list")

                    val times : List<Int> = list
                            .map { yearMonthKey(it.date) }
                            .distinct()
                            .sorted()
                    println("DEBUG: VM - times = $times")

                    val sellers : List<Seller> = list
                        .map { it.seller}
                        .distinctBy { it.id }
                        .sortedBy { it.name }
                        ?: emptyList()
                    println("DEBUG: VM - sellers = $sellers")

                    val prices : Map<Int, Map<Int, Float>> = sellers.associate { seller ->
                        val priceByTime : Map<Int, Float> = list.filter { it.seller.id == seller.id }
                            .groupBy { yearMonthKey(it.date) }
                            .mapValues { entry -> entry.value.last().price }
                        seller.id to priceByTime
                    }
                    println("DEBUG: VM - prices = $prices")

                    val seriesList : List<List<Float>> = sellers.map { seller ->
                        times.map { time ->
                            val price = prices[seller.id]?.get(time) ?: 0f
                            price
                        }
                    }
                    println("DEBUG: VM - series = $seriesList")

                    val labelNames = sellers.map { it.name }
                    println("DEBUG: VM - labels = $labelNames")

                    _state.update {
                        it.copy(
                            labels = labelNames,
                            times = times,
                            series = seriesList,
                            material = "${material?.material?.material?.category} - ${material?.material?.material?.model} (${material?.material?.material?.brand})",
                            materialId = id,
                        )
                    }
                }
            }
        }
    }

    private fun yearMonthKey(date: LocalDate) = date.year * 100 + date.monthValue
}