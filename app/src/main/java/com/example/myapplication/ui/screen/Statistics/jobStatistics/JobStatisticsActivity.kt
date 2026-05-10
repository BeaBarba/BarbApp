package com.example.myapplication.ui.screen.Statistics.jobStatistics

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomDateRangePicker
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.PieChartComposable
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.convertDateToLong

@Composable
fun JobStatisticsActivity(
    state : JobStatisticsState,
    actions: JobStatisticsActions,
    navController : NavHostController
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigate(NavigationRoute.AllStatistics)}},
                id = stringResource(R.string.intervention_summary)
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
                .fillMaxSize()
        ) {
            CustomDateRangePicker(
                selectedStartDate = state.startDate?.let{
                    convertDateToLong(state.startDate)},
                selectedEndDate = state.endDate?.let{
                    convertDateToLong(state.endDate)},
                onRangeSelected = { startDate,endDate ->
                    actions.setRange(startDate = startDate, endDate = endDate)
                }
            )
            if(state.showChart){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    PieChartComposable(
                        labels = state.values.map { it.first },
                        values = state.values.map { it.second.toFloat() },
                        colors = listOf(
                            MaterialTheme.colorScheme.secondary, // ALA
                            MaterialTheme.colorScheme.tertiary, // ELE
                            MaterialTheme.colorScheme.surface // CDZ
                        ),
                    )
                }
            }
        }
    }
}