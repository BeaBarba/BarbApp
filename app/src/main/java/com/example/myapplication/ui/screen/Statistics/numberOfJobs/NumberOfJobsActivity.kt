package com.example.myapplication.ui.screen.Statistics.numberOfJobs

import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.ColumnCartesianChart
import com.example.myapplication.ui.component.CustomLegend
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.colors
import com.patrykandpatrick.vico.compose.common.component.rememberLineComponent
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.columnSeries
import com.patrykandpatrick.vico.core.common.Fill

@Composable
fun NumberOfJobsActivity(
    state : NumberOfJobsState,
    navController : NavHostController
){
    val columnComponents = colors.map { color ->
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
                    state.values.forEachIndexed { index, item ->
                        series( x = listOf(index),y = listOf(item.numberJobs))
                    }
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = { BackButton{ navController.navigate(NavigationRoute.AllStatistics) } },
                id = stringResource(R.string.intervention_number)
            )
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(
                    top = contentPadding.calculateTopPadding() + 8.dp,
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
                .fillMaxSize()
        ) {
            item {
                if (state.started) {
                    ColumnCartesianChart(
                        columnComponents = columnComponents,
                        labels = state.values.indices.map { it.toString() },
                        modelProducer = columnModelProducer,
                        titleAxisY = stringResource(R.string.intervention_number)
                    )
                }
            }
            item {
                if (state.started && state.values.isNotEmpty()) {
                    CustomLegend(
                        labels = state.values.map { it.reference },
                        colors = colors
                    )
                }
            }
        }
    }
}