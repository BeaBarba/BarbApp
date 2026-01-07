package com.example.myapplication.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.StatisticCard
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun AllStatisticsActivity(
    navController : NavHostController
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                id = stringResource(R.string.statistics),
                navigationIcon = {BackButton{navController.navigate(NavigationRoute.Home)}}
            )
        }
    ){ contentPadding ->
        Column (
            modifier = Modifier
                .padding(
                    top = contentPadding.calculateTopPadding() + 8.dp,
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
                .fillMaxSize(),
        ) {
            StatisticCard(
                text = stringResource(R.string.material_price_history),
                icon = painterResource(R.drawable.line_chart),
                iconDescription = stringResource(R.string.material_price_history),
                onClick = {/*navController.navigate(NavigationRoute.)*/}
            )
            Spacer(Modifier.size(15.dp))
            StatisticCard(
                text = stringResource(R.string.intervention_summary),
                icon = painterResource(R.drawable.pie_chart),
                iconDescription = stringResource(R.string.intervention_summary),
                onClick = {navController.navigate(NavigationRoute.JobStatistics)}
            )
            Spacer(Modifier.size(15.dp))
            StatisticCard(
                text = stringResource(R.string.intervention_revenue),
                icon = painterResource(R.drawable.diagram_24dp),
                iconDescription = stringResource(R.string.intervention_revenue),
                onClick = {/*navController.navigate(NavigationRoute.)*/}
            )
            Spacer(Modifier.size(15.dp))
            StatisticCard(
                text = stringResource(R.string.average_times),
                icon = painterResource(R.drawable.bar_chart_24dp),
                iconDescription = stringResource(R.string.average_times),
                description = stringResource(R.string.customers),
                onClick = {navController.navigate(NavigationRoute.AveragePaymentsTimesStatistics)}
            )
            Spacer(Modifier.size(15.dp))
            StatisticCard(
                text = stringResource(R.string.intervention_number),
                icon = painterResource(R.drawable.diagram_24dp),
                iconDescription = stringResource(R.string.intervention_number),
                description = stringResource(R.string.reference),
                onClick = {/*navController.navigate(NavigationRoute.)*/}
            )
            Spacer(Modifier.size(15.dp))
        }
    }
}