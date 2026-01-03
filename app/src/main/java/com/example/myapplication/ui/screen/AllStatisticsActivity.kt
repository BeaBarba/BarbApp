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
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.NavigationRoute
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
                id = "Statistiche",
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
                text = "Storico Prezzi Materiali",
                icon = painterResource(R.drawable.line_chart),
                iconDescription = "Price Statistics",
                onClick = {/*navController.navigate(NavigationRoute.)*/}
            )
            Spacer(Modifier.size(15.dp))
            StatisticCard(
                text = "Sommario Interventi",
                icon = painterResource(R.drawable.pie_chart),
                iconDescription = "Jobs Statistics",
                onClick = {navController.navigate(NavigationRoute.JobStatistics)}
            )
            Spacer(Modifier.size(15.dp))
            StatisticCard(
                text = "Ricavo Interventi",
                icon = painterResource(R.drawable.diagram_24dp),
                iconDescription = "Jobs Revenue",
                onClick = {/*navController.navigate(NavigationRoute.)*/}
            )
            Spacer(Modifier.size(15.dp))
            StatisticCard(
                text = "Tempi Medi Di Riscossione",
                icon = painterResource(R.drawable.bar_chart_24dp),
                iconDescription = "Average Collection Times",
                description = " Clienti",
                onClick = {navController.navigate(NavigationRoute.AveragePaymentsTimesStatistics)}
            )
            Spacer(Modifier.size(15.dp))
            StatisticCard(
                text = "Numero Interventi",
                icon = painterResource(R.drawable.diagram_24dp),
                iconDescription = "Number Of Jobs Per Reference",
                description = "Riferimento",
                onClick = {/*navController.navigate(NavigationRoute.)*/}
            )
        }
    }
}