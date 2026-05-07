package com.example.myapplication.ui.screen.Statistics.materialPriceHistory

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.LineCartesianChar
import com.example.myapplication.ui.component.TopAppBar
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries

@Composable
fun MaterialPriceHistory(
    materialId : Int,
    state : MaterialPriceHistoryState,
    actions: MaterialPriceHistoryActions,
    navController : NavHostController
){
    LaunchedEffect(materialId) {
        actions.populateView(materialId)
    }
    val isDark = isSystemInDarkTheme()
    val lineModelProducer = remember { CartesianChartModelProducer() }
    val colors = listOf(
        Color(0xFFD32F2F),
        Color(0xFFFBC02D),
        Color(0xFF00BCD4),
        Color(0xFFFF5722),
        Color(0xFF4CAF50),
        Color(0xFF9C27B0),
        Color(0xFFFFEB3B),
        Color(0xFF3F51B5),
        Color(0xFFE91E63),
        Color(0xFF00E676),
        Color(0xFFFF9800),
        Color(0xFF607D8B),
        Color(0xFFCDDC39),
        Color(0xFF2196F3),
        Color(0xFFFF4081),
        Color(0xFF8BC34A),
        Color(0xFFFFC107),
        Color(0xFF673AB7),
        Color(0xFF009688),
        Color(0xFFB71C1C),
        Color(0xFF00B0FF),
        Color(0xFF4E342E),
    )

    LaunchedEffect(state.timePriceMap,  isDark) {
        val hasTimes = state.timePriceMap.isEmpty()

        if (hasTimes) {
            return@LaunchedEffect
        }else {
            lineModelProducer.runTransaction {
                lineSeries {
                    state.timePriceMap.values.forEach { (dates, prices) ->
                        series(x = dates, y = prices)
                    }
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = { BackButton { navController.navigate(NavigationRoute.AllMaterials) } },
                id = state.material
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                    top = contentPadding.calculateTopPadding() + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {
            LineCartesianChar(
                colors = colors,
                labels = state.labels,
                lineModelProducer = lineModelProducer,
                xLabels = state.xLabel
            )
        }
    }
}