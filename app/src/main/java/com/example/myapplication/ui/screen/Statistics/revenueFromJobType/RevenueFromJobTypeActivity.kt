package com.example.myapplication.ui.screen.Statistics.revenueFromJobType

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.ColumnCartesianChart
import com.example.myapplication.ui.component.TopAppBar
import com.patrykandpatrick.vico.compose.common.component.rememberLineComponent
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.columnSeries
import com.patrykandpatrick.vico.core.common.Fill

@Composable
fun RevenueFromJobActivity(
    state: RevenueFromJobState,
    navController : NavHostController
){
    val columnColors = listOf(
            MaterialTheme.colorScheme.secondary,    // ALA
            MaterialTheme.colorScheme.tertiary,     // ELE
            MaterialTheme.colorScheme.surface       // CDZ
    )

    val columnComponents = columnColors.map { color ->
        rememberLineComponent(
            fill = Fill(color.toArgb()),
            thickness = 30.dp
        )
    }

    val columnModelProducer = remember { CartesianChartModelProducer() }

    LaunchedEffect(state.started, state.values) {
        if(state.values.isNotEmpty()) {
            columnModelProducer.runTransaction {
                columnSeries {
                    state.values.forEachIndexed { index, pair ->
                        series( x = listOf(index),y = listOf(pair.second))
                    }
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                id = stringResource(R.string.statistics),
                navigationIcon = { BackButton{ navController.navigate(NavigationRoute.AllStatistics) } }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(
                    top = contentPadding.calculateTopPadding() + 8.dp,
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.started) {
                ColumnCartesianChart(
                    columnComponents = columnComponents,
                    labels = state.values.map { it.first },
                    modelProducer = columnModelProducer,
                    titleAxisX = stringResource(R.string.job_type),
                    titleAxisY = stringResource(R.string.revenue)
                )
            }
        }
    }
}