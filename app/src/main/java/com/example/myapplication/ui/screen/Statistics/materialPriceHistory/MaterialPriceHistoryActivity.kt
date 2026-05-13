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
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.LineCartesianChar
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.colors
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries

@Composable
fun MaterialPriceHistoryActivity(
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